package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;

/**
 *
 * @author satrapu
 */
public class PageDetails
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private long totalCount;
    private long index;
    private boolean next;
    private boolean previous;
    private int size;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public boolean hasNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean hasPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
