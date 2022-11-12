package people;

import java.util.List;

public class Quicksort {

    private static <T extends Comparable<T>>

    /**
     * splits the list of people by birthdate, lastname and name
     */
    int split(List<Person> list, int lo, int hi) {
        int left=lo+1;
        int right=hi;
        Person pivot = list.get(lo);

        while (true) {
            while (left <= right) {
                if (list.get(left).getBirthdate().compareTo(pivot.getBirthdate()) < 0) {
                    left++;
                } else if (list.get(left).getBirthdate().compareTo(pivot.getBirthdate()) == 0) {

                    if (list.get(left).getLastname().compareTo(pivot.getLastname()) < 0) {
                        left++;
                    } else if (list.get(left).getLastname().compareTo(pivot.getLastname()) == 0) {

                        if (list.get(left).getName().compareTo(pivot.getName()) < 0) {
                            left++;
                        } else {  break;
                        }

                    }
                    else {  break;
                    }

                }
                else {  break;
                }
            }

            while(right > left) {
                if (list.get(right).getBirthdate().compareTo(pivot.getBirthdate()) < 0) {
                    break;
                } else if (list.get(right).getBirthdate().compareTo(pivot.getBirthdate()) == 0) {

                    if (list.get(right).getLastname().compareTo(pivot.getLastname()) < 0) {
                        break;
                    } else if (list.get(right).getLastname().compareTo(pivot.getLastname()) == 0) {

                        if (list.get(right).getName().compareTo(pivot.getName()) < 0) {
                            break;
                        } else {    right--;
                        }

                    } else {    right--;
                    }

                } else {    right--;
                }
            }

            if (left >= right) {
                break; }

            // swap left and right items
            Person temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            //advance each one step
            left++; right--;
        }

        // swap pivot with left-1 position
        list.set(lo, list.get(left - 1));
        list.set(left - 1, pivot);
        // return the split point

        return left-1;
    }

    /**
     * recurrently calls sorting for sub-lists
     */
    private static <T extends Comparable<T>>
    void sort(List<Person> list, int lo, int hi) {
        if ((hi-lo) <= 0) { // fewer than 2 items
            return; }

        int splitPoint = split(list,lo,hi);
        sort(list,lo,splitPoint-1);  // left subarray recursion
        sort(list,splitPoint+1,hi);  // right subarray recursion
    }

    /**
     * first method of sorting
     */
    public static <T extends Comparable<T>>
    void sort(List<Person> list) {
        if (list.size() <= 1) {
            return;
        }
        sort(list,0,list.size()-1);
    }
}