package com.au.code.dto;

public record ItemRecord(String id,
                         Integer rank,
                         String title,
                         String fullTitle,
                         Integer year,
                         String image,
                         String crew,
                         Double imDbRating,
                         Long imDbRatingCount) {}
