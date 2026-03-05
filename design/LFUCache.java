/*
Problem: LFU Cache
Link: https://leetcode.com/problems/lfu-cache/
Difficulty: Hard

Topics: Design, HashMap, Doubly Linked List

Approach:
We implement an LFU (Least Frequently Used) Cache using a combination of:
1. A HashMap `keyMap` to store key → Node mappings for O(1) access.
2. A HashMap `freqMap` mapping frequency → Doubly Linked List of nodes, 
   to maintain ordering among nodes with the same frequency (recently used order).

Algorithm:
- Each Node stores key, value, and frequency.
- Each frequency bucket is a DoublyLinkedList:
    - New nodes or recently used nodes are added to the head (most recent)
    - When evicting, remove from tail (least recently used within that frequency)
- On get or put:
    - Update the node’s frequency by removing it from the old frequency list 
      and adding it to the new frequency list
    - Update `minFreq` accordingly
- On capacity overflow:
    - Remove the least frequently used node (tail of minFreq list)

Time Complexity:
- get(): O(1)
- put(): O(1)

Space Complexity:
- O(capacity) → storing nodes and frequency lists

Extended Description:
LFU cache requires both frequency tracking and recency tracking within the same frequency.
We use a **map of frequencies to doubly linked lists** to achieve O(1) updates.
This ensures we can:
- Quickly retrieve a key’s value and update its frequency
- Evict the LFU and LRU node when capacity is exceeded
The solution is highly efficient and follows standard design patterns for O(1) LFU cache.
*/

import java.util.*;

class LFUCache {

    class Node {
        int key, val, freq;
        Node prev, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        public DoublyLinkedList() {
            head = new Node(0, 0); // dummy head
            tail = new Node(0, 0); // dummy tail
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void add(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            return null;
        }
    }

    private int capacity, minFreq;
    private Map<Integer, Node> keyMap;
    private Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) return -1;
        Node node = keyMap.get(key);
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.val = value;
            update(node);
        } else {
            if (keyMap.size() == capacity) {
                // Remove LFU node
                DoublyLinkedList minList = freqMap.get(minFreq);
                Node toRemove = minList.removeLast();
                keyMap.remove(toRemove.key);
            }

            Node newNode = new Node(key, value);
            keyMap.put(key, newNode);
            freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).add(newNode);
            minFreq = 1; // reset minFreq to 1 for new node
        }
    }

    private void update(Node node) {
        int freq = node.freq;
        DoublyLinkedList oldList = freqMap.get(freq);
        oldList.remove(node);

        if (freq == minFreq && oldList.size == 0) minFreq++;

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).add(node);
    }
}
