package com.africa.semicolon.service;

import com.africa.semicolon.data.repositories.BookRepo;
import com.africa.semicolon.data.dtos.request.AddBookRequest;
import com.africa.semicolon.data.dtos.request.FindBookRequest;
import com.africa.semicolon.data.dtos.request.RemoveBookRequest;
import com.africa.semicolon.data.dtos.response.FindBookResponse;
import com.africa.semicolon.data.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class BookServiceImplTest {

    @BeforeEach
    public void setUp(){
        bookRepo.deleteAll();
    }
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;
    @Test
    public void testThatBookListIsEmpty(){
        FindBookRequest findBookRequest = new FindBookRequest();
        findBookRequest.setTitle("re");
        assertEquals(null,bookRepo.findBookByTitle("re"));
    }

    @Test
    public void testThatBookCanAdd(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("tu");
        bookService.addBook(addBookRequest);
        assertEquals(1,bookRepo.count());
    }

    @Test
    public void testThatBookCanDelete(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("yu");
        bookService.addBook(addBookRequest);
        assertEquals(1,bookRepo.count());
        RemoveBookRequest removeBookRequest = new RemoveBookRequest();
        removeBookRequest.setTitle("yu");
        bookService.removeBookResponse(removeBookRequest);
        assertEquals(0,bookRepo.count());
    }

    @Test
    public void testThatICanFindBook(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("tr");
        bookService.addBook(addBookRequest);
        assertEquals(1,bookRepo.count());
        FindBookRequest findBookRequest = new FindBookRequest();
        findBookRequest.setTitle("tr");
        FindBookResponse findBookResponse = bookService.book(findBookRequest);
        assertThat(findBookResponse.getMessage()).isEqualTo("Book found");
    }
}