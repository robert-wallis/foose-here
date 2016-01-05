package com.smilingrob.foosehere.match;

/**
 * Tournament player.
 */
public class Player {

    String name;
    boolean isHere;

    /**
     * Make a new player.
     *
     * @param name player's name.
     */
    public Player(String name) {
        this.name = name;
    }

    public String getmName() {
        return name;
    }

    public void setmName(String mName) {
        this.name = mName;
    }

    /**
     * Currently availible to play.
     *
     * @return true if available.
     */
    public boolean isHere() {
        return isHere;
    }

    public void setIsHere(boolean isHere) {
        this.isHere = isHere;
    }

    @Override
    public String toString() {
        return "Player{" + name + '}';
    }
}
