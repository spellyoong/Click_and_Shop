package com.example.clickandshop;

public class Cart extends Products{
    int prodQuantity;

//    public Cart(int productPhoto, String productName, double productPrice, double soldQty, String productDesc, double productRate, int prodQuantity) {
//        this.products = products;
//        this.prodQuantity = prodQuantity;
//    }

    public Cart(int productPhoto, String productName, double productPrice, double soldQty, String productDesc, double productRate, int prodQuantity) {
        super(productPhoto, productName, productPrice, soldQty, productDesc, productRate);
        this.prodQuantity = prodQuantity;
    }

    public Cart(){
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

//    public int addProdQuantity() {
//        return prodQuantity++;
//    }
}
