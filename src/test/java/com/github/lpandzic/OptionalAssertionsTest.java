package com.github.lpandzic;

import static com.github.lpandzic.tolkien.Race.ELF;
import static com.github.lpandzic.tolkien.Race.HOBBIT;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.*;

import com.github.lpandzic.tolkien.Character;
import org.junit.Test;

public class OptionalAssertionsTest {

    private Clock clock = mock(Clock.class);

    @Test
    public void frodosAgeShouldBeFifty() {

        // given date is Council of Elrond
        LocalDate localDate = LocalDate.of(3018, Month.OCTOBER, 25);
        given(clock.instant()).willReturn(localDate.atStartOfDay().atZone(ZoneOffset.systemDefault()).toInstant());
        given(clock.getZone()).willReturn(ZoneId.systemDefault());

        Character frodo = new Character("Frodo", HOBBIT, LocalDate.of(2968, Month.SEPTEMBER, 22));

        then(frodo.getAge(clock)).isPresent().contains(50);
    }

    @Test
    public void legolasAgeShouldBeUnknown() {

	    Character legolas = new Character("Legolas", ELF, null);

        then(legolas.getAge(clock)).isEmpty();
    }
}
