package com.st.demo;

import fmath.conversion.ConvertFromMathMLToWord;

/**
 * created by tlfu on 2018/6/16
 */
public class Mathml2Word {
    public static void main(String[] args) {
        String path=System.getProperty("user.dir")+"/mathml.docx";
        System.out.println(path);
        String mathMl="<math>  \n" +
                "      <mrow>  \n" +
                "        <msup><mi>a</mi><mn>2</mn></msup>  \n" +
                "        <mo>+</mo>  \n" +
                "        <msup><mi>b</mi><mn>2</mn></msup>  \n" +
                "        <mo>=</mo>  \n" +
                "        <msup><mi>c</mi><mn>2</mn></msup>  \n" +
                "      </mrow>  \n" +
                "    </math>  ";
        ConvertFromMathMLToWord.writeWordDocFromMathML(path,mathMl);
    }
}
