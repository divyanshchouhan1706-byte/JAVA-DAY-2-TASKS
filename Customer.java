public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;

    // Parameterized Constructor
    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Customer ID : " + customerId +
               "\nName        : " + name +
               "\nEmail       : " + email +
               "\nPhone       : " + phone;
    }
}
