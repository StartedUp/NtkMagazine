package org.naamtamilar.magazine;


public class Node {

    /*
    *
    *  In a one-dimensional array of numbers, find the largest sum of contiguous subarray.
     *
     * Input: [2,4,5,-4,1,2,-1,0]
     * Output: 11
     * */
    boolean isHead;
    String data;
    Node nextNode;

    public void insert(String s) {
        Node n = nextNode;
        if(nextNode == null) {
            this.data = s;
            isHead = true;
        }
        while (nextNode != null) {
            n = nextNode;
        }
    }
    public String get() {
        return null;
    }

}
