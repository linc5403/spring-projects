package com.banyuan.ioc.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {
    @Qualifier("quick")
    @Autowired
    private SortAlgorithm sortAlgorithm;

/*
    public BinarySearchImpl(@Qualifier("quick") SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
*/

    /*    public BinarySearchImpl(@Qualifier("quick") SortAlgorithm sortAlgorithm) {
        //public BinarySearchImpl(@Qualifier("quickSort") SortAlgorithm sortAlgorithm) {
            this.sortAlgorithm = sortAlgorithm;
        }*/
    public int search(int [] nums, int target) {
        // sort
        int [] sortedNums = sortAlgorithm.sort(nums);
        // search
        // return
        System.out.println(sortAlgorithm);
        return 3;
    }
}
