package mmu.edu.my.signup;

public class User {
    public String name;
    public String location;
    public String title;
    public String description;
    public String date;
    public String price;
    public String number;

    public User(){}


    public User(String name, String number, String location, String title, String description, String date, String price){
        this.name = name;
        this.number = number;
        this.location = location;
        this.title = title;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public User(String name, String title, String description, String number) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
