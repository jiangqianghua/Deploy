package com.jqh.deploy.utils;

import com.jqh.deploy.resp.WxConfig;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

public class WxUtils {
    public static WxConfig getConfig(String jsapi_ticket, String link) {
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String signature = getSignature(jsapi_ticket, nonceStr, timestamp, link);

        WxConfig wxConfig = new WxConfig();
        wxConfig.setNoncestr(nonceStr);
        wxConfig.setTimestamp(timestamp);
        wxConfig.setSignature(signature);
        return wxConfig;
    }

    /**
     * 生成nonce_str随机字符串
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成timestamp
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static String getSignature(String jsapiTicket, String nonceStr, String timestamp, String url) {
        // 注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
                + url;
        System.out.println("string1="+string1);

        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            System.out.println("signature="+signature);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
