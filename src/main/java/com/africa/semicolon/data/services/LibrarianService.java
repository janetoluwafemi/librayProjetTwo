package com.africa.semicolon.data.services;
import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;

public interface LibrarianService {
    AddBookResponse addBookToLiberian(AddBookRequest addBookReques);
    RemoveBookResponse userRemoveBook(RemoveBookRequest removeBookRequest);
    SearchForBookResponse searchForBook(SearchForBookRequest searchForBookRequest);
    VerifyMemberResponse verifyMember(VerifyMemberRequest verifyMemberRequest);
    IssueBookResponse issueBook(IssueBookRequest issueBookRequest);
    CalculateFineResponse calculateFine(CalculateFineRequest calculateFineRequest);
    CreateBillResponse createBillResponse(CreateBillRequest createBillRequest);
}
