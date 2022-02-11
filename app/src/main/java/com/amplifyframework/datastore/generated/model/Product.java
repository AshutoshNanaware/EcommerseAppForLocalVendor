package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Product type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Products", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Product implements Model {
  public static final QueryField ID = field("Product", "id");
  public static final QueryField NAME = field("Product", "name");
  public static final QueryField DETAILS = field("Product", "details");
  public static final QueryField SHOPID = field("Product", "shopid");
  public static final QueryField QUANTITY = field("Product", "quantity");
  public static final QueryField CATEGORYID = field("Product", "categoryid");
  public static final QueryField PRISE = field("Product", "prise");
  public static final QueryField SELLERSHOPID = field("Product", "sellershopid");
  public static final QueryField PRODUCTIMG = field("Product", "productimg");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String details;
  private final @ModelField(targetType="String") String shopid;
  private final @ModelField(targetType="String") String quantity;
  private final @ModelField(targetType="String") String categoryid;
  private final @ModelField(targetType="String") String prise;
  private final @ModelField(targetType="ID") String sellershopid;
  private final @ModelField(targetType="String") String productimg;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getDetails() {
      return details;
  }
  
  public String getShopid() {
      return shopid;
  }
  
  public String getQuantity() {
      return quantity;
  }
  
  public String getCategoryid() {
      return categoryid;
  }
  
  public String getPrise() {
      return prise;
  }
  
  public String getSellershopid() {
      return sellershopid;
  }
  
  public String getProductimg() {
      return productimg;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Product(String id, String name, String details, String shopid, String quantity, String categoryid, String prise, String sellershopid, String productimg) {
    this.id = id;
    this.name = name;
    this.details = details;
    this.shopid = shopid;
    this.quantity = quantity;
    this.categoryid = categoryid;
    this.prise = prise;
    this.sellershopid = sellershopid;
    this.productimg = productimg;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Product product = (Product) obj;
      return ObjectsCompat.equals(getId(), product.getId()) &&
              ObjectsCompat.equals(getName(), product.getName()) &&
              ObjectsCompat.equals(getDetails(), product.getDetails()) &&
              ObjectsCompat.equals(getShopid(), product.getShopid()) &&
              ObjectsCompat.equals(getQuantity(), product.getQuantity()) &&
              ObjectsCompat.equals(getCategoryid(), product.getCategoryid()) &&
              ObjectsCompat.equals(getPrise(), product.getPrise()) &&
              ObjectsCompat.equals(getSellershopid(), product.getSellershopid()) &&
              ObjectsCompat.equals(getProductimg(), product.getProductimg()) &&
              ObjectsCompat.equals(getCreatedAt(), product.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), product.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getDetails())
      .append(getShopid())
      .append(getQuantity())
      .append(getCategoryid())
      .append(getPrise())
      .append(getSellershopid())
      .append(getProductimg())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Product {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("details=" + String.valueOf(getDetails()) + ", ")
      .append("shopid=" + String.valueOf(getShopid()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("categoryid=" + String.valueOf(getCategoryid()) + ", ")
      .append("prise=" + String.valueOf(getPrise()) + ", ")
      .append("sellershopid=" + String.valueOf(getSellershopid()) + ", ")
      .append("productimg=" + String.valueOf(getProductimg()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Product justId(String id) {
    return new Product(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      details,
      shopid,
      quantity,
      categoryid,
      prise,
      sellershopid,
      productimg);
  }
  public interface BuildStep {
    Product build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep details(String details);
    BuildStep shopid(String shopid);
    BuildStep quantity(String quantity);
    BuildStep categoryid(String categoryid);
    BuildStep prise(String prise);
    BuildStep sellershopid(String sellershopid);
    BuildStep productimg(String productimg);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String name;
    private String details;
    private String shopid;
    private String quantity;
    private String categoryid;
    private String prise;
    private String sellershopid;
    private String productimg;
    @Override
     public Product build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Product(
          id,
          name,
          details,
          shopid,
          quantity,
          categoryid,
          prise,
          sellershopid,
          productimg);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep details(String details) {
        this.details = details;
        return this;
    }
    
    @Override
     public BuildStep shopid(String shopid) {
        this.shopid = shopid;
        return this;
    }
    
    @Override
     public BuildStep quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep categoryid(String categoryid) {
        this.categoryid = categoryid;
        return this;
    }
    
    @Override
     public BuildStep prise(String prise) {
        this.prise = prise;
        return this;
    }
    
    @Override
     public BuildStep sellershopid(String sellershopid) {
        this.sellershopid = sellershopid;
        return this;
    }
    
    @Override
     public BuildStep productimg(String productimg) {
        this.productimg = productimg;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String details, String shopid, String quantity, String categoryid, String prise, String sellershopid, String productimg) {
      super.id(id);
      super.name(name)
        .details(details)
        .shopid(shopid)
        .quantity(quantity)
        .categoryid(categoryid)
        .prise(prise)
        .sellershopid(sellershopid)
        .productimg(productimg);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder details(String details) {
      return (CopyOfBuilder) super.details(details);
    }
    
    @Override
     public CopyOfBuilder shopid(String shopid) {
      return (CopyOfBuilder) super.shopid(shopid);
    }
    
    @Override
     public CopyOfBuilder quantity(String quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder categoryid(String categoryid) {
      return (CopyOfBuilder) super.categoryid(categoryid);
    }
    
    @Override
     public CopyOfBuilder prise(String prise) {
      return (CopyOfBuilder) super.prise(prise);
    }
    
    @Override
     public CopyOfBuilder sellershopid(String sellershopid) {
      return (CopyOfBuilder) super.sellershopid(sellershopid);
    }
    
    @Override
     public CopyOfBuilder productimg(String productimg) {
      return (CopyOfBuilder) super.productimg(productimg);
    }
  }
  
}
