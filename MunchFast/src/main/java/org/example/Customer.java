package org.example;

public class Customer extends Person {

    /**
     * To initialize and set the Person's properties.
     *
     * @param firstname to initialize the first name value.
     * @param lastname  to initialize the last name value.
     * @param email     to initialize the email value.
     * @param phone     to initialize the phone value.
     * @throws InvalidArgumentException if any parameter is invalid.
     */
    public Customer(String firstname, String lastname, String email, String phone) throws InvalidArgumentException {
        super(firstname, lastname, email, phone);
    }
}
