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

/** This is an auto generated class representing the Shop type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Shops", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Shop implements Model {
  public static final QueryField ID = field("Shop", "id");
  public static final QueryField USERNAME = field("Shop", "username");
  public static final QueryField SHOPPIN = field("Shop", "shoppin");
  public static final QueryField SHOPNAME = field("Shop", "shopname");
  public static final QueryField ADDRESS = field("Shop", "address");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String") String shoppin;
  private final @ModelField(targetType="String") String shopname;
  private final @ModelField(targetType="String") String address;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getShoppin() {
      return shoppin;
  }
  
  public String getShopname() {
      return shopname;
  }
  
  public String getAddress() {
      return address;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Shop(String id, String username, String shoppin, String shopname, String address) {
    this.id = id;
    this.username = username;
    this.shoppin = shoppin;
    this.shopname = shopname;
    this.address = address;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Shop shop = (Shop) obj;
      return ObjectsCompat.equals(getId(), shop.getId()) &&
              ObjectsCompat.equals(getUsername(), shop.getUsername()) &&
              ObjectsCompat.equals(getShoppin(), shop.getShoppin()) &&
              ObjectsCompat.equals(getShopname(), shop.getShopname()) &&
              ObjectsCompat.equals(getAddress(), shop.getAddress()) &&
              ObjectsCompat.equals(getCreatedAt(), shop.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), shop.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getShoppin())
      .append(getShopname())
      .append(getAddress())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Shop {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("shoppin=" + String.valueOf(getShoppin()) + ", ")
      .append("shopname=" + String.valueOf(getShopname()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
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
  public static Shop justId(String id) {
    return new Shop(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      shoppin,
      shopname,
      address);
  }
  public interface UsernameStep {
    BuildStep username(String username);
  }
  

  public interface BuildStep {
    Shop build();
    BuildStep id(String id);
    BuildStep shoppin(String shoppin);
    BuildStep shopname(String shopname);
    BuildStep address(String address);
  }
  

  public static class Builder implements UsernameStep, BuildStep {
    private String id;
    private String username;
    private String shoppin;
    private String shopname;
    private String address;
    @Override
     public Shop build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Shop(
          id,
          username,
          shoppin,
          shopname,
          address);
    }
    
    @Override
     public BuildStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep shoppin(String shoppin) {
        this.shoppin = shoppin;
        return this;
    }
    
    @Override
     public BuildStep shopname(String shopname) {
        this.shopname = shopname;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
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
    private CopyOfBuilder(String id, String username, String shoppin, String shopname, String address) {
      super.id(id);
      super.username(username)
        .shoppin(shoppin)
        .shopname(shopname)
        .address(address);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder shoppin(String shoppin) {
      return (CopyOfBuilder) super.shoppin(shoppin);
    }
    
    @Override
     public CopyOfBuilder shopname(String shopname) {
      return (CopyOfBuilder) super.shopname(shopname);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
  }
  
}
