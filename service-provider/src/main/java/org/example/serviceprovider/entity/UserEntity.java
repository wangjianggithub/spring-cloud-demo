package org.example.serviceprovider.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    private String id;
    private String name;
    private String password;
    private String age;
    private String email;

    public UserEntity() {
    }

    public UserEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
