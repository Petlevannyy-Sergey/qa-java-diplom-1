package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {
    private final String bunName;
    private final String ingredientFillingName;
    private final String ingredientSauceName;
    private final float totalPrice;
    private final float bunPrice;
    private final float ingredientFillingPrice;
    private final float ingredientSaucePrice;

    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredientFilling;

    @Mock
    Ingredient ingredientSauce;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    public BurgerParameterizedTest(
            String bunName,
            String ingredientFillingName,
            String ingredientSauceName,
            float totalPrice,
            float bunPrice,
            float ingredientFillingPrice,
            float ingredientSaucePrice) {
        this.bunName = bunName;
        this.ingredientFillingName = ingredientFillingName;
        this.ingredientSauceName = ingredientSauceName;
        this.totalPrice = totalPrice;
        this.bunPrice = bunPrice;
        this.ingredientFillingPrice = ingredientFillingPrice;
        this.ingredientSaucePrice = ingredientSaucePrice;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "Булочка: {0}, начинка: {1}, соус: {2}, итоговая стоимость = {3} ({4}х2 + {5} + {6})")
    public static Object[] getTestData() {
        return new Object[][]{
                {"Супер булочка", "Супер начинка", "Супер соус", 700f, 100f, 200f, 300f },
                {"Космическая булочка", "Космическая начинка", "Космический соус", 1100f, 200f, 300f, 400f },
                {"Межгалактическая булочка", "Межгалактическая начинка", "Межгалактический соус", 450f, 50f, 100f, 250f },
                {"Вселенская булочка", "Вселенская начинка", "Вселенский соус", 850f, 150f, 250f, 300f }
        };
    }

    @Test
    public void getReceiptIsCorrect() {
        // Arrange
        String message = "Некорректная работа метода getReceipt";
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
        Mockito.when(ingredientFilling.getName()).thenReturn(ingredientFillingName);
        Mockito.when(ingredientSauce.getName()).thenReturn(ingredientSauceName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(ingredientFillingPrice);
        Mockito.when(ingredientSauce.getPrice()).thenReturn(ingredientSaucePrice);
        Mockito.when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(ingredientFilling);
        burger.addIngredient(ingredientSauce);

        // Assert
        assertEquals(message, expectedReceipt, burger.getReceipt());
    }
}
