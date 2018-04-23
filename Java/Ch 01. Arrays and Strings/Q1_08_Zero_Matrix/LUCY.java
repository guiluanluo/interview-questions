package Q1_08_Zero_Matrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO: Document what Test is.
 *
 * @author Lucy Luo    212391955
 * @version 1.0 Apr 14, 2018
 * @since 1.0
 */
public class LUCY {

  public static void main(String[] args) throws Exception {
//    int n = 5;
//    int minMove = downToZero(n);
//    System.out.println("minMove:" + minMove);

//    int[] arr = {33, 11, 44, 11, 55};
//    for (int i = 1; i <= 5; i++) {
//      System.out.println("min " + i + ": " + findMin(arr, i));
//    }

//    System.out.println("result:" + super_reduced_string("aa"));

//    String[] unsorted = {"1", "31415926535897932384626433832795", "6", "3", "2"};
//    System.out.println("result:" + bigSorting(unsorted));

//    int[] expenditure ={1, 2 ,3 ,4, 4};
//    int d = 4;
//    System.out.println("notify num:" + activityNotifications(expenditure, d));

//    long[] price ={5, 10, 3};
//    System.out.println("minimumLoss:" + minimumLoss(price));

//    int[] arr = {203, 204, 205, 206, 207, 208, 203, 204, 205, 206};
//    int[] brr = {203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204};
//    System.out.println("missingNumbers:" + missingNumbers(arr, brr));

//    System.out.println("Long max: " + Long.MAX_VALUE);
//    System.out.println("digitalSum:" + digitSum("148", 3));

//    String[] pass = {"ab", "abcd", "cd"};
//    String attempt = "abcd";

    String[] pass = {"because", "can", "do", "must", "we", "what"};
    String attempt = "wedowhatwemustbecausewecan";

    //we do what we must because we can

//    String[] pass = {" hello", "planet"};
//    String attempt = "helloworld";

    System.out.println("passwordCracker:" + passwordCracker(pass, attempt));

  }

  static String passwordCracker(String[] pass, String attempt) {
    Map<Integer, String> idxValueMap = new HashMap<>();
    String original = new String(attempt);
    for (String p : pass) {
      int idx = attempt.indexOf(p);
      if (idx < 0) {
        continue;
      } else {
        List<Integer> idxList = getIndex(original, p);
        for(Integer idx1: idxList){
          idxValueMap.put(idx1, p);
        }
        attempt = attempt.replace(p, "");
      }
    }

    if (!attempt.isEmpty()) {
      return "WRONG PASSWORD";
    }

    StringBuilder build = new StringBuilder();
    List<Integer> index = new ArrayList<>(idxValueMap.keySet());
    Collections.sort(index);
    for (Integer idx : index) {
      build.append(idxValueMap.get(idx)).append(" ");
    }
    return build.toString();
  }

  static List<Integer> getIndex(String str, String p){
    List<Integer> idxList = new ArrayList<>();
    int idx = str.indexOf(p);
    while(idx != -1){
      idxList.add(idx);
      idx = str.indexOf(p, idx+p.length());
    }
    return idxList;
  }


  static String passwordCracker_0(String[] pass, String attempt) {

    List<String> result = new ArrayList<>();
    checkPassword(Arrays.asList(pass), attempt, result, "");

    StringBuilder build = new StringBuilder();
//    for (String key : result) {
//      if (!key.equals(attempt)) { //exclude itself
//        build.append(key).append(" ");
//      }
//    }
    return build.toString();
  }

  static void checkPassword(List<String> pass, String attempt, List<String> result, String prefix) {
    if (pass.size() == 0) {
      return;
    }

    if (prefix.equals(attempt)) {
      System.out.println("**found:" + result.toString());
      return;
    }

    for (int i = 0; i < pass.size(); i++) {
      String currentPass = pass.get(i);
      result.add(currentPass);
      List<String> rem = new ArrayList<>();
      rem.addAll(pass.subList(0, i));
      rem.addAll(pass.subList(i + 1, pass.size()));
      checkPassword(rem, attempt, result, prefix + currentPass);
    }
  }


  static int digitSum(String n, int k) {
    StringBuilder build = new StringBuilder();
    for (int i = 0; i < k; i++) {
      build.append(n);
    }

    String nstr = build.toString();
    return Integer.valueOf(superDigital_recursive(nstr));
  }

  static String superDigital_recursive(String str) {
    if (str.length() == 1) {
      return str;
    }

    BigDecimal sum = new BigDecimal(0);
    for (int i = 0; i < str.length(); i++) {
      sum = sum.add(new BigDecimal(Integer.parseInt(Character.toString(str.charAt(i)))));
    }
    return superDigital_recursive(sum.toString());
  }

  static int superDigital(String str) {

    while (str.length() > 1) {
      long sum = 0;
      for (int i = 0; i < str.length(); i++) {
        sum += Integer.parseInt(Character.toString(str.charAt(i)));
      }
      str = Long.toString(sum);
    }
    return Integer.valueOf(str);
  }


  static int[] missingNumbers(int[] arr, int[] brr) {
    int[] miss = new int[100];
    for (int m = 0; m < miss.length; m++) {
      miss[m] = 0;
    }

    for (int a : arr) {
      miss[a % 100]--;
    }

    int min = Integer.MAX_VALUE;
    for (int b : brr) {
      miss[b % 100]++;
      if (b < min) {
        min = b;
      }
    }
    int number = min - min % 100;

    List<Integer> mlist = new ArrayList<>();
    for (int i = 0; i < miss.length; i++) {
      if (miss[i] > 0) {
        mlist.add(i + number);
      }
    }

    int[] results = new int[mlist.size()];
    for (int j = 0; j < mlist.size(); j++) {
      results[j] = mlist.get(j);
    }
    return results;
  }


  static int[] missingNumbers_1(int[] arr, int[] brr) {

    Set<Integer> aset = new HashSet<>();
    Set<Integer> bset = new HashSet<>();
    Map<Integer, Integer> acountMap = new HashMap<>();
    Map<Integer, Integer> bcountMap = new HashMap<>();
    Set<Integer> mset = new HashSet<>();

    for (int a : arr) {
      aset.add(a);
      Integer value = acountMap.get(a);
      value = value != null ? value + 1 : 1;
      acountMap.put(a, value);
    }

    for (int b : brr) {
      bset.add(b);
      Integer value = bcountMap.get(b);
      value = value != null ? value + 1 : 1;
      bcountMap.put(b, value);
    }

    for (int b : brr) {
      Integer acount = acountMap.get(b);
      Integer bcount = bcountMap.get(b);
      if (acount == null || acount != bcount) {
        mset.add(b);
      }
    }

    Set<Integer> diff = symmetricDifference(aset, bset);
    mset.addAll(diff);

    List<Integer> sortedMissList = new ArrayList<>(mset);
    Collections.sort(sortedMissList);
    System.out.println("sortedMissList:" + sortedMissList);

    int[] rarr = new int[sortedMissList.size()];
    for (int i = 0; i < sortedMissList.size(); i++) {
      rarr[i] = sortedMissList.get(i).intValue();
    }
    return rarr;
  }

  static Set<Integer> symmetricDifference(Set<Integer> a, Set<Integer> b) {
    Set<Integer> result = new HashSet<Integer>(a);
    for (Integer element : b) {
      // .add() returns false if element already exists
      if (!result.add(element)) {
        result.remove(element);
      }
    }
    return result;
  }


  static int minimumLoss(long[] price) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < price.length - 1; i++) {
      for (int j = i + 1; j < price.length; j++) {
        int loss = (int) (price[i] - price[j]);
        if (loss > 0 && loss < min) {
          min = loss;
        }
      }
    }
    return min;
  }


  static int activityNotifications(int[] expenditure, int d) {
    int notifyCount = 0;
    int size = expenditure.length;

    if (size <= d) {
      return 0;
    }

    if (size == d + 1) {
      int todayExpenditure = expenditure[d];
      double media = getMedia(expenditure, 0, d);
      notifyCount += (hasNotify(todayExpenditure, media) ? 1 : 0);
      return notifyCount;
    }

    for (int i = d; i < size; i++) {
      int todayExpenditure = expenditure[i];
      double media = getMedia(expenditure, i - d, d);
      notifyCount += (hasNotify(todayExpenditure, media) ? 1 : 0);
    }
    return notifyCount;
  }

  static boolean hasNotify(int todayExpenditure, double media) {
    return todayExpenditure >= 2 * media;
  }

  static double getMedia(int[] expenditure, int start, int range) {
    int[] target = new int[range];
    int tidx = 0;
    for (int i = start; i < (start + range); i++) {
      target[tidx++] = expenditure[i];
    }
    Arrays.sort(target);

    int tlength = target.length;
    double media;
    if (tlength % 2 == 0) {
      media = (double) (target[tlength / 2] + target[(tlength / 2) - 1]) / 2;
    } else {
      media = target[tlength / 2];
    }
    return media;
  }

//  Collections.sort(intArr, new Comparator<BigDecimal>() {
//    @Override
//    public int compare(BigDecimal o1, BigDecimal o2) {
//      if(a.Length == b.Length)
//        return string.Compare(a,b,StringComparison.Ordinal);
//      return a.Length - b.Length;
//      return o1.getAge() - o2.getAge();
//    }
//  });

//  Arrays.Sort(unsorted,(string a,string b) => {
//    if(a.Length == b.Length)
//      return string.Compare(a,b,StringComparison.Ordinal);
//    return a.Length - b.Length;
//  });


  static String[] bigSorting(String[] unsorted) {

    List<String> unsortedList = Arrays.asList(unsorted);
    Collections.sort(unsortedList, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        if (o1.length() == o2.length()) {
          return o1.compareTo(o2);
        }
        return o1.length() - o2.length();
      }
    });

    return unsortedList.toArray(new String[unsortedList.size()]);

  }


  static boolean isSequenceUnique(String str) {
    char[] arr = str.toCharArray();
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] == arr[i + 1]) {
        return false;
      }
    }
    return true;
  }

  static String super_reduced_string(String str) {
    String returnedStr = str;
    while (!isSequenceUnique(returnedStr)) {
      returnedStr = reduced_string(returnedStr);
    }
    return returnedStr;
  }

  static String reduced_string(String str) {
    if (str.length() == 2 && (str.charAt(0) == str.charAt(1))) {
      return "";
    }

    StringBuilder build = new StringBuilder();
    char[] arr = str.toCharArray();

    int i = 0;
    while (i < arr.length) {
      //check two chars
      if (i + 1 < arr.length) {
        if (arr[i] == arr[i + 1]) {
          i = i + 2;
          continue;
        } else {
          build.append(arr[i]);
          i++;
        }
      } else {
        build.append(arr[i]);
        i++;
      }
    }
    return build.toString();
  }

  static int getTotalX(int[] a, int[] b) {
    int x = 1, r = 0, j = 0, count = 0;
    int[] d = new int[101];

    for (x = 1; x < 101; x++) {
      int c = 0;
      for (int i = 0; i < a.length; i++) {
        if (x % a[i] == 0 && x >= a[i]) { //the factor of array a
          c++;
        }
      }
      if (c == a.length) {
        d[j] = x;
        r++;
        j++;
      }
    }

    for (j = 0; j < r; j++) {
      int c = 0;
      for (int i = 0; i < b.length; i++) {
        if (b[i] % d[j] == 0) {
          c++;
        }
      }
      if (c == b.length) {
        count++;
      }
    }
    return count;
  }


  static int findMin(int[] arr, int d) {
    int length = arr.length;

    if (d == length) {
      return findMax(arr, 0, length);
    }

    int minValue = -1;
    for (int i = 0; i < length; i++) {
      int startIdx = i;
      int endIdx = i + d;
      if (endIdx >= length) {
        break;
      }

      int maxValue = findMax(arr, startIdx, endIdx);
      if (minValue < 0) {
        minValue = maxValue;
      }
      minValue = Math.min(minValue, maxValue);
    }
    return minValue;
  }

  static int findMax(int[] arr, int startIdx, int endIdx) {
    int max = arr[startIdx];
    for (int i = startIdx + 1; i < endIdx; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    return max;
  }


  /*
     * Complete the downToZero function below.
     */
  static int[] minMoves = new int[1000001];

  static int downToZero(int n) {
    if (n <= 3) {
      return n;
    }
    if (minMoves[n] > 0) {
      return minMoves[n];
    }

    int min = Integer.MAX_VALUE;
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        int factor = n / i;
        min = Math.min(min, 1 + downToZero(factor));
      }
    }
    min = Math.min(min, 1 + downToZero(n - 1));
    minMoves[n] = min;
    return min;
  }


}
