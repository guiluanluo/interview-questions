package Q8_06_Towers_of_Hanoi;

/**
 * Town of hanoi: in the classic problem of the towers of hanoi, you have 3 towers and N disk of different sizes which
 * can slide onto any tower. the puzzle starts with disks sorted in ascending order of size from top to bottom (i.e.,
 * each disk sits on top of an even larger one). you have the following constraints: 1) only one disk can be moved at a
 * time. 2) a disk is slid off the top of one tower onto another tower. 3) a disk cannot be placed on top of a smaller
 * disk. write a program to move the disks from the first tower to the last using stacks.
 *
 * hint 144: try base case and build approach
 *
 * hint 224: you can easily move the smallest disk form one tower to another. it's also pretty easy to move the smallest
 * two disks from one tower to another. can you move the smallest three disks?
 *
 * hint 250: think about moving the smallest disk from tower X=0 to tower Y=2 using tower Z=1 as a temporary holding
 * spot as having a solution for f(1, X=0, Y=2, Z=1). moving the smallest two disks if f(1, X=0, Y=2, Z=1). given that
 * you have a solution for f(1, X=0, Y=2, Z=1) and f(2, X=0, Y=2, Z=1), can you solve f(3, X=0, Y=2, Z=1)?
 *
 * hint 272: observe that it doesn't really matter which tower is the source, destination, or buffer. you can do f(3,
 * X=0, Y=2, Z=1) by first doing f(2, X=0, Y=1, Z=2) (moving two disks from tower 0 to tower 1, using tower 2 as
 * buffer), then moving disk 3 from tower 0 to tower 2, then doing f(2, X=0, Y=2, Z=0) (moving two disks from tower 1 to
 * tower 2, using tower 0 as a buffer). how does this process repeat?
 *
 * hint 318: if you are having trouble with recursion, then try trusting the recursive process more. once you have
 * figured out how to move the top two disks form tower 0 to tower 1, trust that you have this working. when you need to
 * move three disks, trust that you can move two disks from one tower to another. now, two disks have been moved. what
 * do you do about the third?
 *
 * Solution: let's start with the smallest possible example: n=1  (D-disk, T-tower)
 * case n=1: can we move D1 from T1 to T3? yes, we simply move D1 form T1 to T3
 * case n=2: can we move D1,D2 from T1 to T3? yes, we move D1->T2, D2->T3, then D1 from T2->T3
 * case n=3: can we move D1,D2,D3 from T1 to T3? yes
 * 1) we know how to move top two disks(D1,D2) from T1 to T3, however let's move them from T1 to T2
 * 2) move D3 to T3
 * 3) move D1,D2 to T3. we already know how to do this - just repeat what we did in step 1
 * case n=4: can we move D1,D2,D3,D4 from T1 to T3? yes
 * 1) move D1,D2,D3 to T2, and D4 from T1 to T3
 * 2) move D4 to T3
 * 3) move D1,D2,D3 back to T3
 *
 * remember that the labels of T2 and T3 are not important. they are equivalent towers. so, moving disks to T3 with T2
 * serving as buffer is equivalent to moving disks to T2 with T3 serving as buffer.
 */
public class Question {


  public static void main(String[] args) {
    // Set up code.
    int n = 5;
    Tower[] towers = new Tower[3];
    for (int i = 0; i < 3; i++) {
      towers[i] = new Tower(i);
    }
    for (int i = n - 1; i >= 0; i--) {
      towers[0].add(i);
    }

    // Copy and paste output into a .XML file and open it with internet explorer.
    //towers[0].print();
    towers[0].moveDisks(n, towers[2], towers[1]);
    //towers[2].print();
  }
}
