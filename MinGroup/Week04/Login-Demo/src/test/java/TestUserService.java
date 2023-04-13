package test.java;

import com.lldw.www.po.User;
import com.lldw.www.service.Impl.UserServiceImpl;
import org.testng.annotations.Test;

/**
 * @author
 * @date
 */
public class TestUserService {
    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public  void test(){
        User loginUser = new User();
        String username = "lisi";
        String password = "234";
        loginUser.setUserName(username);
        loginUser.setPassword(password);
        User rs = userService.login(loginUser);
        System.out.println(rs);
    }
}
