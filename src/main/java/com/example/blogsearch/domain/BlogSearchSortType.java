package com.example.blogsearch.domain;

import com.example.blogsearch.adapter.in.web.exception.ErrorCode;
import com.example.blogsearch.adapter.in.web.exception.ValidationException;

public enum BlogSearchSortType {
    ACCURACY,
    RECENCY;
    public static BlogSearchSortType of(String name) {
        try {
            return valueOf(name);
        }catch (IllegalArgumentException e) {
            throw new ValidationException(ErrorCode.BAD_REQUEST, e.getMessage());
        }
    }
}
