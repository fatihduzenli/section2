package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(name = "Loans",
        description = "Schema to hold Loan information"
)
@Data
public class LoanDto {

    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    @NotEmpty(message = "Customer name cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{12}$", message = "Loan number should be 12 digits")
    @Schema(
            description = "Loan Number of the customer", example = "548732457654"
    )
    private String loanNumber;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Loan type should contain only alphabets")
    @NotEmpty(message = "Loan type cannot be empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;


    @Positive(message = "Loan amount should be greater than 0")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
    private int totalLoanAmount;

    @PositiveOrZero(message = "Amount paid should be greater than or equal to 0")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount should be greater than or equal to 0")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outstandingAmount;
}
