package com.smilingrob.foosehere.match;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Test Match model's logic.
 */
public class MatchTest extends TestCase {

    public void testTeamOneName() throws Exception {
        // GIVEN a match
        List<Player> team1 = Arrays.asList(new Player("one"), new Player("two"));
        List<Player> team2 = Arrays.asList(new Player("three"), new Player("four"));
        Match match = new Match(team1, team2);

        // WHEN the team name is generated
        String teamName = match.teamOneName();

        // THEN it should be the expected name
        assertEquals("one - two", teamName);
    }

    public void testTeamTwoName() throws Exception {
        // GIVEN a match
        List<Player> team1 = Arrays.asList(new Player("one"), new Player("two"));
        List<Player> team2 = Arrays.asList(new Player("three"), new Player("four"));
        Match match = new Match(team1, team2);

        // WHEN the team name is generated
        String teamName = match.teamTwoName();

        // THEN it should be the expected name
        assertEquals("three - four", teamName);
    }
}