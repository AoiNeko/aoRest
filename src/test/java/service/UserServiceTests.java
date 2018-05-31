package service;

import com.aoineko.App;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest(classes = {App.class})
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("tes3t");
        user.setPassword("1222");
        user.setGmtCreate(new Date());
        userService.addUser(user);
    }

    @Test
    public void getUser() {
//        Date now = new Date();
//        System.out.println(LocalDateTime.now().toString());
//        System.out.println(Instant.now().atZone(ZoneId.of("GMT+8")).toInstant().toString());
//        System.out.println(now.toInstant().atZone(ZoneId.of("GMT+8")).toString());
//        System.out.println(now.toInstant().toString());
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
//
//        System.out.println(dateFormat.format(now));
//
//        Instant instant = Instant.now();
//        System.out.println(instant.toEpochMilli());
//        System.out.println(instant.atZone(ZoneId.of("GMT+8")).toInstant().toEpochMilli());
//        System.out.println(instant.atZone(ZoneId.of("GMT+6")).toInstant().toEpochMilli());
        User user = userService.validate("test", "1222");
//        System.out.println(user.getGmtCreate());
//        System.out.println(user.getGmtUpdate());
//        System.out.println(new Date());

    }
}
