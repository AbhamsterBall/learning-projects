package com.hyh;

public class MyClass {
    private static int counter = 0;

    public static void main(String[] args) {
        MyClass myObject1 = new MyClass();
        MyClass myObject2 = new MyClass();

        myObject1.incrementCounter();
        myObject2.incrementCounter();

        System.out.println(((Object) myObject1.incrementCounter()).hashCode());
        System.out.println(((Object) myObject2.incrementCounter()).hashCode());

        System.out.println("Counter value for myObject1: " + myObject1.getCounter()); // 输出：Counter value for myObject1: 1
        System.out.println("Counter value for myObject2: " + myObject2.getCounter()); // 输出：Counter value for myObject2: 2
    }

    public static int incrementCounter() {
        counter++;
        return counter;
    }

    public static int getCounter() {
        return counter;
    }
}