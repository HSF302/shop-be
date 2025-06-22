package com.office_dress_shop.shopbackend.pojo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "OfficeDresses")
public class OfficeDress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Base_Price", nullable = false)
    private Double basePrice;

    @Column(name = "Status", nullable = false)
    private Boolean status;

    @Column(name = "Image_Url", nullable = false)
    private String imageUrl;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SizeId")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "ColorId")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "MaterialId")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "AddonId")
    private Addon addon;

    public OfficeDress() {
    }

    public OfficeDress(String description, Double basePrice, Boolean status, String imageUrl, int quantity, List<CartItem> cartItems, Category category, Size size, Color color, Material material, Addon addon) {
        this.description = description;
        this.basePrice = basePrice;
        this.status = status;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.cartItems = cartItems;
        this.category = category;
        this.size = size;
        this.color = color;
        this.material = material;
        this.addon = addon;
    }

    public OfficeDress(int id, String description, Double basePrice, Boolean status, String imageUrl, int quantity, List<CartItem> cartItems, Category category, Size size, Color color, Material material, Addon addon) {
        this.id = id;
        this.description = description;
        this.basePrice = basePrice;
        this.status = status;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.cartItems = cartItems;
        this.category = category;
        this.size = size;
        this.color = color;
        this.material = material;
        this.addon = addon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Addon getAddon() {
        return addon;
    }

    public void setAddon(Addon addon) {
        this.addon = addon;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
