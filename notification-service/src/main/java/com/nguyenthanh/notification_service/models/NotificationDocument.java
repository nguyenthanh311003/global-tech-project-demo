package com.nguyenthanh.notification_service.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "notification_index")
public class NotificationDocument {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String message;
}
