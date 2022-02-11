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

/** This is an auto generated class representing the Customer type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Customers", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Customer implements Model {
  public static final QueryField ID = field("Customer", "id");
  public static final QueryField USERNAME = field("Customer", "username");
  public static final QueryField NAME = field("Customer", "name");
  public static final QueryField MAIL = field("Customer", "mail");
  public static final QueryField PASSWORD = field("Customer", "password");
  public static final QueryField ADDRESS = field("Customer", "address");
  public static final QueryField PIN = field("Customer", "pin");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String mail;
  private final @ModelField(targetType="String") String password;
  private final @ModelField(targetType="String") String address;
  private final @ModelField(targetType="String") String pin;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getName() {
      return name;
  }
  
  public String getMail() {
      return mail;
  }
  
  public String getPassword() {
      return password;
  }
  
  public String getAddress() {
      return address;
  }
  
  public String getPin() {
      return pin;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Customer(String id, String username, String name, String mail, String password, String address, String pin) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.mail = mail;
    this.password = password;
    this.address = address;
    this.pin = pin;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Customer customer = (Customer) obj;
      return ObjectsCompat.equals(getId(), customer.getId()) &&
              ObjectsCompat.equals(getUsername(), customer.getUsername()) &&
              ObjectsCompat.equals(getName(), customer.getName()) &&
              ObjectsCompat.equals(getMail(), customer.getMail()) &&
              ObjectsCompat.equals(getPassword(), customer.getPassword()) &&
              ObjectsCompat.equals(getAddress(), customer.getAddress()) &&
              ObjectsCompat.equals(getPin(), customer.getPin()) &&
              ObjectsCompat.equals(getCreatedAt(), customer.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), customer.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getName())
      .append(getMail())
      .append(getPassword())
      .append(getAddress())
      .append(getPin())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Customer {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("mail=" + String.valueOf(getMail()) + ", ")
      .append("password=" + String.valueOf(getPassword()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("pin=" + String.valueOf(getPin()) + ", ")
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
  public static Customer justId(String id) {
    return new Customer(
      id,
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
      username,
      name,
      mail,
      password,
      address,
      pin);
  }
  public interface UsernameStep {
    BuildStep username(String username);
  }
  

  public interface BuildStep {
    Customer build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep mail(String mail);
    BuildStep password(String password);
    BuildStep address(String address);
    BuildStep pin(String pin);
  }
  

  public static class Builder implements UsernameStep, BuildStep {
    private String id;
    private String username;
    private String name;
    private String mail;
    private String password;
    private String address;
    private String pin;
    @Override
     public Customer build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Customer(
          id,
          username,
          name,
          mail,
          password,
          address,
          pin);
    }
    
    @Override
     public BuildStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep mail(String mail) {
        this.mail = mail;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        this.password = password;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep pin(String pin) {
        this.pin = pin;
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
    private CopyOfBuilder(String id, String username, String name, String mail, String password, String address, String pin) {
      super.id(id);
      super.username(username)
        .name(name)
        .mail(mail)
        .password(password)
        .address(address)
        .pin(pin);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder mail(String mail) {
      return (CopyOfBuilder) super.mail(mail);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder pin(String pin) {
      return (CopyOfBuilder) super.pin(pin);
    }
  }
  
}
