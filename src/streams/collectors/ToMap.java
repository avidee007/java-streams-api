package streams.collectors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToMap {

  public static void main(String[] args) {

    removeDuplicateBasedUponName();

    mapKeyAndLargestValueInArrayAsValue();

    mergeTwoMapWithSumOfValueIfKeyExistsInBothMap();

    longestWordInEachCategory();

    mergeTwoListIntoMap();
  }

  private static void mergeTwoListIntoMap() {
    /*
         You are given two lists: one with keys and one with values. Merge them into a single Map<String, String>
         where each key from the first list is associated with the corresponding value from the second list.
     */

    List<String> first = List.of("1", "2", "3");
    List<String> second = List.of("First", "Second", "Third");
    Stream.iterate(0, i -> i + 1)
        .limit(first.size())
        .collect(Collectors.toMap(first::get, second::get))
        .forEach((k, v) -> System.out.println(k + " ---> " + v));
  }

  private static void longestWordInEachCategory() {
    Map<String, List<String>> categories = Map.of(
        "Fruits", Arrays.asList("apple", "banana", "watermelon"),
        "Animals", Arrays.asList("cat", "elephant", "tiger")
    );

    Map<String, String> longestWords = categories.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey,
            entry -> entry.getValue().stream().max(Comparator.comparingInt(String::length))
                .orElse("")
        ));
    System.out.println(longestWords);
  }


  private static void mergeTwoMapWithSumOfValueIfKeyExistsInBothMap() {
    /*
        Problem: Given two maps (Map<String, Integer> map1 and Map<String, Integer> map2),
        merge them into a single map. If a key exists in both maps, sum their values.
     */
    Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
    Map<String, Integer> map2 = Map.of("b", 3, "c", 4);
    Map<String, Integer> collected =
        Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue, Integer::sum));
    System.out.println(collected);
  }

  private static void mapKeyAndLargestValueInArrayAsValue() {
    /*
        Problem: Given a map where the key is a string and the value is a list of integers, find the highest value
        in each list and return a new map with the same keys but with the highest value from the list.
     */
    Map<String, List<Integer>> map = Map.of(
        "a", Arrays.asList(1, 3, 2),
        "b", Arrays.asList(4, 2, 5),
        "c", Arrays.asList(0, 9, 7)
    );

    Map<String, Integer> collected = map.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey,
            entry -> entry.getValue().stream().max(Integer::compareTo).orElse(Integer.MIN_VALUE)));
    System.out.println(collected);
  }

  private static void removeDuplicateBasedUponName() {
    /*
        Given a list of objects, remove duplicates based on a specific property. For example,
        given a list of Person objects, remove duplicates based on the name field, keeping the first occurrence.
     */

    List<Person> people = Arrays.asList(
        new Person("Alice", "AGR"),
        new Person("Bob", "DHN"),
        new Person("Alice", "SYN"),
        new Person("Charlie", "NYC")
    );

    people.stream()
        .collect(Collectors.toMap(Person::name, e -> e, (p1, p2) -> p1))
        .values()
        .forEach(System.out::println);
  }
}