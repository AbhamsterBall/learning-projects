package tries.stream;

import org.apache.solr.common.util.ContentStreamBase;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStream {

    @Test
    public void streamFromList () {
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        stream1.forEach(System.out::println);

        List<Integer> integerList = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        Stream<Integer> listStream = integerList.stream();
        listStream.forEach(System.out::println);
    }

    @Test
    public void streamFromArray() {
        int[] intArr = {1, 2, 3, 4, 5};
        IntStream arrayStream = Arrays.stream(intArr);
        arrayStream.forEach(System.out::println);
    }

    @Test
    public void readFileLines() {
        try {
            Stream<String> fileStream = Files.lines(
                    Paths.get("F:\\JAVA\\20231010\\LeetCode\\src\\main\\java\\tries\\stream\\data.txt"),
                    Charset.defaultCharset());
            fileStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFunction() {
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2).limit(5);
        iterateStream.forEach(System.out::println);

        Stream<Double> generateStream = Stream.generate(Math::random).limit(5);
        generateStream.forEach(System.out::println);
    }

    List strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    List repeated = Arrays.asList("abc", "abc");
    List disordered = Arrays.asList("f", "a");
    List numbers = Arrays.asList(1, 99, 3, 30, -20);

    //filter map distinct sorted flatMap peek limit skip
    @Test
    public void normalMethod() {
        System.out.println(strings.stream().filter(string -> !string.equals("")).collect(Collectors.toList()));
        System.out.println(strings.stream().map(str -> str + "-itcast").collect(Collectors.toList()));
        System.out.println(repeated.stream().distinct().collect(Collectors.toList()));
        System.out.println(disordered.stream().sorted().collect(Collectors.toList()));
        System.out.println(numbers.stream().peek(System.out::println));
        System.out.println(strings.stream().limit(3).collect(Collectors.toList()));
        System.out.println(strings.stream().skip(3).collect(Collectors.toList()));
        BinaryOperator<Integer> addition = (num1, num2) -> num1 + num2;
        strings.stream().reduce(BinaryOperator.maxBy(Comparator.comparing(addition)));
        strings.stream().anyMatch();
    }

    @Test
    public void streamCompareAndUse() {
        String[] intArr = {"1-1", "2-1", "3-1", "4-1", "5-1"};
        List arrayStream = Arrays.asList(intArr);
        System.out.println(arrayStream.stream().map(number -> Stream.of(number.toString().split("-")).collect(Collectors.toList())).collect(Collectors.toList()));
        System.out.println(arrayStream.stream().flatMap(number -> Stream.of(number.toString().split("-"))).collect(Collectors.toList()));

        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get("F:\\JAVA\\20231010\\LeetCode\\src\\main\\java\\tries\\stream\\passage.txt"),
                    StandardCharsets.UTF_8);
            Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
            System.out.println(words.collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
