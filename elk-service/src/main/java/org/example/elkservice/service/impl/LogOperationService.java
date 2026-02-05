package org.example.elkservice.service.impl;

import org.example.elkservice.entity.UserOperationLog;
import org.example.elkservice.mapper.UserOperationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class LogOperationService {

    @Resource
    private UserOperationRepository repository;

    public void saveOperationLog(String description, String operation, Map<String, Object> params) {
        UserOperationLog log = new UserOperationLog();
        log.setDescription(description);
        log.setOperation(operation);
        log.setTimestamp(new Date());
        log.setParams(params);
        repository.save(log);
    }
}
