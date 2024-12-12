package chessgame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnightDirectionTest {
    @Test
    void of() {
        assertSame(KnightDirection.LEFT_UP, KnightDirection.of(-1, -2));
        assertSame(KnightDirection.RIGHT_UP, KnightDirection.of(-1, 2));
        assertSame(KnightDirection.LEFT_DOWN, KnightDirection.of(1, -2));
        assertSame(KnightDirection.RIGHT_DOWN, KnightDirection.of(1, 2));
        assertSame(KnightDirection.UP_LEFT, KnightDirection.of(-2, -1));
        assertSame(KnightDirection.UP_RIGHT, KnightDirection.of(-2, 1));
        assertSame(KnightDirection.DOWN_LEFT, KnightDirection.of(2, -1));
        assertSame(KnightDirection.DOWN_RIGHT, KnightDirection.of(2, 1));
    }

    @Test
    void of_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> KingDirection.of(0, 0));
    }
}