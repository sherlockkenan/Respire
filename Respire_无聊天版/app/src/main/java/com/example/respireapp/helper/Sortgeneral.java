package com.example.respireapp.helper;

/**
 * Created by piglet on 2016/7/20.
 */
import java.util.Comparator;
import com.example.respireapp.Entity.Place;
public class Sortgeneral implements Comparator{
    @Override
    public int compare(Object lhs, Object rhs) {
        Place a = (Place) lhs;
        Place b = (Place) rhs;
        double gap=b.getGeneral() - a.getGeneral();
        if(gap>0)return 1;
        if(Math.abs(gap-0)<0.0000000000001)return 0;
        else
            return -1;
    }

}
