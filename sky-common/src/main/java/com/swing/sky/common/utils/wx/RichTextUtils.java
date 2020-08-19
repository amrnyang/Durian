package com.swing.sky.common.utils.wx;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信小程序富文本处理工具
 *
 * @author swing
 */
public class RichTextUtils {
    public static final String IMG_TAG = "img";

    public static void main(String[] args) {
        String string = "<p>加热炉采用电。     </p><p>在正常情况下，炉温等于某个期望值T°C，热电偶的输出电压fu正好等于给定电压ru。此时，0\uF03D\uF02D\uF03Dfreuuu，故01\uF03D\uF03Dauu，可逆电动机不转动，调压器的滑动触点停留在某个合适的位置上，使cu保持一定的数值。这时，炉子散失的热量正好等于从加热器吸取的热量，形成稳定的热平衡状态，温度保持恒定。 </p><p>当炉膛温度T°C由于某种原因突然下降(例如炉门打开造成的热量流失)，则出现以下的控制过程： </p><p>控制的结果是使炉膛温度回升，直至T°C的实际值等于期望值为止</p><p><img src=\"https://swing-durian.oss-cn-beijing.aliyuncs.com/81/53/8153f85149c4451691cc371c2797d6d5.png\" data-filename=\"image.png\" style=\"width: 1355.175px; height: 36.5613px;\"></p><p>系统中，加热炉是被控对象，炉温是被控量，给定量是由给定电位器设定的电压ru（表征炉温的希望值）</p><p><img src=\"https://swing-durian.oss-cn-beijing.aliyuncs.com/65/3e/653e930496e74b25b47c71e8b2363721.png\" data-filename=\"image.png\" style=\"width: 355.175px; height: 75.8249px;\"></p><p><br></p>";
        System.out.println(resizePicture(string));
    }

    /**
     * 调整内容中的图片大小
     *
     * @param fullContent 原内容
     * @return 调整后的内容
     */
    public static String resizePicture(String fullContent) {
        for (String imgTag : getHtmlTag(fullContent, IMG_TAG)) {
            fullContent = fullContent.replaceAll(imgTag, resizeWidth(imgTag));
        }
        return fullContent;
    }

    /**
     * 设置img的宽度不大350
     * 为适应手机屏幕，这里的宽度定为不大于350
     *
     * @param imgTag img标签
     * @return 设置后的标签
     */
    public static String resizeWidth(String imgTag) {
        if (imgTag == null) {
            return "";
        }
        //获取style属性
        String styleAttr = null;
        int width = 0;
        int height = 0;
        String regex1 = "style.*\"";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(imgTag);
        while (matcher1.find()) {
            styleAttr = matcher1.group();
        }
        String regex2 = "width:\\s*(\\d*)\\.";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(styleAttr);
        while (matcher2.find()) {
            width = Integer.parseInt(matcher2.group(1));
        }
        String regex3 = "height:\\s*(\\d*)\\.";
        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher(styleAttr);
        while (matcher3.find()) {
            height = Integer.parseInt(matcher3.group(1));
        }
        if (width <= 350) {
            return imgTag;
        }
        String newHeight = Integer.toString(height * 350 / width);
        //等比列缩小到宽度为350
        String newStyle = "style=\"width: 350px; height: " + newHeight + "px;\"";
        return imgTag.replaceAll(styleAttr, newStyle);
    }

    /**
     * 提取HTML标签
     *
     * @param source  HTML标签内容
     * @param element 标签名称    a
     */
    public static List<String> getHtmlTag(String source, String element) {
        List<String> result = new ArrayList<>();
        String regex = "<" + element + "[^<>]*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            String r = matcher.group();
            result.add(r);
        }
        return result;
    }
}
