package tries.stream;

import org.apache.solr.common.util.ContentStreamBase;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
    List<Integer> numbers = Arrays.asList(1, 99, 3, 30, -20);

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
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum of numbers: " + sum);
        System.out.println(numbers.stream().anyMatch(number -> number % 2 == 0));
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

    @Test
    public void streamMultiThread() {
        List<Integer> list = Arrays.asList(1, 2, 3, 99);
        // 通过List 接口 直接获取并行流
        Stream<Integer> integerStream = list.parallelStream();
        // 将已有的串行流转换为并行流
        Stream<Integer> parallel = Stream.of(1, 2, 3).parallel();
        parallel = integerStream;

        Object obj = new Object();
        Vector v = new Vector();
        List<Integer> syncronizedList = Collections.synchronizedList(new ArrayList<>(v)); //相当于线程安全地添加了
        System.out.println(parallel // 将流转换为并发流，Stream处理的时候就会通过多线程处理
                .filter(s -> {
                    syncronizedList.add(s);
                    synchronized (obj) {
                        System.out.println(Thread.currentThread() + " s=" + s);
                        v.add(s);
                        return s > 2;
                    }
                }).count());
        System.out.println(v);
        System.out.println(syncronizedList);

        System.out.println(IntStream.rangeClosed(1, 100)
                .parallel()
                .boxed()
                .collect(Collectors.toList())); //这样并行流也是线程安全的

    }

    @Test
    public void collectorAndDBDataProcessing() {
        User user = new User(1, "name1", "description", 20);
        System.out.println(user.getAge());
        User user0 = new User(1, "name2", "description", 16);

        ArrayList<User> givenList = new ArrayList();
        givenList.add(user);
        givenList.add(user0);

        System.out.println(givenList.stream().map(User::getName).collect(Collectors.toSet()));
        System.out.println(givenList.stream().map(User::getDscrip).collect(Collectors.toList()));
        System.out.println(givenList.stream().map(User::getNo).collect(toCollection(LinkedList::new)).toString());

        System.out.println(givenList.stream().collect(toMap(User::getName, User::getDscrip)));
        System.out.println(givenList.stream().collect(toMap(User::getNo, User::getDscrip, (item, identicalItem) -> item))); //避免碰撞

        givenList.stream().map(User::getNo).collect(Collectors.collectingAndThen(
                        Collectors.toList(), list -> {System.out.println(list); return null;}));

        System.out.println(givenList.stream().map(User::getName).collect(joining(" ", "[ ", " ]")));

        // data comparing
        System.out.println("————————————————————————————————");
        System.out.println(givenList.stream().collect(summarizingDouble(User::getNo)));
        System.out.println(givenList.stream().collect(averagingDouble(User::getNo)));
        System.out.println(givenList.stream().collect(summingDouble(User::getNo)));
        System.out.println(givenList.stream().map(User::getAge).collect(maxBy(Comparator.naturalOrder())));
        System.out.println(givenList.stream().map(User::getAge).collect(minBy(Comparator.naturalOrder())));
        System.out.println(givenList.stream().collect(groupingBy(User::getNo, Collectors.mapping(User::getName, Collectors.toSet()))));

        System.out.println(givenList.stream().collect(partitioningBy(usr -> usr.getAge() > 18, Collectors.mapping(User::getAge, Collectors.toList()))));


    }

}
