package com.yong.data.list;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by ChangYong on 2016. 10. 29..
 */
public class MyLinkedListTest {
    private MyList<String> myList;

    @Before
    public void setUp(){
        myList = new MyLinkedList<>();

        myList.add("a");
        myList.add("b");
        myList.add("c");
    }

    @Test
    public void size_test(){
        assertThat(myList.size(), is(3));
    }

    @Test
    public void toString_empty_test(){
        MyList<String> strings = new MyLinkedList<>();
        assertThat(strings.toString(), is("[]"));
    }

    @Test
    public void toString_test(){
        assertThat(myList.toString(), is("[a, b, c]"));
    }

    @Test
    public void get_test(){
        //then
        assertThat(this.myList.get(0), is("a"));
        assertThat(this.myList.get(1), is("b"));
        assertThat(this.myList.get(2), is("c"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayException_test(){
        myList.set(10, "hello");
        myList.get(10);
        myList.remove(10);
    }

    @Test
    public void set_test(){
        myList.set(1, "hello");

        assertThat(myList.size(), is(3));
        assertThat(myList.get(1), is("hello"));
        assertThat(myList.toString(), is("[a, hello, c]"));
    }

    @Test
    public void remove_last_node_test(){
        String str = myList.remove(2);

        assertThat(myList.size(), is(2));
        assertThat(str, is("c"));
        assertThat(myList.toString(), is("[a, b]"));
    }

    @Test
    public void remove_test(){
        String str = myList.remove(1);

        assertThat(myList.size(), is(2));
        assertThat(str, is("b"));
        assertThat(myList.toString(), is("[a, c]"));
    }

    @Test
    public void remove_first_node_test(){
        String str = myList.remove(0);

        assertThat(myList.size(), is(2));
        assertThat(str, is("a"));
        assertThat(myList.toString(), is("[b, c]"));
    }

    @Test
    public void contains_test(){
        assertTrue(myList.contains("a"));
        assertTrue(!myList.contains("t"));
    }

    @Test
    public void clone_test(){
        MyList<String> cloneList = myList.clone();

        assertThat(myList.size(), is(cloneList.size()));
        assertThat(myList.toString(), is(cloneList.toString()));
    }

    @Test
    public void sort_comparable_test(){
        myList.add("c");
        myList.add("b");
        myList.add("a");

        MyList<String> sortList = myList.sort();

        assertThat(sortList.toString(), is("[a, a, b, b, c, c]"));
    }

    @Test
    public void sort_comparator_test(){
        myList.add("c");
        myList.add("b");
        myList.add("a");

        MyList<String> sortList = myList.sort(String::compareTo);

        assertThat(sortList.toString(), is("[a, a, b, b, c, c]"));
    }

    @Test
    public void iterator_test(){
        Iterator<String> iter = myList.iterator();

        assertThat(iter, is(notNullValue()));

        String str = "";
        while(iter.hasNext()){
            str += iter.next();
        }

        assertThat(str, is("abc"));

        String str2 = "";
        for(String s : myList){
            str2 += s;
        }

        assertThat(str2, is("abc"));
        assertThat(str, is(str2));
    }
}
