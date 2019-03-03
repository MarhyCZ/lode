package test;

import model.board.Ship;
import model.ships.BoatShip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoatShipTest {

    @BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void testShip() {
        Ship ship = new BoatShip();
        Assertions.assertEquals(2, ship.getHealth());
        ship.hit();
        ship.hit();
        Assertions.assertEquals(0, ship.getHealth());
        assertNotNull(ship.getOffsets());
    }
}