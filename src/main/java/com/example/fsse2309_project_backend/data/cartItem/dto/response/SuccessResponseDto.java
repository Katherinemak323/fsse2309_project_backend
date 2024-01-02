package com.example.fsse2309_project_backend.data.cartItem.dto.response;

public class SuccessResponseDto{
    private String result;

    public SuccessResponseDto(){
        setResult("success");
    }

    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result =  "success";;
    }
}

