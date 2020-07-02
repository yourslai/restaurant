package com.fjnu.restaurant.error;

public interface CommonError {

    int getErrorCode();

    String getErrorMessage();

    CommonError setErrorMessage(String msg);

}
