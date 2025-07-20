package com.net.interview;

import java.util.HashSet;
import java.util.Set;

public class Question160 {

    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);
        printList(headA);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = new ListNode(8);
        headB.next.next.next.next = new ListNode(4);
        headB.next.next.next.next.next = new ListNode(5);
        printList(headB);

        ListNode result = getIntersectionNode(headA, headB);
        printList(result);

        result = getIntersectionNode1(headA, headB);
        printList(result);
    }


    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            set.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    private static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static void printList(ListNode node) {
        System.out.print(System.lineSeparator());
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.print(" -> null");
    }
}
