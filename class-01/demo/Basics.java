import java.util.Random;

public class Basics{
  public static void main(String[] args){
    System.out.println("This will run");
    // int -> Integer
    // PRIMITIVES!

    // TYPE VAR NAME = VALUE;
    int int1 = 5;
    long long1 = 100;
    byte byte1 = (byte)0xFF;
    short short1 = (short)0xFFF;
    boolean bool1 = true;

    //DECIMALS
    float float1 = 0.1f;
    float float2 = 0.2f;
    float float3 = float1 + float2;
    boolean booleanFloat = (float3 == 0.3f);
    System.out.println("booleanFloat: " + booleanFloat);
    System.out.println("float3: " + float3);

    double double1 = 0.1;
    double double2 = 0.2;
    double double3 = double1 + double2;
    boolean booleanDouble = (double3 == 0.3); 
    System.out.println("booleanDouble: " + booleanDouble);
    System.out.println("double3: " + double3);

    char char1 = 'a';
    char char2 = 'b';
    System.out.println("char1: " + char1);
    System.out.println(char1 + char2);
    // String Builder

    // Keyword NEW constructs a new Object
    Random rand = new Random();

    double doubleRandom1 = rand.nextDouble();
    System.out.println("doubleRandom1: " + doubleRandom1);

    Integer IntegerRandom2 = rand.nextInt(100);
    System.out.println("IntRandom2: " + IntegerRandom2);


    //STRINGS

    int int2 = 20;
    String numberString1 = "30";
    System.out.println("int2 + numberString1: " + (int2 + numberString1)); //2030
    System.out.println("int2 + Integer.fromString(numberString1) " + (int2 + Integer.valueOf(numberString1)));

    // Comparing strings
      // == -> compare primitives, XXX===XXX
      // Objects .equals()

      String hello = "hello";
      String hel = "hel";
      String lo = "lo";
      System.out.println("\"hello\" == \"hel\" + \"lo\": " + ("hello" == ("hel" + "lo"))); // true
      System.out.println("\"hello\" == hel + lo: " + ("hello" == (hel + lo))); //false

      System.out.println("\"hello\".equals(\"hel\" + \"lo\"): " + ("hello".equals("hel" + "lo"))); //True
      System.out.println("\"hello\".equals(hel + lo): " + ("hello".equals(hel + lo))); // true

      // Conditionals
         // If-else statement
      int number1 = 7;
      int number2 = 10;
      if (number2 > number1) {
        // condition 1
        System.out.println(number2 + " is greater than " + number1);
      } else if (number2 == number1) {
        //condition 2
        System.out.println("The numbers are equal");
      } else {
        // alternative condition
        System.out.println(number2 + " is less than " + number1);
      }

      // Switch case/statement
      int month = 13;
    String monthString;
    switch (month) {
      default: monthString = "Invalid month";
        break;
      case 1:  monthString = "January";
        break;
      case 2:  monthString = "February";
        break;
      case 3:  monthString = "March";
        break;
      case 4:  monthString = "April";
        break;
      case 5:  monthString = "May";
        break;
      case 6:  monthString = "June";
        break;
      case 7:  monthString = "July";
        break;
      case 8:  monthString = "August";
        break;
      case 9:  monthString = "September";
        break;
      case 10: monthString = "October";
        break;
      case 11: monthString = "November";
        break;
      case 12: monthString = "December";
        break;
      
    }
    System.out.println(monthString);

      // Loops and Arrays

        // WHILE loop - have a condition
        int counter = 0;
        while(counter < 6){
          System.out.println("counter: " + counter);
          counter++; // -> counter = counter + 1;
          if(counter > 10){
            // FULL STOP -> returning outta da loop
            break;
          }
          if(counter > 3){
            // Skip current iteration
            continue;
          }
        }
        // FOR LOOP - you know the amount of time to iterate
          // Java Array -> STATIC. THey can't grow or shrink. No push or add
          int[] intArray = {3,2,1};
          String[] strArray = {"a", "{"};

          for(int i = 0; i < intArray.length; i++){
            System.out.println("IntArray at: " + i + "equals: " + intArray[i]);
          }

          for(int currentInt : intArray){
            System.out.println("currentInt: " + currentInt);
          }


      // Functions/methods in Java
      String newString = stringReturner();
      System.out.println("newString: " + newString);
  }

  // What are the 2 things functions/methods do
    // 1. Return value
    // 2. Do something
    

  public static String stringReturner(){
    return "Zork";
  }

}