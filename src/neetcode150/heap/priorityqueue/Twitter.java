package neetcode150.heap.priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Implement a simplified version of Twitter which allows users to post tweets, follow/unfollow each other, and view the 10 most recent tweets within their own news feed.

Users and tweets are uniquely identified by their IDs (integers).

Implement the following methods:

Twitter() Initializes the twitter object.
void postTweet(int userId, int tweetId) Publish a new tweet with ID tweetId by the user userId. You may assume that each tweetId is unique.
List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's news feed. Each item must be posted by users who the user is following or by the user themself. Tweets IDs should be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
Example 1:

Input:
["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], "getNewsFeed", [1], "getNewsFeed", [2], "follow", [1, 2], "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2], "getNewsFeed", [1]]

Output:
[null, null, null, [10], [20], null, [20, 10], [20], null, [10]]

Explanation:
Twitter twitter = new Twitter();
twitter.postTweet(1, 10); // User 1 posts a new tweet with id = 10.
twitter.postTweet(2, 20); // User 2 posts a new tweet with id = 20.
twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
twitter.getNewsFeed(2);   // User 2's news feed should only contain their own tweets -> [20].
twitter.follow(1, 2);     // User 1 follows user 2.
twitter.getNewsFeed(1);   // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
twitter.getNewsFeed(2);   // User 2's news feed should still only contain their own tweets -> [20].
twitter.unfollow(1, 2);   // User 1 unfollows user 2.
twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
Constraints:

1 <= userId, followerId, followeeId <= 100
0 <= tweetId <= 1000

 */
public class Twitter {
	private class Tweet{
		int id;
		int time;
		Tweet(int id, int time){
			this.id = id;
			this.time = time;
		}
	}
	
	int timeStamp;
	private Map<Integer, Set<Integer>> followMap;
	private Map<Integer, List<Tweet>> tweetMap;
	
	public Twitter(){
		timeStamp = 0;
		followMap = new HashMap<>();
		tweetMap = new HashMap<>();
	}
	
	public void postTweet(Integer userId, int tweetId) {
		if(!tweetMap.containsKey(userId)) {
			tweetMap.put(userId, new ArrayList<>());
		}
		tweetMap.get(userId).add(new Tweet(tweetId, timeStamp++));
	}
	
	public void follow(int followerId, int followeeId) {
		if (followerId == followeeId) return;
		if(!followMap.containsKey(followerId)) {
			followMap.put(followerId, new HashSet<>());
		}
		followMap.get(followerId).add(followeeId);
	}
	
	public void unfollow(int followerId, int followeeId) {
		followMap.get(followerId).remove(followeeId);
	}
	public List<Integer> getNewsFeed(int userId) {
		PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b) -> b.time - a.time);
		if(tweetMap.containsKey(userId)) {
			pq.addAll(tweetMap.get(userId));
		}
		if(followMap.containsKey(userId)) {
			for(int followeeId : followMap.get(userId)) {
				if(tweetMap.containsKey(followeeId)) {
					pq.addAll(tweetMap.get(followeeId));
				}
			}
		}
		int count = 0;
		List<Integer> result = new ArrayList<>();
		while(!pq.isEmpty() && count < 10) {
			
		result.add(pq.poll().id);
		count ++;
		}
		return result;
	}
	
	public static void main(String[] args) {
        System.out.println("=== Test Case 1: Basic Operations ===");
        Twitter twitter1 = new Twitter();
        
        twitter1.postTweet(1, 5);
        System.out.println("User 1 posted tweet 5");
        System.out.println("User 1's news feed: " + twitter1.getNewsFeed(1));
        
        twitter1.follow(1, 2);
        System.out.println("\nUser 1 follows User 2");
        
        twitter1.postTweet(2, 6);
        System.out.println("User 2 posted tweet 6");
        System.out.println("User 1's news feed: " + twitter1.getNewsFeed(1));
        
        twitter1.unfollow(1, 2);
        System.out.println("\nUser 1 unfollows User 2");
        System.out.println("User 1's news feed: " + twitter1.getNewsFeed(1));
        
        System.out.println("\n=== Test Case 2: Multiple Users and Tweets ===");
        Twitter twitter2 = new Twitter();
        
        twitter2.postTweet(1, 101);
        twitter2.postTweet(1, 102);
        twitter2.postTweet(2, 201);
        twitter2.postTweet(2, 202);
        twitter2.postTweet(3, 301);
        System.out.println("Multiple tweets posted by users 1, 2, and 3");
        
        twitter2.follow(1, 2);
        twitter2.follow(1, 3);
        System.out.println("User 1 follows User 2 and User 3");
        System.out.println("User 1's news feed: " + twitter2.getNewsFeed(1));
        
        System.out.println("\n=== Test Case 3: More than 10 Tweets ===");
        Twitter twitter3 = new Twitter();
        
        for (int i = 1; i <= 15; i++) {
            twitter3.postTweet(1, i * 100);
        }
        System.out.println("User 1 posted 15 tweets");
        List<Integer> feed = twitter3.getNewsFeed(1);
        System.out.println("User 1's news feed (should show only 10 most recent): " + feed);
        System.out.println("Number of tweets in feed: " + feed.size());
        
        System.out.println("\n=== Test Case 4: Complex Scenario ===");
        Twitter twitter4 = new Twitter();
        
        twitter4.postTweet(1, 10);
        twitter4.postTweet(2, 20);
        twitter4.postTweet(3, 30);
        twitter4.postTweet(1, 11);
        twitter4.postTweet(2, 21);
        
        twitter4.follow(1, 2);
        twitter4.follow(1, 3);
        twitter4.follow(2, 1);
        
        System.out.println("After multiple posts and follows:");
        System.out.println("User 1's news feed: " + twitter4.getNewsFeed(1));
        System.out.println("User 2's news feed: " + twitter4.getNewsFeed(2));
        System.out.println("User 3's news feed: " + twitter4.getNewsFeed(3));
        
        System.out.println("\n=== Test Case 5: Self-Follow Prevention ===");
        Twitter twitter5 = new Twitter();
        
        twitter5.postTweet(1, 100);
        twitter5.follow(1, 1); // Try to follow self
        System.out.println("User 1 tries to follow themselves (should be prevented)");
        System.out.println("User 1's news feed: " + twitter5.getNewsFeed(1));
    }

}
