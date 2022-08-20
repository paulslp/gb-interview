package ru.gb;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.util.Objects.isNull;

public class ArrayList<E> implements List<E> {

    private E[] arrayOfElements;

    private int size;

    public ArrayList() {
        size = 0;
        arrayOfElements = (E[]) new Object[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return isNull(arrayOfElements) || arrayOfElements.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayOfElements, arrayOfElements.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) toArray();
        System.arraycopy(arrayOfElements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        arrayOfElements = Arrays.copyOf(arrayOfElements, ++size);
        arrayOfElements[size - 1] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int indexOfObject = indexOf(o);
        if (indexOfObject < 0) {
            return false;
        } else {
            System.arraycopy(arrayOfElements,
                    indexOfObject + 1,
                    arrayOfElements,
                    indexOfObject,
                    arrayOfElements.length - indexOfObject - 1);
            arrayOfElements = Arrays.copyOf(arrayOfElements, --size);
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        arrayOfElements = Arrays.copyOf(arrayOfElements, size + c.size());
        Object[] elements = arrayOfElements;
        System.arraycopy(c.toArray(), 0, elements, size, c.size());
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        arrayOfElements = Arrays.copyOf(arrayOfElements, size + c.size());
        Object[] elements = arrayOfElements;
        System.arraycopy(elements,
                index,
                elements,
                index + c.size(),
                size - index);

        System.arraycopy(c.toArray(),
                0,
                elements,
                index,
                c.size());
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int[] indexes = new int[c.size()];
        int i = 0;
        E[] arrayFromCollection = (E[]) c.toArray();
        for (i = 0; i < arrayFromCollection.length; i++) {
            indexes[i] = indexOf(arrayFromCollection[i]);
        }
        Arrays.sort(indexes);

        i = 0;
        while (indexes[i] == -1) {
            i++;
        }
        E[] newArrayOfElements = (E[]) new Object[size - i];

        int newArrayIndex = 0;
        for (i = 0; i < indexes.length; i++) {
            if (indexes[i] > -1) newArrayOfElements[newArrayIndex++] = arrayOfElements[indexes[i]];
        }
        arrayOfElements = newArrayOfElements;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) arrayOfElements[i] = null;
        size = 0;
        arrayOfElements = (E[]) new Object[0];
    }

    @Override
    public E get(int index) {
        return arrayOfElements[index];
    }

    @Override
    public E set(int index, E element) {
        arrayOfElements[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        size++;
        arrayOfElements = Arrays.copyOf(arrayOfElements, size);
        System.arraycopy(arrayOfElements,
                index,
                arrayOfElements,
                index + 1,
                size - index - 1);

        arrayOfElements[index] = element;
    }

    @Override
    public E remove(int index) {
        E deletedElement = arrayOfElements[index];
        System.arraycopy(arrayOfElements,
                index + 1,
                arrayOfElements,
                index,
                size - index - 1);
        size--;
        arrayOfElements = Arrays.copyOf(arrayOfElements, size);
        return deletedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < arrayOfElements.length; i++) {
                if (arrayOfElements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < arrayOfElements.length; i++) {
                if (o.equals(arrayOfElements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = arrayOfElements.length - 1; i >= 0; i--) {
                if (arrayOfElements[i] == null) return i;
            }
        } else {
            for (int i = arrayOfElements.length - 1; i >= 0; i--) {
                if (o.equals(arrayOfElements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Arrays.asList(Arrays.copyOfRange(arrayOfElements, fromIndex, toIndex));
    }

    private class Iter implements Iterator<E> {

        int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            currentIndex++;
            return arrayOfElements[currentIndex];
        }
    }

    private class ListIter extends Iter implements ListIterator<E> {
        ListIter(int index) {
            currentIndex = index;
        }


        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            currentIndex--;
            return arrayOfElements[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            ArrayList.this.remove(currentIndex);
        }

        @Override
        public void set(E e) {
            ArrayList.this.set(currentIndex, e);
        }

        @Override
        public void add(E e) {
            ArrayList.this.add(currentIndex, e);
        }
    }
}