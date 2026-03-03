/*
Problem: Merge k Sorted Lists
Link: https://leetcode.com/problems/merge-k-sorted-lists/
Difficulty: Hard
Topic: Linked List / Heap / Divide & Conquer
Approach: Min Heap (PriorityQueue) to merge k sorted lists efficiently
Time Complexity: O(N log k), N = total nodes
Space Complexity: O(k) for the heap

Extended Description:
This solution merges k sorted linked lists using a min-heap (PriorityQueue). 
The head of each non-empty list is added to the heap. 
At each step, the smallest node is extracted and appended to the result list. 
If the extracted node has a next, it is added to the heap. 
This approach ensures the merged list remains sorted at all times and handles k lists efficiently in O(N log k) time.
*/

import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Add the head of each list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}

// Note: Do NOT redefine ListNode on LeetCode
// LeetCode provides ListNode class
