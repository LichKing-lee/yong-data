package com.yong.data.list;

import com.yong.data.node.Node;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * Created by ChangYong on 2016. 10. 27..
 */
public class MyLinkedList<T> implements MyList<T>, Cloneable {
    private static final String EMPTY_TOSTRING = "[]";
    private int size;
    private Node<T> head;

    {
        this.size = 0;
        this.head = new Node<>();
    }

    public MyLinkedList(){

    }

    @Override
    public T add(T t) {
        Node<T> newNode = new Node<>(t, null);
        this.getLastNode().setNext(newNode);
        this.size++;

        return t;
    }

    @Override
    public T set(int idx, T t) {
        this.checkVaildIndex(idx);

        this.getNode(idx).setData(t);
        return t;
    }

    @Override
    public T get(int idx) {
        this.checkVaildIndex(idx);

        return this.getNode(idx).getData();
    }

    @Override
    public T remove(int idx) {
        checkVaildIndex(idx);

        Node<T> node = this.getNode(idx - 1);
        T t = node.getNext().getData();

        Node<T> nextNode = Optional.of(node)
                .map(Node::getNext)
                .map(Node::getNext)
                .orElse(null);

        node.setNext(nextNode);

        this.size--;
        return t;
    }

    @Override
    public boolean contains(T t) {
        Node<T> node = this.head;

        while(node.hasNext()){
            if(node.getNext().getData().equals(t)){
                return true;
            }

            node = node.getNext();
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return !this.head.hasNext();
    }

    @Override
    public MyList<T> sort() {
        return sortHelper(MyList::sort);
    }

    @Override
    public MyList<T> sort(Comparator<? super T> comparator) {
        return sortHelper(list -> list.sort(comparator));
    }

    private MyList<T> sortHelper(UnaryOperator<MyList<T>> operator){
        MyList<T> result = new MyLinkedList<>();
        MyList<T> temp = new MyArrayList<>();

        Node<T> node = this.head;
        while(node.hasNext()){
            node = node.getNext();
            temp.add(node.getData());
        }
        MyList<T> sortList = operator.apply(temp);

        for(T t : sortList){
            result.add(t);
        }

        return result;
    }

    @Override
    public MyList<T> clone() {
        MyList<T> resultList = new MyLinkedList<>();

        Node<T> node = this.head;
        while(node.hasNext()){
            node = node.getNext();
            resultList.add(node.getData());
        }

        return resultList;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedIterator();
    }

    @Override
    public String toString(){
        if(this.isEmpty()){
            return EMPTY_TOSTRING;
        }

        Node<T> node = this.head;
        StringBuilder builder = new StringBuilder("[");
        while(node.hasNext()){
            node = node.getNext();
            builder.append(node.getData());

            if(node.hasNext()){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void checkVaildIndex(int idx){
        if(idx < 0 || idx >= this.size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private Node<T> getLastNode(){
        return this.getNode(this.size - 1);
    }

    private Node<T> getNode(int destinyIdx){
        Node<T> node = this.head;

        for(int i = 0; i <= destinyIdx; i++){
            node = node.getNext();
        }

        return node;
    }

    private class MyLinkedIterator implements Iterator<T>{
        private int cursor;
        private T[] t;

        @SuppressWarnings("unchecked")
        MyLinkedIterator(){
            this.t = (T[])new Object[size];

            Node<T> node = head;
            for(int i = 0; node.hasNext(); i++){
                node = node.getNext();
                t[i] = node.getData();
            }
        }

        @Override
        public boolean hasNext() {
            return this.cursor < size;
        }

        @Override
        public T next() {
            return this.t[this.cursor++];
        }
    }
}
