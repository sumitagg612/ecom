package ecom;

interface Options {
    int showOptions();

    boolean processOptions(String string);
}

class User implements Options {

    String password;
    private String userId;


    User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    void changeInfo(String password) {
        this.password = password;
    }

    String getUserId() {
        return (this.userId);
    }

    String getPassword() {
        return (this.password);
    }

    boolean deleteAccount() {
        return ecom.Authentication.deleteUser(this);
    }

    public int showOptions() {
        return 0;
    }

    public boolean processOptions(String string) {
        return true;
    }

}