package com.yong.data.list.node;

import lombok.Data;

/**
 * Created by ChangYong on 2016. 10. 30..
 */
@Data
public class YongDoublyNode<T> implements YongNode<T> {
    private T data;
    private YongDoublyNode<T> previous;
    private YongDoublyNode<T> next;

    public YongDoublyNode(){}
    public YongDoublyNode(T data, YongDoublyNode<T> previous, YongDoublyNode<T> next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U extends YongNode<T>> void setNext(U node) {
        this.next = (YongDoublyNode<T>) node;
    }

    public boolean hasPrevious(){
        return this.previous != null;
    }
}
