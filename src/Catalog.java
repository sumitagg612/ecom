package ecom;

class Catalog {
    private static ecom.Product[] list_of_products = new ecom.Product[1000];
    private static ecom.Seller[] list_of_corresponding_sellers = new ecom.Seller[1000];
    private static int counter = 0;

    static ecom.Product searchById(String productId, String sellerId) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (list_of_products[i].getProductId().equals(productId) && list_of_corresponding_sellers[i].getUserId().equals(sellerId)) {
                    return list_of_products[i];
                }
            }
        }
        return null;
    }

    static void searchByName(String productName) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (list_of_products[i].getProductName().contains(productName)) {

                    System.out.println("\n\n\n");
                    System.out.println(list_of_products[i].getDetails());

                    System.out.println(list_of_corresponding_sellers[i].getDetails());

                    System.out.println("\n\n\n");
                }
            }
        } else {
            System.out.println("No Such Item Exist");
        }
    }

    static void addProductToList(ecom.Product product, ecom.Seller seller) {
        list_of_products[counter] = product;
        list_of_corresponding_sellers[counter++] = seller;
    }

}

