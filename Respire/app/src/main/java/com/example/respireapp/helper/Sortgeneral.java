package com.example.respireapp.helper;

/**
 * Created by piglet on 2016/7/20.
 */

import com.example.respireapp.Entity.Place;

import java.util.Comparator;
public class Sortgeneral implements Comparator{
    @Override
    public int compare(Object lhs, Object rhs) {
        Place a = (Place) lhs;
        Place b = (Place) rhs;
        double gap=a.getGeneral() - b.getGeneral();
        int r=(int)gap;
        return r;
    }

}
