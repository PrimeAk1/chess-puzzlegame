package chessgame.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedPositionTest {

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.row()),
                () -> assertEquals(expectedCol, position.col())
        );
    }

    static Stream<Position> positionProvider() {
        return Stream.of(new Position(0, 0),
                new Position(0, 2),
                new Position(2, 0),
                new Position(2, 2));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void move(Position position) {
        assertPosition(position.row() - 1, position.col(), position.move(KingDirection.UP));
        assertPosition(position.row(), position.col() + 1, position.move(KingDirection.RIGHT));
        assertPosition(position.row() + 1, position.col(), position.move(KingDirection.DOWN));
        assertPosition(position.row(), position.col() - 1, position.move(KingDirection.LEFT));
        assertPosition(position.row() - 1, position.col() - 1, position.move(KingDirection.UP_LEFT));
        assertPosition(position.row() - 1, position.col() + 1, position.move(KingDirection.UP_RIGHT));
        assertPosition(position.row() + 1, position.col() - 1, position.move(KingDirection.DOWN_LEFT));
        assertPosition(position.row() + 1, position.col() + 1, position.move(KingDirection.DOWN_RIGHT));

        assertPosition(position.row() - 1, position.col() - 2, position.move(KnightDirection.LEFT_UP));
        assertPosition(position.row() - 1, position.col() + 2, position.move(KnightDirection.RIGHT_UP));
        assertPosition(position.row() + 1, position.col() - 2, position.move(KnightDirection.LEFT_DOWN));
        assertPosition(position.row() + 1, position.col() + 2, position.move(KnightDirection.RIGHT_DOWN));
        assertPosition(position.row() - 2, position.col() - 1, position.move(KnightDirection.UP_LEFT));
        assertPosition(position.row() - 2, position.col() + 1, position.move(KnightDirection.UP_RIGHT));
        assertPosition(position.row() + 2, position.col() - 1, position.move(KnightDirection.DOWN_LEFT));
        assertPosition(position.row() + 2, position.col() + 1, position.move(KnightDirection.DOWN_RIGHT));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testToString(Position position) {
        assertEquals(String.format("(%d,%d)", position.row(), position.col()), position.toString());
    }
}
