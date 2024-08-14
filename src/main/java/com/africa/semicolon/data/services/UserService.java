package com.africa.semicolon.data.services;

import com.africa.semicolon.data.dtos.request.*;
import com.africa.semicolon.data.dtos.response.*;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    UserLogInResponse login(LoginRequest loginRequest);
    UserLogOutResponse userLogIOutResponse(UserLogOutRequest userLogOutRequest);
    BuyBookResponse userBuysBook(BuyBookRequest borrowBookRequest);
    DownloadBookResponse userDownloadBook(DownloadBookRequest returnBookRequest);
}
