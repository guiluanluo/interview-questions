package Q8_13_Stack_of_Boxes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Stack of boxes: you have a stack of n boxes, with widths W, heights H, and depths D. the boxes cannot be rotated and
 * can only be stacked on top of one another if each box in the stack is strictly larger than the box above it in width,
 * height, and depth. implement a method to compute the height of the tallest possible stack. the height of a stack is
 * the sum of the heights of each box.
 *
 * hint 155: will sorting the boxes help in any way?
 *
 * hint 194: we can sort the boxes by any dimension in descending order. this will give us a partial order for the
 * boxes, in that boxes later in the array must appear before boxes earlier in the array.
 *
 * hint 214: try to break it down into subproblems.
 *
 * hint 260: think about the first decision you have to make. the first decision is which box will be at the bottom.
 *
 * hint 322: one we pick the box on the bottom, we nee dot pick the second box. then the third box.
 *
 * hint 368: onw ou have a basic recursive algorithm implemented, think about if you can optimize it. are there any
 * repeated subproblems?
 *
 * hint 378: alternatively, we can think about the repeated choices as: does the first box go on the stack? does the
 * second box go on the stack? and so on.
 */
public class QuestionA {

  public static int createStack(ArrayList<Box> boxes) {
    Collections.sort(boxes, new BoxComparator());
    int maxHeight = 0;
    for (int i = 0; i < boxes.size(); i++) {
      int height = createStack(boxes, i);
      maxHeight = Math.max(maxHeight, height);
    }
    return maxHeight;
  }

  public static int createStack(ArrayList<Box> boxes, int bottomIndex) {
    Box bottom = boxes.get(bottomIndex);
    int maxHeight = 0;
    for (int i = bottomIndex + 1; i < boxes.size(); i++) {
      if (boxes.get(i).canBeAbove(bottom)) {
        int height = createStack(boxes, i);
        maxHeight = Math.max(height, maxHeight);
      }
    }
    maxHeight += bottom.height;
    return maxHeight;
  }


  public static void main(String[] args) {
    Box[] boxList = {new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2),
        new Box(9, 7, 3)};
    ArrayList<Box> boxes = new ArrayList<Box>();
    for (Box b : boxList) {
      boxes.add(b);
    }

    int height = createStack(boxes);
    System.out.println(height);
  }

}
