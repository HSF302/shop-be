package com.office_dress_shop.shopbackend.pojo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private OfficeDress product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "Unit_Price", nullable = false)
    private Double unitPrice;

    @Column(name = "Total_Amount", nullable = false)
    private Double totalAmount;

    @Column(name = "Created_At", nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    public OrderDetail() {
    }

    public OrderDetail(Order order, OfficeDress product, int quantity, Double unitPrice, Double totalAmount, LocalDate createdAt) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public OrderDetail(int id, Order order, OfficeDress product, int quantity, Double unitPrice, Double totalAmount, LocalDate createdAt) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OfficeDress getProduct() {
        return product;
    }

    public void setProduct(OfficeDress product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
