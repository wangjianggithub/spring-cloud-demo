package org.example.servercustomer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.servercustomer.entity.UserEntity;


@Mapper
public interface UserInfoMapper {

    UserEntity findUserinfoById(@Param("id") String id);

}
