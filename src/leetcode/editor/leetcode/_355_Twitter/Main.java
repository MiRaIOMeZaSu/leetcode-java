package leetcode.editor._355_Twitter;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> result;
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        result = twitter.getNewsFeed(1);
        for (int i : result) {
            System.out.print(i);
        }
        System.out.println("\n");
//// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
//        twitter.postTweet(1, 5);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//        result = twitter.getNewsFeed(1);
//        for (int i : result) {
//            System.out.print(i);
//        }
//        System.out.println("\n");
//// 用户1关注了用户2.
//        twitter.follow(1, 2);
//
//// 用户2发送了一个新推文 (推文id = 6).
//        twitter.postTweet(2, 6);
//
//// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
//// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//        result = twitter.getNewsFeed(1);
//        for (int i : result) {
//            System.out.print(i);
//        }
//        System.out.println("\n");
//// 用户1取消关注了用户2.
//        twitter.unfollow(1, 2);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//// 因为用户1已经不再关注用户2.
//        result = twitter.getNewsFeed(1);
//        for (int i : result) {
//            System.out.print(i);
//        }
//        System.out.println("\n");
    }
}
