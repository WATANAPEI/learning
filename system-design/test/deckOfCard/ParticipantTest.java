package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    public void testParticipantCreate() {
        Participant p = new Participant();
        assertEquals("Anonymous", p.getName());
        Participant p2 = new Participant("Alice");
        assertEquals("Alice", p2.getName());
        assertNotEquals(p.getId(), p2.getId());
    }

}