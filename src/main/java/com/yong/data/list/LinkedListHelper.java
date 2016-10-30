package com.yong.data.list;

import com.yong.data.list.node.YongNode;

import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * Created by ChangYong on 2016. 10. 30..
 */
class LinkedListHelper {
    private static final String EMPTY_TOSTRING = "[]";

    static void checkVaildIndex(int idx, int size){
        if(idx < 0 || idx >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    static <T, U extends YongNode<T>> U getLastNode(int size, U head){
        return getNode(size - 1, head);
    }

    static <T, U extends YongNode<T>> U getNode(int destinyIdx, U head){
        U yongNode = head;

        for(int i = 0; i <= destinyIdx; i++){
            yongNode = yongNode.getNext();
        }

        return yongNode;
    }

    static <T, U extends YongNode<T>> T remove(int idx, int size, U head){
        checkVaildIndex(idx, size);

        U yongNode = getNode(idx - 1, head);
        T t = yongNode.getNext().getData();

        @SuppressWarnings("unchecked")
        U nextYongSimpleNode = (U) Optional.of(yongNode)
                .map(YongNode::getNext)
                .map(node -> ((U)node).getNext())
                .orElse(null);

        yongNode.setNext(nextYongSimpleNode);

        return t;
    }

    static <T> YongList<T> sort(YongList<T> result, YongNode<T> head, UnaryOperator<YongList<T>> operator){
        YongList<T> temp = new YongArrayList<>();

        YongNode<T> yongNode = head;
        while(yongNode.hasNext()){
            yongNode = yongNode.getNext();
            temp.add(yongNode.getData());
        }
        YongList<T> sortList = operator.apply(temp);

        for(T t : sortList){
            result.add(t);
        }

        return result;
    }

    static <T> boolean contains(T t, YongNode<T> head){
        YongNode<T> yongNode = head;

        while(yongNode.hasNext()){
            if(yongNode.getNext().getData().equals(t)){
                return true;
            }

            yongNode = yongNode.getNext();
        }

        return false;
    }

    static <T> YongList<T> clone(YongList<T> t, YongNode<T> head){
        YongNode<T> yongNode = head;
        while(yongNode.hasNext()){
            yongNode = yongNode.getNext();
            t.add(yongNode.getData());
        }

        return t;
    }

    static <T> String toString(YongNode<T> head){
        if(!head.hasNext()){
            return EMPTY_TOSTRING;
        }

        YongNode<T> yongNode = head;
        StringBuilder builder = new StringBuilder("[");
        while(yongNode.hasNext()){
            yongNode = yongNode.getNext();
            builder.append(yongNode.getData());

            if(yongNode.hasNext()){
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }
}
