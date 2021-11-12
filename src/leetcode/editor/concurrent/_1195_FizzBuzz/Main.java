package leetcode.editor.concurrent._1195_FizzBuzz;

import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(()->{
            try {
                fizzBuzz.fizz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("fizz");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.buzz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("buzz");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.fizzbuzz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("fizzbuzz");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                fizzBuzz.number(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.println(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
