package com.jqh.deploy.utils;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @Author: jiang qiang hua
 * @Description:<h1></h1>
 * @Date: Create in 23:22 2019/4/28
 * @Modified By:
 **/
public class DeployUtils {
    public static void invokeSH(String shpath) throws Exception{
        try {
            System.out.println("--------------invokeSH http start " + shpath);
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();
//            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            String result = sb.toString();
//            //  Log.info("cleanViruses",result);
//            System.out.println(result);
            System.out.println("---------------invokeSH http end " + shpath);
        }catch (Exception e){
            throw e;
        }

    }


    public static void invokeSHForTime(String shpath) throws Exception{
        try {
            System.out.println("--------------invokeSH time start " + shpath);
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();
            System.out.println("---------------invokeSH time end " + shpath);
        }catch (Exception e){
            throw e;
        }

    }

    public static boolean checkRun() throws Exception{
        try {
            RestTemplate restTemplate = new RestTemplate();
            Object o = restTemplate.getForObject("http://www.52res.cn:8888/resource/today/count", Object.class);
//            Map<String, String> map = (Map<String, String>) o;
//            int code = Integer.parseInt(map.get("code"));
//            if (code == 0) {
//                return true;
//            }
//            System.out.println(o);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
