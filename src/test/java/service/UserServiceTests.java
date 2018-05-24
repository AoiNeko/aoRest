package service;

import com.aoineko.App;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {App.class})
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("test");
        user.setPassword("1222");
        userService.addUser(user);

    }
}
