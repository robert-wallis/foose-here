package com.smilingrob.foosehere.match;

import java.util.List;

/**
 * Set of matches.
 */
public class Round {

    private List<Match> matches;
    private List<Player> players;

    /**
     * @param matches list of matches in the round.
     */
    public Round(List<Match> matches, List<Player> players) {
        this.matches = matches;
        this.players = players;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
