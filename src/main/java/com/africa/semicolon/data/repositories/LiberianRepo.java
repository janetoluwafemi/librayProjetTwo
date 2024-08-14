package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Liberian;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiberianRepo extends MongoRepository<Liberian, String> {
    Liberian findUserByEmail(String email);
    Liberian findBookByTitle(String title);
}
