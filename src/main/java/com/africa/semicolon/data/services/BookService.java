package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.AddBookRequest;
import com.africa.semicolon.data.dtos.request.BorrowBookRequest;
import com.africa.semicolon.data.dtos.request.ReturnBookRequest;
import com.africa.semicolon.data.dtos.response.AddBookResponse;
import com.africa.semicolon.data.dtos.response.BorrowBookResponse;
import com.africa.semicolon.data.dtos.response.RemoveBookResponse;
import com.africa.semicolon.data.dtos.response.ReturnBookResponse;
import com.africa.semicolon.data.dtos.request.RemoveBookRequest;
import com.africa.semicolon.data.dtos.request.FindBookRequest;
import com.africa.semicolon.data.dtos.response.FindBookResponse;

public interface BookService {
    FindBookResponse book(FindBookRequest findBookRequest);
    AddBookResponse addBookResponse(AddBookRequest addBookRequest);
    RemoveBookResponse removeBookResponse(RemoveBookRequest removeBookRequest);
    BorrowBookResponse borrowBookResponse(BorrowBookRequest borrowBookRequest);
    ReturnBookResponse returnBookResponse(ReturnBookRequest returnBookRequest);

}
