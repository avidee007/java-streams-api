package streams.collectors;

import java.util.List;

public class Reducing {
  public static void main(String[] args) {

    //Find the total sum of numbers in a list.
    totalSum();

    //Find Maximum and minimum of members in list.
    findMaxAndMin();

  }

  private static void totalSum() {
    List<Integer> integers = List.of(1, 3, 2, 4, 7, 5);
    int sum = integers.stream()
        .mapToInt(value -> value)
        .sum();
    System.out.println(sum);
  }

  private static void findMaxAndMin() {
    List<Integer> numbers = List.of(15, 8, 10, 14, 0, 1, 25);
    numbers.stream().max(Integer::compareTo)
        .ifPresent(System.out::println);

    numbers.stream().min(Integer::compareTo)
        .ifPresent(System.out::println);
  }
}