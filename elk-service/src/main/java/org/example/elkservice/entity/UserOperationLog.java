package org.example.elkservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

@Document(indexName = "user_operation")
@Data
public class UserOperationLog {

    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Keyword)
    private String description;

    @Field(type = FieldType.Text)
    private String operation;

    @Field(type = FieldType.Date)
    private Date timestamp;

    @Field(type = FieldType.Object)
    private Map<String, Object> params;
}
