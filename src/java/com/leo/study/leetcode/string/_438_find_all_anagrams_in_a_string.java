package com.leo.study.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020/4/26
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class _438_find_all_anagrams_in_a_string {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int[] need = new int[26];
        int[] window = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[getIndex(p.charAt(i))]++;
        }

        int left = 0;
        int right = 0;
        while (right < s.length()) {
            int index = getIndex(s.charAt(right));
            right++;
            window[index]++;

            while (window[index] > need[index]) {
                window[s.charAt(left++) - 'a']--;
            }
            if (right - left == p.length()) {
                res.add(left);
                window[s.charAt(left++) - 'a']--;
            }
        }
        return res;
    }

    private static int getIndex(char c){
        return c - 'a';
    }

    public static void main(String[] args) {
        // String s = "abbbb";
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = findAnagrams(s, p);
        System.out.println(res.toString());
    }
}
