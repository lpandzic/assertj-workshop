package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import com.github.lpandzic.tolkien.Ring;
import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.CharacterRepository;
import com.github.lpandzic.tolkien.RingRepository;
import com.github.lpandzic.tolkien.TolkienService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapAssertionsTest {

	@InjectMocks
	private TolkienService tolkienService;

	@Mock
	private RingRepository ringRepository;

	@Mock
	private CharacterRepository characterRepository;

	// characters
	private Character elrond;
	private Character galadriel;
	private Character frodo;
	private Character gandalf;

    // Three Rings for the Elven-kings under the sky
	private Ring narya;
	private Ring nenya;
	private Ring vilya;

    // One for the Dark Lord on his dark throne
	private Ring oneRing;

	@Before
	public void setUp() {

		oneRing = new Ring("One Ring");
		narya = new Ring("Narya");
		nenya = new Ring("Nenya");
		vilya = new Ring("Vilya");

		elrond = new Character("Elrond", ELF, null);
		galadriel = new Character("Galadriel", ELF, null);
		frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));
		gandalf = new Character("Gandalf", MAIAR, LocalDate.MIN);
	}

	@Test
	public void threeRingsShouldBeBearedByGandalfElrondAndGaladriel() {

		// given
		when(ringRepository.findRingByName("Narya")).thenReturn(narya);
		when(ringRepository.findRingByName("Nenya")).thenReturn(nenya);
		when(ringRepository.findRingByName("Vilya")).thenReturn(vilya);
		when(ringRepository.findRingByName("One Ring")).thenReturn(oneRing);
		when(characterRepository.findCharacterByName("Elrond")).thenReturn(elrond);
		when(characterRepository.findCharacterByName("Galadriel")).thenReturn(galadriel);
		when(characterRepository.findCharacterByName("Frodo")).thenReturn(frodo);
		when(characterRepository.findCharacterByName("Gandalf")).thenReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		// then
		assertThat(threeRingBearers, hasEntry(narya, gandalf));
		assertThat(threeRingBearers, hasEntry(nenya, galadriel));
		assertThat(threeRingBearers, not(hasEntry(frodo, oneRing)));
	}

	@Test
	public void gandalfAndGaladrielShouldBeOneOfTheOneRingBearers() {

		// given
		when(ringRepository.findRingByName("Narya")).thenReturn(narya);
		when(ringRepository.findRingByName("Nenya")).thenReturn(nenya);
		when(ringRepository.findRingByName("Vilya")).thenReturn(vilya);
		when(ringRepository.findRingByName("One Ring")).thenReturn(oneRing);
		when(characterRepository.findCharacterByName("Elrond")).thenReturn(elrond);
		when(characterRepository.findCharacterByName("Galadriel")).thenReturn(galadriel);
		when(characterRepository.findCharacterByName("Frodo")).thenReturn(frodo);
		when(characterRepository.findCharacterByName("Gandalf")).thenReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		// then
		assertThat(threeRingBearers, hasValue(gandalf));
		assertThat(threeRingBearers, hasValue(galadriel));
	}

	@Test
	public void oneRingsShouldBeNaryaNenyaAndVilyaAndNotOneRing() {

		// given
		when(ringRepository.findRingByName("Narya")).thenReturn(narya);
		when(ringRepository.findRingByName("Nenya")).thenReturn(nenya);
		when(ringRepository.findRingByName("Vilya")).thenReturn(vilya);
		when(ringRepository.findRingByName("One Ring")).thenReturn(oneRing);
		when(characterRepository.findCharacterByName("Elrond")).thenReturn(elrond);
		when(characterRepository.findCharacterByName("Galadriel")).thenReturn(galadriel);
		when(characterRepository.findCharacterByName("Frodo")).thenReturn(frodo);
		when(characterRepository.findCharacterByName("Gandalf")).thenReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		// then
		assertThat(threeRingBearers, hasKey(narya));
		assertThat(threeRingBearers, hasKey(nenya));
		assertThat(threeRingBearers, hasKey(vilya));
		assertThat(threeRingBearers, not(hasKey(oneRing)));
	}
}
