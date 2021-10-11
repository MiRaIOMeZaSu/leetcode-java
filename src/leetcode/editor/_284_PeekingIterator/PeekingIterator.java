package leetcode.editor._284_PeekingIterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer cache = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cache == null) {
            cache = iterator.next();
        }
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (cache != null) {
            Integer ans = cache;
            cache = null;
            return ans;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (cache != null) {
            return true;
        }
        return iterator.hasNext();
    }
}