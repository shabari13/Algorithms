package neetcode250.heap_priorityqueue;

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

 * ============================================================
 *  DESIGN TWITTER FEED
 * ============================================================
 *
 *  IDEA:
 *  - Each user has a LinkedList of tweets (newest inserted at head).
 *  - A global timestamp counter (decrements) ensures newer tweets
 *    have a LOWER numeric timestamp, so a MinHeap always pops the
 *    most-recent tweet first.
 *  - getNewsFeed() seeds the heap with the HEAD tweet of every
 *    followed user, then pops up to 10 times.  After each pop,
 *    the NEXT tweet of that user (if any) is pushed into the heap.
 *    This is lazy loading — we never load more tweets than needed.
 *
 *  TIME COMPLEXITY:
 *  - postTweet   : O(1)
 *  - follow      : O(1)
 *  - unfollow    : O(1)
 *  - getNewsFeed : O(F log F + 10 log F)
 *      where F = number of followed users.
 *      Seeding the heap = O(F log F).
 *      10 pops + pushes = O(10 log F) ≈ O(log F).
 *      Overall: O(F log F).
 *
 *  SPACE COMPLEXITY:
 *  - O(U * T + U * F)
 *      U = number of users, T = tweets per user, F = followees per user.
 *      We store every tweet and every follow relationship.
 *
 */
public class TwitterFeed {
	
	private class Tweet {
		int id;
		int time;
		public Tweet(int id, int time) {
			this.id = id;
			this.time = time;
		}
	}
	int timeStamp;
	private Map<Integer, Set<Integer>> followMap;
	private Map<Integer, List<Tweet>> tweetMap;
	
	public TwitterFeed() {
		timeStamp = 0;
		followMap = new HashMap<>();
		tweetMap =  new HashMap<>();
	}
	
	public void postTweet(int userId, int twitterId) {
		if(!tweetMap.containsKey(userId)) {
			tweetMap.put(userId, new ArrayList<>());
		}
		tweetMap.get(userId).add(new Tweet(twitterId, timeStamp++));
	}
	
	public void follow(int followerId, int followeeId) {
		if(followerId == followeeId)
			return;
		if(!followMap.containsKey(followerId)) {
			followMap.put(followerId, new HashSet<>());
		}
		followMap.get(followerId).add(followeeId);
	}
	
	public void unfollow(int followerId, int followeeId) {
		if(followerId == followeeId)
			return;
		if(!followMap.containsKey(followerId))
			return;
		followMap.get(followerId).remove(followeeId);
	}
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> result = new ArrayList<>();
		PriorityQueue<Tweet> pq =  new PriorityQueue<>((a,b) -> b.time - a.time);
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
		while(!pq.isEmpty() && count < 10) {
			count++;
			result.add(pq.poll().id);
		}
		return result;
	}
	
	  public static void main(String[] args) {
		  
	        System.out.println("╔══════════════════════════════════════════════════════╗");
	        System.out.println("║          TWITTER FEED — DEMO RUNS                   ║");
	        System.out.println("╚══════════════════════════════════════════════════════╝\n");
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  SAMPLE 1  (classic LeetCode example)
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("═══ SAMPLE 1: Classic Example ═══");
	        TwitterFeed tw1 = new TwitterFeed();
	 
	        // User 1 posts tweet 5 (timestamp = -1)
	        tw1.postTweet(1, 5);
	        System.out.println("User 1 posts tweet #5  (timestamp=-1)");
	 
	        // User 1's feed → [5]
	        System.out.println("User 1 feed  → " + tw1.getNewsFeed(1));
	        // Expected: [5]
	 
	        // User 1 follows User 2
	        tw1.follow(1, 2);
	        System.out.println("User 1 follows User 2");
	 
	        // User 2 posts tweet 6 (timestamp = -2)
	        tw1.postTweet(2, 6);
	        System.out.println("User 2 posts tweet #6  (timestamp=-2)");
	 
	        // User 1's feed now shows tweet 6 (newer) then tweet 5
	        System.out.println("User 1 feed  → " + tw1.getNewsFeed(1));
	        // Expected: [6, 5]
	 
	        // User 1 unfollows User 2
	        tw1.unfollow(1, 2);
	        System.out.println("User 1 unfollows User 2");
	 
	        // User 1's feed → [5]  (tweet 6 from user 2 disappears)
	        System.out.println("User 1 feed  → " + tw1.getNewsFeed(1));
	        // Expected: [5]
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  SAMPLE 2  (only own tweets, no follows)
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n═══ SAMPLE 2: Solo User ═══");
	        TwitterFeed tw2 = new TwitterFeed();
	        tw2.postTweet(10, 101);
	        tw2.postTweet(10, 102);
	        tw2.postTweet(10, 103);
	        System.out.println("User 10 posts tweets 101, 102, 103 in order");
	        System.out.println("User 10 feed → " + tw2.getNewsFeed(10));
	        // Expected newest-first: [103, 102, 101]
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  SAMPLE 3  (multiple users, interleaved tweets, 10-tweet cap)
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n═══ SAMPLE 3: Multiple Users + 10-Tweet Cap ═══");
	        TwitterFeed tw3 = new TwitterFeed();
	 
	        // Users A=1, B=2, C=3 each post several tweets
	        //  timestamps (in order of execution): -1, -2, ..., -12
	        tw3.postTweet(1, 1001); System.out.println("User 1 → tweet 1001");
	        tw3.postTweet(2, 2001); System.out.println("User 2 → tweet 2001");
	        tw3.postTweet(3, 3001); System.out.println("User 3 → tweet 3001");
	        tw3.postTweet(1, 1002); System.out.println("User 1 → tweet 1002");
	        tw3.postTweet(2, 2002); System.out.println("User 2 → tweet 2002");
	        tw3.postTweet(3, 3002); System.out.println("User 3 → tweet 3002");
	        tw3.postTweet(1, 1003); System.out.println("User 1 → tweet 1003");
	        tw3.postTweet(2, 2003); System.out.println("User 2 → tweet 2003");
	        tw3.postTweet(3, 3003); System.out.println("User 3 → tweet 3003");
	        tw3.postTweet(1, 1004); System.out.println("User 1 → tweet 1004");
	        tw3.postTweet(2, 2004); System.out.println("User 2 → tweet 2004");
	        tw3.postTweet(3, 3004); System.out.println("User 3 → tweet 3004");
	 
	        tw3.follow(1, 2);
	        tw3.follow(1, 3);
	        System.out.println("User 1 follows User 2 and User 3");
	 
	        List<Integer> feed3 = tw3.getNewsFeed(1);
	        System.out.println("User 1 feed (capped at 10) → " + feed3);
	        // Newest tweets globally: 3004(-12),2004(-11),1004(-10),3003(-9),2003(-8),
	        //                          1003(-7),3002(-6),2002(-5),1002(-4),3001(-3)
	        // Expected: [3004, 2004, 1004, 3003, 2003, 1003, 3002, 2002, 1002, 3001]
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  SAMPLE 4  (user with no tweets but follows active users)
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n═══ SAMPLE 4: Lurker User ═══");
	        TwitterFeed tw4 = new TwitterFeed();
	        tw4.postTweet(5, 500);
	        tw4.postTweet(5, 501);
	        tw4.follow(99, 5);          // user 99 has NEVER posted
	        System.out.println("User 5 posts 500 and 501");
	        System.out.println("User 99 (lurker) follows User 5");
	        System.out.println("User 99 feed → " + tw4.getNewsFeed(99));
	        // Expected: [501, 500]
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  SAMPLE 5  (follow then unfollow → feed changes)
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n═══ SAMPLE 5: Follow → Unfollow ═══");
	        TwitterFeed tw5 = new TwitterFeed();
	        tw5.postTweet(1, 11);
	        tw5.postTweet(2, 22);
	        tw5.follow(1, 2);
	        System.out.println("User 1 posts 11, User 2 posts 22, User 1 follows User 2");
	        System.out.println("User 1 feed (followed) → " + tw5.getNewsFeed(1));
	        tw5.unfollow(1, 2);
	        System.out.println("User 1 feed (unfollowed) → " + tw5.getNewsFeed(1));
	        // Expected: [22,11] then [11]
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  DETAILED ITERATION TRACE for SAMPLE 1
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n══════════════════════════════════════════════");
	        System.out.println("  DETAILED ITERATION TRACE — Sample 1 step 4");
	        System.out.println("  (getNewsFeed(1) after User1 follows User2)");
	        System.out.println("══════════════════════════════════════════════");
	        System.out.println();
	        System.out.println("  State before calling getNewsFeed(1):");
	        System.out.println("  ┌─────────┬──────────────────────────────────────┐");
	        System.out.println("  │ userId  │ Tweet chain (head→tail)              │");
	        System.out.println("  ├─────────┼──────────────────────────────────────┤");
	        System.out.println("  │ User 1  │ Tweet{id=5, time=-1} → null          │");
	        System.out.println("  │ User 2  │ Tweet{id=6, time=-2} → null          │");
	        System.out.println("  └─────────┴──────────────────────────────────────┘");
	        System.out.println();
	        System.out.println("  Relevant users for feed = {1, 2}");
	        System.out.println();
	        System.out.println("  ── SEED HEAP ──────────────────────────────────────");
	        System.out.println("  Push User 1's head: Tweet{id=5, time=-1}");
	        System.out.println("  Push User 2's head: Tweet{id=6, time=-2}");
	        System.out.println("  Heap (min by time) = [ Tweet{id=6,t=-2}, Tweet{id=5,t=-1} ]");
	        System.out.println("                          ↑ top (smallest time = most recent)");
	        System.out.println();
	        System.out.println("  ── ITERATION 1 ────────────────────────────────────");
	        System.out.println("  Pop Tweet{id=6, time=-2}  → result=[6]");
	        System.out.println("  Its .next = null  → nothing pushed back");
	        System.out.println("  Heap = [ Tweet{id=5,t=-1} ]");
	        System.out.println();
	        System.out.println("  ── ITERATION 2 ────────────────────────────────────");
	        System.out.println("  Pop Tweet{id=5, time=-1}  → result=[6, 5]");
	        System.out.println("  Its .next = null  → nothing pushed back");
	        System.out.println("  Heap = []  → loop ends (heap empty OR result.size()==10)");
	        System.out.println();
	        System.out.println("  Final result: [6, 5]  ✓");
	 
	        // ──────────────────────────────────────────────────────────────────
	        //  COMPLEXITY SUMMARY
	        // ──────────────────────────────────────────────────────────────────
	        System.out.println("\n══════════════════════════════════════════════");
	        System.out.println("  COMPLEXITY SUMMARY");
	        System.out.println("══════════════════════════════════════════════");
	        System.out.println("  postTweet  → O(1) time  |  O(T) space (total tweets)");
	        System.out.println("  follow     → O(1) time  |  O(F) space (total follows)");
	        System.out.println("  unfollow   → O(1) time  |  no extra space");
	        System.out.println("  getNewsFeed→ O(F log F) time  |  O(F) heap space");
	        System.out.println("               F = number of followees of the queried user");
	        System.out.println("  Overall space: O(U*T + U*F)");
	        System.out.println("               U=users, T=avg tweets, F=avg followees");
	    }

}
