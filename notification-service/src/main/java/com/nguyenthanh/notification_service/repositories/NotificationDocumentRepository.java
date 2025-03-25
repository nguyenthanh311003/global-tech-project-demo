package com.nguyenthanh.notification_service.repositories;

import com.nguyenthanh.notification_service.models.NotificationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDocumentRepository extends ElasticsearchRepository<NotificationDocument, String> {
}
