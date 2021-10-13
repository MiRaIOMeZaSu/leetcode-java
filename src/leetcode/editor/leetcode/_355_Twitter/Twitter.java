package leetcode.editor._355_Twitter;

import java.util.*;

class Tweet implements Comparable {
    private int tweetId;

    public int getTweetId() {
        return tweetId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    private int timestamp;

    Tweet(int tweetId, int timestamp) {
        this.tweetId = tweetId;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Object o) {
        if (this.timestamp > ((Tweet) o).timestamp) {
            return 1;
        } else if (this.timestamp < ((Tweet) o).timestamp) {
            return -1;
        }
        return 0;
    }
}

class User {
    public Set<Integer> getFollowees() {
        return followees;
    }

    private final Set<Integer> followees;

    public ArrayList getTweets() {
        return new ArrayList(this.tweets);
    }

    private PriorityQueue<Tweet> tweets;

    User(int userId) {
        followees = new HashSet<>();
        tweets = new PriorityQueue();
        this.follow(userId);
    }

    public void postTweet(int tweetId, int timestamp) {
        tweets.add(new Tweet(tweetId, timestamp));
    }

    public void follow(int followeeId) {
        this.followees.add(followeeId);
    }

    public void unfollow(int followeeId) {
        this.followees.remove(followeeId);
    }
}

public class Twitter {

    /**
     * Initialize your data structure here.
     */
    private final Map<Integer, User> users;
    private int timestamp;

    public Twitter() {
        // 通过哈希链表存储id与类的关联
        this.users = new HashMap<>();
        this.timestamp = 1;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        User user;
        if (!this.users.containsKey(userId)) {
            // 创建
            users.put(userId, new User(userId));
        }
        user = users.get(userId);
        user.postTweet(tweetId, timestamp);
        this.timestamp++;
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!this.users.containsKey(userId)) {
            return new ArrayList<>();
        }
        List<Integer> newsFeed = new ArrayList<>();
        // 返回的是int是id
        Set<Integer> followee = this.users.get(userId).getFollowees();
        List<List<Tweet>> allTweets = new ArrayList<>();
        Tweet temp;
        int maxTimeStamp = 0;
        Tweet max = null;
        List<Tweet> lastMaxQueue = null;
        Set<Tweet> added = new HashSet<>();
        for (int id : followee) {
            allTweets.add(users.get(id).getTweets());
        }
        PriorityQueue<Tweet> now = new PriorityQueue<>();
        // 比较部分或许可以再用一次优先队列
        while (newsFeed.size() < 10) {
            maxTimeStamp = 0;
            lastMaxQueue = null;
            max = null;
            // 直到返回的序列中包含10个,在用尽的情况下直接break
            int deleted = 0;
            int allTweetSize = allTweets.size();

            for (int i = 0; i < allTweetSize; i++) {
                List<Tweet> list = allTweets.get(i - deleted);
                int listSize = list.size();
                if (listSize > 0) {
                    temp = list.get(listSize - 1);
                    if (temp.getTimestamp() > maxTimeStamp) {
                        max = temp;
                        maxTimeStamp = temp.getTimestamp();
                        lastMaxQueue = list;
                    }
                } else {
                    allTweets.remove(list);
                    deleted++;
                }
            }

            if (lastMaxQueue ==null) {
                return newsFeed;
            }
            lastMaxQueue.remove(max);
            newsFeed.add(max.getTweetId());
        }
        return newsFeed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        User follower;
        if (!this.users.containsKey(followerId)) {
            // 如果不存在用户
            follower = new User(followerId);
            users.put(followerId, follower);
        } else {
            follower = users.get(followerId);
        }
        if (!this.users.containsKey(followeeId)) {
            // 如果不存在用户
            User followee = new User(followeeId);
            users.put(followeeId, followee);
        }
        follower.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     * no-op指无操作
     */
    public void unfollow(int followerId, int followeeId) {
        if (!this.users.containsKey(followerId) || followerId == followeeId) {
            return;
        } else {
            User follower = users.get(followerId);
            follower.unfollow(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */