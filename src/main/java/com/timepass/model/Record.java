package com.timepass.model;

import lombok.Builder;
import lombok.Getter;


@Getter
public class Record <T> {

    private final T data;
    private final Boolean isValid;
    private final String error;

    @Builder
    public Record(final T data,String error) {
        this.data       = data;
        this.error      = error;
        this.isValid    = error.isEmpty();
    }
}
