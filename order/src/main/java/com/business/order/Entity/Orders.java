package com.business.order.Entity;

import java.util.List;

import com.paypal.api.payments.Patch;

import com.paypal.http.HttpRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders extends HttpRequest {

    private static final String path = null;
    private static final String verb = null;
    private static final Class responseClass = null;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "flavour")
    private String variation;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int productPrice;

    @Column(name = "totalPrice")
    private double totalPrice;


public Orders() {
    super(path, verb, responseClass);
}


    public Orders(int orderId, String variation, int quantity, int productPrice, double totalPrice) {
        this();
        this.orderId = orderId;
        this.variation = variation;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

	public void requestBody(List<Patch> patches) {
		// TODO Auto-generated method stub
    }
}
