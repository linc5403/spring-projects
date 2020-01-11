package com.banyuan.ioc.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSortAlgorithm implements SortAlgorithm{
    @Override
    public int[] sort(int[] nums) {
        return new int[0];
    }
}
