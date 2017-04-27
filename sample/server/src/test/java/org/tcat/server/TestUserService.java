package org.tcat.server;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tcat.api.user.service.UserServiceRemote;

/**
 * Created by Lin on 2017/4/27.
 */
public class TestUserService extends JunitBaseTest {

    @Autowired
    private UserServiceRemote userService;

    @Test
    public void test() {
        show(userService.test());
    }

}
