package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/********************************
318. Maximum Product of Word Lengths

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. 
If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words. 
*********************************/

public class MaxProductWordLengths {
	public static void main(String[] args) {
		String[] words = new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		MaxProductWordLengths mpwl = new MaxProductWordLengths();
		int ret = mpwl.maxProduct(words);
		System.out.println(ret);
		return;
	}
	
    public int maxProduct(String[] words) {
    	HashMap<Integer, Integer> pattern2Len = new HashMap<Integer, Integer>(); 
        for (String word : words) {
        	int pattern = getWordPattern(word);
        	int len = word.length();
        	if (!pattern2Len.containsKey(pattern)) {
        		pattern2Len.put(pattern, len);
        	} else {
        		int curLen = pattern2Len.get(pattern);
        		pattern2Len.put(pattern, Math.max(curLen, len));
        	}
        }
        
        PatternLen[] pl = sortPatterns(pattern2Len);
        
        int maxProduct = 0;
        for (int i = 0; i < pl.length; ++i) {
        	for (int j = i + 1; j < pl.length && pl[i].length * pl[j].length > maxProduct; ++j) {
        		if (!hasCommonChars(pl[i].pattern, pl[j].pattern)) {
        			maxProduct = pl[i].length * pl[j].length; 
        		}
        	}
        }
        
        return maxProduct;
    }
    
    private PatternLen[] sortPatterns(HashMap<Integer, Integer> pattern2Len) {
        ArrayList<ArrayList<Integer>> d = new ArrayList<ArrayList<Integer>>();
        for (int curPattern : pattern2Len.keySet()) {
        	int curLen = pattern2Len.get(curPattern);
        	while (d.size() - 1 < curLen) {
        		d.add(new ArrayList<Integer>());
        	}
        	d.get(curLen).add(curPattern);
        }
        
        PatternLen[] ret = new PatternLen[pattern2Len.size()];
        int ri = 0;
        for (int i = d.size() - 1; i >= 0; --i) {
        	for (int j = 0; j < d.get(i).size(); ++j) {
        		ret[ri] = new PatternLen();
        		ret[ri].pattern = d.get(i).get(j);
        		ret[ri].length = i;
        		ri += 1;
        	}
        }
        return ret;
    }
    		
    private int getWordPattern(String word) {
    	int ret = 0;
    	for (int i = 0; i < word.length(); ++i) {
    		char c = word.charAt(i);
    		ret = setBit(ret, c);
    	}
    	return ret;
    }
    
    //
    private int setBit(int n, char c) {
    	n = n | (1 << (c - 'a'));
    	return n;
    }
    
    private boolean hasCommonChars(int n1, int n2) {
    	return (n1 & n2) > 0;
    }
    
    private class PatternLen {
    	int pattern;
    	int length;
    }
}
