package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.BookNotFoundException;
import com.africa.semicolon.data.exceptions.EmailNotFoundException;
import com.africa.semicolon.data.exceptions.IncorrectPasswordException;
import com.africa.semicolon.data.models.Book;
import com.africa.semicolon.data.models.Librarian;
import com.africa.semicolon.data.models.User;
import com.africa.semicolon.data.repositories.LibrarianRepo;
import com.africa.semicolon.data.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianServiceImpl implements LibrarianService{
    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookServiceImpl bookService;

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Librarian librarian = librarianRepo.findBookByTitle(addBookRequest.getTitle());
        AddBookResponse addBookResponse = bookService.addBookResponse(addBookRequest);
        return addBookResponse;
    }

    @Override
    public RemoveBookResponse removeBook(RemoveBookRequest removeBookRequest) {
        Librarian librarian = librarianRepo.findBookByTitle(removeBookRequest.getTitle());
        RemoveBookResponse removeBookResponse = bookService.removeBookResponse(removeBookRequest);
        return removeBookResponse;
    }

    @Override
    public SearchForBookResponse searchFoBook(SearchForBookRequest searchForBookRequest) {
        Librarian librarian = librarianRepo.findBookByTitle(searchForBookRequest.getTitle());
        if(librarian.getTitle().equalsIgnoreCase(searchForBookRequest.getTitle())){
            SearchForBookResponse searchForBookResponse = new SearchForBookResponse();
            searchForBookResponse.setTitle(librarian.getTitle());
            searchForBookResponse.setMessage("Book Searched Successfully");
            return searchForBookResponse;
        }
        throw new BookNotFoundException("Book Not Found");
    }

    @Override
    public VerifyMemberResponse verifyMember(VerifyMemberRequest verifyMemberRequest) {
        User user = userRepo.findUserByEmail(verifyMemberRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("Email not found"));
        if (user.getEmailPassword().equals(verifyMemberRequest.getPassword())){
            VerifyMemberResponse verifyMemberResponse = new VerifyMemberResponse();
            verifyMemberResponse.setMessage("Member Verified SuccessFully");
            return verifyMemberResponse;
        }
        throw new IncorrectPasswordException("Incorrect Password");
    }

    @Override
    public IssueBookResponse issueBook(IssueBookRequest issueBookRequest) {
        return null;
    }

    @Override
    public CalculateFineResponse calculateFine(CalculateFineRequest calculateFineRequest) {
        return null;
    }

    @Override
    public CreateBillResponse createBillResponse(CreateBillRequest createBillRequest) {
        return null;
    }
}
