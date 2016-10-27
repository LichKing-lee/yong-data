package com.yong.data.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ChangYong on 2016. 10. 20..
 */
public class MyArrayList<T> implements MyList<T>, Cloneable {
    private int size;
    private int capacity;
    private Object[] arr;
    private final static int DEFAULT_CAPACITY = 10;
    private final static String EMPTY_TOSTRING = "[]";

    {
        this.size = 0;
    }

    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity){
        this.capacity = capacity;
        this.arr = new Object[this.capacity];
    }

    public MyArrayList(T[] t){
        this.arr = t.clone();
        this.size = t.length;
        this.capacity = t.length;
    }

    public static <T> MyList<T> immutableMyList(T... t){
        return new ImmutableMyArrayList<>(t);
    }

    public static <T> MyList<T> mutableMyList(T... t){
        return new MyArrayList<>(t);
    }

    public static <T> MyList<T> from(List<T> list){
        MyList<T> myList = new MyArrayList<>(list.size());

        for (T t : list) {
            myList.add(t);
        }

        return myList;
    }

    @Override
    public T add(T t) {
        if(this.size >= this.capacity){
            this.capacity *= 2;
            Object[] temp = new Object[this.capacity];
            System.arraycopy(this.arr, 0, temp, 0, this.arr.length);
            this.arr = temp;
        }

        this.arr[size++] = t;

        return t;
    }

    @Override
    public T set(int idx, T t) {
        checkValidIndex(idx);

        @SuppressWarnings("unchecked")
        T result = (T)this.arr[idx];
        this.arr[idx] = t;

        return result;
    }

    @Override
    public T get(int idx) {
        checkValidIndex(idx);

        @SuppressWarnings("unchecked")
        T result = (T)this.arr[idx];

        return result;
    }

    @Override
    public T remove(int idx) {
        checkValidIndex(idx);

        @SuppressWarnings("unchecked")
        T result = (T)this.arr[idx];
        Object[] temp = new Object[this.capacity];
        System.arraycopy(this.arr, 0, temp, 0, idx);
        System.arraycopy(this.arr, idx + 1, temp, idx, this.arr.length - idx - 1);
        this.size--;
        this.arr = temp;

        return result;
    }

    @Override
    public boolean contains(T t) {
        for(Object obj : this.arr){
            if(t.equals(obj)){
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size < 1;
    }

    @Override
    public MyList<T> sort(){
        Object[] temp = new Object[this.size];
        System.arraycopy(this.arr, 0, temp, 0, this.size);
        Arrays.sort(temp);
        @SuppressWarnings("unchecked")
        MyList<T> result = mutableMyList((T[])temp);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MyList<T> sort(Comparator<? super T> comparator){
        Object[] temp = new Object[this.size];
        System.arraycopy(this.arr, 0, temp, 0, this.size);
        Arrays.sort((T[])temp, comparator);
        MyList<T> result = mutableMyList((T[])temp);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayMyIterator<>();
    }

    private void checkValidIndex(int idx){
        if(idx < 0 || idx >= this.size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString(){
        if(this.isEmpty()){
            return EMPTY_TOSTRING;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i < this.size; i++){
            builder.append(this.arr[i]).append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), "]");
        return builder.toString();
    }

    @Override
    public MyList<T> clone(){
        try {
            @SuppressWarnings("unchecked")
            MyList<T> result = (MyList<T>) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static class ImmutableMyArrayList<T> extends MyArrayList<T>{
        private ImmutableMyArrayList(T[] t){
            super(t);
        }

        @Override
        public T add(T t){
            throw new UnsupportedOperationException();
        }

        @Override
        public T set(int idx, T t){
            throw new UnsupportedOperationException();
        }

        @Override
        public T remove(int idx){
            throw new UnsupportedOperationException();
        }
    }

    private class MyArrayMyIterator<T> implements Iterator<T> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return this.cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T)arr[this.cursor++];
        }
    }
}
