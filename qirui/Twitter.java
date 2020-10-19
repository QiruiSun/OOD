package qirui;
import java.util.*;

class Twitter {
    private final static int SIZE = 10;

    Map<Integer, User> users;

    public Twitter() {
        this.users = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        User user = getOrCreateUser(userId);
        user.tweet(tweetId);
    }

    public void follow(int userId, int another) {
        User user = getOrCreateUser(userId);
        User toFollow = getOrCreateUser(another);

        user.follow(toFollow);
    }

    public void unfollow(int userId, int another) {
        User user = getOrCreateUser(userId);
        User follow = getOrCreateUser(another);
        user.unfollow(follow);
    }

    public List<Integer> getNewsFeed(int userId) {
        User user = getOrCreateUser(userId);

        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> (Long.compare(b.time, a.time)));
        // if (user.lastTweet() != null) {
        //     pq.offer(user.lastTweet());
        // }
        for (User other : user.getFollowing()) {
            if (other.lastTweet() != null) {
                pq.offer(other.lastTweet());
            }
        }
        List<Integer> res = new ArrayList<>();

        while (res.size() < SIZE && pq.size() > 0) {
            Tweet tweet = pq.poll();
            User curr = tweet.user;
            if (curr.getTweet(tweet.index - 1) != null) {
                pq.offer(curr.getTweet(tweet.index - 1));
            }
            res.add(tweet.id);
        }
        return res;
    }

    private User getOrCreateUser(int id) {
        User user = users.get(id);
        if (user == null) {
            user = new User(id);
            user.follow(user);
            users.put(id, user);
        }
        return user;
    }
}

class User {
    private int id;
    private Set<User> following;
    private List<Tweet> tweets;

    public User(int id) {
        this.id = id;
        this.tweets = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public Set<User> getFollowing() {
        return this.following;
    }

    public void follow(User another) {
        this.following.add(another);
    }

    public void unfollow(User another) {
        if (another == this) {
            return;
        }
        this.following.remove(another);
    }

    public Tweet lastTweet() {
        if (tweets.size() > 0) {
            return tweets.get(tweets.size() - 1);
        }
        return null;
    }

    public Tweet getTweet(int index) {
        if (index >= 0 && tweets.size() > 0) {
            return tweets.get(index);
        }
        return null;
    }

    public void tweet(int id) {
        Tweet tweet = new Tweet(id, tweets.size(), this);
        tweets.add(tweet);
    }
}

class Tweet {
    private static long timeSign = 0;
    int id;
    long time;
    int index;
    User user;

    public Tweet(int id, int index, User user) {
        this.index = index;
        this.id = id;
        this.user = user;
        // this.time = System.currentTimeMillis();
        this.time = timeSign++;
    }
}


// user tweets a new post
// user follows another user
// unfollow
// gets recent activities (which includes users I follow as well as myself)
/*
    Classes:
        User -> Tweet
        User -> User (I don't need to know all of my followers)
 */