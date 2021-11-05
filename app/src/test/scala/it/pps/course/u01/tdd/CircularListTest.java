package it.pps.course.u01.tdd;

import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
class CircularListTest {

    private CircularList circularList;
    private int index;
    private SelectFactory factory;

    @BeforeEach
    void beforeEach(){
        circularList = new SimpleCircularList();
        index = 0;
        factory = new SelectFactoryImpl();
    }

    @Test
    void testAddElement(){
        int elem = 0;
        circularList.add(elem);
        assertFalse(circularList.isEmpty());
    }

    @Test
    void testInitialSize(){
        assertEquals(0, circularList.size());
    }

    @Test
    void testSizeAfterInsertion(){
        int elem = 0;
        circularList.add(elem);
        assertEquals(1, circularList.size());
    }

    @Test
    void testIsEmpty(){
        assertTrue(circularList.isEmpty());
    }

    @Test
    void testIsNotEmptyAfterInsertion(){
        int elem = 0;
        circularList.add(elem);
        assertFalse(circularList.isEmpty());
    }

    @Test
    void testNextWithEmptyList(){
        Optional<Integer> result = circularList.next();
        assertFalse(result.isPresent());
    }

    @Test
    void testNextWithFullList(){
        int attempts = 10;
        int elem = 0;
        for(int i = 0; i < attempts; i++){
            circularList.add(elem);
        }
        for(int i = 0; i < attempts; i++){
            Optional<Integer> result = circularList.next();
            assertTrue(result.isPresent());
        }
    }

    @Test
    void testNextCircularly(){
        int insertions = 10;
        int attempts = insertions*2;
        int elem = 0;
        for(int i = 0; i < insertions; i++){
            circularList.add(elem);
        }
        for(int i = 0; i < attempts; i++){
            Optional<Integer> result = circularList.next();
            assertTrue(result.isPresent());
        }
    }

    @Test
    void testPreviousWithEmptyList(){
        Optional<Integer> result = circularList.previous();
        assertFalse(result.isPresent());
    }

    @Test
    void testPreviousWithFullList(){
        int attempts = 10;
        int elem = 0;
        for(int i = 0; i < attempts; i++){
            circularList.add(elem);
            circularList.next();
        }
        for(int i = 0; i < attempts; i++){
            Optional<Integer> result = circularList.previous();
            assertTrue(result.isPresent());
        }
    }

    @Test
    void testPreviousCircularly(){
        int insertions = 10;
        int attempts = insertions*2;
        int elem = 0;
        for(int i = 0; i < insertions; i++){
            circularList.add(elem);
        }
        for(int i = 0; i < attempts; i++){
            Optional<Integer> result = circularList.previous();
            assertTrue(result.isPresent());
        }
    }

    @Test
    void testReset(){
        int first = 0;
        int second = 1;
        int third = 2;
        circularList.add(first);
        circularList.add(second);
        circularList.add(third);
        assertEquals(first, circularList.next().get());
        circularList.next();
        circularList.reset();
        assertEquals(first, circularList.next().get());
    }

    @Test
    void testNextWithStrategyEmptyList() {
        Optional<Integer> result = circularList.next(factory.createEvenStrategy());
        assertFalse(result.isPresent());
    }

    @Test
    void testNextWithEvenStrategy(){
        int elem = 2;
        circularList.add(elem);
        Optional<Integer> result = circularList.next();
        assertEquals(elem, result.get());
    }

    @Test
    void testNextWithMultipleStrategy(){
        int elem = 2;
        int multiple = 5;
        circularList.add(elem);
        Optional<Integer> result = circularList.next(factory.createMultipleStrategy(multiple));
        assertFalse(result.isPresent());
    }


}
