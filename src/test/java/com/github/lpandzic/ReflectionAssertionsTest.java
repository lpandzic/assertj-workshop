package com.github.lpandzic;

import static org.assertj.core.api.BDDAssertions.then;

import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.*;
import org.junit.Test;

public class ReflectionAssertionsTest {

    @Test
    public void characterRepositoryShouldBeAnInterface() {

        then(CharacterRepository.class).isInterface();
    }

    @Test
    public void magicalShouldBeAnAnnotation() {

        then(Magical.class).isAnnotation();
    }

    @Test
    public void ringShouldNotBeAnAnnotationNorInterface() {

        then(Ring.class).isNotAnnotation().isNotInterface();
    }

    @Test
    public void ringShouldBeMagical() {

        then(Ring.class).hasAnnotation(Magical.class);
    }

    @Test
    public void characterShouldHaveNameAndRaceFields() throws NoSuchFieldException {

        then(Character.class).hasDeclaredFields("name", "race");
    }
}
