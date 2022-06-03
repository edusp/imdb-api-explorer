package com.au.code.dto;

import java.util.List;

public record MovieRecord(List<ItemRecord> items, String errorMessage) {
}
