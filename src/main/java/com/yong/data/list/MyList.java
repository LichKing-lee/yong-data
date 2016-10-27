package com.yong.data.list;

import java.util.Comparator;

/**
 * Created by ChangYong on 2016. 10. 20..
 */
interface MyList<T> extends Iterable<T> {
    T add(T t);
    T set(int idx, T t);
    T get(int idx);
    T remove(int idx);
    boolean contains(T t);
    int size();
    boolean isEmpty();
    MyList<T> sort();
    MyList<T> sort(Comparator<? super T> comparator);
    MyList<T> clone();
}
