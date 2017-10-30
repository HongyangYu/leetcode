/*
 * Created by Henry on 10/29/2017.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Q456_132_Pattern {

    class Pair {
        int max;
        int min;
        Pair(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public boolean find132pattern(int[] nums) {
        if(nums==null || nums.length<3) return false;
        Deque<Pair> stack = new ArrayDeque<>(); //Note [1]
        for(int num : nums) {
            if(stack.isEmpty() || stack.peek().min > num) {
                stack.push(new Pair(num, num));
            } else if(num > stack.peek().min) {
                Pair last = stack.pop();
                if(num < last.max) return true; // last.min < num and num < last.max
                // else: num > last.min but num >= last.max
                last.max = num; //update max
                while(!stack.isEmpty() && num>=stack.peek().max) {
                    stack.pop();
                }
                // if stack not empty, num < stack.peek().max after the while loop
                if(!stack.isEmpty() && stack.peek().min < num) return true;
                stack.push(last);
            }
        }
        return false;
    }

    /** Note [1]
     * using java.util.ArrayDeque instead of java.util.Stack because Stack
     * 1) does not have its own interface;
     * 2) extends Vector class, which is based on a synchronized array with different resized way.
     * LinkedList is okay to do the same thing,
     * but for Deque operations, LinkedList usually runs more slowly due to allocation and GC
     */


    public static void main(String[] args) {
        Q456_132_Pattern sol = new Q456_132_Pattern();
        int[] nums = {100, 50, 60, 40, 45, 20, 47, 55};
        boolean res = sol.find132pattern(nums);
        System.out.println("res = " + res);
    }

}
