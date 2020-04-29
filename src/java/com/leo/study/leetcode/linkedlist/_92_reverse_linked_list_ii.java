package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class _92_reverse_linked_list_ii {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 先找到要转换的前一个节点
        ListNode node = dummy;
        for (int i = 1; i < m; i++) {
            node = node.next;
        }

        // 要转换的头结点
        ListNode newHead = node.next;
        ListNode pre = null;
        // 当前转换的节点
        ListNode curr = node.next;
        for (int i = m; i <= n; i++) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        // 要转换的头节点指向转换后面的节点
        newHead.next = curr;
        // 转换前的节点指向转换的最后一个节点
        node.next = pre;
        return dummy.next;
    }

    public ListNode reverseBetween02(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 先找到要转换的前一个节点
        ListNode node = dummy;
        for (int i = 1; i < m; i++) {
            node = node.next;
        }

        // 头插法  由此将需要转换的元素插入到 转换前节点和第一个节点之间
        ListNode first = node.next;
        ListNode curr = first.next;
        for (int i = m; i < n; i++) {
            first.next = curr.next;
            curr.next = node.next;
            node.next = curr;
            curr = first.next;
        }
        return dummy.next;
    }

    public ListNode reverseBetween03(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween03(head.next, m - 1, n - 1);
        return head;
    }

    private ListNode successor = null;

    private ListNode reverseN(ListNode head, int n){
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
