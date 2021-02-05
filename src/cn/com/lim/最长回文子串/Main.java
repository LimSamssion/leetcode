package cn.com.lim.最长回文子串;

//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划
// 👍 3174 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        // 保存所有最短回文串符合 “aba” 的中间坐标
        List<Integer> middleIndexList = new ArrayList<>();
        // 找出手游最短回文串符合 “aa” 的第一个“a"的坐标
        List<Integer> fisrtMiddleList = new ArrayList<>();
        char[] strCharArray = s.toCharArray();
        for(int i = 1; i+1<s.length(); i++) {
            if(strCharArray[i-1] == strCharArray[i+1]) {
                middleIndexList.add(i);
            }

            if(strCharArray[i] == strCharArray[i+1]) {
                fisrtMiddleList.add(i);
            }
        }

        String result1 = find1(s, middleIndexList);
        String result2 = find2(s, fisrtMiddleList);
        String result3 = s.length()>=2 && strCharArray[0]==strCharArray[1] ? s.substring(0,2) : s.substring(0,1);

        return loggerStr(result1, result2, result3);

    }

    public String find1(String s, List<Integer> list) {
        String result = "";
        if (list.isEmpty()) {
            return result;
        }

        char[] strCharArray = s.toCharArray();
        for(Integer i : list) {
            for (int j = 1; i-j>=0 && i+j<s.length(); j++) {
                if (check(strCharArray[i-j], strCharArray[i+j])) {
                    continue;
                } else if (!check(strCharArray[i-j], strCharArray[i+j])){
                    // 上一个j才满足check方法
                    j--;
                    int first = i-j;
                    int end = i+j;
                    String tmp = s.substring(first, end+1);
                    if (tmp.length() > result.length()) {
                        result = tmp;
                    }

                    j = s.length();
                }
            }
        }
        return result;
    }

    public String find2(String s, List<Integer> list) {
        String result = "";
        if (list.isEmpty()) {
            return result;
        }

        char[] strCharArray = s.toCharArray();
        for(Integer i : list) {
            for (int j = 1; i-j>=0 && i+j+1<s.length(); j++) {
                if (check(strCharArray[i-j], strCharArray[i+j+1])) {
                    continue;
                } else {
                    // 上一个j才满足check方法
                    j--;
                    int first = i-j;
                    int end = i+j+1;
                    String tmp = s.substring(first, end+1);
                    if (tmp.length() > result.length()) {
                        result = tmp;
                    }

                    j = s.length();
                }
            }
        }
        return result;
    }

    public String loggerStr(String s1, String s2, String s3) {
        String result = s1;
        if (s2.length() > result.length()) {
            result = s2;
        }
        if (s3.length() > result.length()) {
            result = s3;
        }
        return result;
    }

    public boolean check(char c1, char c2) {
        return c1 == c2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.next();
            System.out.println(solution.longestPalindrome(s));
        }
    }
}
