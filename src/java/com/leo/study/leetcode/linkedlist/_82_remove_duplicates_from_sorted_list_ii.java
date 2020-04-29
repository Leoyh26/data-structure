package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class _82_remove_duplicates_from_sorted_list_ii {

    public static ListNode deleteDuplicates(ListNode head) {
        // 递归
        if (head == null || head.next == null) {
            return head;
        }

        int count = 1;
        while (head.next != null && head.next.val == head.val) {
            count++;
            head = head.next;
        }

        head.next = deleteDuplicates(head.next);
        return count > 1 ? head.next : head;
    }

    public static ListNode deleteDuplicates02(ListNode head) {
        if (head == null) {
            return head;
        }

        if (head != null && head.next != null && head.val == head.next.val) {
            while (head != null && head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates02(head.next);
        } else {
            head.next = deleteDuplicates02(head.next);
        }
        return head;
    }

    public static ListNode deleteDuplicates03(ListNode head) {
        //迭代
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                ListNode temp = curr.next;
                while (temp != null && temp.next != null && temp.val == temp.next.val ) {
                    temp = temp.next;
                }
                curr.next = temp.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(3);
        ListNode head5 = new ListNode(4);
        ListNode head6 = new ListNode(4);
        ListNode head7 = new ListNode(5);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head7;
        head7.next = null;

        ListNode node = deleteDuplicates02(head);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
