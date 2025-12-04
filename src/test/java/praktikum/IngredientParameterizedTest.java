package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    Ingredient ingredient;
    private final String name;
    private final float price;
    private final IngredientType type;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ингредиент: {0} {1} {2}")
    public static Object[] getTestData() {
        return new Object[][]{
                {IngredientType.FILLING, "Ингредиент1", 100f},
                {IngredientType.SAUCE, "Ингредиент2", 150f},
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getNameIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getName";

        // Act
        String actual = ingredient.getName();

        // Assert
        assertEquals(message, name, actual);
    }

    @Test
    public void getPriceIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getPrice";

        // Act
        float actual = ingredient.getPrice();

        // Assert
        assertEquals(message, price, actual, 0);
    }

    @Test
    public void getTypeIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getType";

        // Act
        IngredientType actual = ingredient.getType();

        // Assert
        assertEquals(message, type, actual);
    }
}
