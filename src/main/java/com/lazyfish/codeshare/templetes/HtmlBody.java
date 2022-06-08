package com.lazyfish.codeshare.templetes;

public class HtmlBody {
    public static String getCodeHtmlBody(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div style=\"text-align: center;margin: 20px;\">\n" +
                "    <h1>WebMaker代码分享平台</h1>\n" +
                "    <hr>\n" +
                "    <div style=\"text-align: left;\">\n" +
                "        <p>尊敬的用户，您好！</p>\n" +
                "        <p>\n" +
                "            您的验证码是：\n" +
                "            <span style=\"color:brown;font-size: 3rem;margin: 10px;\">")
                .append(code).append("</span>\n" +
                "        </p>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div style=\"font-size: 0.5rem;float:right;margin-top:20px;\">\n" +
                "        <p>懒鱼工作室开发团队</p>\n" +
                "    </div>\n" +
                "</div>\n");
        return stringBuilder.toString();
    }
}
