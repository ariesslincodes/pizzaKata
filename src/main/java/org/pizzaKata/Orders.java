package org.pizzaKata;

public interface Orders {
    void addIngredientToCustomerOrder(Customer customer, String ingredient);
    String getCustomerExistingIngredients(Customer customer);
}

