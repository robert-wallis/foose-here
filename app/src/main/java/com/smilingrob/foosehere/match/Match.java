package com.smilingrob.foosehere.match;

import java.util.List;

/**
 * Set of players that play a game.
 */
public class Match {

    List<Player> teamOne;
    List<Player> teamTwo;

    /**
     * Tournament match.
     *
     * @param teamOne players in one team.
     * @param teamTwo playesr in the other team.
     */
    public Match(List<Player> teamOne, List<Player> teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    public List<Player> getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(List<Player> teamOne) {
        this.teamOne = teamOne;
    }

    public List<Player> getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(List<Player> teamTwo) {
        this.teamTwo = teamTwo;
    }

    @Override
    public String toString() {
        return "Match{" + teamOne +
                " vs. " + teamTwo +
                '}';
    }
}
