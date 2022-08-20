package ru.gb;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<E> implements List<E> {

    int size = 0;

    Node<E> first;

    Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
        E[] array = (E[]) new Object[size];
        int i = 0;
        Node<E> node = first;
        while (node != null) {
            array[i] = node.item;
            node = node.next;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) return (T[]) toArray();
        System.arraycopy((T[]) toArray(), 0, a, 0, size);
        if (a.length > size) a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        Node<E> prevLastNode = last;
        Node<E> newLastNode = new Node<>(prevLastNode, e, null);
        if (prevLastNode == null) {
            first = newLastNode;
        } else {
            prevLastNode.next = newLastNode;
        }
        last = newLastNode;
        size++;
        return true;
    }

    public boolean addToStart(E e) {
        Node<E> prevFirstNode = first;
        Node<E> newFirstNode = new Node<>(null, e, prevFirstNode);
        prevFirstNode.prev = newFirstNode;
        first = newFirstNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> n = first;
        if (o == null) {
            while (n != null) {
                if (n.item == null) {
                    deleteNode(n);
                }
                n = n.next;
            }
        } else {
            while (n != null) {
                if (o.equals(n.item)) {
                    deleteNode(n);
                }
                n = n.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) add(element);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Node<E> nextNode = getNode(index);
        Node<E> previousNode = nextNode.prev;
        for (E element : c) {
            Node<E> newNode = new Node<E>(previousNode, element, null);
            previousNode.next = newNode;
            previousNode = newNode;
        }
        previousNode.next = nextNode;
        nextNode.prev = previousNode;
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            remove(element);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<E> it = listIterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
            }
        }
        return true;
    }

    @Override
    public void clear() {
        Node<E> node = first;
        while (node != null) {
            Node<E> nextNode = node.next;
            node.prev = null;
            node.item = null;
            node.next = null;
            node = nextNode;
        }
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getNode(index).item;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = getNode(index);
        node.item = element;
        return node.item;
    }

    @Override
    public void add(int index, E element) {
        Node<E> nextNode = getNode(index);
        Node<E> node = new Node<>(nextNode.prev, element, nextNode);
        Node<E> previousNode = node.prev;
        if (previousNode == null) {
            first = node;
        } else {
            previousNode.next = node;
        }
        nextNode.prev = node;
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = getNode(index);
        Node<E> previousNode = node.prev;
        Node<E> nextNode = node.next;
        if (previousNode == null) {
            first = nextNode;
        } else {
            previousNode.next = nextNode;
        }
        if (nextNode == null) {
            last = node;
        } else {
            nextNode.prev = previousNode;
        }
        size--;
        return node.item;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        Node<E> n = first;
        if (o == null) {
            while (n != null) {
                if (n.item == null) {
                    return i;
                }
                n = n.next;
                i++;
            }
        } else {
            while (n != null) {
                if (o.equals(n.item)) {
                    return i;
                }
                n = n.next;
                i++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = size - 1;
        Node<E> n = last;
        if (o == null) {
            while (n != null) {
                if (n.item == null) {
                    return i;
                }
                n = n.prev;
                i--;
            }
        } else {
            while (n != null) {
                if (o.equals(n.item)) {
                    return i;
                }
                n = n.prev;
                i--;
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
        return (List<E>) Arrays.asList(Arrays.copyOfRange(toArray(), fromIndex, toIndex));
    }

    private void deleteNode(Node<E> n) {
        Node<E> previousNode = n.prev;
        if (previousNode == null) {
            first = first.next;
            size--;
            return;
        }
        Node<E> nextNode = n.next;
        if (nextNode == null) {
            last = last.prev;
            size--;
            return;
        }
        previousNode.next = nextNode;
        size--;
    }

    private Node<E> getNode(int index) {
        Node<E> n = first;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
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
            return (E) toArray()[currentIndex];
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
            return (E) toArray()[currentIndex];
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
            LinkedList.this.remove(currentIndex);
        }

        @Override
        public void set(E e) {
            LinkedList.this.set(currentIndex, e);
        }

        @Override
        public void add(E e) {
            LinkedList.this.add(currentIndex, e);
        }
    }
}
