package com.leo.study.structure.list;

/**
 * @date: 2020/3/26
 */
public class MyArrayList<E> extends MyAbstractList<E>{

    private int size = 0;
    private Object[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity){
        elements = new Object[capacity];
    }

    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public E get(int index){
        rangeCheck(index);
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override
    public int indexOf (E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else{
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FIND;
    }

    @Override
    public void add (int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    private void ensureCapacity(int minCapcity) {
        int oldCapacity = elements.length;
        if (minCapcity > oldCapacity) {
            Object[] newElements = new Object[oldCapacity * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
            /*int newCapacity = oldCapacity + oldCapacity >> 1;
            Arrays.copyOf(elements, newCapacity);*/
        }
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = (E)elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[index] = elements[index+1];
        }
        size--;
        return oldElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("size=").append(size);
        str.append(" [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                str.append(", ");
            }
            str.append(elements[i]);
        }
        str.append("]");
        return str.toString();
    }
}
