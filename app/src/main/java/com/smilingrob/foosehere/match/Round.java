package com.smilingrob.foosehere.match;

import java.util.List;

/**
 * Set of matches.
 */
public class Round {

    private List<Match> matches;

    /**
     * @param matches list of matches in the round.
     */
    public Round(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
