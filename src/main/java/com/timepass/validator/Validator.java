package com.timepass.validator;

import com.timepass.model.Record;

public interface Validator<T> {

    Record<T> validate(T data);
}
