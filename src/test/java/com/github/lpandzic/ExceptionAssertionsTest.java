package com.github.lpandzic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionAssertionsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	// then
	@Test(expected = NoSuchFileException.class)
	public void shouldThrowFileNotFoundException() throws IOException {

		// when
		Files.newInputStream(Paths.get("non-existing-file"));
	}

	@Test
	public void shouldThrowExceptionWithAMessage() throws IOException {

		// then ?!
		expectedException.expect(NoSuchFileException.class);
		expectedException.expectMessage("non-existing-file");

		// when
		Files.newInputStream(Paths.get("non-existing-file"));
	}

	@Test
	public void shouldThrowAssertionErrorWithCustomMessage() {

        // given
        SimpleCharacter frodo = new SimpleCharacter("Frodo");
        SimpleCharacter gandalf = new SimpleCharacter("Gandalf");

        //when
        try {
            assertEquals("frodo and gandalf are not the same character", frodo, gandalf);

            fail("exception not thrown");
        } catch (Throwable e) {
            // then
            assertTrue(e instanceof AssertionError);
            assertTrue(e.getMessage().startsWith("frodo and gandalf are not the same character"));
        }
	}
}
