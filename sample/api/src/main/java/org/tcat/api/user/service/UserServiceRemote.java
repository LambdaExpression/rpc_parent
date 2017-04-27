package org.tcat.api.user.service;

import org.tcat.parent.rpc.annotation.RpcService;

/**
 * Created by Lin on 2017/4/27.
 */
@RpcService("/userService")
public interface UserServiceRemote {

    String test();

}
