package com.st.demo;

import fmath.conversion.ConvertFromLatexToMathML;
import fmath.conversion.ConvertFromMathMLToLatex;

/**
 * @Author: tlfu
 * @Date: 2020-06-18 20:11
 */


public class LatexAndMathML {

  public static void main(String[] args) {
    String mathMl="<math>  \n" +
        "      <mrow>  \n" +
        "        <msup><mi>a</mi><mn>2</mn></msup>  \n" +
        "        <mo>+</mo>  \n" +
        "        <msup><mi>b</mi><mn>2</mn></msup>  \n" +
        "        <mo>=</mo>  \n" +
        "        <msup><mi>c</mi><mn>2</mn></msup>  \n" +
        "      </mrow>  \n" +
        "    </math>  ";
    String latex = ConvertFromMathMLToLatex.convertToLatex(mathMl);
    System.out.println(latex);
    mathMl = ConvertFromLatexToMathML.convertToMathML(latex);
    System.out.println(mathMl);
  }
}
