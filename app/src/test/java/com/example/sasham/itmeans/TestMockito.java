package com.example.sasham.itmeans;

import com.example.sasham.itmeans.recents.RecentsInteractor;
import com.example.sasham.itmeans.recents.RecentsPresenterImp;
import com.example.sasham.itmeans.recents.RecentsView;
import com.example.sasham.itmeans.search.WordDetailsInteractorImp;
import com.example.sasham.itmeans.search.WordDetailsViewModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestMockito {

    List mList = mock(List.class);

    @Mock
    B b;


    @Mock
    RecentsView recentsView;

    @Mock
    RecentsInteractor recentsInteractor;

    @InjectMocks
    RecentsPresenterImp recentsPresenter;



    @Mock
    WordDetailsInteractorImp detailsInteractor;

    @InjectMocks
    WordDetailsViewModel detailsViewModel;


    @Captor
    private ArgumentCaptor<List<String>> captor;


    @BeforeEach
    void initList() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadingSetTrueTest(){
//        when(detailsViewModel.search(anyString())).
        detailsViewModel.search("any");

        verify(detailsViewModel).getDefinition().set(any());
    }

    @Test
    void testCaptor() {
        List<String> mockedStrings = mock(List.class);
        mockedStrings.addAll(Arrays.asList("A", "B", "C"));

        verify(mockedStrings).addAll(captor.capture());

        for (String str : captor.getValue()) {
            System.out.println(str);
        }

        List<String> capStrings = captor.getValue();


    }

    @Test
    void testRecentsView() {
        recentsPresenter.fetchWords();

        verify(recentsView).onLoading();
        verify(recentsView).onLoaded();
        verify(recentsInteractor).getAllRecentWords();
    }

    @Test
    void testList() {

        mList.add("Hello");
        mList.add(" World");
        mList.add(new A());

        Mockito.when(mList.get(1)).thenReturn("haha");

        verify(mList).add(any(A.class));
        Mockito.verify(mList).add("Hello");

        System.out.println(mList.get(1));
        System.out.println("List size:" + mList.size());
        System.out.println(mList.get(0));
    }

    @Test
    void testClassB() {
        A a = new A();
        a.b = b;
        a.printB();

        Mockito.verify(b).print();
    }

    @Test
    void spyTest() {

        List<String> strings = new ArrayList<>();
        List<String> spyList = spy(strings);

        doReturn("foo").when(spyList).get(0);
        Assertions.assertEquals("foo", spyList.get(0));

        verify(spyList).get(0);
    }

    @Test
    void testReflection() {
        try {
            Class clazz = Class.forName(Ref.class.getName());
            Ref ref = (Ref) spy(Ref.class);


            //Fields
            Field field = ref.getClass().getDeclaredField("str");
            field.setAccessible(true);
            String value = (String) field.get(ref);
            assertEquals(value, "default");
            System.out.println(value);

            field.set(ref, (String) "new value");
            String newValue = (String) field.get(ref);
            System.out.println(newValue);


            //Method
            Method method = ref.getClass().getDeclaredMethod("print");
            method.setAccessible(true);
            System.out.println("Invoke");
            method.invoke(ref);


            //Constructor
            Class[] params = {String.class};
            Constructor constructor = clazz.getDeclaredConstructor(params);
            constructor.setAccessible(true);
            Ref ref2 = (Ref) constructor.newInstance("from constr");
            Method method2 = ref2.getClass().getDeclaredMethod("print");
            method2.setAccessible(true);
            method2.invoke(ref2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class Ref {
    private String str = "default";

    public Ref() {
    }

    private Ref(String str) {

        this.str = str;
    }

    private void print() {
        System.out.println(str);
    }
}

class A {
    B b;

    void printB() {
        b.print();
    }
}

class B {
    void print() {
        System.out.println("Class B");
    }
}
