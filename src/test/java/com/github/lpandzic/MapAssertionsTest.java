package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static com.github.lpandzic.tolkien.Race.MAIAR;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.*;
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
	public void threeRingsShouldBeBearedByGandalfElrondAndGaladrielAndNotFrodo() {

		given(ringRepository.findRingByName("Narya")).willReturn(narya);
		given(ringRepository.findRingByName("Nenya")).willReturn(nenya);
		given(ringRepository.findRingByName("Vilya")).willReturn(vilya);
		given(ringRepository.findRingByName("One Ring")).willReturn(oneRing);
		given(characterRepository.findCharacterByName("Elrond")).willReturn(elrond);
		given(characterRepository.findCharacterByName("Galadriel")).willReturn(galadriel);
		given(characterRepository.findCharacterByName("Frodo")).willReturn(frodo);
		given(characterRepository.findCharacterByName("Gandalf")).willReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		then(threeRingBearers).containsEntry(narya, gandalf)
		                      .containsEntry(nenya, galadriel)
		                      .containsEntry(vilya, elrond)
		                      .doesNotContainEntry(oneRing, frodo);
	}

	@Test
	public void gandalfAndGaladrielShouldBeOneOfTheThreeRingBearers() {

		given(ringRepository.findRingByName("Narya")).willReturn(narya);
		given(ringRepository.findRingByName("Nenya")).willReturn(nenya);
		given(ringRepository.findRingByName("Vilya")).willReturn(vilya);
		given(ringRepository.findRingByName("One Ring")).willReturn(oneRing);
		given(characterRepository.findCharacterByName("Elrond")).willReturn(elrond);
		given(characterRepository.findCharacterByName("Galadriel")).willReturn(galadriel);
		given(characterRepository.findCharacterByName("Frodo")).willReturn(frodo);
		given(characterRepository.findCharacterByName("Gandalf")).willReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		then(threeRingBearers).containsValues(gandalf, galadriel);
	}

	@Test
	public void threeRingsShouldBeNaryaNenyaAndVilyaAndNotOneRing() {

		given(ringRepository.findRingByName("Narya")).willReturn(narya);
		given(ringRepository.findRingByName("Nenya")).willReturn(nenya);
		given(ringRepository.findRingByName("Vilya")).willReturn(vilya);
		given(ringRepository.findRingByName("One Ring")).willReturn(oneRing);
		given(characterRepository.findCharacterByName("Elrond")).willReturn(elrond);
		given(characterRepository.findCharacterByName("Galadriel")).willReturn(galadriel);
		given(characterRepository.findCharacterByName("Frodo")).willReturn(frodo);
		given(characterRepository.findCharacterByName("Gandalf")).willReturn(gandalf);

		// when
		Map<Ring, Character> threeRingBearers = tolkienService.getThreeRingsPairedWithBearers();

		then(threeRingBearers).containsKeys(narya, nenya, vilya).doesNotContainKey(oneRing);
	}
}
