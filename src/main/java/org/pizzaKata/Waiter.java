package org.pizzaKata;

import com.google.common.base.Strings;

public class Waiter {
    private final Kitchen kitchen;
    private final Orders orders;

    public Waiter(Kitchen kitchen, Orders orders) {
        this.kitchen = kitchen;
        this.orders = orders;
    }

    public void serve(Customer customer) {
        if (isCustomerHasCompletedOrder(customer)) {
            String customerExistingIngredients = orders.getCustomerExistingIngredients(customer);
            kitchen.makePizzaWith(customerExistingIngredients);
        }
    }

    private boolean isCustomerHasCompletedOrder(Customer customer) {
        String nextIngredient = customer.getNextIngredient();

        orders.addIngredientToCustomerOrder(customer, nextIngredient);
        String customerExistingIngredients = orders.getCustomerExistingIngredients(customer);

        if (Strings.isNullOrEmpty(customerExistingIngredients)) {
            return false;
        }

        return nextIngredient == null;
    }
}
