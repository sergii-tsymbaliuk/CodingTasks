package net.tsymbaliuk.educativeio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SearchRotatedTest {
    private static final int[] arr1 = {4, 5, 6, 1, 2, 3};

    int[] arr;
    int key;
    int expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {arr1, 3, 5},
                {arr1, 6, 2},
                {arr1, 4, 0},
                {arr1, 1, 3},
                {arr1, 7, -1},
                {arr1, 0, -1}
        });
    }

    public SearchRotatedTest(int[] arr, int key, int expected) {
        this.arr = arr;
        this.key = key;
        this.expected = expected;
    }

    @Test
    public void binarySearchRotated(){
        int actual = SearchRotated.binarySearchRotated(arr, key);
        assertThat(actual, is(expected));
    }

    @Test
    public void etalonBinarySearchRotated(){
        int actual = SearchRotated.etalonSearch(arr, key);
        assertThat(actual, is(expected));
    }
}