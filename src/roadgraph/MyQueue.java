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
 * @author Hilary Brobbey
 * @Array Implementation of Queue with Wrap-around
 * @param <T>
 */
public class MyQueue<T> implements Queue<T> {

    private T[] QueueArray;
    private int size;
    private int front;
    private int rear;
    private int openIndex;

    public MyQueue(int INITIAL_CAPACITY) {
        QueueArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        openIndex = 0;
        front = 0;
        rear = -1;
    }

    public MyQueue() {
        this(100);
    }

    @Override
    public boolean add(T e) {
        if (isFull()) {
            return false;
        }
        QueueArray[openIndex] = e; //put element at openIndex
        rear = openIndex;
        size++;
        openIndex++;

        if (openIndex == QueueArray.length) {//rear is at end of array. Wrap around openIndex
            openIndex = 0;
        }

        return true;
    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is Empty!");
        }

        T item = QueueArray[front]; //remove from front
        size--;

        if (front == QueueArray.length - 1) { //front has reached end of array. Wrap around.
            front = 0;
        } else {
            front++;
        }
        if (front == QueueArray.length - 1 && !isFull()) { // After incrementing front, front has hit end of array. 
            //If Queue is not full it means openIndex wraps around to beginning.
            openIndex = 0;
        }

        return item;
    }

    public void expandQueue() {
        T[] newQueueArray = (T[]) new Object[QueueArray.length * 2];
        if (front >= rear) {
            int k = 0;
            for (int i = front; i < QueueArray.length - 1; i++) {
                newQueueArray[k] = QueueArray[k];
                k++;
            }
            for (int i = 0; i <= rear; i++) {
                newQueueArray[k] = QueueArray[k];
                k++;
            }
            front = 0;
            rear = k;
            openIndex = rear + 1;

        } else {
            for (int i = front; i <= rear; i++) {
                newQueueArray[i] = QueueArray[i];
                rear = rear - front;
                front = 0;
                openIndex = rear + 1;
            }
        }
        QueueArray = newQueueArray;
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
        if (isEmpty()) {
            return null;
        }
        return QueueArray[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == QueueArray.length;
    }

    @Override
    public boolean contains(Object o) {
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
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Main function for testing
    public static void main(String[] args) {
        System.out.println("--Test--");
    }

}
