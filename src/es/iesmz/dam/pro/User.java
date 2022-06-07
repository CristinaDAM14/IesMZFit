package es.iesmz.dam.pro;

public class User {
    private int id;
    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String paymentMethod;
    private int subscriptionID;


    public User(int id, String dni, String name, String lastName, String phone, String email, String paymentMethod, int subscriptionID) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.subscriptionID = subscriptionID;
    }

    public User(String dni, String name, String lastName, String phone, String email, String paymentMethod, int subscriptionID) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.subscriptionID = subscriptionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }
}
