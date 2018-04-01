package com.touzhijia.project.remote;



import com.touzhijia.project.domain.dto.RandomValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 调用Rest服务
 * Created by leish on 2017/8/18.
 */
@Service
public class RestClientService {
    String URL = "https://gturnquist-quoters.cfapps.io/api/random";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public RandomValue getValue(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL,RandomValue.class);
    }

}
