// Pizza

package headfirst.designpatterns.factory.pizzas;

import java.util.*;

abstract public class Pizza {
  String name;
  String dough;
  String sauce;
  List<String> toppings = new ArrayList<String>();

  public String getName() {
    return name;
  }

  public void prepare() {
    System.out.println("Preparing " + name);
  }

  public void bake() {
    System.out.println("Baking " + name);
  }

  public void cut() {
    System.out.println("Cutting " + name);
  }

  public void box() {
    System.out.println("Boxing " + name);
  }

  public String toString() {
    // code to display pizza name and ingredients
    StringBuffer display = new StringBuffer();
    display.append("---- " + name + " ----\n");
    display.append(dough + "\n");
    display.append(sauce + "\n");
    for (String topping : toppings) {
      display.append(topping + "\n");
    }
    return display.toString();
  }

}


// CheesePizza

public class CheesePizza extends Pizza {
  public CheesePizza() {
    name = "Cheese Pizza";
    dough = "Regular Crust";
    sauce = "Marinara Pizza Sauce";
    toppings.add("Fresh Mozzarella");
    toppings.add("Parmesan");
  }
}

// PepperoniPizza
public class PepperoniPizza extends Pizza {
  public PepperoniPizza() {
    name = "Pepperoni Pizza";
    dough = "Crust";
    sauce = "Marinara sauce";
    toppings.add("Sliced Pepperoni");
    toppings.add("Sliced Onion");
    toppings.add("Grated parmesan cheese");
  }
}


// ClamPizza

public class ClamPizza extends Pizza {
  public ClamPizza() {
    name = "Clam Pizza";
    dough = "Thin crust";
    sauce = "White garlic sauce";
    toppings.add("Clams");
    toppings.add("Grated parmesan cheese");
  }
}

PizzaBuilder
createPhase: PizzaHandler  -> DoughHandler -> ....
updatePhase: DougHandler -> ..... -> PizzaHandler
