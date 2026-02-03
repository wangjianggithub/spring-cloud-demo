package org.example.elkservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "org.example.elkservice.mapper")
@Slf4j
public class ElkServiceApplication implements ApplicationRunner {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh时mm分ss秒 SSS");

    public static void main(String[] args) {
        SpringApplication.run(ElkServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("hh时mm分ss秒 SSS");
        Random random = new Random();
//        while (true){
//            TimeUnit.MILLISECONDS.sleep(1000+random.nextInt(1000));
//            String format1 = format.format(new Date());
//            log.info("测试案例。当前时间：{}",format1);
//        }
    }

}
