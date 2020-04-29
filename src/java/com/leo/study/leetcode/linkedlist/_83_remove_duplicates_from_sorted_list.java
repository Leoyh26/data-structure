package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_remove_duplicates_from_sorted_list {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = deleteDuplicates(head.next);
        if (head.val == node.val) {
            head = node;
        } else {
            head.next = node;
        }
        return head;
    }

    public ListNode deleteDuplicates02(ListNode head) {
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            if (pre != null && node.val == pre.val) {
                pre.next = node.next;
                node = pre.next;
            } else {
                pre = node;
                node = node.next;
            }
        }
        return head;
    }
}
