package com.office_dress_shop.shopbackend.pojo;

import jakarta.persistence.*;

    @Entity
    @Table(name = "CartItems")
    public class CartItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "CartId")
        private Cart cart;

        @ManyToOne
        @JoinColumn(name = "ProductId")
        private OfficeDress product;

        private int quantity;

        public CartItem() {
        }

        public CartItem(Cart cart, OfficeDress product, int quantity) {
            this.cart = cart;
            this.product = product;
            this.quantity = quantity;
        }

        public CartItem(int id, Cart cart, OfficeDress product, int quantity) {
            this.id = id;
            this.cart = cart;
            this.product = product;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Cart getCart() {
            return cart;
        }

        public void setCart(Cart cart) {
            this.cart = cart;
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
    }

