package com.fdm.pmsuibackend.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountCreationDto {
    @NotBlank(message = "Account name is mandatory")
    private String accountName;

    @NotBlank(message = "Department name is mandatory")
    private String departmentName;
}
