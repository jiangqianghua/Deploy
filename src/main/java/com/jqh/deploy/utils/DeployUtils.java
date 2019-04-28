package com.jqh.deploy.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: jiang qiang hua
 * @Description:<h1></h1>
 * @Date: Create in 23:22 2019/4/28
 * @Modified By:
 **/
public class DeployUtils {
    public static void invokeSH(String shpath) throws Exception{
        try {
            System.out.println("--------------invokeSH start " + shpath);
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
            System.out.println("---------------invokeSH end " + shpath);
        }catch (Exception e){
            throw e;
        }

    }
}
