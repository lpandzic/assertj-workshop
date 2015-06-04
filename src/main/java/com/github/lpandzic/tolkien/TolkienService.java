package com.github.lpandzic.tolkien;

import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TolkienService {

    private final CharacterRepository characterRepository;
    private final RingRepository ringRepository;
    private final Clock clock;

    public TolkienService(CharacterRepository characterRepository,
                          RingRepository ringRepository,
                          Clock clock) {

        this.characterRepository = characterRepository;
        this.ringRepository = ringRepository;
        this.clock = clock;
    }

    public List<Character> getFellowshipOfTheRing() {

        return characterRepository.getFellowshipOfTheRing();
    }

    public List<Character> getFellowshipOfTheRingSortedByName() {

        return characterRepository.getFellowshipOfTheRing()
                                  .stream()
                                  .sorted(Character.nameComparator())
                                  .collect(Collectors.toList());
    }

    public List<Character> getFellowshipOfTheRingSortedByAge() {

        List<Character> fellowshipOfTheRing = characterRepository.getFellowshipOfTheRing();

        fellowshipOfTheRing.sort((o1, o2) -> {

            if (o1.getAge(clock).equals(o2.getAge(clock))) {
                return 0;
            }

            if (!o1.getAge(clock).isPresent()) {
                return 1;
            }

            if (!o2.getAge(clock).isPresent()) {
                return -1;
            }

            return o2.getAge(clock).get().compareTo(o1.getAge(clock).get());
        });

        return fellowshipOfTheRing;
    }

    public Map<Ring, Character> getThreeRingsPairedWithBearers() {

        Character gandalf = characterRepository.findCharacterByName("Gandalf");
        Character galadriel = characterRepository.findCharacterByName("Galadriel");
        Character elrond = characterRepository.findCharacterByName("Elrond");

        Ring narya = ringRepository.findRingByName("Narya");
        Ring nenya = ringRepository.findRingByName("Nenya");
        Ring vilya = ringRepository.findRingByName("Vilya");

        Map<Ring, Character> threeRingBearers = new HashMap<>();
        threeRingBearers.put(narya, gandalf);
        threeRingBearers.put(nenya, galadriel);
        threeRingBearers.put(vilya, elrond);
        return threeRingBearers;
    }
}
