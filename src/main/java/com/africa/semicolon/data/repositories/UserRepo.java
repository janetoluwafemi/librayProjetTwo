package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    User findUserById(String id);

    User findUserByEmail(String email);

}
