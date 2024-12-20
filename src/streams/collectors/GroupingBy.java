package streams.collectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingBy {

  public static void main(String[] args) {

    //group a list of strings by their length but only include strings that
    //contain the letter "a".
    groupByLengthAndFilterOnlyHavingCharacterA();

    //Given a String, find the first non-repeated character in it using Stream functions.
    firstNonRepeatingCharacter();

    //Given a String, find the second non-repeated character in it using Stream functions.
    secondNonRepeatingCharacter();

    //Write a Java 8 program to find the frequency of each element in
    //an array or a list using streams and collectors.
    elementFrequency();

    //Write a Java 8 program to find the frequency of each character in
    //a given string using the stream API and collectors.
    characterFrequency();

    //Find the most expensive item in each category present in a list of items.
    findMostExpensiveItemInEachCategory();

    findCountOfEachNumberInListOfList();

    listOfNamesByCity();

    getCountOfWordsStartingWithSameCharacter();

    getAverageAgeByGrade();


  }

  private static void findMostExpensiveItemInEachCategory() {
    List<Item> items = List.of(
        new Item("Cake", "FMC", 100.00),
        new Item("Random", "ABC", 80.00),
        new Item("Brush", "FMC", 10.00),
        new Item("Random2", "ABC", 180.00)
    );

    items.stream()
        .collect(Collectors.groupingBy(Item::category,
            Collectors.maxBy(Comparator.comparingDouble(Item::price))))
        .forEach(
            (k, v) -> System.out.println("Category: " + k + " has most expensive item " + v.get()));
  }

  private static void findCountOfEachNumberInListOfList() {
        /*
         You are given a list of lists, where each list contains integers. Count how many times each unique integer
         appears across all the lists and return the result as a Map<Integer, Integer>, where the key is the integer
         and the value is its count.
         */
    List<List<Integer>> listOfList = List.of(
        List.of(1, 3, 5, 7, 8),
        List.of(9, 3, 10, 17, 8),
        List.of(1, 13, 15, 7, 8)
    );

    Map<Integer, Long> collected = listOfList.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(collected);
  }

  private static void listOfNamesByCity() {

        /*
          You are given a list of people with their names and cities. Group the people by their city
          and return a Map<String, List<String>> where the key is the city and the value is a list of
          names of people from that city.
         */
    List<Person> persons = List.of(
        new Person("Avi", "DHN"),
        new Person("Raj", "DHN"),
        new Person("Alex", "DEL"),
        new Person("Sony", "DEL"),
        new Person("Robert", "BLR")
    );

    Map<String, List<String>> collected = persons.stream()
        .collect(Collectors.groupingBy(Person::city,
            Collectors.mapping(Person::name, Collectors.toList())));
    System.out.println(collected);
  }

  private static void getCountOfWordsStartingWithSameCharacter() {
    /*
        Problem: Given a list of words, group the words by their first character and count how many words start with
        each character. Return a Map<Character, Long>, where the key is the first character and the value is the count
        of words that start with that character.
     */

    List<String> words = Arrays.asList("apple", "banana", "apricot", "avocado", "berry", "cherry");
    Map<Character, Long> collected = words.stream()
        .collect(Collectors.groupingBy(e -> e.charAt(0), Collectors.counting()));
    System.out.println(collected);
  }

  private static void getAverageAgeByGrade() {
    /*
        Problem: Given a list of students where each student has a name, age, and grade, group the students by their
        grade and then calculate the average age of students in each grade. Return a Map<String, Double>,
        where the key is the grade and the value is the average age of students in that grade.
     */

    List<Student> students = List.of(
        new Student("Alice", 15, "A"),
        new Student("Bob", 16, "B"),
        new Student("Charlie", 15, "A"),
        new Student("David", 17, "B")
    );

    Map<String, Double> averageAgeByGrade = students.stream()
        .collect(Collectors.groupingBy(Student::grade, Collectors.averagingInt(Student::age)));
    System.out.println(averageAgeByGrade);
  }

  private static void groupByLengthAndFilterOnlyHavingCharacterA() {
    List<String> words = List.of("apple", "banana", "pear", "plum");
    Map<Integer, List<String>> collected = words.stream()
        .collect(Collectors.groupingBy(String::length,
            Collectors.filtering(e -> e.contains("a"), Collectors.toList())));
    System.out.println(collected);
  }

  private static void secondNonRepeatingCharacter() {
    String input = "Java is my programming language";
    input.chars()
        .mapToObj(c -> (char) c)
        .collect(
            Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e -> e.getValue() == 1L)
        .skip(1L)
        .findFirst()
        .ifPresent(v -> System.out.println(v.getKey()));

  }

  private static void firstNonRepeatingCharacter() {
    String input = "aaabbbcdd";
    input.chars()
        .mapToObj(c -> (char) c)
        .collect(
            Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e -> e.getValue() == 1)
        .findFirst().ifPresent(val -> System.out.println(val.getKey()));

    //Another simpler approach
    input.chars()
        .mapToObj(i -> (char) i)
        .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
        .findFirst()
        .ifPresent(System.out::println);
  }

  private static void elementFrequency() {
    List<String> list = List.of("Avinash", "Raj", "Avinash", "Rohan", "Raj");
    list.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .forEach((k, v) -> System.out.println(k + " repeats " + v + " times"));
  }

  private static void characterFrequency() {
    String input = "zasavikumaraas";
    input.chars()
        .mapToObj(e -> (char) e)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .forEach((k, v) -> System.out.println(k + " repeats " + v + " times"));
  }
}