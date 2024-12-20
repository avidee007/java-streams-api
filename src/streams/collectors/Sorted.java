package streams.collectors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Sorted {

  public static void main(String[] args) {
    //Find the longest string in a list of strings using Java streams.
    findLongestString();

    //Write a Java 8 program to sort a given list of strings according
    //to the increasing order of their length.
    sortInAscendingOrderAsPerLengthOfStrings();

    //Write a Java 8 program to find the second-largest number in an integer array.
    secondLargestNumber();

    //Write a Java 8 program to get the three maximum
    //and three minimum numbers from a given list of integers.
    max3AndMin3();

    //Write a Java 8 program to merge only unique values between
    //two unsorted arrays into a single-sorted array without duplicates.
    mergeOnlyUniqueValuesOfTwoArrays();

    //Write a Java 8 program to merge two unsorted arrays into a single-sorted
    // array using the stream API.
    mergeTwoUnsortedArrayAndSortMergedArray();

    //Write a Java 8 program to sort a given list of decimal numbers in reverse order.
    sortDecimalsInReverse();

    //Given a map of string keys and integer values,
    // sort the map by its values in descending order.
    sortTheMapBasedUponValuesInDescendingOrder();

  }

  private static void sortTheMapBasedUponValuesInDescendingOrder() {

    Map<String, Integer> map = Map.of("a", 1, "b", 5, "c", 3);

    map.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .forEach(System.out::println);
  }

  private static void findLongestString() {
    List<String> strings = List.of("Alexa", "Zia", "Rohan", "Zyaaaan");
    strings.stream()
        .max(Comparator.comparing(String::length))
        .ifPresent(System.out::println);
  }

  private static void sortInAscendingOrderAsPerLengthOfStrings() {
    List<String> strings = List.of("Alexa", "Zia", "Rohan", "Zyaaaan");
    strings.stream()
        .sorted(Comparator.comparing(String::length))
        .toList()
        .forEach(System.out::println);
  }

  private static void secondLargestNumber() {
    List<Integer> integers = List.of(1, 3, 2, 14, 7, 5);
    integers.stream()
        .sorted(Comparator.comparingInt(Integer::intValue).reversed())
        .skip(1)
        .findFirst()
        .ifPresent(System.out::println);
  }

  private static void max3AndMin3() {
    List<Integer> integers = List.of(12, 32, 2, 4, 777, 5, 32, 890, 422, 44, 99, 43);
    List<Integer> maximumThree = integers.stream()
        .sorted(Comparator.comparingInt(Integer::intValue).reversed())
        .limit(3)
        .toList();
    System.out.println(maximumThree);

    List<Integer> minimumThree = integers.stream()
        .sorted(Comparator.comparingInt(Integer::intValue))
        .limit(3)
        .toList();
    System.out.println(minimumThree);
  }

  private static void mergeOnlyUniqueValuesOfTwoArrays() {
    int[] randomNumbers = {12, 32, 2, 4, 777, 5, 32, 890, 422, 44, 99, 43};
    int[] randomNumber2 = {4, 3, 2, 5, 6, 78, 98, 53, 90};

    int[] mergedSortedArray =
        IntStream.concat(Arrays.stream(randomNumbers), Arrays.stream(randomNumber2))
            .distinct()
            .sorted()
            .toArray();
    System.out.println(Arrays.toString(mergedSortedArray));
  }

  private static void mergeTwoUnsortedArrayAndSortMergedArray() {
    int[] randomNumbers = {12, 32, 2, 4, 777, 5, 32, 890, 422, 44, 99, 43};
    int[] randomNumber2 = {4, 3, 2, 5, 6, 78, 98, 53, 90};

    int[] mergedSortedArray =
        IntStream.concat(Arrays.stream(randomNumbers), Arrays.stream(randomNumber2))
            .sorted()
            .toArray();
    System.out.println(Arrays.toString(mergedSortedArray));
  }

  private static void sortDecimalsInReverse() {
    List<Double> doubles = List.of(10.4, 0.1, 8.0, 8.9, 8.11);
    doubles.stream()
        .sorted(Comparator.comparingDouble(Double::doubleValue).reversed())
        .forEach(System.out::println);
  }
}