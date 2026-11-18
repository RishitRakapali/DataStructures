/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA2 write-up, course materials
 *
 * This file contains an algorithm that moves all occurrences of a
 * target integer to the end of MyArrayList. It also modifies the
 * original list without creating a new array or list.
 */

/**
 * A concrete class that implements a static method utilizing MyArrayList.
 */
public class MyAlgorithm {

    /**
     * Moves all occurrences of the target integer to the end of the list
     * in-place, keeping the other elements in their original order.
     *
     * @param nums the MyArrayList of integers
     * @param target the integer value to move to the end
     * @return the same list with target values at the end
     * @throws NullPointerException if nums is null
     */
    public static MyArrayList<Integer> moveTargetToEnd(
            MyArrayList<Integer> nums, int target) {
        if (nums == null) {
            throw new NullPointerException();
        }

        int nextIndex = 0;
        int originalSize = nums.size();

        // Move non-target elements to the front in their original order.
        for (int i = 0; i < originalSize; i++) {
            Integer current = nums.get(i);
            if (current == null || current != target) {
                nums.set(nextIndex, current);
                nextIndex++;
            }
        }

        // Fill the remaining valid positions with the target.
        for (int i = nextIndex; i < originalSize; i++) {
            nums.set(i, target);
        }
        return nums;
    }
}
