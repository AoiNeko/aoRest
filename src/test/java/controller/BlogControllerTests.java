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
                httpHeaders.set("Authorization", "Bearer aslkdjs98");

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
