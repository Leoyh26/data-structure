package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/submissions/
 */
public class _141_linked_list_cycle {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
