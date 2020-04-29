package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_reverse_linked_list {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode node = head;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public ListNode reverseList02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
