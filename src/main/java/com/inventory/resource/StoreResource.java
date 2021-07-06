package com.inventory.resource;


public class StoreResource {
    private Long id;
    private String address;
    private int stock;

    public Long getId() { return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
