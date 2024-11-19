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
        setLicenseNumber(licenseNb);
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

    /**
     * This method validates the license number qc format based on : A####DDMMYY##        #= any numbers
     *
     * @param number provided license nb
     * @return whether its valid or not
     */
    private boolean licenseNumberValidator(String number) {
        if (number == null) {
            return false;
        }
        //format length
        if (number.length() != 11) {
            return false;
        }
        //A= the first letter of your last name and rest is digit
        if (!(Character.isAlphabetic(number.charAt(0)) || number.substring(1).chars().allMatch(Character::isDigit))) {
            return false;
        }
        //variables for easier calling
        int day = Integer.parseInt(number.substring(5, 7)); //D= day of birth year
        int month = Integer.parseInt(number.substring(7, 9)); //M= month of birth year
        int year = Integer.parseInt(number.substring(9, 11)); //Y= the last two numbers of birth year

        //if born before 1949 return false, if born after 2006 return false, (basically must be between 18 and like 70
        if (year < 49 && !(year <= 6)) {
            return false;
        }

        //month must be between 1 and 12
        if (!(month > 0 && month <= 12)) {
            return false;
        }

        //IF WE HAVE THE TIME, AN ARRAY COULD BE USED TO STORE DAY PER MONTH VALUES AND MAKE THIS METHOD MORE EFFICIENT

        //31 day months
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (!(day > 0 && day <= 31)) {
                return false;
            }
            //30 day months
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (!(day > 0 && day < 31)) {
                return false;
            }
        } else if (month == 2) {
            //if leap then  feb has 29 days
            if (year == 52 || year == 56 || year == 60 || year == 64 || year == 68 ||
                    year == 72 || year == 76 || year == 80 || year == 84 || year == 88 ||
                    year == 92 || year == 96 || year == 0 || year == 4) {
                if (!(day > 0 && day <= 29)) {
                    return false;
                }
                //not leap feb has 28 days
            } else if (!(day > 0 && day <= 28)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method sets the license number if valid
     *
     * @param licenseNumber provided license nb
     * @throws InvalidArgumentException if invalid format
     */
    public void setLicenseNumber(String licenseNumber) throws InvalidArgumentException {
        if (!licenseNumberValidator(licenseNumber)) {
            throw new InvalidArgumentException("Invalid Quebec License Or Invalid Format.");
        }
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


    /**
     * @param assignedOrder
     */
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
