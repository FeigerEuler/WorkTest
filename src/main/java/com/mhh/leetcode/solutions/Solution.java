package com.mhh.leetcode.solutions;


import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;

@Repository
public class Solution {

    public void hello() {
        System.out.println("hello world!");
    }


    /**
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int len = height.length;
        int ans = 0;
        int i = 0, j = len - 1;
        int minHeight = 0;
        int tmp = 0;
        int mini = height[i], minj = height[j];
        boolean towardsRight = height[i] < height[j];
        while (i < j) {

            minHeight = Integer.min(height[i], height[j]);
            tmp = minHeight * (j - i);
            ans = ans > tmp ? ans : tmp;
            if (towardsRight) {
                while (i < j && height[i] <= mini) {
                    i++;
                }
                mini = height[i];
                if (height[j] <= height[i])
                    towardsRight = false;
            } else {
                while (i < j && height[j] <= minj) {
                    j--;
                }

                minj = height[j];
                if (height[i] <= height[j])
                    towardsRight = true;
            }
        }

        return ans;
    }

    /**
     * integer num convert to Roman num
     * <p>
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    public Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        StringBuilder sb = new StringBuilder();
        if (digits.length() < 2) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < map.get(digits.charAt(0)).length(); i++) {
                ans.add(String.valueOf(map.get(digits.charAt(0)).charAt(i)));
            }

            return ans;
        } else {
            int len = digits.length();
            char ch = digits.charAt(len - 1);
            String str = digits.substring(0, len - 1);

            List<String> ls = letterCombinations(str);
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < map.get(ch).length(); i++) {
                for (int j = 0; j < ls.size(); j++) {
                    ans.add(ls.get(j) + map.get(ch).charAt(i));
                }
            }
            return ans;
        }


    }

    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     * <p>
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     * <p>
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-sudoku
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        /*
        here are my ideas;
        this matter equals check a num whether presented only once intrinsically.
        just check the current row ,column and square(3*3) if it appeared only once;
        therefore ,use the hash table,totally 3*9 tables!

        */

        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] square = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j])
                    continue;
                int num = board[i][j] - '0' - 1;
                boolean[] booleans = square[i / 3 * 3 + j / 3];
                if (row[i][num] || col[j][num] || booleans[num]) {
                    return false;
                } else {
                    row[i][num] = true;
                    col[j][num] = true;
                    booleans[num] = true;
                }

            }

        }
        return true;
    }

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        return searchMhf(i, j, nums, target);

    }

    private int searchMhf(int i, int j, int[] nums, int target) {
        if (nums[i] == target) {
            return i;
        }
        if (nums[j] == target) {
            return j;
        }
        if (j - i < 2) {
            return -1;
        }
        int mid = nums[(i + j) / 2];
        if (mid < nums[j] && target > mid && target < nums[j]) {
            return biSearch((i + j) / 2, j, nums, target);
        }
        if (mid > nums[i] && target > nums[i] && target < mid) {
            return biSearch(i, (i + j) / 2, nums, target);
        }
        if (mid > nums[i] && (target > mid || target<nums[j])) {
            return searchMhf((i + j) / 2, j, nums, target);
        }
        return searchMhf(i, (i + j) / 2, nums, target);


    }

    private int biSearch(int i, int j, int[] nums, int target) {
        if (nums[i] == target) {
            return i;
        }
        if (nums[j] == target) {
            return j;
        }
        if (j - i < 2) {
            return -1;
        }

        if (target > nums[(i + j) / 2]) {
            return biSearch((i + j) / 2, j, nums, target);
        }
        return biSearch(i, (i + j) / 2, nums, target);
    }

}
