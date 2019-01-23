package ecom;

import java.util.Scanner;

class Buyer extends ecom.User implements ecom.Options {

    private String name;
    private String contact;
    private String myAddress;
    private int no_of_ratings;
    private double rating;


    Buyer(String userId, String password, String name, String contact, String myAddress) {
        super(userId, password);
        this.name = name;
        this.contact = contact;
        this.myAddress = myAddress;
        this.no_of_ratings = 1;
        this.rating = 5.0;
    }

    String getDetails() {
        return ("{ Name :" + this.name + " Contact : " + this.contact +
                " Rating : " + this.rating + " Address : " + this.myAddress + " }\n");
    }

    private void changeInfo(String password, String name, String contact, String myAddress) {
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.myAddress = myAddress;

    }

    void setRating(String rating) {
        this.rating = (((this.rating) * (this.no_of_ratings) + Double.parseDouble(rating)) / ++this.no_of_ratings);
    }

    private void search(String productName) {

        ecom.Catalog.searchByName(productName);
    }


    private boolean buy(String productId, String sellerId) {

        ecom.Product product = ecom.Catalog.searchById(productId, sellerId);
        if (product != null) {
            if (product.getStock() > 0) {
                product.setStock(Integer.toString(product.getStock() - 1));
                ecom.Order.newOrder(product, ((ecom.Seller) ecom.Authentication.getUser(sellerId)), this);
                return true;
            }
        }
        return false;
    }

    private String getProductDetails() {
        return ("Sample text");

    }

    private String getSellerDetails(String sellerId) {
        ecom.Seller seller = (ecom.Seller) ecom.Authentication.getUser(sellerId);
        if (seller != null) {
            return seller.getDetails();
        }
        return "No such buyer exist";
    }

    private void rateProduct(String productId, String rating) {
        ecom.Product product = ecom.Order.productInOrder(productId, this.getUserId());
        if (product != null) {
            product.setRating(rating);
        } else {
            System.out.println("You had already rate the product or you had not buy this item yet");
        }
    }

    private void rateSeller(String sellerId, String rating) {
        ecom.Seller seller = ecom.Order.sellerInOrder(sellerId, this.getUserId());
        if (seller != null) {
            seller.setRating(rating);
        } else {
            System.out.println("You had already rate the Seller or you had not buy from this Seller yet");
        }
    }

    @Override
    public int showOptions() {
        System.out.println("Enter 0 for Logout \n" +
                "1 for Search Product \n " +
                "2 for Buy Product \n " +
                "3 for Get Product Details \n " +
                "4 for Get Seller Details \n " +
                "5 for Rate Product \n " +
                "6 for Rate Seller \n " +
                "7 for Change Info \n " +
                "8 for Delete Account ");

        return 8;
    }

    @Override
    public boolean processOptions(String string) {
        Scanner in = new Scanner(System.in);

        switch (string) {
            case "1": {

                System.out.println("Enter Product Name");
                String productName = in.nextLine();

                search(productName);
                break;
            }

            case "2": {
                System.out.println("Enter Product ID");
                String productId = in.nextLine();
                System.out.println("Enter Seller ID");
                String sellerId = in.nextLine();
                if (buy(productId, sellerId)) {
                    System.out.println("You bought the product");

                } else {
                    System.out.println("Unsuccessful Transaction");
                }

                break;
            }

            case "3": {
                System.out.println(getProductDetails());
                break;
            }

            case "4": {
                System.out.println("Enter Seller ID");
                System.out.println(getSellerDetails(in.nextLine()));
                break;
            }

            case "5": {
                System.out.println("Enter Product Id");
                String productId = in.nextLine();
                System.out.println("Enter Your Rating [Max{5.0)]");
                rateProduct(productId, in.nextLine());
                break;
            }

            case "6": {
                System.out.println("Enter Seller Id");
                String sellerId = in.nextLine();
                System.out.println("Enter Your Rating [Max{5.0)]");
                rateSeller(sellerId, in.nextLine());
                break;
            }

            case "7": {
                System.out.println("Enter What to change \n" +
                        "1 for Password\n" +
                        "2 for Name\n" +
                        "3 for Contact\n" +
                        "4 for Address\n");
                switch (in.nextLine()) {
                    case "1": {
                        System.out.println("Enter New Password \n");
                        changeInfo(in.nextLine(), this.name, this.contact, this.myAddress);
                        break;
                    }

                    case "2": {
                        System.out.println("Enter New Name\n");
                        changeInfo(this.password, in.nextLine(), this.contact, this.myAddress);
                        break;
                    }

                    case "3": {
                        System.out.println("Enter New Contact \n");
                        changeInfo(this.password, this.name, in.nextLine(), this.myAddress);
                        break;

                    }

                    case "4": {
                        System.out.println("Enter New Password \n");
                        changeInfo(this.password, this.name, this.contact, in.nextLine());
                        break;

                    }
                    default:
                        break;
                }

            }

            case "8": {
                if (deleteAccount()) {
                    System.out.println("Account Deleted Successfully");
                    return true;
                } else {
                    System.out.println("No Such User exist");
                }
                break;
            }

            default:
                break;


        }
        return false;

    }

}