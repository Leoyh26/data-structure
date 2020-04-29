package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _142_linked_list_cycle_ii {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
