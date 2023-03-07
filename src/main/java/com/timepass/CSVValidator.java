package com.timepass;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.timepass.model.Record;
import com.timepass.response.ValidatorResponse;
import com.timepass.validator.Validator;
import lombok.Builder;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class CSVValidator<T>
{

    private final byte[] file;

    private final Validator<T> validator;

    private final Class<T> modelClass;

    public ValidatorResponse<T> parseAndValidate() {

       return ValidatorResponse.<T>builder()
               .records(validate(parse()))
               .build();
    }

    private List<Record<T>> validate(List<T> dataList) {

        return dataList.stream()
                .map(validator::validate)
                .collect(Collectors.toList());

    }

    private List<T> parse() {

        HeaderColumnNameMappingStrategy<T> headerColumnNameMappingStrategy =
            new HeaderColumnNameMappingStrategy<>();
        headerColumnNameMappingStrategy.setType(modelClass);

        CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(file)));


        CsvToBean<T> csvToBean = new CsvToBean<>();
        csvToBean.setCsvReader(csvReader);
        csvToBean.setMappingStrategy(headerColumnNameMappingStrategy);

        return csvToBean.parse();
    }

}
