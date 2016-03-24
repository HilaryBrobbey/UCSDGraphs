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

    public boolean enqueue(T e) {
        return add(e);
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

    public T dequeue() {
        return remove();
    }

    @Override
    public T remove() {
        if (queueSize == 0) {
            throw new NoSuchElementException("Queue is Empty!");
        }
        T item = (T) first.data;
        first = first.next == null ? null : first.next;
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
    public String toString() {
        String str = "";
        Node f = first;
        while (f != null) {
            str = str + " " + f.data.toString();
            f = f.next;
        }
        return str;
    }

    /*
     public String toString() {
     StringBuilder str = new StringBuilder();
     Iterator it = this.iterator();
     while (it.hasNext()) {
     str.append(it.next().toString()).append(" --> ");
     }
     str.append("NULL");
     return str.toString();
     }
     */
    @Override
    public boolean contains(Object o
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeQueueIterator();
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

    private class NodeQueueIterator implements Iterator<T> {

        Node iteratorCursor;

        public NodeQueueIterator() {
            iteratorCursor = first;
        }

        @Override
        public boolean hasNext() {
            return iteratorCursor != null;
        }

        @Override
        public T next() {
            if (iteratorCursor == null) {
                throw new NoSuchElementException("Iteration has no more elements");
            }
            T next_data = (T) first.data;
            iteratorCursor = first.next;
            return next_data;
        }
    }

    private class Node<T> { //internal Node struct

        T data;
        Node next;

        Node(T _data) {
            data = _data;
        }

    }

    //main method for testing
    public static void main(String[] args) {
        System.out.println("--Start Testing--");
        NodeQueue nq = new NodeQueue();
        nq.add(1);
        nq.add(2);
        nq.enqueue(3);
        nq.enqueue(4);
        nq.add(5);
        System.out.println("Queue Size: " + nq.queueSize);
        System.out.println(nq);
        System.out.println("----");
        nq.remove();
        nq.dequeue();
        nq.dequeue();
        nq.add(6);
        nq.add(7);
        System.out.println("Queue Size: " + nq.queueSize);
        System.out.println(nq);

    }

}
