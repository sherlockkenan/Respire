package com.example.respireapp.helper;

/**
 * Created by piglet on 2016/7/20.
 */

import com.example.respireapp.Entity.Place;

import java.util.Comparator;
public class Sortenvironment implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        Place a = (Place) lhs;
        Place b = (Place) rhs;
       return (a.getPm25()-b.getPm25());
    }
}
