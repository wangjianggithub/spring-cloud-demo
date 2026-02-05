package org.example.elkservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.elkservice.entity.UserOperationLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserOperationRepository extends ElasticsearchRepository<UserOperationLog, String> {

    List<UserOperationLog> findByUserId(@Param("id") String userId);

}
