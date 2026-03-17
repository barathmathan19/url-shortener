package com.barath.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShortenRequest {

    @NotBlank(message = "URL cannot be blank")
    @Pattern(
            regexp = "^(https?://).*",
            message = "URL must start with http:// or https://"
    )
    private String originalUrl;

    private Integer expiryDays = 30;
}