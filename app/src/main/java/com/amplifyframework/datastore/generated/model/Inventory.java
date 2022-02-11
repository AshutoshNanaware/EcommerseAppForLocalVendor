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

/** This is an auto generated class representing the Inventory type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Inventories", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Inventory implements Model {
  public static final QueryField ID = field("Inventory", "id");
  public static final QueryField PRODUCTID = field("Inventory", "productid");
  public static final QueryField QUANTITY = field("Inventory", "quantity");
  public static final QueryField PRODUCTPRISE = field("Inventory", "productprise");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID") String productid;
  private final @ModelField(targetType="Float") Double quantity;
  private final @ModelField(targetType="Float") Double productprise;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getProductid() {
      return productid;
  }
  
  public Double getQuantity() {
      return quantity;
  }
  
  public Double getProductprise() {
      return productprise;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Inventory(String id, String productid, Double quantity, Double productprise) {
    this.id = id;
    this.productid = productid;
    this.quantity = quantity;
    this.productprise = productprise;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Inventory inventory = (Inventory) obj;
      return ObjectsCompat.equals(getId(), inventory.getId()) &&
              ObjectsCompat.equals(getProductid(), inventory.getProductid()) &&
              ObjectsCompat.equals(getQuantity(), inventory.getQuantity()) &&
              ObjectsCompat.equals(getProductprise(), inventory.getProductprise()) &&
              ObjectsCompat.equals(getCreatedAt(), inventory.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), inventory.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProductid())
      .append(getQuantity())
      .append(getProductprise())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Inventory {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("productid=" + String.valueOf(getProductid()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("productprise=" + String.valueOf(getProductprise()) + ", ")
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
  public static Inventory justId(String id) {
    return new Inventory(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      productid,
      quantity,
      productprise);
  }
  public interface BuildStep {
    Inventory build();
    BuildStep id(String id);
    BuildStep productid(String productid);
    BuildStep quantity(Double quantity);
    BuildStep productprise(Double productprise);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String productid;
    private Double quantity;
    private Double productprise;
    @Override
     public Inventory build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Inventory(
          id,
          productid,
          quantity,
          productprise);
    }
    
    @Override
     public BuildStep productid(String productid) {
        this.productid = productid;
        return this;
    }
    
    @Override
     public BuildStep quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep productprise(Double productprise) {
        this.productprise = productprise;
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
    private CopyOfBuilder(String id, String productid, Double quantity, Double productprise) {
      super.id(id);
      super.productid(productid)
        .quantity(quantity)
        .productprise(productprise);
    }
    
    @Override
     public CopyOfBuilder productid(String productid) {
      return (CopyOfBuilder) super.productid(productid);
    }
    
    @Override
     public CopyOfBuilder quantity(Double quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder productprise(Double productprise) {
      return (CopyOfBuilder) super.productprise(productprise);
    }
  }
  
}
