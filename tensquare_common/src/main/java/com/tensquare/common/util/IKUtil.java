package com.tensquare.common.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * <p>分词工具类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 15:14
 */
public class IKUtil {

    /**
     *
     * @param content 待分析的字符串
     * @param splitChar 分出来的词汇 用splitChar分开
     * @return
     * @throws IOException
     */
    public static String split(String content ,String splitChar) throws IOException{

        StringReader stringReader=new StringReader(content);
        IKSegmenter ikSegmenter = new IKSegmenter(stringReader, true);
        Lexeme lex =null;
        StringBuilder stringBuilder = new StringBuilder("");
        while ((lex=ikSegmenter.next())!=null){
            stringBuilder.append(lex.getLexemeText()+splitChar); //拼接字符串
        }
        return stringBuilder.toString();
    }
}
