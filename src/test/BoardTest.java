package test;

import javafx.scene.input.MouseEvent;
import model.board.Board;
import model.board.Cell;
import model.ships.BoatShip;
import org.junit.jupiter.api.Assertions;

class BoardTest {
    private Board board;
    private Cell cell;
    private BoatShip ship;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        board = new Board(this::playerBoardClick, this::playerBoardEntered, this::playerBoardExited);
        Assertions.assertNotNull(board);
        ship = new BoatShip();
    }

    private void playerBoardExited(MouseEvent mouseEvent) {
    }

    private void playerBoardEntered(MouseEvent mouseEvent) {
    }

    private void playerBoardClick(MouseEvent mouseEvent) {
    }

    @org.junit.jupiter.api.Test
    void clear() {
        Board board2 = new Board(this::playerBoardClick, this::playerBoardEntered, this::playerBoardExited);
        board2.placeShip(ship, board2.getCell(1, 1));
        board2.clear();
        Assertions.assertTrue(board.getCell(1, 2).isEmpty());

    }


    @org.junit.jupiter.api.Test
    void getCell() {
        cell = board.getCell(1, 1);
        Assertions.assertNotNull(cell);
    }

    @org.junit.jupiter.api.Test
    void placeShip() {
        board.placeShip(ship, cell);
        Assertions.assertNotNull(cell.getShip());
        Cell cell2 = board.getCell(2, 1);
        Assertions.assertNotNull(cell2.getShip());
    }

}