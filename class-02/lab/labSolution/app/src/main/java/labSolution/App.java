/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package labSolution;

import java.util.Arrays;

public class App {
    static int[][] weeklyMonthTemperatures = {
            {66, 64, 58, 65, 71, 57, 60},
            {57, 65, 65, 70, 72, 65, 51},
            {55, 54, 60, 53, 59, 57, 61},
            {65, 56, 55, 52, 55, 62, 57}
    };
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        lowestAverageArray(weeklyMonthTemperatures);
    }


    // Calculating Averages
    // Write a method that accepts an array of integers and calculates and returns the average of all the values in the array.
    public static int calculateAverage(int[] arrayOfValues){
        int sum = 0;
        int arrayLength = arrayOfValues.length;
        // FOR LOOP
        for (int i = 0; i < arrayOfValues.length; i++) {
            // every iteration we add value to sum
            sum = sum + arrayOfValues[i];
        }
        // sum of all values / length of array
        return sum/arrayLength;
    }

    // Arrays of Arrays
    // Given an array of arrays calculate the average value for each array and return the array with the lowest average.
    // Input: array of arrays AKA a Matrix
    // Output: array with the lowest average
    // Logic: Find the average
    // Logic: find average for EACH array -> LOOP
    public static int[] lowestAverageArray(int[][] weeklyMonthTemperatures){
        int[] lowestAverageArray = weeklyMonthTemperatures[0];
        int lowestAverage = calculateAverage(weeklyMonthTemperatures[0]);
        for (int i = 0; i < weeklyMonthTemperatures.length; i++) {
            int currentArrayAverage = calculateAverage(weeklyMonthTemperatures[i]);
            if(currentArrayAverage < lowestAverage){
                lowestAverageArray = weeklyMonthTemperatures[i];
            }
        }
        return lowestAverageArray;
    }
}

/*
// Daily average temperatures for Seattle, October 1-28 2017
int[][] weeklyMonthTemperatures = {
  {66, 64, 58, 65, 71, 57, 60},
  {57, 65, 65, 70, 72, 65, 51},
  {55, 54, 60, 53, 59, 57, 61},
  {65, 56, 55, 52, 55, 62, 57}
};

 */
