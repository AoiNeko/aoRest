package controller;

import com.alibaba.fastjson.JSON;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aoineko on 2018/8/7.
 */
public class UserContorllerTests {

    private static RestTemplate restTemplate;
    public static final String baseUrl = "http://127.0.0.1:8090/";
    @BeforeClass
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testLogin() {
        Map<String, String> param = new HashMap();
        param.put("userName", "test");
        param.put("passwd", "123456");
        ResponseEntity res = restTemplate.getForEntity(baseUrl + "login?userName={userName}&passwd={passwd}", String.class,  param);
        System.out.println(JSON.toJSONString(res.getBody()));

    }
}
