/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        useASet();
        useAMap();
    }

    // HASHSET ->
    // "Tell me the students who have answered a question (without duplicates)"
    public static void useASet(){
        ArrayList<String> studentsWhoAnswered = new ArrayList<>();
        studentsWhoAnswered.add("Sharmarke");
        studentsWhoAnswered.add("Devon");
        studentsWhoAnswered.add("Ryan");
        studentsWhoAnswered.add("Ryan");
        studentsWhoAnswered.add("Adrian");

        // HASHSET -> VALUE ONLY!
        HashSet<String> setOfStudentAnswered = new HashSet<>();
        setOfStudentAnswered.add("Rey");
        setOfStudentAnswered.addAll(studentsWhoAnswered);
    }


    // MAP -> K:V
    // Family Feud (how many times a person answered a question)
    public static void useAMap(){
        HashMap<String, Integer> personAnsweredQuestion = new HashMap<>();
        personAnsweredQuestion.put("Ryan", 8);
        personAnsweredQuestion.put("Adrian", 6);
        personAnsweredQuestion.put("Sharmarke", 1000);
        personAnsweredQuestion.put("Devon", 8);

        int questions = personAnsweredQuestion.get("Adrian");
        personAnsweredQuestion.put("Adrian", questions + 2);


    }
}
