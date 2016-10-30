package com.yong.data.list;

import org.junit.Before;
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
    private YongList<String> yongList;

    @Before
    public void setUp(){
        yongList = new YongLinkedList<>();

        yongList.add("a");
        yongList.add("b");
        yongList.add("c");
    }

    @Test
    public void size_test(){
        assertThat(yongList.size(), is(3));
    }

    @Test
    public void toString_empty_test(){
        YongList<String> strings = new YongLinkedList<>();
        assertThat(strings.toString(), is("[]"));
    }

    @Test
    public void toString_test(){
        assertThat(yongList.toString(), is("[a, b, c]"));
    }

    @Test
    public void get_test(){
        //then
        assertThat(this.yongList.get(0), is("a"));
        assertThat(this.yongList.get(1), is("b"));
        assertThat(this.yongList.get(2), is("c"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayException_test(){
        yongList.set(10, "hello");
        yongList.get(10);
        yongList.remove(10);
    }

    @Test
    public void set_test(){
        yongList.set(1, "hello");

        assertThat(yongList.size(), is(3));
        assertThat(yongList.get(1), is("hello"));
        assertThat(yongList.toString(), is("[a, hello, c]"));
    }

    @Test
    public void remove_last_node_test(){
        String str = yongList.remove(2);

        assertThat(yongList.size(), is(2));
        assertThat(str, is("c"));
        assertThat(yongList.toString(), is("[a, b]"));
    }

    @Test
    public void remove_test(){
        String str = yongList.remove(1);

        assertThat(yongList.size(), is(2));
        assertThat(str, is("b"));
        assertThat(yongList.toString(), is("[a, c]"));
    }

    @Test
    public void remove_first_node_test(){
        String str = yongList.remove(0);

        assertThat(yongList.size(), is(2));
        assertThat(str, is("a"));
        assertThat(yongList.toString(), is("[b, c]"));
    }

    @Test
    public void contains_test(){
        assertTrue(yongList.contains("a"));
        assertTrue(!yongList.contains("t"));
    }

    @Test
    public void clone_test(){
        YongList<String> cloneList = yongList.clone();

        assertThat(yongList.size(), is(cloneList.size()));
        assertThat(yongList.toString(), is(cloneList.toString()));
    }

    @Test
    public void sort_comparable_test(){
        yongList.add("c");
        yongList.add("b");
        yongList.add("a");

        YongList<String> sortList = yongList.sort();

        assertThat(sortList.toString(), is("[a, a, b, b, c, c]"));
    }

    @Test
    public void sort_comparator_test(){
        yongList.add("c");
        yongList.add("b");
        yongList.add("a");

        YongList<String> sortList = yongList.sort(String::compareTo);

        assertThat(sortList.toString(), is("[a, a, b, b, c, c]"));
    }

    @Test
    public void iterator_test(){
        Iterator<String> iter = yongList.iterator();

        assertThat(iter, is(notNullValue()));

        String str = "";
        while(iter.hasNext()){
            str += iter.next();
        }

        assertThat(str, is("abc"));

        String str2 = "";
        for(String s : yongList){
            str2 += s;
        }

        assertThat(str2, is("abc"));
        assertThat(str, is(str2));
    }
}
