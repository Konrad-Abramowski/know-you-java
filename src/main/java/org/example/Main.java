package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        collectionRemove();
        //       arraysAsList();
        //        forEach();
        //        streams();
        //        streamsTwo();
        //        inheritance();
        //        typeInference();
        //        usingToList();
        //        defaultSwitch();
        //        recordCanonicalConstructors();
    }

    private static void collectionRemove() {
        List<Integer> list = new ArrayList<Integer>(List.of(1, 2, 3));

        list.remove(1);

        System.out.println(list);

        Collection<Integer> list2 = new ArrayList<Integer>(List.of(1, 2, 3));

        list2.remove(1);

        System.out.println(list2);

        var list3 = new ArrayList<Integer>(List.of(1, 2, 3));

        list3.remove(1);

        System.out.println(list3);
    }

    private static void arraysAsList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, null);

        try {
            numbers.add(4);
        } catch (Exception ex) {
            System.out.print("add unsupported ");
        }

        try {
            numbers.set(2, 2);
        } catch (Exception ex) {
            System.out.print("set unsupported ");
        }

        System.out.print(numbers);

    }

    private static void forEach() {
        boolean resultIsSeven = true;
        int iterationCounter = 0;

        while (resultIsSeven) {
            List<String> names = List.of("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");

            List<String> inUppercase = new ArrayList<>();

            names.parallelStream()
                    .map(String::toUpperCase)
                    .forEach(name -> inUppercase.add(name));

            resultIsSeven = names.size() == 7;

            iterationCounter++;

            System.out.println(names.size());
            System.out.println(inUppercase.size());
        }
        System.out.println("Number of iterations: " + iterationCounter);
    }

    private static void streams() {
        int[] factor = new int[] { 2 };

        var numbers = List.of(1, 2, 3);

        var stream = numbers.stream()
                .map(number -> number * factor[0]);

        factor[0] = 0;

        stream.forEach(System.out::print);
    }

    static class MultiplierDetails {
        private int multiplier;

        MultiplierDetails(int multiplier) {
            this.multiplier = multiplier;
        }
    }

    private static void streamsTwo() {
        List.of(1, 2, 3).stream()
                .parallel()
                .map(number -> transform(number))
                .sequential()
                .forEach(number -> print(number));
    }

    public static int transform(int number) {

        System.out.println("transform: " + Thread.currentThread());
        return number * 2;

    }

    public static void print(int number) {
        System.out.println("print: " + Thread.currentThread());
    }

    static class Parent {

        public Parent() {
            System.out.println("In parent constructor");
            check();
        }

        public void check() {
            System.out.println("In parent check");
        }

    }

    static class Child extends Parent {

        private String value;

        public Child(String value) {
            System.out.println("In child constructor");
            this.value = value;
        }

        public void check() {
            System.out.println("In child check");
            if (value.length() == 0) {
                throw new RuntimeException("value given is empty"

                );
            }
        }
    }

    private static void inheritance() {
        try {
            new Child("");
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    private static void typeInference() {
        //        var list = List.of(1, 1.2, "hello", new StringBuilder("there"));
        //
        //        try {
        //            list.add(new Sample());
        //
        //        } catch (Exception ex) {
        //            System.out.println(ex);
        //
        //        }
    }

    static class Sample {
    }

    private static void usingToList() {
        var numbers = List.of(1, 2, 3);

        var doubled = numbers.stream()
                .map(number -> number * 2)
                .collect(toList());
        //        .toList();

        System.out.println(doubled);
    }

    private static void defaultSwitch() {
        System.out.println(lights(When.DAY));
    }

    private static String lights(When when) {
        return switch (when) {
            case DAY -> "lights optional";
            case NIGHT -> "lights required";
        };
    }

    enum When {
        DAY,
        NIGHT
        //        DAWN
    }

    private static void recordCanonicalConstructors() {
        System.out.println(new Year(2023));
        System.out.println(new Year(1993));
        System.out.println(new Year(24));

        var list = new ArrayList<>(List.of("sample string"));
        var immutableRecord = new ImmutableRecord(list);
        System.out.println(immutableRecord);

        list.add("another string");

        System.out.println(immutableRecord);
    }

    record ImmutableRecord(List<String> list) {
        //        ImmutableRecord {
        //            list = List.copyOf(list);
        //        }
    }

    record Year(int year) {


        //        compact constructor
        //        nie możemy użyć this
        //        Year {
        //            if (year < 0) {
        //                throw new RuntimeException("year should not be negative");
        //            }
        //            if (year < 100) {
        //                this.year = 2000 + year;
        //            }
        //        }

        //        canonical constructor - czyli taki klasyczny
        //        Year(int year) {
        //            if (year < 0) {
        //                throw new RuntimeException("year should not be negative");
        //            }
        //            if (year < 100) {
        //                this.year = 2000 + year;
        //            }
        //            //            else {
        //            //                this.year = year;
        //            //            }
        //        }
    }
}