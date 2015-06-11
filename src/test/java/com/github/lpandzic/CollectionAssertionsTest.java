package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.DWARF;
import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static com.github.lpandzic.tolkien.Race.MAN;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.time.*;
import java.util.*;

import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CollectionAssertionsTest {

	@InjectMocks
	private TolkienService tolkienService;

	@Mock
	private CharacterRepository characterRepository;

    @Mock
    private Clock clock;

	private List<Character> fellowshipOfTheRing;
	private Character frodo;
	private Character gandalf;

	@Before
	public void setUp() {

		fellowshipOfTheRing = new ArrayList<>();
		frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
		gandalf = new Character("Gandalf", MAIAR, LocalDate.MIN);
		fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(new Character("Sam", HOBBIT, LocalDate.of(2980, Month.APRIL, 6)));
		fellowshipOfTheRing.add(new Character("Merry", HOBBIT, LocalDate.of(2982, 1, 1)));
		fellowshipOfTheRing.add(new Character("Pippin", HOBBIT, LocalDate.of(2990, 1, 1)));
		fellowshipOfTheRing.add(new Character("Aragorn", MAN, LocalDate.of(2931, Month.MARCH, 1)));
		fellowshipOfTheRing.add(gandalf);
		fellowshipOfTheRing.add(new Character("Legolas",  ELF, null));
		fellowshipOfTheRing.add(new Character("Gimli", DWARF, LocalDate.of(2879, 1, 1)));
		fellowshipOfTheRing.add(new Character("Boromir", MAN, LocalDate.of(2978, 1, 1)));
	}

	@Test
	public void foundHeroesShouldContainFrodoAndGandalfAndNotSauronOrElrond() {

		given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

		then(heroes).extracting(Character::getName).contains("Frodo", "Gandalf").doesNotContain("Sauron", "Elrond");
	}

    @Test
    public void foundHeroesShouldContainFrodoAndGandalfAndNotSauronUsingNameComparator() {

        given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);

        // when
        List<Character> heroes = tolkienService.getFellowshipOfTheRing();

	    then(heroes).usingElementComparator(Character.nameComparator())
                    .contains(frodo, gandalf)
                    .doesNotContain(new Character("Sauron", null, null), new Character("Elrond", null, null));
    }

	@Test
	public void foundHeroesShouldContainFrodoAndGandalfByNameAndRace() {

		given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

        then(heroes).extracting(Character::getName, Character::getRace)
                    .contains(tuple("Frodo", Race.HOBBIT), tuple("Gandalf", Race.MAIAR));

	}

	@Test
	public void foundHeroesShouldContainFrodoAndGandalf() {

		given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

        then(heroes).usingFieldByFieldElementComparator()
                    .contains(frodo, gandalf);
	}

    @Test
    public void shouldOrderFellowshipOfTheRingByName() {

        given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);

        // when
        List<Character> actual = tolkienService.getFellowshipOfTheRingSortedByName();

        then(actual).extracting(Character::getName).containsExactly("Aragorn",
                                                                    "Boromir",
                                                                    "Frodo",
                                                                    "Gandalf",
                                                                    "Gimli",
                                                                    "Legolas",
                                                                    "Merry",
                                                                    "Pippin",
                                                                    "Sam");
    }

    @Test
    public void shouldSortFellowshipOfTheRingByAge() {

        given(characterRepository.getFellowshipOfTheRing()).willReturn(fellowshipOfTheRing);
        given(clock.instant()).willReturn(Instant.now());
        given(clock.getZone()).willReturn(ZoneId.systemDefault());

        // when
        List<Character> actual = tolkienService.getFellowshipOfTheRingSortedByAge();

        then(actual).extracting(Character::getName)
                    .containsExactly("Gandalf",
                                     "Gimli",
                                     "Aragorn",
                                     "Frodo",
                                     "Boromir",
                                     "Sam",
                                     "Merry",
                                     "Pippin",
                                     "Legolas");
    }

    @Test
	public void shouldHaveTeammates() {

		Player pippen = new Player();
		Player kukoc = new Player();
		Player jabbar = new Player();
		Player worthy = new Player();
		Player jordan = new Player();
		Player magic = new Player();

		jordan.addTeammate(pippen);
		jordan.addTeammate(kukoc);
		magic.addTeammate(jabbar);
		magic.addTeammate(worthy);

        List<Player> reallyGoodPlayers = Arrays.asList(jordan, magic);

        then(reallyGoodPlayers).flatExtracting(Player::getTeammates)
                               .contains(pippen, kukoc, jabbar, worthy);
    }
}
