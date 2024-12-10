package org.example;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String deliveryAddress;

    /**
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param deliveryAddress
     */
    public Customer(String firstName, String lastName, String email, String phoneNumber, String deliveryAddress) throws InvalidArgumentException {
        this.id = CustomerDAO.LoadLastId(); // Auto-increment customer ID
        validateAndSetName(firstName, 'R');
        validateAndSetName(lastName, 'L');
        validateAndSetEmail(email);
        validateSetAndFormatPhone(phoneNumber);
        validateAndSetAddress(deliveryAddress);
    }

    /**
     * This method sets the person's phone number. It accepts 3 formats to be
     * user-friendly, but formats it
     *
     * @param phone is the provided phone number to be checked, formatted and
     *              set.
     * @throws InvalidArgumentException if the phone format is invalid.
     */
    public void setPhone(String phone) throws InvalidArgumentException {
        validateSetAndFormatPhone(phone);
    }

    private void validateSetAndFormatPhone(String phone) throws InvalidArgumentException {
        if (!phoneChecker(phone)) {
            throw new InvalidArgumentException("Invalid phone number format, please enter it as XXX-XXX-XXXX, XXX XXX XXXX, XXXXXXXXXX.");
        }
        this.phone = formatPhone(phone);
    }

    /**
     * This method sets the Person's first name.
     *
     * @param firstName is the provided name to be checked and set.
     * @throws InvalidArgumentException if the name is invalid.
     */
    public void setFirstName(String firstName) throws InvalidArgumentException {
        validateAndSetName(firstName, 'R');
    }

    /**
     * This method sets the Person's last name.
     *
     * @param lastName is the provided name to be checked and set.
     * @throws InvalidArgumentException if the name is invalid.
     */
    public void setLastName(String lastName) throws InvalidArgumentException {
        validateAndSetName(lastName, 'L');
    }

    private void validateAndSetName(String name, char type) throws InvalidArgumentException {
        if (type == 'L') {
            if (!nameChecker(name)) {
                throw new InvalidArgumentException("Invalid last name format. Only letters allowed.");
            }
            this.lastName = name;
        } else if (type == 'R') {
            if (!nameChecker(name)) {
                throw new InvalidArgumentException("Invalid first name format. Only letters allowed.");
            }
            this.firstName = name;
        }
    }

    private boolean validateAddress(String address) {
        if (address == null) {
            return false; //if null false
        }
        String[] elements = address.split(" ");
        if (elements.length < 2) {
            return false; // if not at least 2 par, then return false.
        }
        //first part must be number
        char[] houseNb = elements[0].toCharArray();
        for (char each : houseNb) {
            if (!Character.isDigit(each)) {
                return false;
            }
        }
        //second part (street name)
        char[] street = elements[1].toCharArray();
        for (char each : street) {
            if (!Character.isAlphabetic(each)) {
                return false;
            }
        }
        return true;
    }

    private void validateAndSetAddress(String address) throws InvalidArgumentException {
        if (!validateAddress(address)) {
            throw new InvalidArgumentException("Invalid adress format.Valid ex: 123 Elm street");
        }
        this.deliveryAddress = address;
    }

    /**
     * This method sets the Person's email.
     *
     * @param email is the provided email to be checked and set.
     * @throws InvalidArgumentException if the email is invalid.
     */
    public void setEmail(String email) throws InvalidArgumentException {
        validateAndSetEmail(email);
    }

    private void validateAndSetEmail(String email) throws InvalidArgumentException {
        if (!emailChecker(email)) {
            throw new InvalidArgumentException("Invalid email address format.Valid ex: name@mail.com");
        }
        this.email = email;

    }

    /**
     * This method will validate the provided email.
     *
     * @param email is the provided email to be checked.
     * @return boolean response of whether the email is valid or not.
     */
    private boolean emailChecker(String email) {
        if (email == null) {
            return false;
        }

        char[] arr = email.toCharArray();
        if (arr[0] == '@' || arr[0] == '.' || !Character.isAlphabetic(arr[arr.length - 1]) || !Character.isAlphabetic(arr[0])) {
            return false;
        }

        int symbolFound = 0;
        int dotFound = 0;

        for (char c : arr) {

            if (c == '.') {
                dotFound++;
            }
            if (c == '@') {
                symbolFound++;
            }
            if (c == ' ') {
                return false;
            }
        }
        if ((symbolFound == 0) && dotFound >= 0) {
            return false;
        }
        return symbolFound == 1 && dotFound >= 1;
    }

    /**
     * This method will validate the provided name.
     *
     * @param name is the provided name to be checked.
     * @return boolean response of whether the name is valid or not.
     */
    private boolean nameChecker(String name) {
        if (name == null) {
            return false;
        }
        return name.chars().allMatch(Character::isAlphabetic);
    }

    /**
     * This method verifies if phone input is of appropriate format.
     *
     * @param phone provided phone to be checked.
     * @return boolean response of whether the phone is valid or not.
     */
    private boolean phoneChecker(String phone) {
        if (phone == null) {
            return false;
        }
        //format like XXX-XXX-XXXX or XXX XXX XXXX
        if (phone.length() == 12) {
            for (int i = 0; i < 12; i++) {
                if (i == 3 || i == 7) {
                    if (!(phone.charAt(i) == '-' || phone.charAt(i) == ' ')) {
                        return false;
                    }
                } else {
                    if (!Character.isDigit(phone.charAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        //format like XXXXXXXXXX
        if (phone.length() == 10) {
            return phone.chars().allMatch(Character::isDigit);
        }
        return false;
    }

    /**
     * Formats the phone number as XXX-XXX-XXXX for the stored property.
     *
     * @param phone provided phone to be formatted.
     * @return formatted phone number.
     */
    private String formatPhone(String phone) {
        if (phone == null) {
            return null;
        }

        //remove spaces
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("-", "");
        if (phoneChecker(phone)) {
            return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
        }
        return null;
    }

    public void setCustomerId(int id) {
        this.id = id;
    }



}
