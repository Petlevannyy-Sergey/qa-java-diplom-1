package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTests {
    Bun bun;

    @Before
    public void setUp() {
        bun = new Bun("Булочка", 100.5f);
    }

    @Test
    public void getNameIsCorrect() {
        // Arrange
        String message = "getName возвращает некорректное значение";
        String expected = "Булочка";

        // Act
        String actual = bun.getName();

        // Assert
        assertEquals(message, expected, actual);
    }

    @Test
    public void getPriceIsCorrect() {
        // Arrange
        String message = "getPrice возвращает некорректное значение";
        float expected = 100.5f;

        // Act
        float actual = bun.getPrice();

        // Assert
        Assert.assertEquals(message, expected, actual, 0);
    }
}
