package com.barath.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortenResponse {

    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private String expiresAt;
}