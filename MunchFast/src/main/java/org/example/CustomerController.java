package org.example;

import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO;

    public CustomerController() {
        customerDAO = new CustomerDAO();
    }

    /**
     * add Customer
     * @param customer
     */
    public void addCustomer(Customer customer){
        customerDAO.addCustomer(customer);
    }

    /**
     * Delete Customer by their ID
     *
     * @return
     */
    public boolean deleteCustomer(int clientId) {
        return customerDAO.deleteCustomer(clientId);
    }

    /**
     * Get Customer by their Email
     *
     * @param email
     * @return
     */
    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

    public Customer getCustomerById(int id){
        return customerDAO.getCustomerById(id);
    }

    /**
     * Get all customers
     *
     * @return
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public String getAllCustomersAsString(){
        return customerDAO.getAllCustomersAsString();
    }
}
