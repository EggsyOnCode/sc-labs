/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.*;

import org.junit.Test;

public class SocialNetworkTest {

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // ensure assertions are enabled
    }

    // 1. Verifies that an empty list of tweets returns an empty graph
    @Test
    public void testGuessFollowsGraphEmptyList() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("Expected empty graph", followsGraph.isEmpty());
    }

    // 2. Ensures tweets with no mentions return an empty graph
    @Test
    public void testGuessFollowsGraphNoMentions() {
        List<Tweet> tweets = Arrays.asList(new Tweet(1, "user1", "Hello world", Instant.now()));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("Expected empty graph", followsGraph.isEmpty());
    }

    // 3. Checks correct identification and association of mentioned users
    @Test
    public void testGuessFollowsGraphWithMentions() {
        List<Tweet> tweets = Arrays.asList(new Tweet(1, "user1", "@user2 Hello", Instant.now()));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("user1 should follow user2", followsGraph.get("user1").contains("user2"));
    }

    // 4. Validates multiple mentioned users associated with the author
    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        List<Tweet> tweets = Arrays.asList(new Tweet(1, "user1", "@user2 @user3 Hello", Instant.now()));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("user1 should follow user2 and user3",
                followsGraph.get("user1").containsAll(Arrays.asList("user2", "user3")));
    }

    // 5. Verifies multiple tweets from the same author
    @Test
    public void testGuessFollowsGraphMultipleTweetsSameAuthor() {
        List<Tweet> tweets = Arrays.asList(
                new Tweet(1, "user1", "@user2 Hello", Instant.now()),
                new Tweet(2, "user1", "@user3 Hi again", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("user1 should follow user2 and user3",
                followsGraph.get("user1").containsAll(Arrays.asList("user2", "user3")));
    }

    // 6. Empty followsGraph returns an empty influencers list
    @Test
    public void testInfluencersEmptyGraph() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("Expected empty list of influencers", influencers.isEmpty());
    }

    // 7. Single user with no followers returns an empty influencers list
    @Test
    public void testInfluencersSingleUserNoFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>());
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("Expected empty list of influencers", influencers.isEmpty());
    }

    // 8. Single influencer in the graph
    @Test
    public void testInfluencersSingleInfluencer() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", Set.of("user2"));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("Expected user2 as the only influencer", "user2", influencers.get(0));
    }

    // 9. Multiple users with varying followers
    @Test
    public void testInfluencersMultipleUsers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", Set.of("user2", "user3"));
        followsGraph.put("user2", Set.of("user3"));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("Expected user3 as top influencer", "user3", influencers.get(0));
    }

    // 10. Multiple users with equal followers
    @Test
    public void testInfluencersEqualFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", Set.of("user2"));
        followsGraph.put("user3", Set.of("user2"));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("user2 should be the influencer", influencers.contains("user2"));
    }

}
