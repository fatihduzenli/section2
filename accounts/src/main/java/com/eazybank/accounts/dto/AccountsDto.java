package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Schema(
        name = "Accounts",
        description = "Holds Account information"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Account number of EasyBank account"
    )
    @NotEmpty(message = "Account holder name cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account holder name should be 10 digits")
    private Long accountNumber;
    @Schema(
            description = "Account type of EasyBank account"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;
    @Schema(
            description = "Branch name of EasyBank account",
            example = "main Street"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
