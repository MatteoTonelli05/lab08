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
    
    DeathNote deathNote=new DeathNoteImplementation();

    @BeforeEach
    public void SetUp(){

    };

    @Test
    public void testNegativeOrNullRules(){
        Exception e=assertThrows(IllegalArgumentException.class, ()->deathNote.getRule(-1));
        assertNotNull( e.getMessage());
        assertFalse(e.getMessage().isBlank());

        e=assertThrows(IllegalArgumentException.class, ()->deathNote.getRule(0));
        assertNotNull( e.getMessage());
        assertFalse(e.getMessage().isBlank());
    }

    @Test
    public void testEmptyOrNullRules(){
        Iterator<String> iter=DeathNote.RULES.iterator();
        String tmp;
        while(iter.hasNext())
        {
            tmp=iter.next();
            assertNotNull(tmp);
            assertFalse(tmp.isBlank());
        }
    }

    @Test
    public void testHumanName(){
        String name="nicholas magi";
        String cause="hot sex night";
        String detail="too much males";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assert(deathNote.isNameWritten(name));
        deathNote.writeDeathCause(cause);
        deathNote.writeDetails(detail);
        assertFalse(deathNote.isNameWritten("magi nicholas"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    public void testTimer(){}

}