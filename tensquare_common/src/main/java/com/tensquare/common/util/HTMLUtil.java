package com.tensquare.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>html标签处理工具类</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-30 15:19
 */
public class HTMLUtil {

    /**
     * 将文本中 的html标签剔除
     *
     * @param htmlString
     * @return
     */
    public static String delHTMLTag(String htmlString) {

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern pattern_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher matcher_script = pattern_script.matcher(htmlString);
        matcher_script.replaceAll("");  //过滤script标签

        Pattern pattern_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher matcher_style = pattern_style.matcher(htmlString);
        matcher_style.replaceAll("");  //过滤style标签

        Pattern pattern_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher_html = pattern_html.matcher(htmlString);
        matcher_html.replaceAll("");  //过滤html标签

        return htmlString.trim(); //返回文本字符串
    }
}
