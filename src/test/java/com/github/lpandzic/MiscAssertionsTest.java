package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import com.github.lpandzic.tolkien.Character;

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

        assertTrue(frodo.getBirthday().isAfter(gandalf.getBirthday()));
        assertTrue(frodo.getBirthday().isBefore(sam.getBirthday()));
    }

    @Test
    public void shouldBeEqualToInHoursAndMinutes() {

        LocalTime noon = LocalTime.of(12, 0);
        LocalTime secondAfterNoon = LocalTime.of(12, 0, 1);

        assertEquals(noon.getHour(), secondAfterNoon.getHour());
        assertEquals(noon.getMinute(), secondAfterNoon.getMinute());
    }

    /**
     * SoftAssertions
     */
    @Test
    public void frodoShouldBeOfSameRaceAsSamButNotGandalf() {

        assertTrue(frodo.getRace().equals(sam.getRace()));
        assertFalse(frodo.getRace().equals(gandalf.getRace()));
    }

    @Test
    public void frodoAndSamShouldBeEqualIgnoringAllFieldsExceptRace() {

        assertTrue(frodo.getRace().equals(sam.getRace()));
    }

    @Test
    public void frodoAndSamShouldBeEqualIgnoringAllFieldsExceptRaceUsingNull() {

        assertTrue(frodo.getRace().equals(sam.getRace()));
    }
}
