package leetcode.editor.leetcode._2034_StockPrice;

import java.util.*;

class StockPrice {
    TreeSet<Integer> treeSet = new TreeSet<>();
    Map<Integer, Integer> hashMap = new HashMap<>(16);
    Map<Integer, Integer> timeToPrice = new HashMap<>(16);
    int maxTimeStamp = 1;

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
        maxTimeStamp = Math.max(timestamp, maxTimeStamp);
        if (!timeToPrice.containsKey(timestamp)) {
            // 添加
            int presVal = hashMap.getOrDefault(price, 0);
            if (presVal != 0) {
                hashMap.put(price, presVal + 1);
            } else {
                treeSet.add(price);
                hashMap.put(price,1);
            }
        } else {
            // 更新
            int presVal = hashMap.getOrDefault(price, 0);
            if (presVal != 0) {
                hashMap.put(price, presVal + 1);
            } else {
                treeSet.add(price);
                hashMap.put(price,1);
            }
            int presPrice = timeToPrice.get(timestamp);
            presVal = hashMap.getOrDefault(presPrice, 0);
            if (presVal != 1) {
                hashMap.put(presPrice, presVal - 1);
            } else {
                hashMap.remove(presPrice);
                treeSet.remove(presPrice);
            }
        }
        timeToPrice.put(timestamp, price);
    }

    public int current() {
        return timeToPrice.get(maxTimeStamp);
    }

    public int maximum() {
        return treeSet.last();
    }

    public int minimum() {
        return treeSet.first();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println(stockPrice.current());;
        System.out.println(stockPrice.maximum());;
        stockPrice.update(1, 3);
        System.out.println(stockPrice.maximum());;
        stockPrice.update(4, 2);
        System.out.println(stockPrice.maximum());;
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */