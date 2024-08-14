package com.africa.semicolon;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.repositories.UserRepo;
import com.africa.semicolon.data.services.BookService;
import com.africa.semicolon.data.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceImplTest {
    @BeforeEach
    public void setUp(){
        userRepo.deleteAll();
    }
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Test
    public void testThatUserCanRegister(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        assertEquals(1,userRepo.count());
    }
    @Test
    public void testThatUserCanNotRegisterTwice(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        assertEquals(1,userRepo.count());
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        UserRegisterResponse userRegisterResponse = userService.registerUser(userRegisterRequest);
        assertThat(userRegisterResponse.getMessage()).isEqualTo("User already exist");
    }

    @Test
    public void testThatUserCanLogIn(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        assertEquals(1,userRepo.count());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("90");
        loginRequest.setEmailPassword("23");
        userService.login(loginRequest);
        UserLogInResponse login = userService.login(loginRequest);
        assertThat(login.getMessage()).isEqualTo("LoggedIn Successful");
    }

    @Test
    public void testThatUserCanLogOut() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepo.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("90");
        loginRequest.setEmailPassword("23");
        userService.login(loginRequest);
        UserLogInResponse login = userService.login(loginRequest);
        assertThat(login.getMessage()).isEqualTo("LoggedIn Successful");

        UserLogOutRequest userLogOutRequest = new UserLogOutRequest();
        userLogOutRequest.setEmail("90");
        userService.userLogIOutResponse(userLogOutRequest);
        UserLogOutResponse userLogOutResponse = userService.userLogIOutResponse(userLogOutRequest);
        assertThat(userLogOutResponse.getMessage()).isEqualTo("Log Out Successful");
    }

    @Test
    public void testThatUserCanBuyBook(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName("jan");
        userRegisterRequest.setEmail("90");
        userRegisterRequest.setEmailPassword("23");
        userRegisterRequest.setPhoneNumber("87");
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepo.count());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("90");
        loginRequest.setEmailPassword("23");
        userService.login(loginRequest);
        UserLogInResponse login = userService.login(loginRequest);
        assertThat(login.getMessage()).isEqualTo("LoggedIn Successful");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("BELOVED");
        addBookRequest.setAuthor("Love");
        bookService.addBook(addBookRequest);
        AddBookResponse response = bookService.addBook(addBookRequest);
        assertThat(response.getMessage()).isEqualTo("Book Added Successfully");

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("Sisters");
        addBookRequest1.setAuthor("Janet");
        bookService.addBook(addBookRequest);
        response = bookService.addBook(addBookRequest);
        assertThat(response.getMessage()).isEqualTo("Book Added Successfully");

        BuyBookRequest userBorrowBookRequest = new BuyBookRequest();
        userBorrowBookRequest.setTitle("BELOVED");
        userBorrowBookRequest.setEmail("90");
        BuyBookResponse userBorrowBookResponse = userService.userBuysBook(userBorrowBookRequest);
        assertThat(userBorrowBookResponse.getMessage()).isEqualTo("Book Bought Successful");
    }
//
//    @Test
//    public void testThatUserCanReturnBook() {
//        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
//        userRegisterRequest.setName("jan");
//        userRegisterRequest.setEmail("90");
//        userRegisterRequest.setEmailPassword("23");
//        userRegisterRequest.setPhoneNumber("87");
//        userService.registerUser(userRegisterRequest);
//        assertEquals(1, userRepo.count());
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("90");
//        loginRequest.setEmailPassword("23");
//        userService.login(loginRequest);
//        UserLogInResponse login = userService.login(loginRequest);
//        assertThat(login.getMessage()).isEqualTo("LoggedIn Successful");
//
//        BorrowBookRequest userBorrowBookRequest = new BorrowBookRequest();
//        userBorrowBookRequest.setBookTitle("BELOVED");
//        userBorrowBookRequest.setEmail("90");
//        userBorrowBookRequest.setEmailPassword("23");
//        userService.borrowBook(userBorrowBookRequest);
//        BorrowBookResponse borrowBookResponse = userService.borrowBook(userBorrowBookRequest);
//        assertThat(borrowBookResponse.getMessage()).isEqualTo("Book Borrowed Successfully");
//
//        ReturnBookRequest userReturnBookRequest = new ReturnBookRequest();
//        userReturnBookRequest.setBookTitle("BELOVED");
//        userReturnBookRequest.setEmail("90");
//        userReturnBookRequest.setEmailPassword("23");
//        userService.returnBook(userReturnBookRequest);
//        ReturnBookResponse returnBookResponse = userService.returnBook(userReturnBookRequest);
//        assertThat(returnBookResponse.getMessage()).isEqualTo("Book Returned Successfully");
//    }
}
