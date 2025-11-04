package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

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

        // Act
        burger.addIngredient(ingredient1);

        // Assert
        assertEquals(message, 1, burger.ingredients.size());
        assertEquals(message, ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода removeIngredient";

        // Act
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);

        // Assert
        assertTrue(message, burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода addIngredient";

        // Act
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);

        // Assert
        assertEquals(message, ingredient2, burger.ingredients.get(0));
        assertEquals(message, ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getPrice";
        float expected = 700F;

        // Act
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient1.getPrice()).thenReturn(200F);
        Mockito.when(ingredient2.getPrice()).thenReturn(300F);
        float actual = burger.getPrice();

        // Assert
        assertEquals(message, expected, actual, 0);
    }

    @Test
    public void getReceiptIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getReceipt";
        String bunName = "Булочка";
        String ingredientFillingName = "Ингредиент1";
        String ingredientSauceName = "Ингредиент2";
        float totalPrice = 700f;
        float bunPrice = 100f;
        float ingredient1Price = 200f;
        float ingredient2Price = 300f;
        String expectedReceipt = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n" + "(==== %s ====)%n%nPrice: %f%n",
                bunName,
                IngredientType.FILLING.toString().toLowerCase(),
                ingredientFillingName,
                IngredientType.SAUCE.toString().toLowerCase(),
                ingredientSauceName,
                bunName,
                totalPrice);

        // Act
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient1.getName()).thenReturn(ingredientFillingName);
        Mockito.when(ingredient2.getName()).thenReturn(ingredientSauceName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingredient2Price);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Assert
        assertEquals(message, expectedReceipt, burger.getReceipt());
    }
}
