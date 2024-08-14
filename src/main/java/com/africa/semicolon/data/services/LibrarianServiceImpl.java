package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.BookNotFoundException;
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
public class LibrarianServiceImpl implements LibrarianService{
    @Autowired
    private LiberianRepo librarianRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookServiceImpl bookService;
    private LiberianRepo liberianRepo;

    @Override
    public AddBookResponse addBookToLiberian(AddBookRequest addBookRequest) {
        Book newBook = bookRepo.findBookByTitle(addBookRequest.getTitle());
        Liberian liberian = new Liberian();
        List<Book> liberianBooks = liberian.getListOfbooks();
        liberianBooks.add(newBook);
        liberian.setListOfbooks(liberianBooks);
        librarianRepo.save(liberian);
        AddBookResponse addBookResponse = new AddBookResponse();
        liberian.setTitle(addBookResponse.getTitle());
        liberian.setId(addBookResponse.getId());
        addBookResponse.setMessage("Book successfully added to Liberian list");
        return addBookResponse;
    }

    @Override
    public RemoveBookResponse userRemoveBook(RemoveBookRequest removeBookRequest) {
        User user = userRepo.findUserByEmail(removeBookRequest.getEmail());
        Liberian librarian = new Liberian();
        if(user.isLogIn()){
            Book book = new Book();
            RemoveBookResponse response = bookService.removeBookResponse(removeBookRequest);
            List<Book> booksList = librarian.getListOfbooks();
            book.setTitle(response.getTitle());
            booksList.remove(book);
            return response;
        }
        throw new YouHaveNotLogInException("Not Logged In");
    }

    @Override
    public SearchForBookResponse searchForBook(SearchForBookRequest searchForBookRequest) {
        Liberian liberian = new Liberian();
        liberian.setTitle(searchForBookRequest.getTitle());
        if(liberian.getTitle().equals(searchForBookRequest.getTitle())){
            SearchForBookResponse searchForBookResponse = new SearchForBookResponse();
            searchForBookResponse.setTitle(liberian.getTitle());
            searchForBookResponse.setMessage("Book Searched Successfully");
            librarianRepo.save(liberian);
            return searchForBookResponse;
        }
        throw new BookNotFoundException("Book Not Found");
    }

    @Override
    public VerifyMemberResponse verifyMember(VerifyMemberRequest verifyMemberRequest) {
        User user = userRepo.findUserByEmail(verifyMemberRequest.getEmail());
        user.setEmailPassword(verifyMemberRequest.getPassword());
        if (user.getEmailPassword().equals(verifyMemberRequest.getPassword())){
            List<User> userList = new ArrayList<>();
            userList.add(user);
            Liberian librarian = new Liberian();
            librarian.setListOfUsers(userList);
            librarianRepo.save(librarian);
            VerifyMemberResponse verifyMemberResponse = new VerifyMemberResponse();
            verifyMemberResponse.setMessage("Member Verified SuccessFully");
            return verifyMemberResponse;
        }
        throw new IncorrectPasswordException("Incorrect Password");
    }

    @Override
    public IssueBookResponse issueBook(IssueBookRequest issueBookRequest) {
        User user = userRepo.findUserById(issueBookRequest.getId());
        Liberian liberian = new Liberian();
        liberian.setTitle(issueBookRequest.getTitle());
        liberian.setBookAuthor(issueBookRequest.getBookAuthor());
        liberian = librarianRepo.findBookByTitle(issueBookRequest.getTitle());
        List<Book> liberianBooks = liberian.getListOfbooks();
        liberianBooks.getFirst();
        liberian.setListOfbooks(liberianBooks);
        librarianRepo.save(liberian);
        IssueBookResponse response = new IssueBookResponse();
        liberian.setTitle(issueBookRequest.getTitle());
        liberian.setId(issueBookRequest.getId());
        response.setMessage("Book Issued Successfully");
        return response;
    }

    @Override
    public CalculateFineResponse calculateFine(CalculateFineRequest calculateFineRequest) {
        User user = userRepo.findUserById(calculateFineRequest.getUserId());
        Liberian liberian = new Liberian();
        liberianRepo.findBookByTitle(calculateFineRequest.getTitle());
        liberian.setPrice(calculateFineRequest.getPrice());
        CalculateFineResponse calculateFineResponse = new CalculateFineResponse();
        calculateFineResponse.setMessage("Amount Of Book Successfully Calculated");
        return null;
    }

    @Override
    public CreateBillResponse createBillResponse(CreateBillRequest createBillRequest) {
        return null;
    }
}
