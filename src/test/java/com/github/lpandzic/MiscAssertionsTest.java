package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.*;

import com.github.lpandzic.tolkien.Character;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class MiscAssertionsTest {

    private Character frodo;
    private Character gandalf;
    private Character sam;

    @Before
    public void setUp() {

        frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
        gandalf = new Character("Gandalf", MAIAR, LocalDate.MIN);
        sam = new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6));
    }

    @Test
    public void frodoShouldBeYoungerThanGandalfButOlderThanSam() {

        then(frodo.getBirthday()).isAfter(gandalf.getBirthday()).isBefore(sam.getBirthday());
    }

    @Test
    public void shouldBeEqualToInHoursAndMinutes() {

        LocalTime noon = LocalTime.of(12, 0);
        LocalTime secondAfterNoon = LocalTime.of(12, 0, 1);

        then(noon).isEqualToIgnoringSeconds(secondAfterNoon);
    }

    /**
     * SoftAssertions
     */
    @Test
    public void frodoShouldBeOfSameRaceAsSamButNotGandalf() {

        BDDSoftAssertions softly = new BDDSoftAssertions();
        softly.then(frodo.getRace()).isEqualTo(sam.getRace());
        softly.then(frodo.getRace()).isNotEqualTo(gandalf.getRace());
        softly.assertAll();
    }

    @Test
    public void frodoAndSamShouldBeEqualIgnoringAllFieldsExceptRace() {

        then(frodo).isEqualToComparingOnlyGivenFields(sam, "race");
    }

    @Test
    public void frodoAndSamShouldBeEqualIgnoringAllFieldsExceptRaceUsingNull() {

        then(frodo).isEqualToIgnoringNullFields(new Character(null, HOBBIT, null));
    }
}
