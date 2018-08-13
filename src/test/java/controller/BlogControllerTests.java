package controller;

import com.alibaba.fastjson.JSON;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by aoineko on 2018/8/8.
 */
public class BlogControllerTests {

    private static RestTemplate restTemplate;
    public static final String baseUrl = "http://127.0.0.1:8090/";
    @BeforeClass
    public static void init() {
        restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> res = restTemplate.getInterceptors();
        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                HttpHeaders httpHeaders =request.getHeaders();
                httpHeaders.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhb2luZWtvIiwidXNlck5hbWUiOiJ0ZXN0IiwiZXhwIjoxNTM0NjA5MjQ0LCJpYXQiOjE1MzQxNzcyNDR9.aux4u3Z6U7Z1OqDP0yi2tSi9kbmHC9tpgdzXASmXGqSUS4s1oPIH4Vj6lphUDXFV944N174i9_byptKeAlY_YHLJJdZRLWPGPX9zswz41WdI4696L0-H3aPw5eJKdqEDTDV4VK2WQLlzhu8LLPbDjKeeAcD_R6SC0waaHUDltjERCNuQvqqR4N4EZJv3skj2lNPBCJOGe5UKH6iGWOFcjyfkzLYYF0bXlD3TElPSog8Qy6H2ei2pV6CT2QkQNgnBKlrgRIs0iFq9MHKuB--TPrtaBfkw3YTkH683jW3EuUX0X4TWyqLiISD4wisd5FgeSljfRXhWKvgVQO8apLYZug");

                return execution.execute(request, body);
            }
        };
        res.add(interceptor);
        restTemplate.setInterceptors(res);
    }



    @Test
    public void testDayList() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,5, 11, 1, 11);

        Map<String, String> param = new HashMap();
        param.put("t", String.valueOf(calendar.getTimeInMillis()));
        param.put("tz", "8");

        ResponseEntity res = restTemplate.getForEntity(baseUrl + "post/day/list?t={t}&tz={tz}", String.class,  param);
        System.out.println(JSON.toJSONString(res.getBody()));

    }

    @Test
    public void testMonthList() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,5, 11, 1, 11);

        Map<String, String> param = new HashMap();
        param.put("t", String.valueOf(calendar.getTimeInMillis()));
        param.put("tz", "8");
        ResponseEntity res = restTemplate.getForEntity(baseUrl + "post/month/list?t={t}&tz={tz}", String.class,  param);
        System.out.println(JSON.toJSONString(res.getBody()));

    }
}
