package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @param <T> 
 * @author satrapu
 */
public class EntityList<T extends Serializable>
        implements List<T> {

    private long totalCount;
    private List<T> entities;

    public EntityList(Collection<T> entities, long totalCount) {
        if (entities == null) {
            throw new RuntimeException("Cannot construct an EntityList using a null collection");
        }

        if (totalCount < 0) {
            throw new RuntimeException("Total count cannot be negative");
        }

        this.entities = new ArrayList<T>(entities);
        this.totalCount = totalCount;
    }

    public List<T> getEntities() {
        return entities;
    }

    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public int size() {
        return entities.size();
    }

    @Override
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return entities.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }

    @Override
    public Object[] toArray() {
        return entities.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return entities.toArray(ts);
    }

    @Override
    public boolean add(T e) {
        return entities.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return entities.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return entities.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return entities.addAll(collection);
    }

    @Override
    public boolean addAll(int startIndex,
                          Collection<? extends T> collection) {
        return entities.addAll(startIndex, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return entities.retainAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return entities.retainAll(collection);
    }

    @Override
    public void clear() {
        entities.clear();
    }

    @Override
    public T get(int index) {
        return entities.get(index);
    }

    @Override
    public T set(int index, T element) {
        return entities.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        entities.add(index, element);
    }

    @Override
    public T remove(int index) {
        return entities.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return entities.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return entities.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return entities.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startIndex) {
        return entities.listIterator(startIndex);
    }

    @Override
    public List<T> subList(int startIndex, int lastIndex) {
        return entities.subList(startIndex, lastIndex);
    }
}
