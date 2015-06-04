package com.github.lpandzic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.github.lpandzic.tolkien.Character;
import com.github.lpandzic.tolkien.CharacterRepository;
import com.github.lpandzic.tolkien.Magical;
import com.github.lpandzic.tolkien.Ring;
import org.junit.Test;

public class ReflectionAssertionsTest {

    @Test
    public void characterRepositoryShouldBeAnInterface() {

        assertTrue(CharacterRepository.class.isInterface());
    }

    @Test
    public void magicalShouldBeAnAnnotation() {

        assertTrue(Magical.class.isAnnotation());
    }

    @Test
    public void ringShouldNotBeAnAnnotationNorInterface() {

        assertFalse(Ring.class.isAnnotation());
        assertFalse(Ring.class.isInterface());
    }

    @Test
    public void ringShouldBeMagical() {

        assertTrue(Ring.class.isAnnotationPresent(Magical.class));
    }

    @Test
    public void characterShouldHaveNameAndRaceFields() throws NoSuchFieldException {

        assertNotNull(Character.class.getDeclaredField("name"));
        assertNotNull(Character.class.getDeclaredField("race"));
    }
}
