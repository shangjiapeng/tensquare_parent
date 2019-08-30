package com.tensquare.common.test;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * <p></p>
 * IKSegmenter的第一个构造参数为StringReader类型。 StringReader是装饰Reader的 类，其用法是读取一个String字符串
 * IKSegmenter 的第二个构造参数userSmart 为切分粒度 true表示最大切分 false表示最 细切分
 * Lexeme: 词单位类
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 14:57
 */
public class IkTest {

    public static void main(String[] args) throws IOException {

        String text ="IK是基于JAVA语言开发的轻量级的中文分词工具包";
        StringReader stringReader=new StringReader(text);
        IKSegmenter ikSegmenter = new IKSegmenter(stringReader, true);
        Lexeme lexeme =null;
        while ((lexeme=ikSegmenter.next())!=null){
            System.out.println(lexeme.getLexemeText()+" ");
        }

    }
}
