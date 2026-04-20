package session4;

import java.util.Scanner;

public class SortEvenNumberThenOddNumber {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("So luong phan tu: ");
        int numberElements = scanner.nextInt();
        int[] arrayInt = new int[numberElements];
        for (int i = 0; i < numberElements; i++) {
            System.out.print("Nhap phan tu: " + (i + 1));
            arrayInt[i] = scanner.nextInt();
        }

        //----------
        Sort(arrayInt);
        for (int i = 0; i < arrayInt.length; i++) {
            System.out.print(" " + arrayInt[i]);
        }
    }

    static void Sort(int[] array) {
        if ( array.length == 0 ){
            System.out.print("No element in array");
        }

        int lastEvenIndex = IsEven(array[0]) ? 0 : -1 ;
        for (int i = 1; i < array.length; i++) {
            if (!IsEven(array[i])) {
                continue;
            }

            int minIndex = lastEvenIndex;
            for (int j = i - 1; j > minIndex; j--) {
                lastEvenIndex = j;
                if (IsEven(array[j])) {
                    break;
                }

                // is odd number
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }

    static boolean IsEven(int value) {
        return value % 2 == 0;
    }
}


