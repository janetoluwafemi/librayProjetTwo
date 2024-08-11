package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Librarian;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibrarianRepo extends MongoRepository<Librarian, String> {
    Librarian findUserByEmail(String email);
    Librarian findBookByTitle(String title);
}
