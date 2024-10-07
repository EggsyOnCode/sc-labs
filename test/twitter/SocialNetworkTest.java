/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * Testing strategy for guessFollowsGraph():
     *
     * Partition the inputs as follows:
     *  - Empty list of tweets.
     *  - List with one tweet:
     *      - No mentions.
     *      - One mention.
     *      - Multiple mentions.
     *  - List with multiple tweets:
     *      - No mentions in any tweet.
     *      - Some tweets with mentions, some without.
     *      - All tweets with mentions, but no overlap in mentioned users.
     *      - Tweets with overlapping mentions.
     */

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testGuessFollowsGraphEmpty() {
        // Covers the case where the list of tweets is empty.
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());

        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    @Test
    public void testGuessFollowsGraphSingleTweetNoMentions() {
        // Covers the case where there is one tweet with no mentions.
        List<Tweet> tweets = List.of(
                new Tweet(1, "userA", "Hello world!", Instant.parse("2023-10-01T10:00:00Z"))
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

        assertTrue("userA should have no follows", followsGraph.get("usera").isEmpty());
    }

    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        // Covers the case where there is one tweet with multiple mentions.
        List<Tweet> tweets = List.of(
                new Tweet(1, "userA", "Hello @userB @userC!", Instant.parse("2023-10-01T10:00:00Z")),
                new Tweet(2, "userB", "Hi @userA", Instant.parse("2023-10-01T11:00:00Z"))
        );

        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

        assertTrue("userA should follow userB and userC", followsGraph.get("usera").contains("userb"));
        assertTrue("userA should follow userB and userC", followsGraph.get("usera").contains("userc"));
        assertTrue("userB should follow userA", followsGraph.get("userb").contains("usera"));
    }

    /*
     * Testing strategy for influencers():
     *
     * Partition the inputs as follows:
     *  - Empty followsGraph.
     *  - Non-empty followsGraph:
     *      - No one follows anyone.
     *      - Some users have followers, but others do not.
     *      - All users follow at least one person.
     *      - Users have different numbers of followers.
     *      - Users have the same number of followers.
     */

    @Test
    public void testInfluencersEmpty() {
        // Covers the case where the followsGraph is empty.
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);

        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testInfluencersWithFollowers() {
        // Covers the case where some users have followers, but others do not.
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("userA", Set.of("userB", "userC"));
        followsGraph.put("userB", Set.of("userC"));
        followsGraph.put("userC", Set.of());

        List<String> influencers = SocialNetwork.influencers(followsGraph);

        assertEquals("userC should be the most followed", "userC", influencers.get(0));
        assertTrue("userB should follow after userC", influencers.indexOf("userB") > influencers.indexOf("userC"));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
