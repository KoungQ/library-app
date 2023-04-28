package com.group.libraryapp.dto.calculator.request;

public class CalculatorMultiplyRequest {

    // @RequestBody를 사용하면 생성자를 굳이 만들지 않아도 됨
//    public CalculatorMultiplyRequest(int number1, int number2) {
//        this.number1 = number1;
//        this.number2 = number2;
//    }

    private int number1;
    private int number2;

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
