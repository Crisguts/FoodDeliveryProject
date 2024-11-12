package org.example;

import lombok.Getter;

@Getter
abstract public class Person {
    private static int counter = 0;
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;

    /**
     * To initialize and set the Person's properties
     *
     * @param firstname to initialize the first name value
     * @param lastname  to initialize the last name value
     * @param email     to initialize the email value
     * @param phone     to initialize the phone value
     * @throws InvalidArgumentException if any parameter is invalid
     */
    public Person(String firstname, String lastname, String email, String phone) throws InvalidArgumentException {
        id = ++counter;
        setFirstName(firstname);
        setLastName(lastname);
        setEmail(email);
        this.phone = phone;
    }

    /**
     * This method sets the Person's first name
     *
     * @param firstName is the provided name to be checked and set
     * @throws InvalidArgumentException if the name is invalid
     */
    public void setFirstName(String firstName) throws InvalidArgumentException {
        if (!nameChecker(firstName)) {
            throw new InvalidArgumentException("Invalid first name format");
        }
        this.firstName = firstName;
    }

    /**
     * This method sets the Person's last name
     *
     * @param lastName is the provided name to be checked and set
     * @throws InvalidArgumentException if the name is invalid
     */
    public void setLastName(String lastName) throws InvalidArgumentException {
        if (!nameChecker(lastName)) {
            throw new InvalidArgumentException("Invalid last name format");
        }
        this.lastName = lastName;
    }

    /**
     * This method sets the Person's email
     *
     * @param email is the provided email to be checked and set
     * @throws InvalidArgumentException if the email is invalid
     */
    public void setEmail(String email) throws InvalidArgumentException {
        if (!emailChecker(email)) {
            throw new InvalidArgumentException("Invalid email address format.");
        }
        this.email = email;

    }

    /**
     * This method will validate the provided email.
     *
     * @param email is the provided email to be checked
     * @return boolean response of whether the email is valid or not
     */
    private boolean emailChecker(String email) {
        if (email == null) {
            return false;
        }

        char[] arr = email.toCharArray();
        if (arr[0] == '@' || arr[0] == '.' || !Character.isAlphabetic(arr[arr.length - 1])) {
            return false;
        }

        boolean symbolFound = false;
        boolean dotFound = false;

        for (char c : arr) {

            if (c == '.') {
                dotFound = true;
            }
            if (c == '@') {
                symbolFound = true;
            }
            if (!symbolFound && dotFound || c == ' ') {
                return false;
            }

        }
        return symbolFound && dotFound;
    }

    /**
     * @param name is the provided name to be checked
     * @return boolean response of whether the name is valid or not
     */
    private boolean nameChecker(String name) {
        if (name == null) {
            return false;
        }
        return name.chars().allMatch(Character::isAlphabetic);
    }
}
