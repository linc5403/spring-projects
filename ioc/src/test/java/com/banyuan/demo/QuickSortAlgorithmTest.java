package com.banyuan.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortAlgorithmTest {

    @Test
    void sort1() {
        QuickSortAlgorithm quickSortAlgorithm = new QuickSortAlgorithm();
        assertArrayEquals(new int []{1, 2, 3}, quickSortAlgorithm.sort(new int [] {3, 2, 1}));
    }
}