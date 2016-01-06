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

    public String getName() {
        return name;
    }

    public void setName(String mName) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Player) {
            Player op = (Player) o;
            if (name != null) {
                String opName = op.getName();
                return opName != null && name.contentEquals(opName);
            } else {
                return op.getName() == null;
            }
        }
        return false;
    }
}
