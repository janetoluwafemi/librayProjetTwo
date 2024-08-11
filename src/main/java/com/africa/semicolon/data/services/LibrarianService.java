package com.africa.semicolon.data.services;
import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;

public interface LibrarianService {
    AddBookResponse addBook(AddBookRequest addBookRequest);
    RemoveBookResponse removeBook(RemoveBookRequest removeBookRequest);
    SearchForBookResponse searchFoBook(SearchForBookRequest searchForBookRequest);
    VerifyMemberResponse verifyMember(VerifyMemberRequest verifyMemberRequest);
    IssueBookResponse issueBook(IssueBookRequest issueBookRequest);
    CalculateFineResponse calculateFine(CalculateFineRequest calculateFineRequest);
    CreateBillResponse createBillResponse(CreateBillRequest createBillRequest);
}
