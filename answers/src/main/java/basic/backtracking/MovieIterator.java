package basic.backtracking;

import java.util.Iterator;

/**
 * Created by vidhyaa on 13/02/18.
 */
public class MovieIterator<E> implements Iterator<E> {
    Iterator<E> iter;

    static class Movie {}

    public boolean isMovie(E e) {
        return e instanceof Movie;
    }

    private E nextMovie;

    private void advanceAndCacheNext() {
        nextMovie = null;

        while (iter.hasNext() && nextMovie == null) {
            E next = iter.next();
            if (isMovie(next)) {
                nextMovie = next;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextMovie != null;
    }

    @Override
    public E next() {
        E ret = nextMovie;
        advanceAndCacheNext();
        return ret;
    }
}
