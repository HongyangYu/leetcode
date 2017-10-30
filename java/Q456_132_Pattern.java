/*
 * Created by Henry on 10/29/2017.
 */

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * https://leetcode.com/problems/132-pattern/
 */
public class Q456_132_Pattern {

    /* ---------------   Method 1   -------------------- */
    static class Method1 {
        class Pair {
            int max;
            int min;
            Pair(int max, int min) {
                this.max = max;
                this.min = min;
            }
        }

        public boolean find132pattern(int[] nums) {
            if (nums == null || nums.length < 3) return false;
            Deque<Pair> stack = new ArrayDeque<>(); //Note [1]
            for (int num : nums) {
                if (stack.isEmpty() || stack.peek().min > num) {
                    stack.push(new Pair(num, num));
                } else if (num > stack.peek().min) {
                    Pair last = stack.pop();
                    if (num < last.max) return true; // last.min < num and num < last.max
                    // else: num > last.min but num >= last.max
                    last.max = num; //update max
                    while (!stack.isEmpty() && num >= stack.peek().max) {
                        stack.pop();
                    }
                    // if stack not empty, num < stack.peek().max after the while loop
                    if (!stack.isEmpty() && stack.peek().min < num) return true;
                    stack.push(last);
                }
            }
            return false;
        }
    }

    /* ---------------   Method 2   -------------------- */
    static class Method2 {
        public boolean find132pattern(int[] nums) {
            if(nums==null || nums.length<3) return false;
            Deque<Integer> stack = new ArrayDeque<>(); // numbers in stack should be in desc order
            int third = Integer.MIN_VALUE;
            for(int i = nums.length-1; i>=0; i--) {
                int num = nums[i];
                if(num < third) return true;
                // to guarentee numbers in stack in desc order, and third < num (third num < second num)
                while(!stack.isEmpty() && stack.peek() < num) {
                    third = stack.pop();
                }
                stack.push(num);
            }
            return false;
        }
    }

    /** Note [1]
     * using java.util.ArrayDeque instead of java.util.Stack because Stack
     * 1) does not have its own interface;
     * 2) extends Vector class, which uses synchronized methods.
     *
     * synchronized methods will lock the object that calls the method,
     * that is, it will lock the Stack object we use.
     * This will influence the performance.
     *
     * Vector class uses grow method, which allow us set capacityIncrement in constructor
     *
     * LinkedList can do the same thing,
     * but for Deque operations, LinkedList usually runs more slowly due to allocation and GC
     */


    public static void main(String[] args) {
        int[] nums = {100, 50, 60, 40, 45, 20, 47, 55};

        boolean res1 = new Method1().find132pattern(nums);
        System.out.println("res1 = " + res1);

        boolean res2 = new Method2().find132pattern(nums);
        System.out.println("res2 = " + res2);
    }

}
