package com.yong.data.node;

/**
 * Created by ChangYong on 2016. 10. 29..
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(){}

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public void setNext(Node<T> next){
        this.next = next;
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
