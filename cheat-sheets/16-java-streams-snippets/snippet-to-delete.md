### Problem

```java
import java.util.Date;
import java.util.Set;

public class Product {
    private String name;
    private Date offerBeginDt;
    private Date offerEndDt;
    private Set<Seller> sellers;

    // Constructor, getters, and setters
    public Product(String name, Date offerBeginDt, Date offerEndDt, Set<Seller> sellers) {
        this.name = name;
        this.offerBeginDt = offerBeginDt;
        this.offerEndDt = offerEndDt;
        this.sellers = sellers;
    }

    public String getName() {
        return name;
    }

    public Date getOfferBeginDt() {
        return offerBeginDt;
    }

    public Date getOfferEndDt() {
        return offerEndDt;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }
}

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Seller {
    private String type; // ONLINE, FACTORY
    private String id;

    // Constructor, getters, and setters
    public Seller(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}

```

### My Original Requirements

1. The store has a inventory that has set of Products
2. When the inventory has products, at any given time only one Product is ACTIVE that is offerEndDt = 12319999
3. When store receives a new Product it adds them with following rules
    - If store's inventory is empty, it will just add the new product
    - If the store's inventory already has a similar product with same name, then it records the Seller if it has
      different type and id.
    - If the new product is from seller type FACTORY AND does not exist in the inventory but there's
      another product from the seller of type FACTORY, then set the offerEndDt = 12319999 on the
      older of 2 offer Dates and add the new Product to the inventory
    - If the new product from seller type FACTORY does not exist in the inventory and there's no other product from
      the seller of type FACTORY, then set the offerEndDt = 12319999 on the new Product and add to the inventory
    - If the new product is from seller type ONLINE AND there exist's an ACTIVE same product from seller FACTORY,
      then just add ONLINE seller
    - If the new product is from seller type ONLINE AND there exist's an ACTIVE same product from seller ONLINE but
      with SAME Id, then skip adding the new product
    - If the new product is from seller type ONLINE AND there exist's an ACTIVE same product from seller ONLINE but
      with different Id, then just add the seller
    - If the new product is from seller type ONLINE AND there exist's an ACTIVE same product from seller ONLINE and
      having the same Id, then set the offerEndDt = 12319999 on the older of 2 offerBeginDt and add new product to
      the inventory

### CHATGPT created following Rules

- Rule 1: If the inventory is empty, add the new product directly
- Rule 2: ACTIVE same product and same seller ID and type, skip
- Rule 3: If the new product is same as existing ACTIVE product but has a different seller type or ID, add it to the existing product's sellers
- Rule 4: If another FACTORY ONLY ACTIVE product exists, deactivate the older product and add new FACTORY as ACTIVE Product
- Rule 5: If another ONLINE ACTIVE product exists, but with same seller Id, deactivate the older product and add new ONLINE as ACTIVE Product
- Rule 6: If another ONLINE ACTIVE product exists, but with different seller Id, just add the seller

### Java Solution

```java
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Store {
    private Set<Product> inventory = new HashSet<>();

    public void addProduct(Product newProduct) {
        // Rule 1: If the inventory is empty, add the new product directly as ACTIVE
        if (inventory.isEmpty()) {
            newProduct.setOfferEndDt(new Date(12319999L));
            inventory.add(newProduct);
            return;
        }

        // Find an ACTIVE product with the same name
        Optional<Product> activeProduct = inventory.stream()
            .filter(p -> p.getName().equals(newProduct.getName()) && p.getOfferEndDt().equals(new Date(12319999L)))
            .findFirst();

        // Check for an ACTIVE factory product
        Optional<Product> activeFactoryProduct = inventory.stream()
            .filter(p -> p.getOfferEndDt().equals(new Date(12319999L)) && p.getSellers().stream().anyMatch(s -> s.getType().equals("FACTORY")))
            .findFirst();

        // Check for an ACTIVE online product with the same seller ID
        Seller newSeller = newProduct.getSellers().iterator().next();
        Optional<Product> activeOnlineProductWithSameId = inventory.stream()
            .filter(p -> p.getOfferEndDt().equals(new Date(12319999L)))
            .filter(p -> p.getSellers().stream()
                .anyMatch(s -> s.getType().equals("ONLINE") && s.getId().equals(newSeller.getId())))
            .findFirst();

        // Apply the rules
        if (activeProduct.isPresent()) {
            Product existingProduct = activeProduct.get();

            // Rule 2: ACTIVE same product and same seller ID and type, skip
            boolean sameSellerExists = existingProduct.getSellers().stream()
                .anyMatch(s -> s.getType().equals(newSeller.getType()) && s.getId().equals(newSeller.getId()));
            if (sameSellerExists) {
                return; // Skip adding
            }

            // Rule 3: If the new product has a different seller type or ID, add it to the existing product's sellers
            existingProduct.getSellers().add(newSeller);

            // Further rules based on seller type
            if (newSeller.getType().equals("FACTORY")) {
                // Rule 4: If another FACTORY ACTIVE product exists, deactivate the older one and set new as ACTIVE
                if (activeFactoryProduct.isPresent()) {
                    Product olderProduct = activeFactoryProduct.get().getOfferBeginDt().before(newProduct.getOfferBeginDt()) ? activeFactoryProduct.get() : newProduct;
                    olderProduct.setOfferEndDt(new Date(System.currentTimeMillis())); // Deactivate
                    newProduct.setOfferEndDt(new Date(12319999L)); // Set new as ACTIVE
                    inventory.add(newProduct);
                }
            } else if (newSeller.getType().equals("ONLINE")) {
                // Rule 5: If another ONLINE ACTIVE product exists with the same seller ID, deactivate older and set new as ACTIVE
                if (activeOnlineProductWithSameId.isPresent()) {
                    Product existingOnlineProduct = activeOnlineProductWithSameId.get();
                    existingOnlineProduct.setOfferEndDt(new Date(System.currentTimeMillis())); // Deactivate existing
                    newProduct.setOfferEndDt(new Date(12319999L)); // Set new as ACTIVE
                    inventory.add(newProduct);
                } else {
                    // Rule 6: If another ONLINE ACTIVE product exists but with a different seller ID, add the new seller
                    existingProduct.getSellers().add(newSeller);
                }
            }
        } else {
            // If no ACTIVE product with the same name exists, add the new product as ACTIVE
            newProduct.setOfferEndDt(new Date(12319999L));
            inventory.add(newProduct);
        }
    }

    public Set<Product> getInventory() {
        return inventory;
    }
}

```