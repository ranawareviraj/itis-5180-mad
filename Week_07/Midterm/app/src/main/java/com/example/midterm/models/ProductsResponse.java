package com.example.midterm.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductsResponse implements Serializable {
    public String status;
    public ArrayList<Product> products;

    public ProductsResponse(String status, ArrayList<Product> products) {
        this.status = status;
        this.products = products;
    }

    public ProductsResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}

//public class Product{
//    public String pid;
//    public String name;
//    public String img_url;
//    public String price;
//    public String description;
//    public String review_count;
//}