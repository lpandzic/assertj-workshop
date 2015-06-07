package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.DWARF;
import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static com.github.lpandzic.tolkien.Race.MAN;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.CharacterRepository;
import com.github.lpandzic.tolkien.Race;
import com.github.lpandzic.tolkien.TolkienService;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
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

		// given
		when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

		// then
		assertThat(heroes, hasItems(hasProperty("name", is("Frodo")),
		                            hasProperty("name", is("Gandalf"))));
		assertThat(heroes, not(hasItem(hasProperty("name", is("Sauron")))));
		assertThat(heroes, not(hasItem(hasProperty("name", is("Elrond")))));
	}

    @Test
    public void foundHeroesShouldContainFrodoAndGandalfAndNotSauronUsingNameComparator() {

        // given
        when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);

        // when
        List<Character> heroes = tolkienService.getFellowshipOfTheRing();

        // then
        assertThat(heroes, hasItems(hasNameUsingComparator("Frodo"),
                                    hasNameUsingComparator("Gandalf")));
        assertThat(heroes, not(hasItem(hasNameUsingComparator("Sauron"))));
        assertThat(heroes, not(hasItem(hasNameUsingComparator("Elrond"))));
    }

	@Test
	public void foundHeroesShouldContainFrodoAndGandalfByNameAndRace() {

		// given
		when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

		// then
		assertThat(heroes, hasItems(hasNameAndRace("Frodo", Race.HOBBIT),
                                    hasNameAndRace("Gandalf", Race.MAIAR)));

	}

	@Test
	public void foundHeroesShouldContainFrodoAndGandalf() {

		// given
		when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);

		// when
		List<Character> heroes = tolkienService.getFellowshipOfTheRing();

		// then
		assertThat(heroes,
                   hasItems(new ReflectionEquals(new Character("Frodo",
                                                               HOBBIT,
                                                               LocalDate.of(2968, Month.SEPTEMBER, 22))),
                            new ReflectionEquals(new Character("Gandalf", MAIAR, LocalDate.MIN))));
	}

    @Test
    public void shouldOrderFellowshipOfTheRingByName() {

        // given
        when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);

        // when
        List<Character> actual = tolkienService.getFellowshipOfTheRingSortedByName();

        // then
        assertThat(actual,
                   contains(hasProperty("name", equalTo("Aragorn")),
                            hasProperty("name", equalTo("Boromir")),
                            hasProperty("name", equalTo("Frodo")),
                            hasProperty("name", equalTo("Gandalf")),
                            hasProperty("name", equalTo("Gimli")),
                            hasProperty("name", equalTo("Legolas")),
                            hasProperty("name", equalTo("Merry")),
                            hasProperty("name", equalTo("Pippin")),
                            hasProperty("name", equalTo("Sam"))));
    }

    @Test
    public void shouldSortFellowshipOfTheRingByAge() {

        // given
        when(characterRepository.getFellowshipOfTheRing()).thenReturn(fellowshipOfTheRing);
        when(clock.instant()).thenReturn(Instant.now());
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        // when
        List<Character> actual = tolkienService.getFellowshipOfTheRingSortedByAge();

        // then
        assertThat(actual,
                   contains(hasProperty("name", equalTo("Gandalf")),
                            hasProperty("name", equalTo("Gimli")),
                            hasProperty("name", equalTo("Aragorn")),
                            hasProperty("name", equalTo("Frodo")),
                            hasProperty("name", equalTo("Boromir")),
                            hasProperty("name", equalTo("Sam")),
                            hasProperty("name", equalTo("Merry")),
                            hasProperty("name", equalTo("Pippin")),
                            hasProperty("name", equalTo("Legolas"))));
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

        // then
        List<Player> teammates = reallyGoodPlayers.stream()
                                                .flatMap(player -> player.getTeammates().stream())
                                                .collect(Collectors.toList());
		assertThat(teammates, contains(pippen, kukoc, jabbar, worthy));
	}

	private Matcher<Character> hasNameAndRace(String name, Race race) {

		return new TypeSafeMatcher<Character>() {
			@Override
			public void describeTo(Description description) {

				description.appendText(String.format(" has name %s and race %s", name, race));
			}

			@Override
			protected boolean matchesSafely(Character item) {

				return Objects.equals(name, item.getName()) && Objects.equals(race, item.getRace());
			}
		};
	}

    private Matcher<Character> hasNameUsingComparator(String name) {

        return new TypeSafeMatcher<Character>() {
            @Override
            public void describeTo(Description description) {

                description.appendText(String.format(" has name %s ", name));
            }

            @Override
            protected boolean matchesSafely(Character item) {

                return item.getName().compareTo(name) == 0;
            }
        };
    }
}
