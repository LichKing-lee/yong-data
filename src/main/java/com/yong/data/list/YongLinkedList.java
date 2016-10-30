package com.yong.data.list;

import com.yong.data.list.node.YongNode;
import com.yong.data.list.node.YongSimpleNode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.UnaryOperator;


/**
 * Created by ChangYong on 2016. 10. 27..
 */
public class YongLinkedList<T> implements YongList<T>, Cloneable {
    private int size;
    private YongNode<T> head;

    public YongLinkedList(){
        this.size = 0;
        this.head = new YongSimpleNode<>();
    }

    @Override
    public T add(T t) {
        YongSimpleNode<T> newYongSimpleNode = new YongSimpleNode<>(t, null);
        LinkedListHelper.getLastNode(this.size, this.head).setNext(newYongSimpleNode);
        this.size++;

        return t;
    }

    @Override
    public T set(int idx, T t) {
        LinkedListHelper.checkVaildIndex(idx, this.size);

        LinkedListHelper.getNode(idx, this.head).setData(t);
        return t;
    }

    @Override
    public T get(int idx) {
        LinkedListHelper.checkVaildIndex(idx, this.size);

        return LinkedListHelper.getNode(idx, this.head).getData();
    }

    @Override
    public T remove(int idx) {
        T t = LinkedListHelper.remove(idx, this.size, this.head);

        this.size--;
        return t;
    }

    @Override
    public boolean contains(T t) {
        return LinkedListHelper.contains(t, this.head);
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
    public YongList<T> sort() {
        return LinkedListHelper.sort(new YongLinkedList<>(), this.head, YongList::sort);
    }

    @Override
    public YongList<T> sort(Comparator<? super T> comparator) {
        return LinkedListHelper.sort(new YongLinkedList<>(), this.head, list -> list.sort(comparator));
    }

    @Override
    public YongList<T> clone() {
        return LinkedListHelper.clone(new YongLinkedList<>(), this.head);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedIterator();
    }

    @Override
    public String toString(){
        return LinkedListHelper.toString(this.head);
    }

    private class MyLinkedIterator implements Iterator<T>{
        private YongNode<T> node;

        @SuppressWarnings("unchecked")
        MyLinkedIterator(){
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return this.node.hasNext();
        }

        @Override
        public T next() {
            T result = this.node.getNext().getData();
            this.node = this.node.getNext();
            return result;
        }
    }
}
