package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient firstIngredient;

    @Mock
    Ingredient secondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода setBuns";

        // Act
        burger.setBuns(bun);

        // Assert
        assertEquals(message, bun, burger.bun);
    }

    @Test
    public void addIngredientIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода addIngredient";
        SoftAssert softAssert = new SoftAssert();

        // Act
        burger.addIngredient(firstIngredient);

        // Assert
        softAssert.assertEquals(burger.ingredients.size(), 1, message);
        softAssert.assertEquals(burger.ingredients.get(0), firstIngredient, message);
        softAssert.assertAll();
    }

    @Test
    public void removeIngredientIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода removeIngredient";

        // Act
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);

        // Assert
        assertTrue(message, burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода addIngredient";
        SoftAssert softAssert = new SoftAssert();

        // Act
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);

        // Assert
        softAssert.assertEquals(burger.ingredients.get(0), secondIngredient, message);
        softAssert.assertEquals(burger.ingredients.get(1), firstIngredient, message);
        softAssert.assertAll();
    }

    @Test
    public void getPriceIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getPrice";
        float expected = 700F;

        // Act
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(200F);
        Mockito.when(secondIngredient.getPrice()).thenReturn(300F);
        float actual = burger.getPrice();

        // Assert
        assertEquals(message, expected, actual, 0);
    }
}
