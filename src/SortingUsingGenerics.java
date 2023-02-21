import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SortingUsingGenerics {

    //implementation of Bubble Sort using Generics
    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> a) {
        for (int i = a.size(); i > 1; i --) {
            for (int j = 0; j < i - 1; j ++) {
                if (a.get(j).compareTo(a.get(j+1)) > 0) {
                    E temp = a.get(j);
                    a.set(j, a.get(j+1));
                    a.set(j+1, temp);
                }
            }
        }
    }

    //implementation of Merge Sort using Generics
    public static <E extends Comparable<E>> void mergeArray(ArrayList<E> a, int start, int middle, int end) {
        ArrayList<E> c = new ArrayList<>();
        int i = start, j = middle;

        while(i < middle && j < end) {
            if (a.get(i).compareTo(a.get(j)) <= 0) {
                c.add(a.get(i));
                i ++;
            } else {
                c.add(a.get(j));
                j ++;
            }
        }

        while (i < middle) {
            c.add(a.get(i));
            i ++;
        }

        while (j < end) {
            c.add(a.get(j));
            j ++;
        }

        for (i = start; i < end; i ++) {
            a.set(i, c.get(i - start));
        }
    }

    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> a, int start, int end) {
        if (end - start <= 1) {
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(a, start, middle);
        mergeSort(a, middle, end);
        mergeArray(a, start, middle, end);
    }

    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> a) {
        mergeSort(a, 0, a.size());
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program will compare Bubble Sort and Merge Sort by using a random array of user-inputted length.");
        System.out.println("The only difference between this code and the previous called \"Sorting Comparison\" is that this code implements Bubble Sort and Merge Sort using Generics.");
        System.out.println("Please input the array length:");
        int arrayLength = scanner.nextInt();
        ArrayList<Number> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < arrayLength; i ++) {
            arrayList.add(new Number(random.nextInt(100)));
        }
        System.out.println("Enter 1 if you would like to sort a small array and display both the unsorted and sorted arrays.");
        System.out.println("Enter 2 if you would like to compare the time it takes to complete Bubble Sort and Merge Sort using a much larger array.");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println(arrayList.toString());
            mergeSort(arrayList);
            System.out.println(arrayList.toString());

        } else if (choice == 2) {
            long start_time, time_spent;
            start_time = System.currentTimeMillis();
            bubbleSort(arrayList);
            time_spent = System.currentTimeMillis() - start_time;
            System.out.println("Bubble Sort time: " + time_spent + " ms");
            start_time = System.currentTimeMillis();
            mergeSort(arrayList);
            time_spent = System.currentTimeMillis() - start_time;
            System.out.println("Merge Sort time: " + time_spent + " ms");

        } else {
            System.out.println("Invalid Input. Please try again");
        }
        scanner.close();
    }
}

class Number implements Comparable<Number> {
    int num;

    public Number (int num) {
        this.num = num;
    }

    public int compareTo(Number stu) {
        return num - stu.num;

    }

    @Override
    public String toString() {
        return "" + num;
    }
}