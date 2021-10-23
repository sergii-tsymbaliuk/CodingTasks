package net.tsymbaliuk.educativeio;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RotateArrayTest {

    @Test
    public void rotateArray() {
        List<Integer>arr = Arrays.asList(1, 10, 20, 0, 59, 86, 32, 11, 9, 40);
        RotateArray.rotateArray(arr, 3);
        assertThat(arr, is(List.of(11, 9, 40, 1, 10, 20, 0, 59, 86, 32)));
    }
}