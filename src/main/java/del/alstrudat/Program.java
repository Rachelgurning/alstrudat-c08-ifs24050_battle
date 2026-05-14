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
        StackTopFirst() { head = null; }

        void push(char c) {
            Node newNode = new Node(c);
            newNode.next = head;
            head = newNode;
        }

        char pop() {
            char data = head.data;
            head = head.next;
            return data;
        }

        boolean isEmpty() { return head == null; }
    }

    private class StackTopLast {
        private Node head;
        private Node tail;
        StackTopLast() { head = null; tail = null; }

        void push(char c) {
            Node newNode = new Node(c);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        char pop() {
            if (head == tail) {
                char data = head.data;
                head = null;
                tail = null;
                return data;
            }
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            char data = tail.data;
            current.next = null;
            tail = current;
            return data;
        }

        boolean isEmpty() { return head == null; }
    }

    public String convert(String bilangan, int basisAsal, int basisTujuan, int modelStack) {
        if (bilangan.equals("0")) return "0";

        bilangan = bilangan.toUpperCase();
        long desimal = 0;
        for (int i = 0; i < bilangan.length(); i++) {
            char c = bilangan.charAt(i);
            int digitValue = (c >= '0' && c <= '9') ? (c - '0') : (c - 'A' + 10);
            desimal = desimal * basisAsal + digitValue;
        }

        StringBuilder result = new StringBuilder();

        if (modelStack == 1) {
            StackTopFirst stack = new StackTopFirst();
            long temp = desimal;
            while (temp > 0) {
                int rem = (int)(temp % basisTujuan);
                char c = (rem < 10) ? (char)('0' + rem) : (char)('A' + rem - 10);
                stack.push(c);
                temp /= basisTujuan;
            }
            while (!stack.isEmpty()) result.append(stack.pop());
        } else {
            StackTopLast stack = new StackTopLast();
            long temp = desimal;
            while (temp > 0) {
                int rem = (int)(temp % basisTujuan);
                char c = (rem < 10) ? (char)('0' + rem) : (char)('A' + rem - 10);
                stack.push(c);
                temp /= basisTujuan;
            }
            while (!stack.isEmpty()) result.append(stack.pop());
        }

        return result.toString();
    }
}