package com.leo.study.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class _567_permutation_in_string {

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<Character, Integer>();
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            if (!need.containsKey(ch)) {
                need.put(ch, 0);
            }
            need.put(ch, need.get(ch) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                if (!window.containsKey(c)) {
                    window.put(c, 0);
                }
                window.put(c, window.get(c) + 1);
                if (need.get(c).compareTo(window.get(c)) == 0) {
                    valid++;
                }
            }

            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).compareTo(window.get(d)) == 0) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "ooolleooolehl";
        System.out.println(checkInclusion(s1, s2));
    }
}
