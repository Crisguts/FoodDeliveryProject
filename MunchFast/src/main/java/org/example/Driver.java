package org.example;

import lombok.Getter;

@Getter
public class Driver extends Person {
    private String licensePlate;
    private String licenseNumber;
    private double rating;
    private boolean isAvailable;
    private Order assignedOrder;
    private boolean isFast;

    //I SAW COMMENTS ABOUT USING A BUILDER PATTERN FOR USE OF MANY PARAMETERS

    /**
     * To initialize and set the Person's properties.
     *
     * @param firstname to initialize the first name value.
     * @param lastname  to initialize the last name value.
     * @param email     to initialize the email value.
     * @param phone     to initialize the phone value.
     * @throws InvalidArgumentException if any parameter is invalid.
     */
    public Driver(String firstname, String lastname, String email, String phone, String licenseP, String licenseNb) throws InvalidArgumentException {
        super(firstname, lastname, email, phone);
        isFast = false;
        assignedOrder = null;  //initialize as null since he has no order assigned to him
        isAvailable = true; //initially true since well no orders assigned to it
        rating = 0.0;
        setLicensePlate(licenseP);
        licenseNumber = licenseNb;
    }


    /**
     * This sets the license plate.
     *
     * @param licensePlate is the value to be checked and set.
     * @throws InvalidArgumentException when the format is invalid.
     */
    public void setLicensePlate(String licensePlate) throws InvalidArgumentException {
        if (!licensePlateValidator(licensePlate)) {
            throw new InvalidArgumentException("Invalid License Plate Format.");
        }
        this.licensePlate = licensePlate.replaceAll(" ", "");
    }

    /**
     * This method checks that the license plate is of format XXX XXX or XXXXXX.
     *
     * @param plate is the provided license plate to get checked.
     * @returns whether the plate is valid or not.
     */
    private boolean licensePlateValidator(String plate) {
        if (plate == null) {
            return false;
        }
        if (plate.length() == 7 || plate.length() == 6) {
            int spaceCount = 0;
            for (char c : plate.toCharArray()) {
                if (c == ' ') {
                    spaceCount++;
                } else if (!(Character.isLetter(c) || Character.isDigit(c))) {
                    return false;
                }
            }
            if (!(spaceCount == 1 || spaceCount == 0)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * This method sets the rating using an average of it's current one and new one.
     *
     * @param newRating rating to be given, has to be 1,2,3,4 or 5
     * @throws InvalidArgumentException if new rating doesn't fit criteria
     */
    public void setRating(int newRating) throws InvalidArgumentException {
        if (newRating > 5 || newRating < 1) {
            throw new InvalidArgumentException("Rating must be between 1 and 5.");
        }
        if (rating == 0.0) {
            rating = newRating;
        } else {
            rating = (rating + newRating) / 2;
            if (rating > 5) {
                rating = 5.0;
            }
        }
    }

    /**
     * This method sets the availability of the driver
     *
     * @param available whether driver is available or not
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void takeOrder(Order assignedOrder) {
        if (this.assignedOrder != null) {
            //reject the order
            setAvailable(false);
        } else {
            this.assignedOrder = assignedOrder;
            setAvailable(true);
        }
    }


    /**
     * This method sets the driver as an express delivery type or normal delivery type (pretty much)
     *
     * @param fast is the boolean value to change his type
     */
    public void setFast(boolean fast) {
        isFast = fast;
    }
}
