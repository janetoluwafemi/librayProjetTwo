package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;
import com.africa.semicolon.data.exceptions.YouHaveToBorrowException;
import com.africa.semicolon.data.models.Book;
import com.africa.semicolon.data.repositories.BookRepo;
import com.africa.semicolon.data.repositories.LiberianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private LiberianRepo liberianRepo;
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
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        bookRepo.save(book);
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setTitle(book.getTitle());
        addBookResponse.setMessage("Book Added Successfully");
        return addBookResponse;
    }

    @Override
    public RemoveBookResponse removeBookResponse(RemoveBookRequest removeBookRequest) {
        Book book = bookRepo.findBookByTitle(removeBookRequest.getTitle());
        book.setTitle(removeBookRequest.getTitle());
        book.setAuthor(removeBookRequest.getAuthor());
        bookRepo.delete(book);
        RemoveBookResponse removeBookResponse = new RemoveBookResponse();
        removeBookResponse.setMessage("Removed successfully");
        return removeBookResponse;
    }

    @Override
    public BuyBookResponse buyBookResponse(BuyBookRequest buyBookRequest) {
        Book book = bookRepo.findBookByTitle(buyBookRequest.getTitle());
        BuyBookResponse buyBookResponse = new BuyBookResponse();
        buyBookResponse.setTitle(book.getTitle());
        buyBookResponse.setAuthor(book.getAuthor());
        buyBookResponse.setId(book.getId());
        buyBookResponse.setMessage("Book Bought Successful");
        return buyBookResponse;
    }

    @Override
    public DownloadBookResponse downloadBookResponse(DownloadBookRequest downloadBookRequest) {
        Book book = new Book();
        if(book.isBought()){
            book.setTitle(downloadBookRequest.getTitle());
            book.setAuthor(downloadBookRequest.getAuthor());
            bookRepo.save(book);
            DownloadBookResponse returnBookResponse = new DownloadBookResponse();
            returnBookResponse.setMessage("Book Returned");
            return returnBookResponse;
        }
        throw new YouHaveToBorrowException("Please Buy Book First");
    }
}
