package leetcode;

/**************
No. 316: Remove Duplicate Letters
Given a string which contains only lowercase letters, 
remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc", Return "abc"
Given "cbacdcbc", Return "acdb"

Approach:
Use a stack to maintain the result order.
**************/
public class RemoveDupLetters {
    public String removeDuplicateLetters(String s) {
        // Corner Cases:
    	
    	// In General:
    	char[] cs = s.toCharArray();
    	
    	// First pass:
    	int[] freq = new int[26];
    	for (int i = 0; i < cs.length; freq[cs[i++] - 'a'] += 1);
    	
    	// Second pass:
    	int[] added = new int[26];
    	char[] ret = new char[cs.length];
    	int ri = -1;
    	for (int i = 0; i < cs.length; ++i) {
    		char c = cs[i];
    		int ci = c - 'a';
    		freq[ci] -= 1;
    		
    		if (added[ci] > 0) continue;
    		
    		for(; ri >= 0 && c < ret[ri] && freq[ret[ri] - 'a'] > 0; added[ret[ri--] - 'a'] -= 1);
    		added[ci] += 1;
    		ret[++ri] = c;
    	}
    	
    	return new String(ret, 0, ri + 1);
    }
}
