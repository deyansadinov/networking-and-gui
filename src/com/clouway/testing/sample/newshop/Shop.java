package com.clouway.testing.sample.newshop;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:41 Oct 14-10-21
 */
public class Shop {
  private HashMap<String, ProductHolder> productHolder = new HashMap<>();

  public int sell(String name, int quantity) {
    if (productHolder.isEmpty()) {
      throw new ShopOutOfProductsException();
    }

    if (productHolder.get(name) == null) {
      throw new ProductNotFoundException();
    }

    ProductHolder myProduct = productHolder.get(name);

    return myProduct.sell(quantity);
  }

  public void addNewProduct(Product product, int maxQuantity, int quantity) {
    productHolder.put(product.getName(), new ProductHolder(product, maxQuantity, quantity));
  }

  public int add(String name, int quantity) {
    ProductHolder myProduct = productHolder.get(name);
    return myProduct.add(quantity);
  }

  public List<Product> sort(SortByPrice sortByPrice) {
    List<Product> products = new ArrayList<>();

    for (Map.Entry<String, ProductHolder> entry : productHolder.entrySet()) {
      products.add(entry.getValue().product);
    }

    Collections.sort(products, sortByPrice);

    return products;
  }

  public void sellOrder(HashMap<String,Integer> orderToSell) {
    for(Map.Entry<String, Integer> entry:orderToSell.entrySet()){
      sell(entry.getKey(),entry.getValue());
    }
  }

  private class ProductHolder {

    private final Product product;
    private final int maxQuantity;
    private int currentQuantity;

    private ProductHolder(Product product, int maxQuantity, int currentQuantity) {
      this.product = product;
      this.maxQuantity = maxQuantity;
      this.currentQuantity = currentQuantity;
    }

    public int sell(int quantity) {
      if (currentQuantity - quantity < 0) {
        throw new ErrorInShopException();
      }

      currentQuantity += -quantity;
      return currentQuantity;
    }

    public int add(int quantity) {
      if (currentQuantity + quantity > maxQuantity) {
        throw new ErrorInShopException();
      }
      currentQuantity += quantity;
      return currentQuantity;
    }

    public Product getProduct() {
      return product;
    }

  }
}
