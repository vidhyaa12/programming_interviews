package leetcode;

import java.util.Iterator;

public class UserSet implements Iterable<Integer> {
    private int[] memberStatus;

    public UserSet(int numMembers) {
        int memberArraySize = numMembers / 32;

        if (numMembers % 32 != 0) {
            memberArraySize++;
        }

        memberStatus = new int[memberArraySize];
    }

    public void set(int userIdx) {
        int arrIdx = userIdx / 32;
        int bitIdx = userIdx % 32;

        // set the bit
        memberStatus[arrIdx] |= (1 << bitIdx);
    }

    public boolean get(int userIdx) {
        int arrIdx = userIdx / 32;
        int bitIdx = userIdx % 32;
        // To check whether a userIdx value is present, we need to use both array index and bit index
        // when we know the array index, we need to check if the specific bit is set.
        return (memberStatus[arrIdx] & (1 << bitIdx)) == (1 << bitIdx);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new UserSetIterator(memberStatus);
    }

    static class UserSetIterator implements Iterator<Integer> {

        int arrIdx, bitIdx;
        final int[] memberStatusArray;

        private void advanceToNextUser() {
            for (; arrIdx < memberStatusArray.length; arrIdx++) {
                for (; bitIdx < 32; bitIdx++) {
                    if ((memberStatusArray[arrIdx] & (1 << bitIdx)) == (1 << bitIdx)) {
                        //Return from the method when you find the first bit that is set
                        return;
                    }
                }
                bitIdx = 0; // after every byte, bitIdx should be reset to 0
            }
        }

        public UserSetIterator(int[] memberStatus) {
            arrIdx = 0;
            bitIdx = 0;
            memberStatusArray = memberStatus;
        }

        @Override
        public boolean hasNext() {
            return arrIdx < memberStatusArray.length;
        }

        @Override
        public Integer next() {
            Integer curUserId = arrIdx * 32 + bitIdx;
            advanceToNextUser();
            return curUserId;
        }
    }

    public static void main(String[] args) {
        UserSet userSet = new UserSet(400000000);//max 400mm userIds
    }
}
