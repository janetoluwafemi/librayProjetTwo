package com.africa.semicolon.service;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.repositories.LiberianRepo;
import com.africa.semicolon.data.repositories.UserRepo;
import com.africa.semicolon.data.services.BookService;
import com.africa.semicolon.data.services.LibrarianService;
import com.africa.semicolon.data.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibrarianServiceImplTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private LiberianRepo librarianRepo;
    @Autowired
    private UserRepo userRepo;
    @BeforeEach
    public void set(){
        userRepo.deleteAll();
    }
    @BeforeEach
    public void setUp(){
        librarianRepo.deleteAll();
    }
    @Test
    public void testThatUserCanAddBook(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("BELOVED");
        addBookRequest.setAuthor("you");
        AddBookResponse response1 = bookService.addBook(addBookRequest);
        assertThat(response1.getMessage()).isEqualTo("Book Added Successfully");
    }
    @Test
    public void testThatBookCanRemoved(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Sister Blood");
        addBookRequest.setAuthor("Janet");
        AddBookResponse response1 = bookService.addBook(addBookRequest);
        assertThat(response1.getMessage()).isEqualTo("Book Added Successfully");

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("Blood Brothers");
        addBookRequest1.setAuthor("Olamide");
        AddBookResponse response2 = bookService.addBook(addBookRequest1);
        assertThat(response2.getMessage()).isEqualTo("Book Added Successfully");

        RemoveBookRequest removeBookRequest = new RemoveBookRequest();
        removeBookRequest.setTitle("Blood Brothers");
        removeBookRequest.setAuthor("Olamide");
        RemoveBookResponse removeBookResponse = bookService.removeBookResponse(removeBookRequest);
        assertThat(removeBookResponse.getMessage()).isEqualTo("Removed successfully");
    }
    @Test
    public void testThatLibrarianCanSearchForBook(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Siblings");
        addBookRequest.setAuthor("Oladimeji");
        AddBookResponse response1 = bookService.addBook(addBookRequest);
        assertThat(response1.getMessage()).isEqualTo("Book Added Successfully");

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("Bloodly");
        addBookRequest1.setAuthor("Olami");
        AddBookResponse response2 = bookService.addBook(addBookRequest);
        assertThat(response2.getMessage()).isEqualTo("Book Added Successfully");

        SearchForBookRequest searchForBookRequest = new SearchForBookRequest();
        searchForBookRequest.setTitle("Sister");
        searchForBookRequest.setAuthor("Jane");
        SearchForBookResponse response = librarianService.searchForBook(searchForBookRequest);
        assertThat(response.getMessage()).isEqualTo("Book Searched Successfully");
    }
    @Test
    public void testThatLibraryCanIssueBookToUser(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("papi");
        userRegisterRequest.setEmail("papi@gmail");
        userRegisterRequest.setEmailPassword("230");
        userRegisterRequest.setPhoneNumber("8789");
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepo.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("papi@gmail");
        loginRequest.setEmailPassword("230");
        userService.login(loginRequest);
        UserLogInResponse login = userService.login(loginRequest);
        assertThat(login.getMessage()).isEqualTo("LoggedIn Successful");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Siblings");
        addBookRequest.setAuthor("Oladimeji");
        AddBookResponse addBookResponse = librarianService.addBookToLiberian(addBookRequest);
        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added to Liberian list");

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("Bloodly");
        addBookRequest1.setAuthor("Olami");
        AddBookResponse addBookResponse1 = librarianService.addBookToLiberian(addBookRequest1);
        assertThat(addBookResponse1.getMessage()).isEqualTo("Book successfully added to Liberian list");

        IssueBookRequest issueBookRequest = new IssueBookRequest();
        issueBookRequest.setEmail("papi@gmail");
        issueBookRequest.setTitle("Siblings");
        issueBookRequest.setBookAuthor("Oladimeji");
        IssueBookResponse response = librarianService.issueBook(issueBookRequest);
        assertThat(response.getMessage()).isEqualTo("Book Issued Successfully");
    }
}
