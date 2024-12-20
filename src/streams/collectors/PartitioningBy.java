package streams.collectors;

import java.util.List;
import java.util.stream.Collectors;

public class PartitioningBy {

  public static void main(String[] args) {

    //Given a list of integers, write a Java 8 program to
    // separate the odd and even numbers into two separate lists.
    separateOddEven();

  }

  private static void separateOddEven() {
    List<Integer> integers = List.of(1, 2, 3, 4, 10, 6, 7, 9);
    integers.stream()
        .collect(Collectors.partitioningBy(e -> e % 2 == 0))
        .forEach((k, v) -> {
          if (Boolean.TRUE.equals(k)) {
            System.out.println("Even Numbers : " + v);
          } else {
            System.out.println("Odd Numbers : " + v);
          }
        });
  }
}