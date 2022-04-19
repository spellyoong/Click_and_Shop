package com.example.clickandshop;

import java.util.ArrayList;
import java.util.List;

public class Products {
    int productID;
    int productPhoto;
    String productName;
    double productPrice;
    double soldQty;
    String productDesc;
    double productRate;

    // Initiate products variables
    Products prod1;
    Products prod2;
    Products prod3;
    Products prod4;
    Products prod5;
    Products prod6;
    Products prod7;
    Products prod8;
    Products prod9;
    Products prod10;

    public Products(int productID, int productPhoto, String productName, double productPrice, double soldQty, String productDesc, double productRate) {
        this.productID = productID;
        this.productPhoto = productPhoto;
        this.productName = productName;
        this.productPrice = productPrice;
        this.soldQty = soldQty;
        this.productDesc = productDesc;
        this.productRate = productRate;
    }

    public Products(){
    }

    public int getProductID() {
        return productID;
    }

    public int getProductPhoto() {
        return productPhoto;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getSoldQty() {
        return soldQty;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductPhoto(int productPhoto) {
        this.productPhoto = productPhoto;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setSoldQty(double soldQty) {
        this.soldQty = soldQty;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }

    // Replicate database
    public void initiateProducts(){
        prod1 = new Products(100000001, R.drawable.product_mask, "Cotton Washable Plain Assorted Three Layers Face Mask / Topeng Muka", 5.5, 183, "Our face mask is made from cotton material. 3D eagle Mouth Shape Design, make your nose feel comfortable and fits your face well and allow some space for proper breathing. Highly elastic and rubbery earloop. Reusable, fashionable and can be worn in adifferent kinds of weather. Suitable for Bicycle Motercycle Riding, Outdoor Sports, Snowboard, Cycling, Running, Hiking, Camping, Skiing, Climbing, Fishing, Hungting, Jogging, Motorcycling and so on.", 4.5);
        prod2 = new Products(100000002, R.drawable.product_chair, "Ergonomics Backrest Chair Comfortable Office Chair", 239, 3.4, "Our products are all in stock. You can place an order right away!\nFacing fabric: plastic\nChair foot material: stainless steel, nylon\nCushion padding: latex mattress\nAdditional features: rotatable", 4.8);
        prod3 = new Products(100000003, R.drawable.product_coffee, "Ipoh 2 in 1 Chang Jiang White Coffee (10 Sachets x 30g)", 15.2, 152, "hang Jiang 2 in 1 White Coffee 10 sachets x 30g\nChang Jiang 2 in1 White Coffee is suitable for loving healthy coffee customers. Traditional white coffee and creamy milk taste, no sugar packaging, with additional sugar packet, can allocate own sweetness.", 4.2);
        prod4 = new Products(100000004, R.drawable.product_maggie, "MAGGI 2-Min Curry 5 Packs, 79g Each", 10.1, 221, "• 2-minute instant noodles • Contains 5 individual packets of 79g • Real curry taste • Source of wheat protein • Made with real chillies and 12 secret spices • Slow-cooked to perfection for great curry taste • With our springy noodle • Each serving (79g) contains 351kcal • HALAL certified Don\'t know what to eat? Cook yourself a delicious bowl of MAGGI® 2-Minute Noodles Curry. Made with real chillies and a secret blend of 12 spices, it is coupled together with our springy noodles made from Australian wheat grains that promises satisfaction with every slurp. Throw in your favourite fresh ingredients for a richer taste and a balanced meal. It’s no wonder that it is still a Malaysian favourite till today!", 3);
        prod5 = new Products(100000005, R.drawable.product_durian, "Frozen Durian Musang King 1kg", 55.9, 99, "Durian sejuk beku / Frozen durian :\nMusang king\n1kg = 16 - 30 ulas/ pieces\nlemak manis / creamy sweet\nisi lebih tebal dari gred A / flesh thicker than grade A", 4.4);
        prod6 = new Products(100000006, R.drawable.product_vacuum, "Handheld Vacuum MVC-SC861B", 129, 1.7, "Power: 600W\n2-in-1 design\nTransparent dust cup\nLight and user-friendly design\nQuick release cord hook", 3.5);
        prod7 = new Products(100000007, R.drawable.product_bottle, "500ml Smart LED Display Stainless Steel Water Thermal Bottle", 69.9, 51, "Specifications\nMaterial: 304 Stainless Steel\nSize: 22.5*6.5 cm\nCapacity: 500 ml", 4);
        prod8 = new Products(100000008, R.drawable.product_disinfectant, "Disinfectant Spray Morning Dew/Crisp Breeze 225ml", 15, 132, "- Kills 99.9% of germs, bacteria and viruses, including E. coli, salmonella and the flu virus\n- Deodorises by killing germs that cause odours\n- Controls mould and mildew on mattresses, pillows and shower curtains\n- Lasting fresh fragrance: Crisp Breeze\n- To disinfect: Spray over pre-cleaned surface for 2 to 3 seconds until covered with mist and allow to stand 10 minutes to air dry\n- To sanitise: Let stand for 30 seconds to control and prevent mould and mildew and their odours", 4.7);
        prod9 = new Products(100000009, R.drawable.product_sauce, "Handmade Aglio Olio Sauce Pasta Sauce (200g)", 23.4, 1.2, "Our Aglio Olio sauce is handmade - All natural. Fresh. Healthy. It is a secret blend of six herbs and spices. For taste, we added a sprinkle of Himalayan salt. And the tiniest bit of salted fish caught from our own oceans.\nWe have created the tastiest flavor. Worry not a moment about preservatives, artificial flavors, MSG or coloring. We put health first.", 4.1);
        prod10 = new Products(100000010, R.drawable.product_chips, "Mister Potato Crisps Honey Cheese", 4.2, 178, "Stack em\' up! Because one is never enough when it comes to our delectable crisps made from 100% imported potatoes of the highest grade.", 4.9);

    }

    // Retrieve products list
    public List getProdList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
        list.add(prod4);
        list.add(prod5);
        list.add(prod6);
        list.add(prod7);
        list.add(prod8);
        list.add(prod9);
        list.add(prod10);

        return list;
    }

    // Retrieve products list from Categories - Hot Deals
    public List getCategoryHotList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod1);
        list.add(prod3);
        list.add(prod4);
        list.add(prod8);
        list.add(prod10);

        return list;
    }

    // Retrieve products list from Categories - Essential
    public List getCategoryEssentialList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod3);
        list.add(prod4);
        list.add(prod5);
        list.add(prod7);
        list.add(prod9);
        list.add(prod10);

        return list;
    }

    // Retrieve products list from Categories - Sport
    public List getCategorySportList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        return list;
    }

    // Retrieve products list from Categories - Beauty
    public List getCategoryBeautyList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        return list;
    }

    // Retrieve products list from Categories - Gaming
    public List getCategoryGamingList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        return list;
    }

    // Retrieve products list from Categories - Electronics
    public List getCategoryElectronicsList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod6);

        return list;
    }

    // Retrieve products list from Categories - Fashion
    public List getCategoryFashionList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod1);

        return list;
    }

    // Retrieve products list from Categories - Toys, Kids & Baby
    public List getCategoryToyList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        return list;
    }

    // Retrieve products list from Categories - Home & Living
    public List getCategoryHomeList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod2);

        return list;
    }

    // Retrieve products list from Categories - Health
    public List getCategoryHealthList(){
        initiateProducts();

        // Initiate ArrayList and add products into the list
        List<Products> list = new ArrayList<Products>();

        list.add(prod1);
        list.add(prod8);

        return list;
    }

}