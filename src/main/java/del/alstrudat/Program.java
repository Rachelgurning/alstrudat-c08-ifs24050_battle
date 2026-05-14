package del.alstrudat;

public class Program {

    private class Node {
        char data;
        Node next;
        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private class StackTopFirst {
        private Node head;
        private int size;
        StackTopFirst() { head = null; size = 0; }
        void push(char data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
            size++;
        }
        char pop() {
            if (head == null) throw new RuntimeException("Stack kosong");
            char data = head.data;
            head = head.next;
            size--;
            return data;
        }
        boolean isEmpty() { return size == 0; }
    }

    private class StackTopLast {
        private Node head;
        private Node tail;
        private int size;
        StackTopLast() { head = null; tail = null; size = 0; }
        void push(char data) {
            Node newNode = new Node(data);
            if (head == null) { head = newNode; tail = newNode; }
            else { tail.next = newNode; tail = newNode; }
            size++;
        }
        char pop() {
            if (head == null) throw new RuntimeException("Stack kosong");
            char data = tail.data;
            if (head == tail) { head = null; tail = null; }
            else {
                Node current = head;
                while (current.next != tail) current = current.next;
                current.next = null;
                tail = current;
            }
            size--;
            return data;
        }
        boolean isEmpty() { return size == 0; }
    }

    public String convert(String bilangan, int basisAsal, int basisTujuan, int modelStack) {
        if (bilangan.equals("0")) return "0";
        bilangan = bilangan.toUpperCase();

        long decimal = 0;
        for (int i = 0; i < bilangan.length(); i++) {
            char c = bilangan.charAt(i);
            int digitValue = (c >= '0' && c <= '9') ? (c - '0') : (c - 'A' + 10);
            decimal = decimal * basisAsal + digitValue;
        }
        if (decimal == 0) return "0";

        StringBuilder result = new StringBuilder();
        if (modelStack == 1) {
            StackTopFirst stack = new StackTopFirst();
            long temp = decimal;
            while (temp > 0) {
                int r = (int)(temp % basisTujuan);
                stack.push(r < 10 ? (char)('0' + r) : (char)('A' + r - 10));
                temp /= basisTujuan;
            }
            while (!stack.isEmpty()) result.append(stack.pop());
        } else {
            StackTopLast stack = new StackTopLast();
            long temp = decimal;
            while (temp > 0) {
                int r = (int)(temp % basisTujuan);
                stack.push(r < 10 ? (char)('0' + r) : (char)('A' + r - 10));
                temp /= basisTujuan;
            }
            while (!stack.isEmpty()) result.append(stack.pop());
        }
        return result.toString();
    }
}