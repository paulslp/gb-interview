package ru.gb;

import org.junit.jupiter.api.BeforeEach;

class LinkedListTest extends AbstractListTest {

    @BeforeEach
    void setUp() {
        products = new LinkedList<>();
        products.add("milk");
        products.add("meat");
        products.add("wine");
    }
}