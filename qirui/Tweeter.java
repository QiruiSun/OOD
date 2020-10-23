package qirui;
import java.util.*;

class Twetter {
    private static int timeSign;
    private final static int SIZE = 10;
    Map<Integer, User> users;
    /** Initialize your data structure here. */
    public Twetter() {
        users = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = users.get(userId);
        if (user == null) {
            user = new User(userId);
        }
        user.tweet(tweetId);
        users.put(userId, user);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = users.get(userId);
        if (user == null) {
            return new ArrayList<>();
        }
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> (Integer.compare(b.time, a.time)));
        for (int id : user.getFollowing()) {
            User curr = users.get(id);
            if (curr != null && curr.getTweet() != null) {
                pq.offer(curr.getTweet());
            }
        }
        List<Integer> res = new ArrayList<>();
        int num = SIZE;
        while (pq.size() > 0 && num > 0) {
            Tweet curr = pq.poll();
            res.add(curr.id);
            if (curr.next != null) {
                pq.offer(curr.next);
            }
            num--;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User user = users.get(followerId);
        if (user == null) {
            user = new User(followerId);
        }
        user.follow(followeeId);
        users.put(followerId, user);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = users.get(followerId);
        if (user == null) {
            user = new User(followerId);
        }
        user.unFollow(followeeId);
        users.put(followerId, user);
    }


    static class User {
        private int id;
        private Set<Integer> following;
        private Tweet lastTweet;

        public User(int id) {
            this.id = id;
            following = new HashSet<>();
            following.add(id);
            this.following = following;
        }

        public void tweet(int tweetId) {
            Tweet curr = new Tweet(tweetId);
            curr.next = lastTweet;
            lastTweet = curr;
        }

        public void follow(int userId) {
            following.add(userId);
        }

        public void unFollow(int userId) {
            if (userId == id) {
                return;
            }
            following.remove(userId);
        }

        public Tweet getTweet() {
            return lastTweet;
        }

        public Set<Integer> getFollowing() {
            return following;
        }
    }


    static class Tweet {
        int time;
        int id;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            this.time = timeSign++;
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