package org.pizzaKata;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaiterTest {
    Customer customer = Mockito.mock(Customer.class);
    Orders orders = Mockito.mock(Orders.class);
    Kitchen kitchen = Mockito.mock(Kitchen.class);
    Waiter waiter = new Waiter(kitchen, orders);
    @Test
    public void shouldCallKitchenToMakePizzaWithOneIngredientWhenCustomerHasFinishedTheOrder() {
        String IngredientsFromCustomer = "cheese";
        String nextIngredient = null;
        Mockito.when(orders.getCustomerExistingIngredients(customer)).thenReturn(IngredientsFromCustomer);
        Mockito.when(customer.getNextIngredient()).thenReturn(nextIngredient);

        waiter.serve(customer);

        Mockito.verify(kitchen).makePizzaWith(IngredientsFromCustomer);
    }

    @Test
    public void shouldNotCallKitchenToMakePizzaWhenCustomerHasNotFinishedTheOrder() {
        String IngredientsFromCustomer = "cheese, tomato";
        String nextIngredient = "oregano";
        Mockito.when(orders.getCustomerExistingIngredients(customer)).thenReturn(IngredientsFromCustomer);
        Mockito.when(customer.getNextIngredient()).thenReturn(nextIngredient);

        waiter.serve(customer);

        Mockito.verifyNoMoreInteractions(kitchen);
    }

    @Test
    public void shouldNotCallKitchenToMakePizzaWhenCustomerHasNotOrderedAnything() {
        String IngredientsFromCustomer = null;
        String nextIngredient = null;
        Mockito.when(orders.getCustomerExistingIngredients(customer)).thenReturn(IngredientsFromCustomer);
        Mockito.when(customer.getNextIngredient()).thenReturn(nextIngredient);

        waiter.serve(customer);

        Mockito.verifyNoMoreInteractions(kitchen);
    }

    @Test
    public void ShouldCallKitchenToMakePizzaWithMultipleIngredientsWhenCustomerHasFinishedTheOrder() {
        String IngredientsFromCustomer = "cheese, tomato";
        String nextIngredient1 = "oregano";
        Mockito.when(customer.getNextIngredient()).thenReturn(nextIngredient1);
        waiter.serve(customer);

        Mockito.verifyNoMoreInteractions(kitchen);

        String finalIngredientsFromCustomer = IngredientsFromCustomer + ", " + nextIngredient1;
        Mockito.when(orders.getCustomerExistingIngredients(customer)).thenReturn(finalIngredientsFromCustomer);

        String nextIngredient2 = null;
        Mockito.when(customer.getNextIngredient()).thenReturn(nextIngredient2);
        waiter.serve(customer);

        Mockito.verify(kitchen).makePizzaWith(finalIngredientsFromCustomer);
    }
}
