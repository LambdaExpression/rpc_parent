package org.tcat.api.user.service;

import org.tcat.parent.rpc.annotation.RService;

/**
 * Created by Lin on 2017/4/27.
 */
@RService("/userService")
public interface UserServiceRemote {

    String test();

}
