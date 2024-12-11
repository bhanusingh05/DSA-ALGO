public class Day1 {
    public static void main(String[] args) {
        // Variables and Data Types

        // Variables: Used to store data values.
        int number1 = 10; // integer data type
        int number2 = 20; // another integer

        // Sum of two numbers
        int sum = number1 + number2; // Adding two integers
        System.out.println("Sum: " + sum);

        // Product of two numbers
        int product = number1 * number2; // Multiplying two integers
        System.out.println("Product: " + product);

        // Area of a Circle
        double radius = 7.5; // double data type for precision
        double area = Math.PI * radius * radius; // Area formula: πr^2
        System.out.println("Area of Circle: " + area);

        // Type Casting

        // Implicit Casting (Widening): int to double
        double widenedValue = number1; // no explicit cast needed
        System.out.println("Widened Value: " + widenedValue);

        // Explicit Casting (Narrowing): double to int
        int narrowedValue = (int) area; // fractional part is truncated
        System.out.println("Narrowed Value (Area as int): " + narrowedValue);

        // Comments

        // Single-line comment: This is used to write brief notes.

        /*
         * Multi-line comment:
         * This is useful for writing detailed explanations or disabling
         * blocks of code temporarily during debugging.
         */

        // Primitive Data Types
        byte aByte = 127; // 8-bit integer
        short aShort = 32000; // 16-bit integer
        long aLong = 123456789L; // 64-bit integer
        float aFloat = 3.14F; // 32-bit floating point
        boolean isJavaFun = true; // boolean type

        // Printing different data types
        System.out.println("Byte value: " + aByte);
        System.out.println("Short value: " + aShort);
        System.out.println("Long value: " + aLong);
        System.out.println("Float value: " + aFloat);
        System.out.println("Is Java fun? " + isJavaFun);
    }
}