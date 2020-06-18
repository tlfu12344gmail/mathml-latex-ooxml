package com.st.demo;

import fmath.conversion.ConvertFromWordToMathML;

/**
 * created by tlfu on 2018/6/16
 */
public class Word2Mathml {
    public static void main(String[] args) {
        String path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/mathml.docx";
        System.out.println(path);
        System.out.println(ConvertFromWordToMathML.getMathMLFromDocFile(path));
    }
}
