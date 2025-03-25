package com.nguyenthanh.user_service.repositoties;

import com.nguyenthanh.user_service.entities.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
}
