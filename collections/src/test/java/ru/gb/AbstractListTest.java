package ru.gb;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractListTest {

    protected List<String> products;

    @Test
    void size() {
        assertEquals(3, products.size());
    }

    @Test
    void isEmpty() {
        assertFalse(products.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(products.contains("meat"));
        assertFalse(products.contains("chocolate"));
    }

    @Test
    void iterator() {
        Iterator<String> iterator = products.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("meat", iterator.next());
    }

    @Test
    void toArray() {
        assertArrayEquals(new String[]{"milk", "meat", "wine"}, products.toArray());
    }


    @Test
    void add() {
        products.add("water");
        assertArrayEquals(new String[]{"milk", "meat", "wine", "water"}, products.toArray());
    }

    @Test
    void addWithIndex() {
        products.add(1, "water");
        assertArrayEquals(new String[]{"milk", "water", "meat", "wine"}, products.toArray());
    }

    @Test
    void removeByIndex() {
        products.remove(1);
        assertArrayEquals(new String[]{"milk", "wine"}, products.toArray());
    }

    @Test
    void removeObject() {
        products.remove("meat");
        assertArrayEquals(new String[]{"milk", "wine"}, products.toArray());
    }

    @Test
    void containsAll() {
        assertTrue(products.containsAll(List.of("milk", "wine")));
        assertFalse(products.containsAll(List.of("milk", "water")));
    }

    @Test
    void addAll() {
        products.addAll(Arrays.asList("water", "tea"));
        assertArrayEquals(new String[]{"milk", "meat", "wine", "water", "tea"}, products.toArray());
    }

    @Test
    void testAddAllWithStartIndex() {
        products.addAll(1, Arrays.asList("water", "tea"));
        assertArrayEquals(new String[]{"milk", "water", "tea", "meat", "wine"}, products.toArray());
    }

    @Test
    void removeAll() {
        products.removeAll(Arrays.asList("meat", "tea", "milk"));
        assertArrayEquals(new String[]{"wine"}, products.toArray());
    }

    @Test
    void retainAll() {
        products.retainAll(Arrays.asList("meat", "tea", "milk"));
        assertArrayEquals(new String[]{"milk", "meat"}, products.toArray());
    }

    @Test
    void clear() {
        products.clear();
        assertEquals(0, products.size());
        assertArrayEquals(new String[]{}, products.toArray());
    }

    @Test
    void get() {
        assertEquals("meat", products.get(1));
    }

    @Test
    void set() {
        products.set(0, "water");
        assertArrayEquals(new String[]{"water", "meat", "wine"}, products.toArray());
    }

    @Test
    void indexOf() {
        assertEquals(2, products.indexOf("wine"));
        assertEquals(-1, products.indexOf("water"));
    }

    @Test
    void lastIndexOf() {
        products.add("wine");
        assertEquals(3, products.lastIndexOf("wine"));
    }

    @Test
    void listIterator() {
        ListIterator<String> iterator = products.listIterator();
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());
        assertEquals("meat", iterator.next());
        assertEquals(2, iterator.nextIndex());
        assertEquals(0, iterator.previousIndex());
        assertTrue(iterator.hasPrevious());
        assertEquals("milk", iterator.previous());
        iterator.add("water");
        assertArrayEquals(new String[]{"water", "milk", "meat", "wine"}, products.toArray());
        iterator.set("new water");
        assertArrayEquals(new String[]{"new water", "milk", "meat", "wine"}, products.toArray());
        iterator.remove();
        assertArrayEquals(new String[]{"milk", "meat", "wine"}, products.toArray());
    }

    @Test
    void listIteratorWithNonNulStartIndex() {
        ListIterator<String> iterator = products.listIterator(1);
        assertEquals("wine", iterator.next());
    }

    @Test
    void subList() {
        assertArrayEquals(new String[]{"meat", "wine"}, products.subList(1, 3).toArray());
    }
}