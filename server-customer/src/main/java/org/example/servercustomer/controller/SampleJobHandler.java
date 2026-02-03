package org.example.servercustomer.controller;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SampleJobHandler {

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        // 示例：处理任务
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @XxlJob("syncDataJobHandler")
    public ReturnT<String> syncDataJobHandler(String param) {
        try {
            XxlJobHelper.log("开始同步数据，参数：" + param);

            // 业务逻辑
//            boolean success = syncDataService.sync(param);
            boolean success = true;
            if (success) {
                return ReturnT.SUCCESS;
            } else {
                return new ReturnT<>(ReturnT.FAIL_CODE, "同步失败");
            }
        } catch (Exception e) {
            XxlJobHelper.log("同步数据异常", e);
            return new ReturnT<>(ReturnT.FAIL_CODE, e.getMessage());
        }
    }
}
