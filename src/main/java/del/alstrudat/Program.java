// Program.java
package del.alstrudat;

import java.util.*;

public class Program {

    // =============================================
    // Inner class untuk Node (digunakan oleh BST dan AVL)
    // =============================================
    private class TreeNode {
        int key;
        int priority;
        int height;
        TreeNode left;
        TreeNode right;

        TreeNode(int key, int priority) {
            this.key = key;
            this.priority = priority;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

    // =============================================
    // Inner class untuk Heap Node
    // =============================================
    private class HeapNode {
        int key;
        int priority;

        HeapNode(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    // =============================================
    // Fields
    // =============================================
    private TreeNode bstRoot;
    private TreeNode avlRoot;
    private List<HeapNode> heap;
    private Map<Integer, Integer> keyToPriority;
    
    // Untuk melacak apakah ini output pertama atau bukan
    private boolean firstOutput;

    // =============================================
    // Constructor
    // =============================================
    public Program() {
        bstRoot = null;
        avlRoot = null;
        heap = new ArrayList<>();
        keyToPriority = new HashMap<>();
        firstOutput = true;
    }

    // =============================================
    // Helper Methods untuk Height dan Balance
    // =============================================
    private int getHeight(TreeNode node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    private int getBalance(TreeNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // =============================================
    // BST Operations
    // =============================================
    private TreeNode bstInsert(TreeNode root, int key, int priority) {
        if (root == null) {
            return new TreeNode(key, priority);
        }
        if (key < root.key) {
            root.left = bstInsert(root.left, key, priority);
        } else if (key > root.key) {
            root.right = bstInsert(root.right, key, priority);
        }
        return root;
    }

    private TreeNode bstDelete(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.key) {
            root.left = bstDelete(root.left, key);
        } else if (key > root.key) {
            root.right = bstDelete(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode successor = getMinNode(root.right);
            root.key = successor.key;
            root.priority = successor.priority;
            root.right = bstDelete(root.right, successor.key);
        }
        return root;
    }

    private TreeNode getMinNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    private void bstInorderRange(TreeNode node, int lo, int hi, List<Integer> result) {
        if (node == null) return;
        bstInorderRange(node.left, lo, hi, result);
        if (node.key >= lo && node.key <= hi) {
            result.add(node.key);
        }
        bstInorderRange(node.right, lo, hi, result);
    }

    private int countBstGreater(TreeNode node, int x) {
        if (node == null) return 0;
        if (node.key > x) {
            return 1 + countBstGreater(node.left, x) + countBstGreater(node.right, x);
        }
        return countBstGreater(node.right, x);
    }

    // =============================================
    // AVL Operations (with balancing)
    // =============================================
    private TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private TreeNode avlInsert(TreeNode node, int key, int priority) {
        if (node == null) {
            return new TreeNode(key, priority);
        }
        if (key < node.key) {
            node.left = avlInsert(node.left, key, priority);
        } else if (key > node.key) {
            node.right = avlInsert(node.right, key, priority);
        } else {
            return node;
        }
        updateHeight(node);
        int balance = getBalance(node);
        // LL
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }
        // RR
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }
        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private TreeNode avlDelete(TreeNode node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = avlDelete(node.left, key);
        } else if (key > node.key) {
            node.right = avlDelete(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = getMinNode(node.right);
            node.key = successor.key;
            node.priority = successor.priority;
            node.right = avlDelete(node.right, successor.key);
        }
        updateHeight(node);
        int balance = getBalance(node);
        // LL
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RR
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private TreeNode findAvlNode(TreeNode node, int key) {
        if (node == null) return null;
        if (key == node.key) return node;
        if (key < node.key) return findAvlNode(node.left, key);
        return findAvlNode(node.right, key);
    }

    private boolean isAvlValid(TreeNode node) {
        if (node == null) return true;
        if (Math.abs(getBalance(node)) > 1) return false;
        return isAvlValid(node.left) && isAvlValid(node.right);
    }

    // =============================================
    // Heap Operations (Min-Heap based on priority)
    // =============================================
    private void heapInsert(int key, int priority) {
        heap.add(new HeapNode(key, priority));
        int index = heap.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            HeapNode current = heap.get(index);
            HeapNode pNode = heap.get(parent);
            if (current.priority < pNode.priority ||
                (current.priority == pNode.priority && current.key < pNode.key)) {
                heap.set(index, pNode);
                heap.set(parent, current);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapDeleteByKey(int key) {
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).key == key) {
                index = i;
                break;
            }
        }
        if (index == -1) return;
        
        int lastIndex = heap.size() - 1;
        if (index == lastIndex) {
            heap.remove(lastIndex);
        } else {
            heap.set(index, heap.get(lastIndex));
            heap.remove(lastIndex);
            // Heapify down
            int i = index;
            while (true) {
                int smallest = i;
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < heap.size()) {
                    HeapNode leftNode = heap.get(left);
                    HeapNode smallNode = heap.get(smallest);
                    if (leftNode.priority < smallNode.priority ||
                        (leftNode.priority == smallNode.priority && leftNode.key < smallNode.key)) {
                        smallest = left;
                    }
                }
                if (right < heap.size()) {
                    HeapNode rightNode = heap.get(right);
                    HeapNode smallNode = heap.get(smallest);
                    if (rightNode.priority < smallNode.priority ||
                        (rightNode.priority == smallNode.priority && rightNode.key < smallNode.key)) {
                        smallest = right;
                    }
                }
                if (smallest == i) break;
                HeapNode temp = heap.get(i);
                heap.set(i, heap.get(smallest));
                heap.set(smallest, temp);
                i = smallest;
            }
            // Heapify up
            i = index;
            while (i > 0) {
                int parent = (i - 1) / 2;
                HeapNode current = heap.get(i);
                HeapNode pNode = heap.get(parent);
                if (current.priority < pNode.priority ||
                    (current.priority == pNode.priority && current.key < pNode.key)) {
                    heap.set(i, pNode);
                    heap.set(parent, current);
                    i = parent;
                } else {
                    break;
                }
            }
        }
    }

    private List<Integer> getKMin(int k) {
        List<HeapNode> temp = new ArrayList<>(heap);
        List<Integer> result = new ArrayList<>();
        int limit = Math.min(k, temp.size());
        for (int count = 0; count < limit; count++) {
            HeapNode min = temp.get(0);
            result.add(min.key);
            int lastIdx = temp.size() - 1;
            temp.set(0, temp.get(lastIdx));
            temp.remove(lastIdx);
            int i = 0;
            while (true) {
                int smallest = i;
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < temp.size()) {
                    HeapNode leftNode = temp.get(left);
                    HeapNode smallNode = temp.get(smallest);
                    if (leftNode.priority < smallNode.priority ||
                        (leftNode.priority == smallNode.priority && leftNode.key < smallNode.key)) {
                        smallest = left;
                    }
                }
                if (right < temp.size()) {
                    HeapNode rightNode = temp.get(right);
                    HeapNode smallNode = temp.get(smallest);
                    if (rightNode.priority < smallNode.priority ||
                        (rightNode.priority == smallNode.priority && rightNode.key < smallNode.key)) {
                        smallest = right;
                    }
                }
                if (smallest == i) break;
                HeapNode swap = temp.get(i);
                temp.set(i, temp.get(smallest));
                temp.set(smallest, swap);
                i = smallest;
            }
        }
        return result;
    }

    // =============================================
    // Helper untuk print output tanpa newline di akhir
    // =============================================
    private void printOutput(String value) {
        if (firstOutput) {
            System.out.print(value);
            firstOutput = false;
        } else {
            System.out.print("\n" + value);
        }
    }

    private void printOutput(int value) {
        printOutput(String.valueOf(value));
    }

    // =============================================
    // Public API Methods
    // =============================================

    public void insert(int key, int priority) {
        if (keyToPriority.containsKey(key)) return;
        keyToPriority.put(key, priority);
        bstRoot = bstInsert(bstRoot, key, priority);
        avlRoot = avlInsert(avlRoot, key, priority);
        heapInsert(key, priority);
    }

    public void delete(int key) {
        if (!keyToPriority.containsKey(key)) return;
        keyToPriority.remove(key);
        bstRoot = bstDelete(bstRoot, key);
        avlRoot = avlDelete(avlRoot, key);
        heapDeleteByKey(key);
    }

    public void queryBSTRange(int lo, int hi) {
        List<Integer> result = new ArrayList<>();
        bstInorderRange(bstRoot, lo, hi, result);
        for (int key : result) {
            printOutput(key);
        }
    }

    public void queryAVLHeight() {
        printOutput(getHeight(avlRoot));
    }

    public void queryAVLBalance(int key) {
        TreeNode node = findAvlNode(avlRoot, key);
        if (node == null) {
            printOutput("NOT_FOUND");
        } else {
            printOutput(getBalance(node));
        }
    }

    public void queryHeapMin() {
        if (heap.isEmpty()) {
            return;
        }
        printOutput(heap.get(0).key);
    }

    public void queryHeapKMin(int k) {
        List<Integer> result = getKMin(k);
        for (int key : result) {
            printOutput(key);
        }
    }

    public void isAVLValid() {
        printOutput(isAvlValid(avlRoot) ? "VALID" : "INVALID");
    }

    public void countBSTGreater(int x) {
        printOutput(countBstGreater(bstRoot, x));
    }
}