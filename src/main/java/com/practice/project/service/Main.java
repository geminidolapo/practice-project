package com.practice.project.service;

import com.practice.project.constant.ShippingType;
import com.practice.project.dto.BodyMassIndex;
import com.practice.project.dto.record.InsuranceRecord;
import com.practice.project.dto.record.PremiumsAndTotalRecord;
import com.practice.project.model.Product;
import com.practice.project.service.function.MathOperation;
import com.practice.project.service.sample.Clipboard;
import com.practice.project.service.sample.ShippingCostCalculator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        @Cleanup FileReader fileReader = new FileReader("filePath");

        // used to show how enum can be used to replace if else
        var calculator = new ShippingCostCalculator();
        var cost = calculator.calculateShippingCost(ShippingType.EXPRESS, 2.5);
        log.info("Shipping cost: {}", cost);


        // Getting the singleton instances
        Clipboard clipboard1 = Clipboard.getInstance();
        Clipboard clipboard2 = Clipboard.getInstance();

        clipboard1.copy("Java");
        clipboard2.copy("Design patterns");

        System.out.println(clipboard1.paste()); // output: Design patterns
        System.out.println(clipboard2.paste()); // output: Design patterns


        //using gui to take input
//        String name = JOptionPane.showInputDialog ("Enter your name");
//        JOptionPane.showInputDialog(null, "Hello "+name);
//        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
//        JOptionPane.showMessageDialog(null, "You are "+age+" years old");
//        double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height"));
//        JOptionPane.showMessageDialog(null, "You are " +height+" cm tall");


        //using records
        BodyMassIndex bodyMassIndex = new BodyMassIndex(5D,3D,20);
        Double weight = bodyMassIndex.calculate();
        log.info("weight is {}", weight);

        //lazy loading sample
        Product product = new Product("Apple Juice",3.59);
        System.out.println(product.getFinalPrice());


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);


        //iterating over a hash map
        Map<String, Integer> map = new HashMap<>();
        for (var entry: map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " -> " + value);
        }


        //Using iterator to traverse list
        List<String> list = new LinkedList<>(); // Assume list is a Linkedlist
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String thisName = iterator.next();
            // Process name
        }

        List<String> words = new ArrayList<>(List.of("A", "B", "C"));
        words.removeIf(word -> word.equals("A")); // Use removeIf method to remove elements matching the condition
        System.out.println(words); // Output: [B, C]



        // Create a CopyOnWriteArrayList
        List<String> copyOnWriteArrayList = List.of("Apple","Banana","Cherry");

        // Iterate over the list
        for (String fruit : copyOnWriteArrayList) {
            System.out.println(fruit + "1");
        }

        //    2 ways of creating platform threads
        //    1. by using the Thread constructor and passing a runnable lambda to it
        //    2. by using the Thread’s builder method ofPlatform()

        // Modify the list concurrently
        Runnable modifier = () -> {
            list.add("Date");
            list.remove("Apple");
        };

        // Start a thread to modify the list
        new Thread(modifier).start();

        // Iterate over the list again
        for (String fruit : list) {
            System.out.println(fruit + "2");
        }

        // Sleep to allow the modification thread to complete
        try {
            Thread.sleep(1000);  // Adjust sleep time as necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Iterate over the list after modification
        for (String fruit : list) {
            System.out.println(fruit + "3");
        }

        /*
        You need a fixed-size list backed by an array.
        You might need to modify existing elements.
        You require null elements in your list.
        You want changes in the list to reflect in the original array.
         */
        String[] numbersArray = {"One", "Two", "Three"};
        List<String> numbersList = Arrays.asList(numbersArray);
        log.info(numbersList.toString());

        /*
        You need an immutable list.
        You want to prevent modifications to your list.
        You don’t need null elements.
        You’re working with unmodifiable data sets.
        */
        List<String> numberList = List.of("One", "Two", "Three");
        log.info(numberList.toString());


        // Define a Consumer that prints a string
        Consumer<String> printConsumer = System.out::println;

        // Use the Consumer
        printConsumer.accept("Hello, World!");  // Output: Hello, World!
        printConsumer.accept("This is a Consumer example in Java.");


        //I used a supplier to ensure calculateSum doesn't initialize till its needed
        Supplier<Integer> calculatedSum = Main::calculateSum;
        calculatedSum.get();

        // This gives you an instance of InsuranceRecord with default values.
        InsuranceRecord defaultRecord = InsuranceRecord.getDefaultInsurance();

        //access to the ststic class in InsuranceRecord
        InsuranceRecord.PolicyValidator validator = new InsuranceRecord.PolicyValidator();
        validator.validate(defaultRecord);

        MathOperation addition = Integer::sum;
//        MathOperation addition = (a, b) -> a + b;
        System.out.println("Sum: " + addition.operate(5, 3));


        double d1 = 0.1;
        double d2 = 0.2;
        double sumT = d1 + d2;

        System.out.println(sumT == 0.3); // false
        System.out.println(sumT); // 0.30000000000000004

        double epsilon = 1e-10;
        System.out.println(Math.abs(sumT - 0.3) < epsilon); // true


        List<InsuranceRecord> insurancePolicies = Arrays.asList(
                new InsuranceRecord("Home Insurance", 1500),
                new InsuranceRecord("Car Insurance", 1200),
                new InsuranceRecord("Health Insurance", 2000)
        );

        /* teeing is a new static method in the `Collectors` interface that
            enables performing two distinct operations on a collection in parallel
            and then merging their results.
        */
        PremiumsAndTotalRecord premiumsAndTotal = insurancePolicies.stream()
                .collect(Collectors.teeing(
                        Collectors.summingDouble(InsuranceRecord::premium),
                        Collectors.mapping(InsuranceRecord::premium, Collectors.toList()),
                        PremiumsAndTotalRecord::new
                ));

        log.info("PremiumsAndTotal {}",premiumsAndTotal);

        outer: // Label for the outer loop
        for (int i = 1; i <= 3; i++) {
            System.out.println("Outer loop iteration: " + i);
            for (int j = 1; j <= 3; j++) {
                System.out.println("  Inner loop iteration: " + j);
                if (i == 2 && j == 2) {
                    System.out.println("    Breaking out of the outer loop!");
                    break outer; // Breaks the outer loop labeled "outer"
                }
            }
        }
        System.out.println("Loop finished.");

        Flux<Integer> fluxNum = Flux.just(1, 2, 3, 4, 5);
        fluxNum.subscribe(System.out::println);
    }

    private static int calculateSum(int... numbers){
        int sum = 0;
        for (int num : numbers){
            sum+=num;
        }
        return sum;
    }
}
