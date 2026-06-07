package Arrays;

//Problem: Reverse the contents of an array in O(N) linear time complexity

import java.util.Arrays;

public class reverseArray {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};

        reverseArray reversedArray = new reverseArray();

        int[] result = reversedArray.solve(nums);

        System.out.println(Arrays.toString(result));
    }


    // this is the algorithm that reverses the input array nums
    public int[] solve(int[] nums) {

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }

        return nums;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


}
