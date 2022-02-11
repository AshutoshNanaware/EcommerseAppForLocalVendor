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

/** This is an auto generated class representing the Cart type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Carts", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Cart implements Model {
  public static final QueryField ID = field("Cart", "id");
  public static final QueryField PRODUCTID = field("Cart", "productid");
  public static final QueryField CUSTOMERID = field("Cart", "customerid");
  public static final QueryField SELLERID = field("Cart", "sellerid");
  public static final QueryField QUANTITY = field("Cart", "quantity");
  public static final QueryField PRODUCTNAME = field("Cart", "productname");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID") String productid;
  private final @ModelField(targetType="ID") String customerid;
  private final @ModelField(targetType="ID") String sellerid;
  private final @ModelField(targetType="Float") Double quantity;
  private final @ModelField(targetType="String") String productname;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getProductid() {
      return productid;
  }
  
  public String getCustomerid() {
      return customerid;
  }
  
  public String getSellerid() {
      return sellerid;
  }
  
  public Double getQuantity() {
      return quantity;
  }
  
  public String getProductname() {
      return productname;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Cart(String id, String productid, String customerid, String sellerid, Double quantity, String productname) {
    this.id = id;
    this.productid = productid;
    this.customerid = customerid;
    this.sellerid = sellerid;
    this.quantity = quantity;
    this.productname = productname;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Cart cart = (Cart) obj;
      return ObjectsCompat.equals(getId(), cart.getId()) &&
              ObjectsCompat.equals(getProductid(), cart.getProductid()) &&
              ObjectsCompat.equals(getCustomerid(), cart.getCustomerid()) &&
              ObjectsCompat.equals(getSellerid(), cart.getSellerid()) &&
              ObjectsCompat.equals(getQuantity(), cart.getQuantity()) &&
              ObjectsCompat.equals(getProductname(), cart.getProductname()) &&
              ObjectsCompat.equals(getCreatedAt(), cart.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), cart.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProductid())
      .append(getCustomerid())
      .append(getSellerid())
      .append(getQuantity())
      .append(getProductname())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Cart {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("productid=" + String.valueOf(getProductid()) + ", ")
      .append("customerid=" + String.valueOf(getCustomerid()) + ", ")
      .append("sellerid=" + String.valueOf(getSellerid()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("productname=" + String.valueOf(getProductname()) + ", ")
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
  public static Cart justId(String id) {
    return new Cart(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      productid,
      customerid,
      sellerid,
      quantity,
      productname);
  }
  public interface BuildStep {
    Cart build();
    BuildStep id(String id);
    BuildStep productid(String productid);
    BuildStep customerid(String customerid);
    BuildStep sellerid(String sellerid);
    BuildStep quantity(Double quantity);
    BuildStep productname(String productname);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String productid;
    private String customerid;
    private String sellerid;
    private Double quantity;
    private String productname;
    @Override
     public Cart build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Cart(
          id,
          productid,
          customerid,
          sellerid,
          quantity,
          productname);
    }
    
    @Override
     public BuildStep productid(String productid) {
        this.productid = productid;
        return this;
    }
    
    @Override
     public BuildStep customerid(String customerid) {
        this.customerid = customerid;
        return this;
    }
    
    @Override
     public BuildStep sellerid(String sellerid) {
        this.sellerid = sellerid;
        return this;
    }
    
    @Override
     public BuildStep quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep productname(String productname) {
        this.productname = productname;
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
    private CopyOfBuilder(String id, String productid, String customerid, String sellerid, Double quantity, String productname) {
      super.id(id);
      super.productid(productid)
        .customerid(customerid)
        .sellerid(sellerid)
        .quantity(quantity)
        .productname(productname);
    }
    
    @Override
     public CopyOfBuilder productid(String productid) {
      return (CopyOfBuilder) super.productid(productid);
    }
    
    @Override
     public CopyOfBuilder customerid(String customerid) {
      return (CopyOfBuilder) super.customerid(customerid);
    }
    
    @Override
     public CopyOfBuilder sellerid(String sellerid) {
      return (CopyOfBuilder) super.sellerid(sellerid);
    }
    
    @Override
     public CopyOfBuilder quantity(Double quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder productname(String productname) {
      return (CopyOfBuilder) super.productname(productname);
    }
  }
  
}
