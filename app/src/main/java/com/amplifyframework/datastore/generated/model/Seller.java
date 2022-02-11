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

/** This is an auto generated class representing the Seller type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Sellers", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Seller implements Model {
  public static final QueryField ID = field("Seller", "id");
  public static final QueryField USERNAME = field("Seller", "username");
  public static final QueryField NAME = field("Seller", "name");
  public static final QueryField MAIL = field("Seller", "mail");
  public static final QueryField PASSWORD = field("Seller", "password");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String mail;
  private final @ModelField(targetType="String") String password;
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Seller(String id, String username, String name, String mail, String password) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.mail = mail;
    this.password = password;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Seller seller = (Seller) obj;
      return ObjectsCompat.equals(getId(), seller.getId()) &&
              ObjectsCompat.equals(getUsername(), seller.getUsername()) &&
              ObjectsCompat.equals(getName(), seller.getName()) &&
              ObjectsCompat.equals(getMail(), seller.getMail()) &&
              ObjectsCompat.equals(getPassword(), seller.getPassword()) &&
              ObjectsCompat.equals(getCreatedAt(), seller.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), seller.getUpdatedAt());
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
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Seller {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("mail=" + String.valueOf(getMail()) + ", ")
      .append("password=" + String.valueOf(getPassword()) + ", ")
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
  public static Seller justId(String id) {
    return new Seller(
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
      name,
      mail,
      password);
  }
  public interface UsernameStep {
    BuildStep username(String username);
  }
  

  public interface BuildStep {
    Seller build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep mail(String mail);
    BuildStep password(String password);
  }
  

  public static class Builder implements UsernameStep, BuildStep {
    private String id;
    private String username;
    private String name;
    private String mail;
    private String password;
    @Override
     public Seller build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Seller(
          id,
          username,
          name,
          mail,
          password);
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
    private CopyOfBuilder(String id, String username, String name, String mail, String password) {
      super.id(id);
      super.username(username)
        .name(name)
        .mail(mail)
        .password(password);
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
  }
  
}
