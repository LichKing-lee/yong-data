package com.yong.data.list.node;

/**
 * Created by ChangYong on 2016. 10. 30..
 */
public interface YongNode<T> {
    <R extends YongNode<T>> R getNext();
    <U extends YongNode<T>> void setNext(U node);
    T getData();
    void setData(T t);

    default boolean hasNext(){
        return this.getNext() != null;
    }
}
