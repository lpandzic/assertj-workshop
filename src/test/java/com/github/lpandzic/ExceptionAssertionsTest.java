package com.github.lpandzic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.nio.file.*;

import org.junit.Test;

public class ExceptionAssertionsTest {

	@Test
	public void shouldThrowFileNotFoundException() throws IOException {

		// when
		Throwable throwable = catchThrowable(() -> Files.newInputStream(Paths.get("non-existing-file")));

		then(throwable).isInstanceOf(NoSuchFileException.class);
	}

	@Test
	public void shouldThrowExceptionWithAMessage() throws IOException {

		// when
		Throwable throwable = catchThrowable(() -> Files.newInputStream(Paths.get("non-existing-file")));

		then(throwable).isInstanceOf(NoSuchFileException.class).hasMessage("non-existing-file");
	}

	@Test
	public void shouldThrowAssertionErrorWithCustomMessage() {

        // given
        SimpleCharacter frodo = new SimpleCharacter("Frodo");
        SimpleCharacter gandalf = new SimpleCharacter("Gandalf");

		Throwable throwable = catchThrowable(() -> assertThat(frodo).overridingErrorMessage("frodo and gandalf are not the same character").isEqualTo(gandalf));

		then(throwable).isInstanceOf(AssertionError.class)
		               .hasMessageStartingWith("frodo and gandalf are not the same character");
	}
}
