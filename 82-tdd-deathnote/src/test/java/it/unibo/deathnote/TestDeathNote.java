package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    DeathNote deathNote = new DeathNoteImplementation();

    @BeforeEach
    public void SetUp() {

    };

    @Test
    public void testNegativeOrNullRules() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(-1));
        assertNotNull(e.getMessage());
        assertFalse(e.getMessage().isBlank());

        e = assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(0));
        assertNotNull(e.getMessage());
        assertFalse(e.getMessage().isBlank());
    }

    @Test
    public void testEmptyOrNullRules() throws Exception {
        Iterator<String> iter = DeathNote.RULES.iterator();
        String tmp;
        while (iter.hasNext()) {
            tmp = iter.next();
            assertNotNull(tmp);
            assertFalse(tmp.isBlank());
        }
    }

    @Test
    public void testHumanName() throws Exception {
        String name = "Paolo Bitta";
        String cause = "murder";
        String detail = "following an argument with his friend Luca";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assert (deathNote.isNameWritten(name));
        deathNote.writeDeathCause(cause);
        deathNote.writeDetails(detail);
        assertFalse(deathNote.isNameWritten("Luca Giurato"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    public void test4() throws Exception {
        assertThrows(Exception.class, () -> deathNote.writeDeathCause("cold"));
        deathNote.writeName("Paolo Ruffini");
        deathNote.writeDeathCause("heart attack");
        String name = "Giancarlo Magalli";
        String cause = "karting accident";
        deathNote.writeName(name);
        assert (deathNote.writeDeathCause(cause));
        assertEquals(cause, deathNote.getDeathCause(name));
        Thread.sleep(100);
        assertThrows(Exception.class, () -> deathNote.writeDeathCause("cold"));
        assertEquals(cause, deathNote.getDeathCause(name));
    }

    @Test
    public void test5() throws Exception {
        assertThrows(Exception.class, () -> deathNote.writeDetails("fighting with Cat-Man (the sarabanda one)"));
        String name = "Fabio de Luigi";
        deathNote.writeName(name);
        assert (deathNote.getDeathCause(name).isEmpty());
        String cause = "ran for too long";
        String cause2 = "Falling from the Messina's bridge";
        deathNote.writeDeathCause(cause);
        deathNote.writeName("Ingegner Cane");
        Thread.sleep(6100);
        deathNote.writeDetails(cause2);
        assertFalse(cause.equals(cause2));
    }

}