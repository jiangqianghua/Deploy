package com.jqh.deploy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: jiang qiang hua
 * @Description:<h1></h1>
 * @Date: Create in 23:28 2019/4/28
 * @Modified By:
 **/
@Component
public class BaseConfig {
    @Value("${spring.airesource_server}")
    private String airesourceServer ;

    @Value("${spring.airesource_user}")
    private String airesourceUser ;

    @Value("${spring.airesource_admin}")
    private String airesourceAdmin ;

    public String getAiresourceServer() {
        return airesourceServer;
    }

    public String getAiresourceUser() {
        return airesourceUser;
    }

    public String getAiresourceAdmin() {
        return airesourceAdmin;
    }
}
