package com.leo.study.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _72_minimum_window_substring {

    public static String minWindow(String s, String t) {
        // 存放需要的字符和个数
        Map<Character, Integer> need = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!need.containsKey(c)) {
                need.put(c, 0);
            }
            need.put(c, need.get(c) + 1);
        }

        // 存放滑动过程中存入的字符和个数
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        int valid = 0;

        // 记录最小覆盖子串的位置
        int start = 0;
        int len = Integer.MAX_VALUE;
        int end = 0;
        // 向右滑动
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                if (!window.containsKey(c)) {
                    window.put(c, 0);
                }
                window.put(c, window.get(c) + 1);
                // bug记录：Integer类型如果数值在-128-127之间会有缓存，使用==可以得到正确结果，但是不在这个范围的话就会创建对象，使用==就会出问题
                /*if (window.get(c) == need.get(c)) {
                    valid++;
                }*/
                if (need.get(c).compareTo(window.get(c)) == 0) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    end = right;
                    len = right - left;
                }

                // 将要移除窗口的字符
                char d = s.charAt(left);
                // 左边界收缩移动
                left++;
                // 进行窗口内一系列数据的更新
                if (need.containsKey(d)) {
                    if (need.get(d).compareTo(window.get(d)) == 0) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    public static String minWindow02(String s, String t) {
        // 存放需要的字符和个数
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        // 存放滑动过程中存入的字符和个数
        int[] window = new int[128];
        int left = 0;
        int right = 0;
        int valid = 0;

        // 记录最小覆盖子串的位置
        int start = 0;
        int len = Integer.MAX_VALUE;
        // 向右滑动
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window[c]++;
            if (need[c] > 0 && need[c] >= window[c]) {
                valid++;
            }

            // 判断左侧窗口是否要收缩
            while (valid == t.length()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 将要移除窗口的字符
                char d = s.charAt(left);
                // 左边界收缩移动
                left++;
                if (need[d] > 0 && need[d] >= window[d]) {
                    valid--;
                }
                window[d]--;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";
        System.out.println(minWindow02(s, t));
    }
}
