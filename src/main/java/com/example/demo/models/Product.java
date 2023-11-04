package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // đahs dấu Product.java là 1 entity class, product.java sẽ đại diện cho bảng trong csdl, ct sẽ tự độngt ạo bảng trong csld dựa trrn product , mỗi trươnng trong lớp là 1 cootj trong bảng

public class Product {
    @Id // đánh dâu id là trường khóa chính trong bảng
    @GeneratedValue(strategy = GenerationType.AUTO) // đánh dấu id được tạo tự động
    private Long id;
    private String productName;
    private int year;
    private Double price;
    private String url;
    public Product(){};

    public Product( String productName, int year, Double price, String url) {

        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
