package com.github.lpandzic;

import com.github.lpandzic.tolkien.Character;

import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class BasicAssertionsTest {

    private List<SimpleCharacter> fellowshipOfTheRing;
    private SimpleCharacter frodo;

    @Before
    public void setUp() {

        frodo = new SimpleCharacter("Frodo");
        fellowshipOfTheRing = new ArrayList<>();
        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(new SimpleCharacter("Sam"));
        fellowshipOfTheRing.add(new SimpleCharacter("Merry"));
        fellowshipOfTheRing.add(new SimpleCharacter("Pippin"));
        fellowshipOfTheRing.add(new SimpleCharacter("Boromir"));
        fellowshipOfTheRing.add(new SimpleCharacter("Legolas"));
        fellowshipOfTheRing.add(new SimpleCharacter("Gandalf"));
        fellowshipOfTheRing.add(new SimpleCharacter("Gimli"));
        fellowshipOfTheRing.add(new SimpleCharacter("Aragorn"));
    }

    @Test
    public void shouldBeNull() {

        SimpleCharacter character = null;

        assertNull(character);
    }

    @Test
    public void frodoShouldHaveAName() {

        assertNotNull(frodo.getName());
    }

    @Test
    public void fellowshipOfTheRingShouldContainFrodo() {

        assertTrue(fellowshipOfTheRing.contains(frodo));
    }

    @Test
    public void fellowshipOfTheRingShouldNotContainSaruman() {

        assertFalse(fellowshipOfTheRing.contains(new SimpleCharacter("Saruman")));
    }

    @Test
    public void shouldBeEqual() {

        assertEquals(frodo, new SimpleCharacter("Frodo"));
    }

    @Test
    public void shouldNotBeEqual() {

        assertNotEquals(frodo, new SimpleCharacter("Elrond"));
    }

    @Test
    public void shouldBeEmpty() {

        assertEquals(0, new ArrayList<>().size());
    }

    @Test
    public void fellowshipOfTheRingShouldHaveNineMembers() {

        assertEquals(9, fellowshipOfTheRing.size());
    }

    @Test
    public void shouldBeEqualArrays() {

        assertArrayEquals(new int[]{}, new int[]{});
    }

    @Test
    public void shouldBeSame() {

        assertSame(fellowshipOfTheRing, fellowshipOfTheRing);
    }

    @Test
    public void frodosNameShouldStartWithFroAndEndWithDo() {

        assertTrue(frodo.getName().startsWith("Fro"));
        assertTrue(frodo.getName().endsWith("do"));
    }

    @Test
    public void frodoShouldNotBeEqualToSamUsingNameComparator() {

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
        Character sam = new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6));

        assertNotEquals(0, Character.nameComparator().compare(frodo, sam));
    }

    @Test
    public void frodoShouldBeEqualToSamUsingRaceComparator() {

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
        Character sam = new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6));

        assertEquals(0, Character.raceComparator().compare(frodo, sam));
    }
}
