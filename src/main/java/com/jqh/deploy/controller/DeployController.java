package com.jqh.deploy.controller;

import com.jqh.deploy.config.BaseConfig;
import com.jqh.deploy.utils.DeployUtils;
import com.jqh.deploy.utils.ResultVOUtil;
import com.jqh.deploy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jiang qiang hua
 * @Description:<h1></h1>
 * @Date: Create in 23:11 2019/4/27
 * @Modified By:
 **/
@RestController
@RequestMapping("/")
public class DeployController {

    @Autowired
    public BaseConfig baseConfig ;

    @PostMapping("/deploy")
    public ResultVo deploy(){
        System.out.println("deploy");
        try {
            DeployUtils.invokeSH(baseConfig.getAiresourceServer());
            return ResultVOUtil.success();
        }catch (Exception e){
            return ResultVOUtil.error(1,e.getMessage());
        }
    }

    @PostMapping("/stop")
    public ResultVo stop(){
        System.out.println("stop");
        try {
            DeployUtils.invokeSH(baseConfig.getAiresourceServer());
            return ResultVOUtil.success();
        }catch (Exception e){
            return ResultVOUtil.error(1,e.getMessage());
        }
    }

    @PostMapping("/start")
    public ResultVo start(){
        System.out.println("start");
        try {
            DeployUtils.invokeSH(baseConfig.getAiresourceServer());
            return ResultVOUtil.success();
        }catch (Exception e){
            return ResultVOUtil.error(1,e.getMessage());
        }
    }
}
