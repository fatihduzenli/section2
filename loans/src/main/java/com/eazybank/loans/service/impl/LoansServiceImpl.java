package com.eazybank.loans.service.impl;

import com.eazybank.loans.constants.LoansConstants;
import com.eazybank.loans.dto.LoanDto;
import com.eazybank.loans.entity.Loans;
import com.eazybank.loans.exceptions.ResourceNotFoundException;
import com.eazybank.loans.mapper.LoanMapper;
import com.eazybank.loans.repository.LoansRepository;
import com.eazybank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor

public class LoansServiceImpl implements ILoansService {

    private  LoansRepository loansRepository;



    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans= loansRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()){
            throw new RuntimeException("Loan already exists for the given mobile number "+mobileNumber);
        }

        loansRepository.save(createNewLoans(mobileNumber));
    }

    private Loans createNewLoans(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number ", mobileNumber)
        );

        return LoanMapper.mapToLoanDto(loans, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {

     Loans loans= loansRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "loan number", loanDto.getLoanNumber()));
     LoanMapper.mapToLoanEntity(loanDto,loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans= loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number ", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
