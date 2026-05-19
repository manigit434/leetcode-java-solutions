import java.util.HashMap;
import java.util.Map;

class AllOne {
    // Inner Layer: Represents individual string elements
    private class StringNode {
        String key;
        Bucket bucket;
        StringNode prev, next;

        StringNode(String key) {
            this.key = key;
        }
    }

    // Outer Layer: Represents frequency bucket groups
    private class Bucket {
        int count;
        Bucket prev, next;
        // Sentinels for the strings assigned to this specific frequency
        StringNode head, tail;

        Bucket(int count) {
            this.count = count;
            head = new StringNode("");
            tail = new StringNode("");
            head.next = tail;
            tail.prev = head;
        }

        void addString(StringNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            node.bucket = this;
        }

        void removeString(StringNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }

    private Bucket head, tail;
    private Map<String, StringNode> map;

    public AllOne() {
        head = new Bucket(0); // Sentinel head
        tail = new Bucket(0); // Sentinel tail
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    private Bucket addBucketAfter(Bucket prevBucket, int count) {
        Bucket newBucket = new Bucket(count);
        newBucket.next = prevBucket.next;
        newBucket.prev = prevBucket;
        prevBucket.next.prev = newBucket;
        prevBucket.next = newBucket;
        return newBucket;
    }

    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            StringNode sNode = map.get(key);
            Bucket curBucket = sNode.bucket;
            Bucket nextBucket = curBucket.next;

            if (nextBucket == tail || nextBucket.count != curBucket.count + 1) {
                nextBucket = addBucketAfter(curBucket, curBucket.count + 1);
            }

            curBucket.removeString(sNode);
            nextBucket.addString(sNode);

            if (curBucket.isEmpty()) {
                removeBucket(curBucket);
            }
        } else {
            StringNode sNode = new StringNode(key);
            Bucket firstBucket = head.next;

            if (firstBucket == tail || firstBucket.count != 1) {
                firstBucket = addBucketAfter(head, 1);
            }

            firstBucket.addString(sNode);
            map.put(key, sNode);
        }
    }

    public void dec(String key) {
        if (!map.containsKey(key)) return;

        StringNode sNode = map.get(key);
        Bucket curBucket = sNode.bucket;

        curBucket.removeString(sNode);

        if (curBucket.count == 1) {
            map.remove(key);
        } else {
            Bucket prevBucket = curBucket.prev;
            if (prevBucket == head || prevBucket.count != curBucket.count - 1) {
                prevBucket = addBucketAfter(curBucket.prev, curBucket.count - 1);
            }
            prevBucket.addString(sNode);
        }

        if (curBucket.isEmpty()) {
            removeBucket(curBucket);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) return "";
        return tail.prev.head.next.key; // O(1) direct reference pointer access
    }

    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.head.next.key; // O(1) direct reference pointer access
    }
}

/*
 * Time Complexity: Absolute O(1) for all methods.
 * Space Complexity: O(N)
 */
