package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.BookNotBorrowedException;
import com.africa.semicolon.data.exceptions.IncorrectPasswordException;
import com.africa.semicolon.data.exceptions.YouHaveNotLogInException;
import com.africa.semicolon.data.models.Book;
import com.africa.semicolon.data.models.Liberian;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.BookRepo;
import com.africa.semicolon.data.repositories.LiberianRepo;
import com.africa.semicolon.data.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private LiberianRepo librarianRepo;
    @Autowired
    private BookService bookService;
    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        if(!user.isRegistered()){
            user.setName(userRegisterRequest.getName());
            user.setEmail(userRegisterRequest.getEmail());
            user.setEmailPassword(userRegisterRequest.getEmailPassword());
            user.setPhoneNumber(userRegisterRequest.getPhoneNumber());
            UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
            userRegisterResponse.setMessage("User registered successfully");
            userRepo.save(user);
            for(User user1 : userRepo.findAll()){
                if(user1.getEmail().equals(userRegisterRequest.getEmail())){
                    userRegisterResponse.setMessage("User already exist");
                }
            }
            return userRegisterResponse;
        }
        throw new IllegalArgumentException("You have been registered");
    }

    @Override
    public UserLogInResponse login(LoginRequest loginRequest) {
        User user = userRepo.findUserByEmail(loginRequest.getEmail());
        user.setEmail(loginRequest.getEmail());
        user.setEmailPassword(loginRequest.getEmailPassword());
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setMessage("LoggedIn Successful");
        user.setLogIn(true);
        if(!user.getEmailPassword().equals(loginRequest.getEmailPassword())) throw new IncorrectPasswordException("Incorrect password");
        userRepo.save(user);
        return userLogInResponse;
    }

    @Override
    public UserLogOutResponse userLogIOutResponse(UserLogOutRequest userLogOutRequest) {
        User user = userRepo.findUserByEmail(userLogOutRequest.getEmail());
        user.setLogIn(false);
        UserLogOutResponse userLogOutResponse = new UserLogOutResponse();
        userLogOutResponse.setMessage("Log Out Successful");
        return userLogOutResponse;
    }


    @Override
    public BuyBookResponse userBuysBook(BuyBookRequest buyBookRequest) {
        User user = userRepo.findUserByEmail(buyBookRequest.getEmail());
            if(user.isLogIn()) {
                Book book = new Book();
                BuyBookResponse buyBookResponse = bookService.buyBookResponse(buyBookRequest);
                book.setTitle(buyBookResponse.getTitle());
                book.setAuthor(buyBookResponse.getAuthor());
                book.setId(buyBookResponse.getId());
                List<Book>bookList = user.getListOfbooks();
                bookList.add(book);
                user.setListOfbooks(bookList);
                userRepo.save(user);
                return buyBookResponse;
            }
        throw new YouHaveNotLogInException("Go and login or the user is not register");
    }

    @Override
    public DownloadBookResponse userDownloadBook(DownloadBookRequest returnBookRequest) {
        User user = userRepo.findUserByEmail(returnBookRequest.getEmail());
        if(user.isLogIn()){
            Book book = bookRepo.findBookByTitle(returnBookRequest.getTitle());
            if (user.isBought()){
                DownloadBookResponse response = bookService.downloadBookResponse(returnBookRequest);
                return response;
            }
            throw new BookNotBorrowedException("The book have not been bought");
        }
        throw new YouHaveNotLogInException("Go and login");
    }
}









