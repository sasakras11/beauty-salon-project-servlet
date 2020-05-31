package com.salon.service.util;

import java.util.Optional;

public class DataParser {

    public Optional<Integer> parseInt(String val){
        try {
            return Optional.of(Integer.parseInt(val));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
