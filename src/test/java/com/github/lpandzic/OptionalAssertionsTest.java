package com.github.lpandzic;

import com.github.lpandzic.tolkien.Character;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OptionalAssertionsTest {

    private Clock clock = mock(Clock.class);

    @Test
    public void frodosAgeShouldBeFifty() {

        // given date is Council of Elrond
        LocalDate localDate = LocalDate.of(3018, Month.OCTOBER, 25);
        when(clock.instant()).thenReturn(localDate.atStartOfDay().atZone(ZoneOffset.systemDefault()).toInstant());
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));

        assertTrue(frodo.getAge(clock).isPresent());
        assertTrue(frodo.getAge(clock).get().equals(50));
    }

    @Test
    public void legolasAgeShouldBeUnknown() {

	    Character legolas = new Character("Legolas", ELF, null);

        assertFalse(legolas.getAge(clock).isPresent());
    }
}
