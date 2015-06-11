package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.github.lpandzic.tolkien.Character;
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

        then(character).isNull();
    }

    @Test
    public void frodoShouldHaveAName() {

        then(frodo.getName()).isNotNull();
    }

    @Test
    public void fellowshipOfTheRingShouldContainFrodo() {

        then(fellowshipOfTheRing).contains(frodo);
    }

    @Test
    public void shouldBeEqual() {

        then(frodo).isEqualTo(new SimpleCharacter("Frodo"));
    }

    @Test
    public void shouldBeEmpty() {

        then(new ArrayList<>()).isEmpty();
    }

    @Test
    public void fellowshipOfTheRingShouldHaveNineMembers() {

        then(fellowshipOfTheRing).hasSize(9);
    }

    @Test
    public void shouldBeEqualArrays() {

        then(new int[]{}).isEqualTo(new int[]{});
    }

    @Test
    public void shouldBeSame() {

        then(fellowshipOfTheRing).isSameAs(fellowshipOfTheRing);
    }

    @Test
    public void frodosNameShouldStartWithFroAndEndWithDo() {

        then(frodo.getName()).startsWith("Fro").endsWith("do");
    }

    @Test
    public void frodoShouldNotBeEqualToSamUsingNameComparator() {

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
        Character sam = new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6));

        then(frodo).usingComparator(Character.nameComparator()).isNotEqualTo(sam);
    }

    @Test
    public void frodoShouldBeEqualToSamUsingRaceComparator() {

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
        Character sam = new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6));

        then(frodo).usingComparator(Character.raceComparator()).isEqualTo(sam);
    }
}
