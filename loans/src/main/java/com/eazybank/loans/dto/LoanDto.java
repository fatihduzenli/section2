package com.eazybank.loans.dto;

import lombok.Data;

@Data
public class LoanDto {

    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private int totalLoanAmount;
    private int amountPaid;
    private int outstandingAmount;
}
