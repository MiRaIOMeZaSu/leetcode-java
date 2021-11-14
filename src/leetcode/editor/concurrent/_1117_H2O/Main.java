package leetcode.editor.concurrent._1117_H2O;

public class Main {
    public static void main(String[] args) {
        H2O h2O = new H2O();
        new Thread(()->{
            try {
                h2O.hydrogen(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("H");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                h2O.oxygen(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("O");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                h2O.hydrogen(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("H");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
