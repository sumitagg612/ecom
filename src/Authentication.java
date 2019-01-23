package ecom;


class Authentication {

    private static ecom.User[] users = new ecom.User[1000];
    private static int counter = 0;


    static ecom.User login(String userId, String password) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (users[i] != null && users[i].getUserId().equals(userId) && users[i].getPassword().equals(password)) {
                    return users[i];
                }
            }
        }
        return null;
    }

    static ecom.User signUp(int role, String userId, String password, String name, String contact, String myAddress) {

        if (getUser(userId) == null) {
            if (role == 2) {
                users[counter++] = new ecom.Seller(userId, password, name, contact, myAddress);
                int index = counter - 1;
                return users[index];
            } else if (role == 1) {
                users[counter++] = new ecom.Buyer(userId, password, name, contact, myAddress);
                int index = counter - 1;
                return users[index];
            }
            return null;
        } else {
            return null;
        }

    }

    static ecom.User getUser(String userId) {
        System.out.println("No of users" + counter);
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (users[i] != null && users[i].getUserId().equals(userId)) {
                    return users[i];
                }
            }
        }
        return null;
    }

    static boolean deleteUser(ecom.User user) {
        if (counter > 0) {
            for (int i = 0; i < counter; i++) {
                if (users[i] != null && users[i].getUserId().equals(user.getUserId())) {
                    ecom.Authentication.users[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    static void addUser(ecom.User user) {
        users[counter++] = user;
    }


}