package com.model;
public class Staff {
    private int staffId;
    private String name;
    private int age;
    private String email;
    private String address;
    private String username;
    private String password;
    private int previlege;
    // 1: Staff - 2: Stock - 3: Admin
    
    public Staff(int staffId, String name,String email, String address,String position) {
        this.staffId = staffId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        setPosition(position);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrevilege() {
        return previlege;
    }

    public void setPrevilege(int previlege) {
        this.previlege = previlege;
    }

    public Staff(int staffId, String name, int age, String email, String address, String username, String password, int previlege) {
        this.staffId = staffId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.previlege = previlege;
    }

    public Staff() {
        this.staffId = 0;
        this.name = "";
        this.age = 0;
        this.email = "";
        this.address = "";
        this.username = "";
        this.password = "";
        this.previlege = 0;
    }
    
    

    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPosition(String position) {
        if (position == "Sale"){
            this.previlege = 1;
        }
        else if(position == "Stock Manager"){
            this.previlege = 2;
        }
        else if(position == "Admin"){
            this.previlege = 3;
        }
        else{
            this.previlege = 0;
        }
        
    }

    public String getPosition() {
        if (this.previlege == 1){
            return "Sale";
        }
        else if(this.previlege == 2){
            return "Stock Manager";
        }
        else if(this.previlege == 3){
            return "Admin";
        }
        else{
            return "Unknow";
        }
    }
}