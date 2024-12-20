package streams;

import java.util.Arrays;
import java.util.List;

public class Filter {

  public static void main(String[] args) {

    //Create a new list with intersection of elements.
    intersectionOfLists();

    //Print the numbers from a given list of integers that are multiples of 5.
    multipleOfFive();

  }

  private static void multipleOfFive() {
    List<Integer> numbers = List.of(15, 8, 10, 14, 0, 1, 25);
    numbers.stream()
        .filter(e -> e % 5 == 0)
        .toList()
        .forEach(System.out::println);
  }

  private static void intersectionOfLists() {
    List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);

    List<Integer> intersectionList = list1.stream()
        .filter(list2::contains)
        .toList();
    System.out.println(intersectionList);
  }
}