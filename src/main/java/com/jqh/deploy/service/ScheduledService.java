package com.jqh.deploy.service;

import com.jqh.deploy.utils.DeployUtils;
import com.jqh.deploy.utils.ResultVOUtil;
import com.jqh.deploy.vo.SHVo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {


    @Scheduled(cron = "0/60 * * * * *")
    public void scheduled(){
        System.out.println("start sceduled.....");
        try {
            if(!DeployUtils.checkRun()) {
                System.out.println("start running.....");
                DeployUtils.invokeSHForTime(SHVo.runPath);
            } else {
                System.out.println("no need start run.....");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("stop sceduled.....");
    }


}
