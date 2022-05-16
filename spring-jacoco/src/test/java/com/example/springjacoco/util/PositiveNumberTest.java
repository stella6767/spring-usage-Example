package com.example.springjacoco.util;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



class PositiveNumberTest {


    @Test
    public void notPositiveValueThrowException() {
        long notPositive = 0;

        Assertions.assertThatThrownBy(() -> new PositiveNumber(notPositive))
                .isInstanceOf(IllegalArgumentException.class);
    }


}