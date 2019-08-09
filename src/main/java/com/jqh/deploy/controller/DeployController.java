package com.jqh.deploy.controller;

import com.jqh.deploy.config.BaseConfig;
import com.jqh.deploy.resp.AccessToken;
import com.jqh.deploy.resp.TicketResponse;
import com.jqh.deploy.resp.WxConfig;
import com.jqh.deploy.utils.*;
import com.jqh.deploy.vo.ResultVo;
import com.jqh.deploy.vo.SHVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jiang qiang hua
 * @Description:<h1></h1>
 * @Date: Create in 23:11 2019/4/27
 * @Modified By:
 **/
@RestController
@RequestMapping("/")
public class DeployController {

    private static final String ACCESS_TOKE_CACHE_KEY = "access:token";
    /**
     * 根据微信文档，获取access_token时，grant_type固定为该值
     */
    private static final String GRANT_TYPE = "client_credential";
    /**
     * 两小时过期，但是考虑到网络延迟和微信辣鸡文档，设置过期时间为一小时
     */
    private static final long EXPIRE = 3600 * 1000;
    private static final String ACCESS_TOKEN_REQ_BASE_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String TICKET_BASE_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    @Autowired
    public BaseConfig baseConfig ;
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @GetMapping("/ticket")
    public ResultVo getToken(String link){
        System.out.println("link="+link);
        String token = getAccessToken();
        String ticket = getTicket(token);
        WxConfig wxConfig = WxUtils.getConfig(ticket, link);
        return ResultVOUtil.success(wxConfig);
    }

    private String getAccessToken(){
        String accessToken = redisTemplate.opsForValue().get(ACCESS_TOKE_CACHE_KEY);
        if (!StringUtils.isEmpty(accessToken)){
            System.out.println("from redis:" + accessToken);
            return accessToken;
        }
        Map<String, String> map = new TreeMap<>();
        map.put("appid", "wxd3809d046e738139");
        map.put("secret", "cb966a3121778f76654cf14b11c12c24");
        map.put("grant_type", "client_credential");
        String json = HttpClientUtil.doGet(ACCESS_TOKEN_REQ_BASE_URL,map);
        AccessToken bean = JsonUtil.fromJson(json, AccessToken.class);
        System.out.println("json:" + json);
        accessToken = bean.getAccess_token();
        redisTemplate.opsForValue().set(ACCESS_TOKE_CACHE_KEY, accessToken, EXPIRE, TimeUnit.MILLISECONDS);
        return accessToken;
    }

    private String getTicket(String accessToken) {
        // 这个url链接和参数不能变
        String url = generateTicketUrl(accessToken);

        String json = HttpClientUtil.doGet(url);
//        HttpEntity<String> requestEntity = generateHttpEntity();
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        if (!HttpStatus.OK.equals(statusCode)){
//            System.out.println("请求ticket异常");
//            return "";
//        }
//        String body = responseEntity.getBody();
        String body = json ;
        System.out.println("ticket:"+body);
        TicketResponse ticketResponse = JsonUtil.fromJson(body, TicketResponse.class);
        return ticketResponse.getTicket();
    }

    private HttpEntity<String> generateHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        return new HttpEntity<>(null, headers);
    }

    private String generateTicketUrl(String accessToken) {
        return TICKET_BASE_URL + "?access_token=" + accessToken + "&type=jsapi";
    }
}
