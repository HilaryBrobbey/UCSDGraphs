/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadgraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author HilaryB
 * @param <T>
 */
public class NodeQueue<T> implements Queue<T> {

    Node first;
    Node last;
    int queueSize;

    public NodeQueue() {
        first = null;
        last = null;
        queueSize = 0;
    }

    @Override
    public boolean add(T e) {
        Node n = new Node(e);
        if (first == null) { //very first item to be added to queue
            first = n;
            last = n;
        } else {
            last.next = n;
            last = last.next;
        }
        queueSize++;
        return true;

    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remove() {
        if (queueSize == 0) {
            throw new NoSuchElementException("Queue is Empty!");
        }
        T item = (T) first.data;
        first = first.next;
        queueSize--;
        return item;

    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T element() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T peek() {
        return first == null ? null : (T) first.data;
    }

    @Override
    public int size() {
        return queueSize;
    }

    @Override
    public boolean isEmpty() {
        return queueSize == 0;
    }

    @Override
    public boolean contains(Object o
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[]
            toArray(T[] a
            ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> c
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class Node<T> {

        T data;
        Node next;

        Node(T _data) {
            data = _data;
        }

    }

}
