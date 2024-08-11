package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.IncorrectPasswordException;
import com.africa.semicolon.data.exceptions.IncorrectTitleOfBookBorrowedException;
import com.africa.semicolon.data.exceptions.YouHaveNotLogInException;
import com.africa.semicolon.data.models.Book;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.BookRepo;
import com.africa.semicolon.data.repositories.UserRepo;
import com.africa.semicolon.data.exceptions.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

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
        User user = userRepo.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("Not Found"));
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
        User user = userRepo.findUserByEmail(userLogOutRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("Not Found"));
        user.setLogIn(false);
        UserLogOutResponse userLogOutResponse = new UserLogOutResponse();
        userLogOutResponse.setMessage("Log Out Successful");
        return userLogOutResponse;
    }
//
//    @Override
//    public UserBorrowBookResponse borrowBook(UserBorrowBookRequest userBorrowBookRequest) {
//        User user = userRepo.findUserByEmail(userBorrowBookRequest.getEmail());
//            if(user.isLogIn()) {
//                for (ListOfBooks books : ListOfBooks.values()) {
//                    if(userBorrowBookRequest.getBookTitle().equalsIgnoreCase(String.valueOf(books))){
//                        UserBorrowBookResponse userBorrowBookResponse = new UserBorrowBookResponse();
//                        userBorrowBookResponse.setTimeOfBookBorrowed(userBorrowBookResponse.getTimeOfBookBorrowed());
//                        user.setBorrowed(true);
//                        userBorrowBookResponse.setMessage("Book Borrowed Successfully");
//                        userRepo.save(user);
//                        return userBorrowBookResponse;
//                    }
//                }
//            }
//        throw new YouHaveNotLogInException("Go and login or the book is not available");
//    }
//
//    @Override
//    public UserReturnBookResponse returnBook(UserReturnBookRequest userReturnBookRequest) {
//        User user = userRepo.findUserByEmail(userReturnBookRequest.getEmail());
//
//        Book book = bookRepo.findBookByTitle(userReturnBookRequest.getBookTitle());
////
////        if(foundBook == null){
////            throw new RuntimeException("Book not found");
////        }
////        boolean isBookBorrowed = foundBook.isBorrowed();
//
//        if(user.isLogIn()){
////                if(isBookBorrowed) {
//            if (user.isBorrowed()){
//                user.setBorrowed(true);
//                user.setId(userReturnBookRequest.getId());
//                user.setBookTitle(userReturnBookRequest.getBookTitle());
//                UserReturnBookResponse userReturnBookResponse = new UserReturnBookResponse();
//                userReturnBookResponse.setBookTitle(userReturnBookResponse.getBookTitle());
//                user.setBorrowed(false);
//                user.setReturned(true);
//                userRepo.save(user);
//                userReturnBookResponse.setMessage("Book Returned Successfully");
//                return userReturnBookResponse;
//            }
//            throw new IncorrectTitleOfBookBorrowedException("The title of the book you borrowed is not the same with the title of book you want to return");
//        }
//        throw new YouHaveNotLogInException("Go and login");
//    }

}









