package com.timepass.response;

import com.timepass.model.Record;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorResponse<T> {

    private final List<Record<T>> records;

    @Builder
    public ValidatorResponse(final List<Record<T>> records) {
        this.records = records;
    }

    public List<Record<T>> successfulRecords() {
        return records.stream()
                .filter(Record::getIsValid)
                .collect(Collectors.toList());
    }

    public List<Record<T>> failureRecords() {
        return records.stream()
                .filter(record -> !record.getIsValid())
                .collect(Collectors.toList());
    }

    public List<Record<T>> records() {
        return records;
    }
}
