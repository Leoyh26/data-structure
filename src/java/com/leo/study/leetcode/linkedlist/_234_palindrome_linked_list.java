package com.leo.study.leetcode.linkedlist;

import com.leo.study.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class _234_palindrome_linked_list {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        List<Integer> list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            if (list.get(i++) != list.get(j--)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome02(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverse(firstHalfEnd.next);
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 反转回去
        firstHalfEnd.next = reverse(secondHalfStart);
        return true;
    }

    private ListNode reverse(ListNode node){
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
