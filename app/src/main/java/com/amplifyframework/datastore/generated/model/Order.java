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

/** This is an auto generated class representing the Order type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Orders", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Order implements Model {
  public static final QueryField ID = field("Order", "id");
  public static final QueryField PRODUCTID = field("Order", "productid");
  public static final QueryField SELLERID = field("Order", "sellerid");
  public static final QueryField CUSTOMERID = field("Order", "customerid");
  public static final QueryField PRODUCTSTATUS = field("Order", "productstatus");
  public static final QueryField SHIPPINGADDRESS = field("Order", "shippingaddress");
  public static final QueryField ZIPCODE = field("Order", "zipcode");
  public static final QueryField ORDERAMMOUNT = field("Order", "orderammount");
  public static final QueryField ORDERNAME = field("Order", "ordername");
  public static final QueryField ORDERMOBILENUMBER = field("Order", "ordermobilenumber");
  public static final QueryField ORDERDATE = field("Order", "orderdate");
  public static final QueryField ORDERQUANTITY = field("Order", "orderquantity");
  public static final QueryField PRODUCTNAME = field("Order", "productname");
  public static final QueryField DELIVERYDATE = field("Order", "deliverydate");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID") String productid;
  private final @ModelField(targetType="ID") String sellerid;
  private final @ModelField(targetType="ID") String customerid;
  private final @ModelField(targetType="String") String productstatus;
  private final @ModelField(targetType="String") String shippingaddress;
  private final @ModelField(targetType="String") String zipcode;
  private final @ModelField(targetType="Float") Double orderammount;
  private final @ModelField(targetType="String") String ordername;
  private final @ModelField(targetType="String") String ordermobilenumber;
  private final @ModelField(targetType="String") String orderdate;
  private final @ModelField(targetType="Float") Double orderquantity;
  private final @ModelField(targetType="String") String productname;
  private final @ModelField(targetType="String") String deliverydate;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getProductid() {
      return productid;
  }
  
  public String getSellerid() {
      return sellerid;
  }
  
  public String getCustomerid() {
      return customerid;
  }
  
  public String getProductstatus() {
      return productstatus;
  }
  
  public String getShippingaddress() {
      return shippingaddress;
  }
  
  public String getZipcode() {
      return zipcode;
  }
  
  public Double getOrderammount() {
      return orderammount;
  }
  
  public String getOrdername() {
      return ordername;
  }
  
  public String getOrdermobilenumber() {
      return ordermobilenumber;
  }
  
  public String getOrderdate() {
      return orderdate;
  }
  
  public Double getOrderquantity() {
      return orderquantity;
  }
  
  public String getProductname() {
      return productname;
  }
  
  public String getDeliverydate() {
      return deliverydate;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Order(String id, String productid, String sellerid, String customerid, String productstatus, String shippingaddress, String zipcode, Double orderammount, String ordername, String ordermobilenumber, String orderdate, Double orderquantity, String productname, String deliverydate) {
    this.id = id;
    this.productid = productid;
    this.sellerid = sellerid;
    this.customerid = customerid;
    this.productstatus = productstatus;
    this.shippingaddress = shippingaddress;
    this.zipcode = zipcode;
    this.orderammount = orderammount;
    this.ordername = ordername;
    this.ordermobilenumber = ordermobilenumber;
    this.orderdate = orderdate;
    this.orderquantity = orderquantity;
    this.productname = productname;
    this.deliverydate = deliverydate;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Order order = (Order) obj;
      return ObjectsCompat.equals(getId(), order.getId()) &&
              ObjectsCompat.equals(getProductid(), order.getProductid()) &&
              ObjectsCompat.equals(getSellerid(), order.getSellerid()) &&
              ObjectsCompat.equals(getCustomerid(), order.getCustomerid()) &&
              ObjectsCompat.equals(getProductstatus(), order.getProductstatus()) &&
              ObjectsCompat.equals(getShippingaddress(), order.getShippingaddress()) &&
              ObjectsCompat.equals(getZipcode(), order.getZipcode()) &&
              ObjectsCompat.equals(getOrderammount(), order.getOrderammount()) &&
              ObjectsCompat.equals(getOrdername(), order.getOrdername()) &&
              ObjectsCompat.equals(getOrdermobilenumber(), order.getOrdermobilenumber()) &&
              ObjectsCompat.equals(getOrderdate(), order.getOrderdate()) &&
              ObjectsCompat.equals(getOrderquantity(), order.getOrderquantity()) &&
              ObjectsCompat.equals(getProductname(), order.getProductname()) &&
              ObjectsCompat.equals(getDeliverydate(), order.getDeliverydate()) &&
              ObjectsCompat.equals(getCreatedAt(), order.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), order.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getProductid())
      .append(getSellerid())
      .append(getCustomerid())
      .append(getProductstatus())
      .append(getShippingaddress())
      .append(getZipcode())
      .append(getOrderammount())
      .append(getOrdername())
      .append(getOrdermobilenumber())
      .append(getOrderdate())
      .append(getOrderquantity())
      .append(getProductname())
      .append(getDeliverydate())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Order {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("productid=" + String.valueOf(getProductid()) + ", ")
      .append("sellerid=" + String.valueOf(getSellerid()) + ", ")
      .append("customerid=" + String.valueOf(getCustomerid()) + ", ")
      .append("productstatus=" + String.valueOf(getProductstatus()) + ", ")
      .append("shippingaddress=" + String.valueOf(getShippingaddress()) + ", ")
      .append("zipcode=" + String.valueOf(getZipcode()) + ", ")
      .append("orderammount=" + String.valueOf(getOrderammount()) + ", ")
      .append("ordername=" + String.valueOf(getOrdername()) + ", ")
      .append("ordermobilenumber=" + String.valueOf(getOrdermobilenumber()) + ", ")
      .append("orderdate=" + String.valueOf(getOrderdate()) + ", ")
      .append("orderquantity=" + String.valueOf(getOrderquantity()) + ", ")
      .append("productname=" + String.valueOf(getProductname()) + ", ")
      .append("deliverydate=" + String.valueOf(getDeliverydate()) + ", ")
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
  public static Order justId(String id) {
    return new Order(
      id,
      null,
      null,
      null,
      null,
      null,
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
      productid,
      sellerid,
      customerid,
      productstatus,
      shippingaddress,
      zipcode,
      orderammount,
      ordername,
      ordermobilenumber,
      orderdate,
      orderquantity,
      productname,
      deliverydate);
  }
  public interface BuildStep {
    Order build();
    BuildStep id(String id);
    BuildStep productid(String productid);
    BuildStep sellerid(String sellerid);
    BuildStep customerid(String customerid);
    BuildStep productstatus(String productstatus);
    BuildStep shippingaddress(String shippingaddress);
    BuildStep zipcode(String zipcode);
    BuildStep orderammount(Double orderammount);
    BuildStep ordername(String ordername);
    BuildStep ordermobilenumber(String ordermobilenumber);
    BuildStep orderdate(String orderdate);
    BuildStep orderquantity(Double orderquantity);
    BuildStep productname(String productname);
    BuildStep deliverydate(String deliverydate);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String productid;
    private String sellerid;
    private String customerid;
    private String productstatus;
    private String shippingaddress;
    private String zipcode;
    private Double orderammount;
    private String ordername;
    private String ordermobilenumber;
    private String orderdate;
    private Double orderquantity;
    private String productname;
    private String deliverydate;
    @Override
     public Order build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Order(
          id,
          productid,
          sellerid,
          customerid,
          productstatus,
          shippingaddress,
          zipcode,
          orderammount,
          ordername,
          ordermobilenumber,
          orderdate,
          orderquantity,
          productname,
          deliverydate);
    }
    
    @Override
     public BuildStep productid(String productid) {
        this.productid = productid;
        return this;
    }
    
    @Override
     public BuildStep sellerid(String sellerid) {
        this.sellerid = sellerid;
        return this;
    }
    
    @Override
     public BuildStep customerid(String customerid) {
        this.customerid = customerid;
        return this;
    }
    
    @Override
     public BuildStep productstatus(String productstatus) {
        this.productstatus = productstatus;
        return this;
    }
    
    @Override
     public BuildStep shippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
        return this;
    }
    
    @Override
     public BuildStep zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }
    
    @Override
     public BuildStep orderammount(Double orderammount) {
        this.orderammount = orderammount;
        return this;
    }
    
    @Override
     public BuildStep ordername(String ordername) {
        this.ordername = ordername;
        return this;
    }
    
    @Override
     public BuildStep ordermobilenumber(String ordermobilenumber) {
        this.ordermobilenumber = ordermobilenumber;
        return this;
    }
    
    @Override
     public BuildStep orderdate(String orderdate) {
        this.orderdate = orderdate;
        return this;
    }
    
    @Override
     public BuildStep orderquantity(Double orderquantity) {
        this.orderquantity = orderquantity;
        return this;
    }
    
    @Override
     public BuildStep productname(String productname) {
        this.productname = productname;
        return this;
    }
    
    @Override
     public BuildStep deliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
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
    private CopyOfBuilder(String id, String productid, String sellerid, String customerid, String productstatus, String shippingaddress, String zipcode, Double orderammount, String ordername, String ordermobilenumber, String orderdate, Double orderquantity, String productname, String deliverydate) {
      super.id(id);
      super.productid(productid)
        .sellerid(sellerid)
        .customerid(customerid)
        .productstatus(productstatus)
        .shippingaddress(shippingaddress)
        .zipcode(zipcode)
        .orderammount(orderammount)
        .ordername(ordername)
        .ordermobilenumber(ordermobilenumber)
        .orderdate(orderdate)
        .orderquantity(orderquantity)
        .productname(productname)
        .deliverydate(deliverydate);
    }
    
    @Override
     public CopyOfBuilder productid(String productid) {
      return (CopyOfBuilder) super.productid(productid);
    }
    
    @Override
     public CopyOfBuilder sellerid(String sellerid) {
      return (CopyOfBuilder) super.sellerid(sellerid);
    }
    
    @Override
     public CopyOfBuilder customerid(String customerid) {
      return (CopyOfBuilder) super.customerid(customerid);
    }
    
    @Override
     public CopyOfBuilder productstatus(String productstatus) {
      return (CopyOfBuilder) super.productstatus(productstatus);
    }
    
    @Override
     public CopyOfBuilder shippingaddress(String shippingaddress) {
      return (CopyOfBuilder) super.shippingaddress(shippingaddress);
    }
    
    @Override
     public CopyOfBuilder zipcode(String zipcode) {
      return (CopyOfBuilder) super.zipcode(zipcode);
    }
    
    @Override
     public CopyOfBuilder orderammount(Double orderammount) {
      return (CopyOfBuilder) super.orderammount(orderammount);
    }
    
    @Override
     public CopyOfBuilder ordername(String ordername) {
      return (CopyOfBuilder) super.ordername(ordername);
    }
    
    @Override
     public CopyOfBuilder ordermobilenumber(String ordermobilenumber) {
      return (CopyOfBuilder) super.ordermobilenumber(ordermobilenumber);
    }
    
    @Override
     public CopyOfBuilder orderdate(String orderdate) {
      return (CopyOfBuilder) super.orderdate(orderdate);
    }
    
    @Override
     public CopyOfBuilder orderquantity(Double orderquantity) {
      return (CopyOfBuilder) super.orderquantity(orderquantity);
    }
    
    @Override
     public CopyOfBuilder productname(String productname) {
      return (CopyOfBuilder) super.productname(productname);
    }
    
    @Override
     public CopyOfBuilder deliverydate(String deliverydate) {
      return (CopyOfBuilder) super.deliverydate(deliverydate);
    }
  }
  
}
