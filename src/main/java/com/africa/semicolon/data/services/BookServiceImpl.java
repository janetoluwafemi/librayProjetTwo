package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.BookAlreadyExistException;
import com.africa.semicolon.data.exceptions.BookNotFoundException;
import com.africa.semicolon.data.exceptions.YouHaveToBorrowException;
import com.africa.semicolon.data.models.Book;
import com.africa.semicolon.data.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookService bookService;
    @Override
    public FindBookResponse book(FindBookRequest findBookRequest) {
        for (Book book: bookRepo.findAll()){
            if(book.getTitle().equals(findBookRequest.getTitle())){
                FindBookResponse findBookResponse = new FindBookResponse();
                findBookResponse.setMessage("Book found");
                return findBookResponse;
            }
        }
        throw new IllegalArgumentException("Book not available");
    }

    @Override
    public AddBookResponse addBookResponse(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book = bookRepo.findBookByTitle(addBookRequest.getTitle());
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setTitle(book.getTitle());
        bookRepo.save(book);
        return addBookResponse;
    }

    @Override
    public RemoveBookResponse removeBookResponse(RemoveBookRequest removeBookRequest) {
        Book book = bookRepo.findBookByTitle(removeBookRequest.getTitle());
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();
        removeBookResponse.setMessage("Removed successfully");
        bookRepo.delete(book);
        return removeBookResponse;
    }

    @Override
    public BorrowBookResponse borrowBookResponse(BorrowBookRequest borrowBookRequest) {
        List<Book> bookList = bookRepo.findAll();
        for(Book book: bookList){
            if(book.getTitle().equalsIgnoreCase(borrowBookRequest.getTitle())){
                BorrowBookResponse borrowBookResponse = new BorrowBookResponse();
                borrowBookResponse.setMessage("Book Borrowed");
                return borrowBookResponse;
            }
        }
        throw new IllegalArgumentException("Book not available");
    }

    @Override
    public ReturnBookResponse returnBookResponse(ReturnBookRequest returnBookRequest) {
        Book book = new Book();
        if(book.isBorrowed()){
            ReturnBookResponse returnBookResponse = new ReturnBookResponse();
            returnBookResponse.setMessage("Book Returned");
            return returnBookResponse;
        }
        throw new YouHaveToBorrowException("Please Borrow First");
    }
}
