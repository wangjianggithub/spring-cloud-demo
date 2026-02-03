package org.example.elkservice.controller;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author 姑苏老陈
 * @date 2024/12/18
 */
@Component
public class TestJob {

    @XxlJob("demoJobHandler")
    public ReturnT<String> myTestJob(){
        try {
            System.out.println("***************测试定时任务，开始执行---------------!!!");
            return new ReturnT<String>(ReturnT.SUCCESS_CODE, "执行成功，处理了XX条数据");
        }catch (Exception e){
            return new ReturnT<>(ReturnT.FAIL_CODE, "异常原因: " + e.getMessage());
        }
    }

}

