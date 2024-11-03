package com.eazybank.loans.mapper;

import com.eazybank.loans.dto.LoanDto;
import com.eazybank.loans.entity.Loans;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public static LoanDto mapToLoanDto(Loans loans, LoanDto loansDto) {
        // TODO: Implement mapping logic to map Loans entity to LoansDto
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoanAmount(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }


    public static Loans mapToLoanEntity(LoanDto loansDto, Loans loans) {
        loans.setTotalLoan(loansDto.getTotalLoanAmount());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setLoanNumber(loansDto.getLoanNumber());

        return loans;
    }
}
