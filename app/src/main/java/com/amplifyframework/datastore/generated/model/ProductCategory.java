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

/** This is an auto generated class representing the ProductCategory type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ProductCategories", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class ProductCategory implements Model {
  public static final QueryField ID = field("ProductCategory", "id");
  public static final QueryField PRODUCT_ID = field("ProductCategory", "ProductID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String ProductID;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getProductId() {
      return ProductID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ProductCategory(String id, String ProductID) {
    this.id = id;
    this.ProductID = ProductID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ProductCategory productCategory = (ProductCategory) obj;
      return ObjectsCompat.equals(getId(), productCategory.getId()) &&
              ObjectsCompat.equals(getProductId(), productCategory.getProductId()) &&
              ObjectsCompat.equals(getCreatedAt(), productCategory.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), productCategory.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProductId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ProductCategory {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("ProductID=" + String.valueOf(getProductId()) + ", ")
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
  public static ProductCategory justId(String id) {
    return new ProductCategory(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      ProductID);
  }
  public interface BuildStep {
    ProductCategory build();
    BuildStep id(String id);
    BuildStep productId(String productId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String ProductID;
    @Override
     public ProductCategory build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ProductCategory(
          id,
          ProductID);
    }
    
    @Override
     public BuildStep productId(String productId) {
        this.ProductID = productId;
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
    private CopyOfBuilder(String id, String productId) {
      super.id(id);
      super.productId(productId);
    }
    
    @Override
     public CopyOfBuilder productId(String productId) {
      return (CopyOfBuilder) super.productId(productId);
    }
  }
  
}
