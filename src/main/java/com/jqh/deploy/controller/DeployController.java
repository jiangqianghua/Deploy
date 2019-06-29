package com.jqh.deploy.controller;

import com.jqh.deploy.config.BaseConfig;
import com.jqh.deploy.utils.DeployUtils;
import com.jqh.deploy.utils.ResultVOUtil;
import com.jqh.deploy.vo.ResultVo;
import com.jqh.deploy.vo.SHVo;
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
//        System.out.println("deploy");
//        try {
//            DeployUtils.invokeSH(baseConfig.getAiresourceServer());
//            return ResultVOUtil.success();
//        }catch (Exception e){
//            return ResultVOUtil.error(1,e.getMessage());
//        }
        return null;
    }

    @GetMapping("/airesource/stop")
    public ResultVo stop(){
        System.out.println("stop");
        try {
            DeployUtils.invokeSH(SHVo.killPath);
            return ResultVOUtil.success("已停止");
        }catch (Exception e){
            return ResultVOUtil.error(1,e.getMessage());
        }
    }

    @GetMapping("/airesource/start")
    public ResultVo start(){
        System.out.println("start");
        try {
            if(!DeployUtils.checkRun()) {
                DeployUtils.invokeSH(SHVo.runPath);
                return ResultVOUtil.success("已启动");
            } else {
                return ResultVOUtil.success("已启动,无需再启动");
            }
        }catch (Exception e){
            return ResultVOUtil.error(1,e.getMessage());
        }
    }
}
