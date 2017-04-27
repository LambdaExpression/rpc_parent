package org.tcat.api.user.dto;

import java.io.Serializable;

/**
 * Created by Lin on 2017/4/27.
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 3742642942949001410L;

    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
