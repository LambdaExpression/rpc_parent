package org.tcat.server.service;

import org.springframework.stereotype.Service;
import org.tcat.api.user.service.UserServiceRemote;

/**
 * Created by Lin on 2017/4/27.
 */
@Service("userService")
public class UserService implements UserServiceRemote {

    @Override
    public String test() {
        return "testing";
    }
}
