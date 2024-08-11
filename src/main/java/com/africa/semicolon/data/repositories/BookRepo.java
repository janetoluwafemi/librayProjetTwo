package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book, String> {
    Book findBookByTitle(String title);
}
