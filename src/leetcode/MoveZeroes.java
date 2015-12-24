package leetcode;
/*********************************
283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it 
while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, 
nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*********************************/

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int lastIdxOfNonZero = -1;
        for (int i = 0; i < nums.length; ++i) {
        	if (nums[i] != 0) {
        		swap(nums, ++lastIdxOfNonZero, i);
        	}
        }
    }
    
    private void swap(int[] nums, int ai, int bi) {
    	int temp = nums[ai];
    	nums[ai] = nums[bi];
    	nums[bi] = temp;
    }
}
