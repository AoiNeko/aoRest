package service;

import com.aoineko.App;
import com.aoineko.controller.BlogController;
import com.aoineko.dto.PostDTO;
import com.aoineko.entity.Response;
import com.aoineko.service.PostService;
import com.aoineko.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by aoineko on 2018/6/18.
 */
@SpringBootTest(classes = {App.class})
@RunWith(SpringRunner.class)
public class PostServiceTests {
    @Autowired
    private PostService postService;
    @Autowired
    private BlogController blogController;

    @Test
    public void testGetDayList() throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = df.parse("2018-06-11 08:22:22");


        Response response = blogController.dayList(date.getTime(),8);
        System.out.println(response);
    }
}
