package ru.gb;

import org.junit.jupiter.api.BeforeEach;

class ArrayListTest extends AbstractListTest {

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        products.add("milk");
        products.add("meat");
        products.add("wine");
    }
}