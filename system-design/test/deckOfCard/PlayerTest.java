package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testParticipantCreate() {
        Player p = new Player();
        assertEquals("Anonymous", p.getName());
        Player p2 = new Player("Alice");
        assertEquals("Alice", p2.getName());
        assertNotEquals(p.getId(), p2.getId());
    }

    @Test
    public void testTreeSet() {
        Player p = new Player("Alice");
        p.haveCard(new NormalCard(Mark.Spade, 3));
        p.haveCard(new NormalCard(Mark.Spade, 8));
        p.haveCard(new NormalCard(Mark.Spade, 7));
        assertEquals(8, p.getHand().first().getNumber());
        assertEquals(3, p.getHand().last().getNumber());

    }

}