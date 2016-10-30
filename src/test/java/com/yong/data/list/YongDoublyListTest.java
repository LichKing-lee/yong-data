package com.yong.data.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ChangYong on 2016. 10. 30..
 */
public class YongDoublyListTest {
    private YongList<Integer> list;

    @Before
    public void setUp(){
        this.list = new YongDoublyList<>();
        this.list.add(3);
        this.list.add(1);
        this.list.add(2);
    }

    @Test
    public void add_test(){
        assertThat(this.list.size(), is(3));
    }

    @Test
    public void get_test(){
        assertThat(this.list.get(0), is(3));
        assertThat(this.list.get(1), is(1));
        assertThat(this.list.get(2), is(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void array_index_exception_test(){
        this.list.get(10);
    }

    @Test
    public void set_test(){
        this.list.set(0, 10);
        this.list.set(1, 20);
        this.list.set(2, 30);

        assertThat(this.list.get(0), is(10));
        assertThat(this.list.get(1), is(20));
        assertThat(this.list.get(2), is(30));
    }

    @Test
    public void remove_test(){
        this.list.remove(1);
        assertThat(this.list.get(1), is(2));
        this.list.remove(0);
        this.list.remove(0);

        assertThat(this.list.size(), is(0));
    }

    @Test
    public void contains_test(){
        assertTrue(this.list.contains(2));
        assertTrue(this.list.contains(1));
        assertTrue(!this.list.contains(10));
    }

    @Test
    public void empty_test(){
        YongList<Integer> l = new YongDoublyList<>();

        assertTrue(!this.list.isEmpty());
        assertTrue(l.isEmpty());
    }

    @Test
    public void sort_test(){
        YongList<Integer> result = this.list.sort();

        assertThat(result.get(0), is(1));
        assertThat(result.get(1), is(2));
        assertThat(result.get(2), is(3));

        YongList<Integer> result1 = this.list.sort(Integer::compareTo);
        assertThat(result1.get(0), is(1));
        assertThat(result1.get(1), is(2));
        assertThat(result1.get(2), is(3));
    }

    @Test
    public void toString_test(){
        YongList<Integer> l = new YongDoublyList<>();

        assertThat(l.toString(), is("[]"));
        assertThat(this.list.toString(), is("[3, 1, 2]"));
    }

    @Test
    public void clone_test(){
        YongList<Integer> l1 = this.list.clone();

        assertThat(l1.toString(), is(this.list.toString()));
    }

    @Test
    public void iterator_test(){
        Iterator<Integer> iter1 = this.list.iterator();

        String str1 = "";
        while(iter1.hasNext()){
            str1 += iter1.next();
        }

        assertThat(str1, is("312"));

        String str2 = "";
        for(Integer i : this.list){
            str2 += i;
        }

        assertThat(str2, is("312"));
    }

    @Test
    public void list_iterator_test(){
        ListIterator<Integer> iter1 = ((YongDoublyList)this.list).listIterator();

        String str1 = "";
        while(iter1.hasNext()){
            str1 += iter1.next();
        }

        assertThat(str1, is("312"));

        String str2 = "";
        while(iter1.hasPrevious()){
            str2 += iter1.previous();
        }

        assertThat(str2, is("213"));
    }
}
