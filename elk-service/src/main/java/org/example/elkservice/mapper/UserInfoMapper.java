package org.example.elkservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.elkservice.entity.UserEntity;

@Mapper
public interface UserInfoMapper {

    UserEntity getUserById(@Param("id") String id);

}
