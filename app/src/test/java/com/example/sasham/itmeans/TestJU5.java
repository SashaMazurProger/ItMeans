package com.example.sasham.itmeans;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.function.Executable;
import org.junit.runners.Parameterized;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyExt implements Extension{

}


@ExtendWith(MyExt.class)
@Tag("ju5test")
class TestJU5 {
    static int count = 0;

    public TestJU5() {
        count++;
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("\nBeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("\nBeforeEach");
    }

    @RepeatedTest(3)
    @DisplayName("TestHello")
    @Test
    void testHello() {
        System.out.println("Count:" + count);
        Assertions.assertEquals("Hello", "Hello");
    }

    @Test
    void testHelloArgs() {
        System.out.println("Count:" + count);
        Assertions.assertEquals("Hello", "Hello");
    }

    @DisplayName("NestedTest")
    @Nested
    class CalcTest {
        @Test
        void sumTest() {
            System.out.println("Count:" + count);

            assertAll(() -> assertEquals(1, 1),
                    () -> assertEquals(2, 2));
        }
    }


//    @Test()
//    void testThrowEx() throws Exception{
//        throw new Exception("error");
//    }

    @Test
    void timeTest(){
        assertTimeout(Duration.ofMillis(20),()->Thread.sleep(10));
    }

    @TestFactory
    Collection<DynamicTest> sumTest() {
        List<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Executable executable = () -> assertEquals((finalI + finalI), finalI * 2, "test sum");
            DynamicTest test = DynamicTest.dynamicTest("test mult i:" + i + " count:" + count, executable);
            dynamicTests.add(test);
        }
        return dynamicTests;
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }
}