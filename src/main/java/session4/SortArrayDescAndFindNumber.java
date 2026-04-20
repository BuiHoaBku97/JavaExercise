package session4;

import java.util.Scanner;

public class SortArrayDescAndFindNumber {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("So luong phan tu: ");
        int numberElements = scanner.nextInt();
        int[] arrayInt = new int[numberElements];
        for (int i = 0; i < numberElements; i++) {
            System.out.print("Nhap phan tu: " + (i + 1));
            arrayInt[i] = scanner.nextInt();
        }

        SelectionSortDesc(arrayInt);
        for (int i = 0; i < arrayInt.length; i++) {
            System.out.print(" " + arrayInt[i]);
        }

        System.out.print("Index cua so: ");
        int input = scanner.nextInt();
        System.out.println("Index cua so(LinearSearch): "+ LinearSearch(arrayInt, input));
        System.out.println("Index cua so(BinarySearch): "+ BinarySearch(arrayInt, input));
    }

    private static void SelectionSortDesc( int[] array ){
        for (int i = 0; i < array.length - 1; i++) {
            int indexMaxValue = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > array[indexMaxValue]) {
                    indexMaxValue = j;
                }
            }

            int temp = array[i];
            array[i] = array[indexMaxValue];
            array[indexMaxValue] = temp;
        }
    }

    private static int LinearSearch( int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if ( array[i] == value ){
                return i;
            }
        }
        return  -1;
    }

    private static int BinarySearch( int[] array, int value ){
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + ( right - left ) / 2;

           if ( array[mid] == value ){
               return mid;
           }
           else if ( array[mid] < value ){
               right = mid - 1;
           }
           else {
               left = mid + 1;
           }
        }

        return -1;
    }
}
