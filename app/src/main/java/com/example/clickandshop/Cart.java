package com.example.clickandshop;

public class Cart extends Products{
    int prodQuantity;
    boolean prodCheck;

    public Cart(int productID, int productPhoto, String productName, double productPrice, double soldQty, String productDesc, double productRate, int prodQuantity, boolean prodCheck) {
        super(productID, productPhoto, productName, productPrice, soldQty, productDesc, productRate);
        this.prodQuantity = prodQuantity;
        this.prodCheck = prodCheck;
    }

    public Cart(){
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public boolean isProdCheck() {
        return prodCheck;
    }

    public void setProdCheck(boolean prodCheck) {
        this.prodCheck = prodCheck;
    }

}
