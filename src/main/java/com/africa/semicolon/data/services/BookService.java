package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.AddBookRequest;
import com.africa.semicolon.data.dtos.request.BuyBookRequest;
import com.africa.semicolon.data.dtos.request.DownloadBookRequest;
import com.africa.semicolon.data.dtos.response.AddBookResponse;
import com.africa.semicolon.data.dtos.response.BuyBookResponse;
import com.africa.semicolon.data.dtos.response.RemoveBookResponse;
import com.africa.semicolon.data.dtos.response.DownloadBookResponse;
import com.africa.semicolon.data.dtos.request.RemoveBookRequest;
import com.africa.semicolon.data.dtos.request.FindBookRequest;
import com.africa.semicolon.data.dtos.response.FindBookResponse;

public interface BookService {
    FindBookResponse book(FindBookRequest findBookRequest);
    AddBookResponse addBook(AddBookRequest addBookRequest);
    RemoveBookResponse removeBookResponse(RemoveBookRequest removeBookRequest);
    BuyBookResponse buyBookResponse(BuyBookRequest buyBookRequest);
    DownloadBookResponse downloadBookResponse(DownloadBookRequest downloadBookRequest);

}
