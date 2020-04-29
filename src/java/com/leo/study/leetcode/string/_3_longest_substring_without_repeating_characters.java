package com.leo.study.leetcode.string;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class _3_longest_substring_without_repeating_characters {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (!window.containsKey(c)) {
                window.put(c, 0);
            }
            window.put(c, window.get(c) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        double a = 1.5d;
        double b = 0.5d;
        double c = 2.5d;
        //System.out.println(c-a);
        //System.out.println(a-b);
        Objects.equals("1", "2");
    }
}
