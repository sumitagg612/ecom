package ecom;

class Product {

    private String productId;
    private float marketPrice;
    private float discount;
    private float finalPrice;
    private int stock;
    private int no_of_ratings;
    private double rating;
    private String productName;
    private String[] productDetails;


    Product(String productId, String productName, String marketPrice, String discount, String stock, String[] productDetails) {
        this.productId = productId;
        this.productName = productName;
        this.marketPrice = Float.parseFloat(marketPrice);
        this.discount = Float.parseFloat(discount);
        this.finalPrice = ((1 - this.discount) * this.marketPrice);
        this.stock = Integer.parseInt(stock);
        this.productDetails = productDetails;
        this.no_of_ratings = 1;
        this.rating = 5.0;
    }

    String getProductId() {
        return (this.productId);

    }

    String getProductName() {
        return this.productName;
    }

    int getStock() {
        return this.stock;
    }

    void setStock(String stock) {
        this.stock = Integer.parseInt(stock);
    }

    void setMarketPrice(String marketPrice) {
        this.marketPrice = Float.parseFloat(marketPrice);
        this.finalPrice = (1 - this.discount) * (this.marketPrice);
    }

    void setDiscount(String discount) {
        this.discount = Float.parseFloat(discount);
        this.finalPrice = (1 - this.discount) * (this.marketPrice);
    }

    void setRating(String rating) {

        this.rating = (((this.rating) * (this.no_of_ratings) + Double.parseDouble(rating)) / ++this.no_of_ratings);
    }

    String getDetails() {
        return ("{ Product Name: " + this.productName +
                "Price:  " + this.finalPrice +
                "Rating : " + this.rating + "  }");
    }

    String[] getProductDetails() {
        return (this.productDetails);
    }


}
