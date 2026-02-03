package org.example.serviceprovider.config;

import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class GlobalFeignConfig {

    @Bean
    public Decoder feignDecoder() {
        // 创建支持多种 Content-Type 的解码器
        ObjectFactory<HttpMessageConverters> messageConverters = () -> {
            List<HttpMessageConverter<?>> converters = new ArrayList<>();

            // 1. JSON 转换器
            MappingJackson2HttpMessageConverter jsonConverter =
                    new MappingJackson2HttpMessageConverter();
            jsonConverter.setSupportedMediaTypes(Arrays.asList(
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_JSON_UTF8,
                    MediaType.TEXT_PLAIN  // 也尝试解析 text/plain 为 JSON
            ));
            converters.add(jsonConverter);

            // 2. 文本转换器
            converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

            return new HttpMessageConverters(converters);
        };

        return new ResponseEntityDecoder(new SpringDecoder(messageConverters));
    }

    @Bean
    public Encoder feignEncoder() {
        return new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() {
                return new HttpMessageConverters(
                        new MappingJackson2HttpMessageConverter()
                );
            }
        });
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;  // 启用详细日志，便于调试
    }
}
