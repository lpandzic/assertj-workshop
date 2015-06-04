package com.github.lpandzic.tolkien;

import java.util.List;

public interface CharacterRepository {

    /**
     * Nine companions. So be it. You shall be the Fellowship of the Ring.
     */
    List<Character> getFellowshipOfTheRing();

    Character findCharacterByName(String name);
}
