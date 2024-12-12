package chessgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    Position position;

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }

    @BeforeEach
    void init() {
        position = new Position(0, 0);
    }

    @Test
    void move() {
        assertPosition(-1, 0, position.move(KingDirection.UP));
        assertPosition(0, 1, position.move(KingDirection.RIGHT));
        assertPosition(1, 0, position.move(KingDirection.DOWN));
        assertPosition(0, -1, position.move(KingDirection.LEFT));
        assertPosition(-1, -1, position.move(KingDirection.UP_LEFT));
        assertPosition(-1, 1, position.move(KingDirection.UP_RIGHT));
        assertPosition(1, -1, position.move(KingDirection.DOWN_LEFT));
        assertPosition(1, 1, position.move(KingDirection.DOWN_RIGHT));

        assertPosition(-2, -1, position.move(KnightDirection.UP_LEFT));
        assertPosition(-2, 1, position.move(KnightDirection.UP_RIGHT));
        assertPosition(2, -1, position.move(KnightDirection.DOWN_LEFT));
        assertPosition(2, 1, position.move(KnightDirection.DOWN_RIGHT));
        assertPosition(-1, -2, position.move(KnightDirection.LEFT_UP));
        assertPosition(1, -2, position.move(KnightDirection.LEFT_DOWN));
        assertPosition(-1, 2, position.move(KnightDirection.RIGHT_UP));
        assertPosition(1, 2, position.move(KnightDirection.RIGHT_DOWN));
    }

    @Test
    void testToString() {
        assertEquals("(0,0)", position.toString());
    }

}
