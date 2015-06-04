package com.github.lpandzic.tolkien;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Optional;

public class Character {

    private final String name;
    private final Race race;
    private final LocalDate birthday;

    public Character(String name, Race race, LocalDate birthday) {

        this.name = name;
        this.race = race;
        this.birthday = birthday;
    }

    public static Comparator<Character> nameComparator() {

        return Comparator.comparing(Character::getName);
    }

    public static Comparator<Character> raceComparator() {

        return Comparator.comparing(Character::getRace);
    }

    public String getName() {

        return name;
    }

    public Optional<Integer> getAge(Clock clock) {

        if (birthday == null) {
            return Optional.empty();
        }
        Period period = Period.between(birthday, LocalDate.now(clock));
        return Optional.of(period.getYears());
    }

    public Race getRace() {

        return race;
    }

    public LocalDate getBirthday() {

        return birthday;
    }

    @Override
    public String toString() {

        return "Character{" +
                "name='" + name + '\'' +
                ", race=" + race +
                ", birthday=" + birthday +
                '}';
    }
}
