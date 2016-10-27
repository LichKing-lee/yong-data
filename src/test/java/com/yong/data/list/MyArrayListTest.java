package com.yong.data.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ChangYong on 2016. 10. 20..
 */
public class MyArrayListTest {
    private MyList<Integer> list1;

    @Before
    public void setUp(){
        list1 = new MyArrayList<>();
    }

    @Test
    public void 리스트_테스트(){
        list1.add(1);
        list1.add(2);
        list1.add(3);

        assertThat(list1.size(), is(3));
    }

    @Test
    public void 리스트_초과_테스트(){
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);

        assertThat(list1.size(), is(12));
    }

    @Test
    public void list_set_test(){
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.set(2, 10);

        assertThat(list1.get(2), is(10));
        assertThat(list1.size(), is(3));
    }

    @Test
    public void list_remove_test(){
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list1.remove(1);

        assertThat(list1.toString(), is("[1, 3]"));
        MyList<String> list = MyArrayList.mutableMyList("aa", "bb", "cc");
        list.remove(0);
        list.remove(0);
        assertThat(list.toString(), is("[cc]"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void array_index_exception_test(){
        list1.set(4, 5);
    }

    @Test
    public void toString_test(){
        assertThat(list1.toString(), is("[]"));
        list1.add(1);
        assertThat(list1.toString(), is("[1]"));
        list1.add(2);
        list1.add(3);

        assertThat(list1.toString(), is("[1, 2, 3]"));
    }

    @Test
    public void clone_test(){
        list1.add(1);
        list1.add(2);
        list1.add(3);
        MyList<Integer> copyList = list1.clone();

        assertThat(copyList, not(list1));
        assertThat(copyList.toString(), is(list1.toString()));
    }

    @Test
    public void sort_test(){
        list1.add(5);
        list1.add(3);
        list1.add(7);
        list1.add(6);
        list1.add(1);

        MyList<Integer> sortList = list1.sort();
        assertThat(sortList.toString(), is("[1, 3, 5, 6, 7]"));
    }

    @Test
    public void comparator_sort_test(){
        list1.add(5);
        list1.add(3);
        list1.add(7);
        list1.add(6);
        list1.add(1);

        MyList<Integer> sortList = list1.sort(Integer::compareTo);
        assertThat(sortList.toString(), is("[1, 3, 5, 6, 7]"));

        assertThat(list1.toString(), is("[5, 3, 7, 6, 1]"));

        MyList<Integer> sortList2 = list1.sort(Integer::compare);
        assertThat(sortList2.toString(), is("[1, 3, 5, 6, 7]"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void immutable_test(){
        MyList<Integer> list = MyArrayList.immutableMyList(1, 2, 3, 4, 5);

        assertThat(list.toString(), is("[1, 2, 3, 4, 5]"));
        list.add(6);
    }

    @Test
    public void iterator_test(){
        list1.add(3);
        list1.add(2);
        list1.add(1);
        Iterator<Integer> iterator = list1.iterator();

        String result = "";
        while(iterator.hasNext()){
            result += iterator.next();
        }

        assertThat(result, is("321"));
    }

    @Test
    public void foreach_test(){
        list1.add(2);
        list1.add(1);
        list1.add(3);

        String result = "";

        for(int i : list1){
            result += i;
        }

        assertThat(result, is("213"));
    }

    @Test
    public void from_test(){
        List<Integer> list = Arrays.asList(5, 6, 3, 4, 2);

        MyList<Integer> myList = MyArrayList.from(list);

        assertThat(myList.toString(), is("[5, 6, 3, 4, 2]"));

        MyList<Integer> result = myList.sort();

        assertThat(result.toString(), is("[2, 3, 4, 5, 6]"));
    }
}
