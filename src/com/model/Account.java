package com.model;

public class Account {
    private Staff userAccount;
    private static Account instance = null;

    public void setUserAccount(int staffId, String name, int age, String email, String address, String username, String password, int previlege) {
        this.userAccount = new Staff(staffId, name, age, email, address, username, password, previlege);
        instance = this;
    }
    
    // Getter - Setter
    
    public void setUserAccount(Staff staff){
        this.userAccount = staff;
    }
    
    public void freeUserAccount(){
        this.userAccount = null;
    }

    public int getPrevilege() {
        return userAccount.getPrevilege();
    }

    public Staff getUserAccount() {
        return userAccount;
    }

    public Account() {
        this.userAccount = new Staff();
    }
    
    
    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }
    
}
