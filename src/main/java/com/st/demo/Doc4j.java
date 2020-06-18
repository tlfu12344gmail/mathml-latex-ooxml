package com.st.demo;

import fmath.conversion.c.a;
import org.docx4j.Docx4J;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;

import javax.xml.bind.JAXBElement;
import java.io.File;

/**
 * created by tlfu on 2018/6/17
 */
public class Doc4j {

    public static void main(String[] args) throws Exception{
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
        String filename = System.getProperty("user.dir") + "/test.docx";
        addImage(wordMLPackage);
        addParagraph(wordMLPackage);
        addParagraphWithStyle(wordMLPackage);
        addFormula(wordMLPackage);
        addHtml(wordMLPackage);
        Docx4J.save(wordMLPackage, new java.io.File(filename), Docx4J.FLAG_SAVE_ZIP_FILE);
        System.out.println(filename);
    }

    private static void addParagraphWithStyle(WordprocessingMLPackage wordMLPackage){
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
        mdp.addStyledParagraphOfText("Heading1","heading1");

    }

    private static void addParagraph(WordprocessingMLPackage wordMLPackage){
        MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
        mdp.addParagraphOfText("ParagraphOfText");

    }

    private static void addImage(WordprocessingMLPackage wordMLPackage) throws Exception{
        // The image to add
        File file = new File(System.getProperty("user.dir")
                + "/src/main/resources/images/t01.jpg" );

        // Our utility method wants that as a byte array
        java.io.InputStream is = new java.io.FileInputStream(file );
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            System.out.println("Could not completely read file "+file.getName());
        }
        is.close();

        String filenameHint = null;
        String altText = null;
        int id1 = 0;
        int id2 = 1;
        org.docx4j.wml.P p = newImage( wordMLPackage, bytes,
                filenameHint, altText,
                id1, id2 );
        wordMLPackage.getMainDocumentPart().addObject(p);
    }

    private static void addFormula(WordprocessingMLPackage wordMLPackage) throws Exception{
        P p = new P();
        wordMLPackage.getMainDocumentPart().getContent().add(p);
        String mathMl="<math>  \n" +
                "      <mrow>  \n" +
                "        <msup><mi>a</mi><mn>2</mn></msup>  \n" +
                "        <mo>+</mo>  \n" +
                "        <msup><mi>b</mi><mn>2</mn></msup>  \n" +
                "        <mo>=</mo>  \n" +
                "        <msup><mi>c</mi><mn>2</mn></msup>  \n" +
                "      </mrow>  \n" +
                "    </math>  ";
        String openXML=a.a(mathMl);
        String header="<m:oMathPara xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\">";
        openXML=header+openXML+"</m:oMathPara>";
        javax.xml.bind.JAXBElement omathpara = (JAXBElement) XmlUtils.unmarshalString(openXML);
        p.getContent().add(omathpara);

    }
    private static void addHtml(WordprocessingMLPackage wordMLPackage) throws Exception{
        String html = "<html><body><div style='font:20px'>Hello World!</div><div><table border='2'><tr><td>1</td><td>1</td><td>1</td></tr><tr><td>1</td><td>1</td><td>1</td></tr><tr><td>1</td><td>1</td><td>1</td></tr></table></div><div><img src=\"http://www.storm-spirit.cn/images/p02.jpg" +

                "\" /></div></body></html>";

        wordMLPackage.getMainDocumentPart().addAltChunk(AltChunkType.Html, html.getBytes());
    }
    private static org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
                                             byte[] bytes,
                                             String filenameHint, String altText,
                                             int id1, int id2) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline( filenameHint, altText,
                id1, id2, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        org.docx4j.wml.P  p = factory.createP();
        org.docx4j.wml.R  run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

}
