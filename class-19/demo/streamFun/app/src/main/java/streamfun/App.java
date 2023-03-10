/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package streamfun;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.invoke.CallSite;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class App {
    private static List<Java401Person> setup() {
        List<Java401Person> java401People = new ArrayList<>();
        java401People.add(new Java401Person("Devon", false, 2, 0, new String[]{"Jazz", "Chocolate"}, true, "exercise", true));
        java401People.add(new Java401Person("Sharmarke", false, 0, 0, new String[0], true, "basketball", true));
        java401People.add(new Java401Person("Adrian", true, 1, 0, new String[]{"Odesza"}, false, "golf", true));
        java401People.add(new Java401Person("Ryan", false, 1, 1, new String[]{"Pumpkin"}, true, "hiking", true));
        java401People.add(new Java401Person("Rey", true, 2, 0, new String[]{"Indi Bean", "Pepper Jones"}, true, "hiking", true));
        return java401People;
    }

    public static void main(String[] args) {
        List<Java401Person> people = setup();



        // ***** TEST CASE 1 *****

        System.out.println("Test Case 1 Imperative: Print the first 10 numbers");
        int[] numbersFrom1To10Array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int i  = 0; i < 10; i++) {
            System.out.println(numbersFrom1To10Array[i]);
        }

        System.out.println("Test Case 1 Functional:");
        // Streams need a source and a terminal operation
        // .boxed is used to convert a primitive to an Integer
        List<Integer> numbersFrom1To10Functional = IntStream.range(1, 11).boxed().collect(toList());
        System.out.println(numbersFrom1To10Functional);



        // ***** TEST CASE 2 *****

        System.out.println("Test Case 2 I(mperative): Print the last 5 numbers (from 1-10)");
        for(int i = 5; i < 10; i++) {
            System.out.println(numbersFrom1To10Array[i]);
        }

        System.out.println("Test Case 2 F(unctional):");
        //                                             source op           intermediate ops    terminal op
        List<Integer> numbersFrom6To10Functional = IntStream.range(1, 11).skip(5).boxed().collect(toList());
        System.out.println(numbersFrom6To10Functional);



        // ***** TEST CASE 3 *****

        // use numbersFrom1To10Array from TEST CASE 1
        System.out.println("Test Case 3 I: Print the first 5 numbers (starting with 1)");
        for(int i = 0; i < 5; i++) {
            System.out.println(numbersFrom1To10Array[i]);
        }

        System.out.println("Test Case 3 F:");
        List<Integer> numbersFrom1To5Functional = IntStream.range(1, 11).limit(5).boxed().collect(toList());
        System.out.println(numbersFrom1To5Functional);



        // ***** TEST CASE 4 *****

        System.out.println("Test Case 4 I: Print the number of people in our class");
        System.out.println(people.size());

        System.out.println("Test Case 4 F:");
        long countPeople = people.stream().count();
        // long countingPeople = people.stream().collect(counting());
        System.out.println(countPeople);



        // ***** TEST CASE 5 *****

        System.out.println("Test Case 5 I: Print out if anyone is tired");
        boolean isAnyoneTired = false;
        for(Java401Person person : people) {
            if (person.isTired) {
                System.out.println("Someone is tired!");
                isAnyoneTired = true;
                break;
            }
        }
        if(!isAnyoneTired) {
            System.out.println("No one is tired!");
        }

        System.out.println("Test Case 5 F:");
        // Method 1: findAny()/findFirst() -> logically the same FOR THIS EXAMPLE
        Java401Person tiredPerson = people.stream().filter(person -> person.isTired == true).findAny().orElse(null);
        if(tiredPerson != null) {
            System.out.println("Someone is tired!");
        } else {
            System.out.println("No one is tired!");
        }

        // Method 2: anyMatch()
        boolean isAnyoneTiredFunctional = people.stream().anyMatch(person -> person.isTired);
        if(isAnyoneTiredFunctional) {
            System.out.println("Someone is tired!");
        } else {
            System.out.println("No one is tired!");
        }



        // ***** TEST CASE 6 *****

        System.out.println("Test Case 6 I: Print out if everyone is smart");
        boolean isEveryoneSmart = true;
        for(Java401Person person : people) {
            if(person.isSmart == false) {
                System.out.println("Someone is not smart!");
                isEveryoneSmart = false;
                break;
            }
        }
        if(isEveryoneSmart) {
            System.out.println("Everyone is smart!");
        }

        System.out.println("Test Case 6 F:");
        // Method 1: noneMatch()
        boolean isEveryoneSmartFunctional = people.stream().noneMatch(person -> person.isSmart == false);
        if(isEveryoneSmartFunctional) {
            System.out.println("Everyone is smart!");
        } else {
            System.out.println("Someone is not smart!");
        }

        // Method 2: allMatch()
        isEveryoneSmartFunctional = people.stream().allMatch(person -> person.isSmart == true);
        if(isEveryoneSmartFunctional) {
            System.out.println("Everyone is smart!");
        } else {
            System.out.println("Someone is not smart!");
        }



        // ***** TEST CASE 7 *****

        System.out.println("Test Case 7 I: Print out the sum of all our pet numbers, and print the average");
        double averageNumOfPets = 0.0;
        int totalNumPets = 0;
        for(Java401Person person : people) {
            averageNumOfPets += person.numPets;
            totalNumPets += person.numPets;
        }
        averageNumOfPets /= people.size();
        System.out.println("Average num of pets: " + averageNumOfPets);
        System.out.println("Total num of pets: " + totalNumPets);

        System.out.println("Test Case 7 F:");
        double averageNumOfPetsFunctional = people.stream().collect(averagingDouble(person -> person.numPets));
        int totalNumPetsFunctional = people.stream().collect(summingInt(person -> person.numPets));
        System.out.println("Average num of pets: " + averageNumOfPetsFunctional);
        System.out.println("Total num of pets: " + totalNumPetsFunctional);



        // ***** TEST CASE 8 *****

        System.out.println("Test Case 8 I: Print how many people are tired, and not tired");
        List<Java401Person> tiredPeople = new ArrayList<>();
        List<Java401Person> notTiredPeople = new ArrayList<>();
        for(Java401Person person : people) {
            if(person.isTired) {
                tiredPeople.add(person);
            } else {
                notTiredPeople.add(person);
            }
        }
        System.out.println("Tired people: " + tiredPeople);
        System.out.println("Not tired people: " + notTiredPeople);

        System.out.println("Test Case 8 F:");
        Map<Boolean, Long> tiredPeopleMap = people.stream().collect(groupingBy(person -> person.isTired, counting()));
        System.out.println("tiredPeopleMap: " + tiredPeopleMap);



        // ***** TEST CASE 9 *****

        System.out.println("Test Case 9 I: Print out all our names in sorted alphabetical order (ascending)");
        System.out.println("People before sorting: " + people);
        List<Java401Person> sortedPeople = new ArrayList<>(people);
        Collections.sort(sortedPeople);
        System.out.println("People after sorting: " + sortedPeople);

        System.out.println("Test Case 9 F:");
        List<String> sortedPeopleFunctional = people.stream().map(person -> person.name).sorted().collect(toList());
        System.out.println("Sorted people functional: " + sortedPeopleFunctional);



        // ***** TEST CASE 10 *****

        System.out.println("Test Case 10 I: Print out all our names, separated by semicolons");
        String peopleSeparatedBySemicolons = "";
        for(Java401Person person : people) {
            peopleSeparatedBySemicolons += person.getName() + ";";
        }
        System.out.println(peopleSeparatedBySemicolons);

        System.out.println("Test Case 10 F:");
        String peopleSeparatedBySemicolonsFunctional = people.stream().map(Java401Person::getName).collect(joining(";"));
        System.out.println(peopleSeparatedBySemicolonsFunctional);



        // ***** TEST CASE 11 *****

        System.out.println("Test Case 11 I: Print out all our distinct hobbies");
        List<String> hobbies = new ArrayList<>();
        Set<String> hobbySet = new HashSet<>();
        Map<String, String> hobbyMap = new HashMap<>();
        for(Java401Person person : people) {
            // using arrayList
            if(!hobbies.contains(person.hobby)) {
               hobbies.add(person.hobby);
            }

            // using set
            hobbySet.add(person.hobby);

            // using map, would prefer to add logic to keep count of how many times we've seen it (would also need to
            // change 'putIfAbsent()' to 'put()'
            hobbyMap.putIfAbsent(person.hobby, "wheeeeee");
        }
        System.out.println("Unique hobbies (list): " + hobbies);
        System.out.println("Unique hobbies (set): " + hobbySet);
        System.out.println("Unique hobbies (map): " + hobbyMap);

        System.out.println("Test Case 11 F:");
        List<String> uniqueHobbiesFunctional = people.stream().map(person -> person.hobby).distinct().collect(toList());
        System.out.println(uniqueHobbiesFunctional);
    }
}


























