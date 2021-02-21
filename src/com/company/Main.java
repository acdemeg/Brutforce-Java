package com.company;


import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final String[] base = {"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "R", "T", "U", "V", "W", "X", "Y", "Z",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};


    private static String test;

    public static void main(String[] args) throws IOException {
        System.out.print("Enter test password: ");
        test = new Scanner(System.in).nextLine();

        final long startTime =  System.currentTimeMillis();
        String answer = "";
        answer = invokeSearch(answer);

        final long executionTime = System.currentTimeMillis() - startTime;
        print(answer, (double) executionTime);
        //noinspection ResultOfMethodCallIgnored
        System.in.read();
    }

    private static void print(String answer, double executionTime) {
        System.out.println("Password = " + answer);
        System.out.println("Time spent: " + executionTime /1_000 + " sec");
    }

    private static String invokeSearch(String answer) {
        for(int maxDepth = 0; maxDepth <= 5; maxDepth++){
            answer = recurrentSearch("", maxDepth, 0);
            if(!answer.isEmpty())
                break;
        }
        return answer;
    }

    private static String recurrentSearch(final String acc, final int maxDepth, final int depth){

        String answer = Arrays.stream(base).filter(s -> acc.concat(s).compareTo(test) == 0)
                .collect(Collectors.joining());

        if(acc.concat(answer).compareTo(test) == 0)
            return acc.concat(answer);


        if(answer.isEmpty() && depth < maxDepth){
            Set<String> answers = Arrays.stream(base).map(
                    s -> recurrentSearch(acc.concat(s), maxDepth, depth + 1))
                    .collect(Collectors.toSet());

            return answers.stream().filter(s -> s.compareTo(test) == 0)
                    .collect(Collectors.joining());
        }

        return acc.concat(answer);
    }
}












