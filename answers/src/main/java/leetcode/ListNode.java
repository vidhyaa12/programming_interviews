package leetcode;

import com.google.common.base.Objects;

public class ListNode<T> implements Comparable<T> {
    private T value;
    private ListNode<T> next;

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public ListNode<T> getNext() {
        return next;
    }
    public void setNext(ListNode<T> ref) {
        this.next = ref;
    }

    public int compareTo(T arg) {
        if(arg == this.value){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode<?> that = (ListNode<?>) o;
        return Objects.equal(value, that.value) &&
                Objects.equal(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value, next);
    }
}

