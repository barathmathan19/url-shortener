package com.barath.urlshortener.service;

import com.barath.urlshortener.dto.ShortenRequest;
import com.barath.urlshortener.dto.ShortenResponse;
import com.barath.urlshortener.entity.UrlMapping;
import com.barath.urlshortener.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final UrlMappingRepository repository;
    private static final String BASE_URL = "http://localhost:8080/";

    public ShortenResponse shortenUrl(ShortenRequest request) {
        String shortCode = generateUniqueShortCode();

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(request.getOriginalUrl());
        mapping.setShortCode(shortCode);
        mapping.setCreatedAt(LocalDateTime.now());
        mapping.setExpiresAt(LocalDateTime.now().plusDays(request.getExpiryDays()));
        mapping.setClickCount(0L);

        repository.save(mapping);

        return new ShortenResponse(
                mapping.getOriginalUrl(),
                mapping.getShortCode(),
                BASE_URL + mapping.getShortCode(),
                mapping.getExpiresAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode = Base62Encoder.encode();
        } while (repository.existsByShortCode(shortCode));
        return shortCode;
    }
}