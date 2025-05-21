package dataStructures.arrays;

public class ArrayExample {

    public static void main(String[] args) {
        // 1. Declaration of int[] and String[]
        int[] intArray;
        String[] stringArray;

        // 2. Initialization
        // Using new int[5] (creates an array of 5 integers, initialized to default value 0)
        intArray = new int[5]; 
        System.out.println("intArray initialized with new: " + java.util.Arrays.toString(intArray));

        // With initial values
        int[] initializedIntArray = {1, 2, 3, 4, 5};
        System.out.println("initializedIntArray with values: " + java.util.Arrays.toString(initializedIntArray));

        stringArray = new String[]{"apple", "banana", "cherry"};
        System.out.println("stringArray with values: " + java.util.Arrays.toString(stringArray));

        // 3. Accessing elements
        // Accessing the first element (index 0)
        System.out.println("First element of initializedIntArray: " + initializedIntArray[0]);

        // Modifying an element
        System.out.println("intArray before modification: " + java.util.Arrays.toString(intArray));
        intArray[1] = 10; // Set the second element to 10
        System.out.println("intArray after modifying intArray[1]: " + java.util.Arrays.toString(intArray));

        // 4. Iterating
        System.out.println("\nIterating through initializedIntArray using a standard for loop:");
        for (int i = 0; i < initializedIntArray.length; i++) {
            System.out.print(initializedIntArray[i] + " ");
        }
        System.out.println();

        System.out.println("\nIterating through stringArray using an enhanced for loop (for-each):");
        for (String fruit : stringArray) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // 5. Getting array length
        System.out.println("\nLength of intArray: " + intArray.length);
        System.out.println("Length of stringArray: " + stringArray.length);

        // 6. Brief example of a 2D array
        System.out.println("\n2D Array Example:");
        // Declaration and initialization of a 2D array (3 rows, 2 columns)
        int[][] twoDArray = {
            {10, 20},
            {30, 40},
            {50, 60}
        };

        // Accessing an element in a 2D array
        System.out.println("Element at twoDArray[1][0]: " + twoDArray[1][0]); // Accesses element in the second row, first column

        // Iterating through a 2D array
        System.out.println("Iterating through twoDArray:");
        for (int i = 0; i < twoDArray.length; i++) { // Iterate over rows
            for (int j = 0; j < twoDArray[i].length; j++) { // Iterate over columns in the current row
                System.out.print(twoDArray[i][j] + " ");
            }
            System.out.println(); // Newline after each row
        }
    }
}
