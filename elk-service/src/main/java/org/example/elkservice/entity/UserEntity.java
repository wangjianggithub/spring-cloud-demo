package org.example.elkservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    @Schema(description = "用户ID", example = "1")
    private String id;
    @Schema(description = "用户名", example = "张三", required = false)
    private String name;
    @Schema(description = "密码")
    private String password;
    private String age;
    @Schema(description = "邮箱", example = "user@example.com")
    private String email;

}
