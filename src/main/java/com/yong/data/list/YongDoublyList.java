package com.yong.data.list;

import com.yong.data.list.node.YongDoublyNode;
import com.yong.data.list.node.YongNode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * Created by ChangYong on 2016. 10. 30..
 */
public class YongDoublyList<T> implements YongList<T>, Cloneable {
    private YongDoublyNode<T> head;
    private int size;

    public YongDoublyList(){
        this.size = 0;
        this.head = new YongDoublyNode<>();
    }

    @Override
    public T add(T t) {
        YongDoublyNode<T> lastNode = LinkedListHelper.getLastNode(this.size, this.head);
        YongDoublyNode<T> node = new YongDoublyNode<>(t, lastNode, null);
        lastNode.setNext(node);
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
        return LinkedListHelper.sort(new YongDoublyList<>(), this.head, YongList::sort);
    }

    @Override
    public YongList<T> sort(Comparator<? super T> comparator) {
        return LinkedListHelper.sort(new YongDoublyList<>(), this.head, list -> list.sort(comparator));
    }

    @Override
    public YongList<T> clone() {
        return LinkedListHelper.clone(new YongDoublyList<>(), this.head);
    }

    @Override
    public String toString(){
        return LinkedListHelper.toString(this.head);
    }

    @Override
    public Iterator<T> iterator() {
        return new YongDoublyIterator();
    }

    public ListIterator<T> listIterator(){
        return new YongDoublyIterator();
    }

    private class YongDoublyIterator implements ListIterator<T>{
        private int cursor;
        private YongDoublyNode<T> node;

        public YongDoublyIterator(){
            this.cursor = -1;
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return this.node.hasNext();
        }

        @Override
        public T next() {
            T t = this.node.getNext().getData();
            this.node = this.node.getNext();
            this.cursor++;
            return t;
        }

        @Override
        public boolean hasPrevious() {
            return this.node.hasPrevious();
        }

        @Override
        public T previous() {
            T t = this.node.getData();
            this.node = this.node.getPrevious();
            this.cursor--;
            return t;
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}