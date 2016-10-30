package com.yong.data.list.node;

/**
 * Created by ChangYong on 2016. 10. 29..
 */
public class YongSimpleNode<T> implements YongNode<T> {
    private T data;
    private YongSimpleNode<T> next;

    public YongSimpleNode(){}

    public YongSimpleNode(T data, YongSimpleNode<T> next){
        this.data = data;
        this.next = next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends YongNode<T>> R getNext(){
        return (R)this.next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U extends YongNode<T>> void setNext(U node) {
        this.next = (YongSimpleNode<T>) node;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T t){
        this.data = t;
    }

    public boolean hasNext(){
        return this.getNext() != null;
    }
}
