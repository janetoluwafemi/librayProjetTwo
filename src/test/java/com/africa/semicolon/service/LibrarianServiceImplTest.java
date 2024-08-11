package com.africa.semicolon.service;

import com.africa.semicolon.data.dtos.request.SearchForBookRequest;
import com.africa.semicolon.data.dtos.response.SearchForBookResponse;
import com.africa.semicolon.data.repositories.LibrarianRepo;
import com.africa.semicolon.data.services.LibrarianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibrarianServiceImplTest {
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private LibrarianRepo librarianRepo;
    @Test
    public void testThatLibrarianCanSearchForBook(){
        SearchForBookRequest searchForBookRequest = new SearchForBookRequest();
        searchForBookRequest.setTitle("");
    }
}
