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
        assertEquals("getName возвращает некорректное значение", "Булочка", bun.getName());
    }

    @Test
    public void getPriceIsCorrect() {
        Assert.assertEquals("getPrice возвращает некорректное значение", 100.5f, bun.getPrice(), 0);
    }
}
