package com.inventory.resource;

import javax.validation.constraints.NotNull;

public class StoreSaveResource {
    private String address;
    private int stock;

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
