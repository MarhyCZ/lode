package model.board;

import model.ships.BoatShip;
import model.ships.PlaneShip;
import model.ships.TowerShip;
import model.ships.YachtShip;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class Ship Inventory for creating the inventory of the ships we play with.
 * This class is part of game Duelovka.
 */
public class ShipInventory {
    private int shipsNumber;
    private List<Ship> ships;
    private ListIterator<Ship> shipsCounter;

    /**
     * Instantiates a new ship inventory.
     */
    public ShipInventory() {
        initializeShipsInventory();
        this.shipsNumber = ships.size();
    }

    /**
     * Method which initializes ships inventory with specific boats.
     */
    public void initializeShipsInventory() {
        ships = new ArrayList<>();

        ships.add(new YachtShip());
        ships.add(new BoatShip());
        ships.add(new BoatShip());
        ships.add(new BoatShip());
        ships.add(new TowerShip());
        ships.add(new TowerShip());
        ships.add(new PlaneShip());
        ships.add(new PlaneShip());
        shipsCounter = ships.listIterator();
    }

    /**
     * The method which returns next ship.
     *
     * @return the next ship
     */
    public Ship getNextShip() {
        if (shipsCounter.hasNext()) return shipsCounter.next();
        else return null;
    }

    /**
     * The method which returns cells number.
     *
     * @return the cells number
     */
    public int getCellsNumber() {
        int number = 0;
        for (Ship ship : ships) {
            number += ship.getHealth();
        }
        return number;
    }
}
