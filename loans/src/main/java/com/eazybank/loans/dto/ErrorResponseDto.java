package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Holds Error Response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "API path where the error occurred",
            example = "/api/create"
    )
    private String apiPath;
    @Schema(
            description = "Status code of the error response",
            example = "400"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message that occurred",
            example = "Invalid request parameters"
    )
    private String errorMessage;
    @Schema(
            description = "Timestamp when the error occurred",
            example = "2023-01-01T12:00:00"
    )
    private LocalDateTime errorTime;
}
