package com.swing.sky.common.utils.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html工具类
 *
 * @author swing
 */
public class HtmlUtils {
    /**
     * 过滤字符串中所有的html标签
     *
     * @param string 目标字符串
     * @return 过滤后的结果
     */
    public static String tagsFilter(String string) {
        String htmlStr = string.replaceAll("\"", "'");
        String regScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regHtml = "<[^>]+>";

        Pattern pScript = Pattern.compile(regScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(htmlStr);
        htmlStr = mScript.replaceAll("");

        Pattern pStyle = Pattern.compile(regStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(htmlStr);
        htmlStr = mStyle.replaceAll("");

        Pattern pHtml = Pattern.compile(regHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(htmlStr);
        return mHtml.replaceAll("");
    }
}
