package ecom;

class Order {

    private static ecom.Product[] list_of_products = new ecom.Product[1000];
    private static ecom.Seller[] list_of_sellers = new ecom.Seller[1000];
    private static ecom.Buyer[] list_of_buyers = new ecom.Buyer[1000];
    private static int[] product = new int[100];
    private static int[] seller = new int[100];
    private static int[] buyer = new int[100];
    private static int counter = 0;

    static void newOrder(ecom.Product product, ecom.Seller seller, ecom.Buyer buyer) {
        list_of_products[counter] = product;
        list_of_sellers[counter] = seller;
        list_of_buyers[counter++] = buyer;
    }

    static ecom.Product productInOrder(String productId, String buyerId) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (list_of_products[i].getProductId().equals(productId) && list_of_buyers[i].getUserId().equals(buyerId) && product[i] == 0) {
                    product[i] = 1;
                    return list_of_products[i];
                }

            }
        }
        return null;
    }

    static ecom.Seller sellerInOrder(String sellerId, String buyerId) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (list_of_sellers[i].getUserId().equals(sellerId) && list_of_buyers[i].getUserId().equals(buyerId) && seller[i] == 0) {
                    seller[i] = 1;
                    return list_of_sellers[i];
                }

            }
        }
        return null;
    }

    static ecom.Buyer buyerInOrder(String buyerId, String sellerId) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (list_of_buyers[i].getUserId().equals(buyerId) && list_of_sellers[i].getUserId().equals(sellerId) && buyer[i] == 0) {
                    buyer[i] = 1;
                    return list_of_buyers[i];
                }

            }
        }
        return null;
    }

}