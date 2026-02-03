package org.example.serviceprovider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.serviceprovider.entity.UserEntity;

@Mapper
public interface UserInfoMapper {

    UserEntity queryList(@Param("id") String id);

    void insertUser(UserEntity user);
}
