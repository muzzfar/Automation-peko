package utils;

import java.security.SecureRandom;
import java.util.Random;

public class HelperClass {
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Emily", "David", "Emma", "Chris", "Olivia", "Daniel", "Sophia","shane","Mensa","King","Thor", "Order", "Adam","Shyam"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor","Neil","Davinder","Versha","Priti","Jitin","Milind"
    };
    static String fullName;
    public String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("+1");

        // Generate random 9-digit number for phone number
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }

        return phoneNumber.toString();
    }


    public static String generateRandomFirstName() {
        Random random = new Random();
        int index = random.nextInt(FIRST_NAMES.length);
        return FIRST_NAMES[index];
    }

    public static String generateRandomLastName() {
        Random random = new Random();
        int index = random.nextInt(LAST_NAMES.length);
        return LAST_NAMES[index];
    }

    public static String generateRandomFullName() {
        String firstName = generateRandomFirstName();
        String lastName = generateRandomLastName();
        // return firstName + " " + lastName;
        fullName = firstName + " " + lastName;
        return fullName;
    }
    public static String getFullNameOfPerson() {
        return fullName;
    }

    public static String generateRandomEmail(){
        Random random = new Random();
        int randomNum = random.nextInt(10000); // Generate a random number between 0 and 9999
        return "john.doe " + System.currentTimeMillis() + randomNum + " @yopmail.com";
    }

}
//  class RandomPasswordGenerator {
//
//     // Define character sets for password generation
//     private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//     private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
//     private static final String DIGITS = "0123456789";
//     private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
//
//     // Combine all character sets
//     private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
//
//     // Secure random number generator
//     private static final SecureRandom random = new SecureRandom();
//
//     public static String generatePassword(int length) {
//         StringBuilder password = new StringBuilder(length);
//
//         // Ensure at least one character from each set is included
//         password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
//         password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
//         password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
//         password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
//
//         // Fill the remaining length of the password
//         for (int i = 4; i < length; i++) {
//             password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
//         }
//
//         // Shuffle the password to ensure random distribution of characters
//         return shuffleString(password.toString());
//     }
//
//     // Helper method to shuffle the characters of a string
//     private static String shuffleString(String input) {
//         StringBuilder output = new StringBuilder(input.length());
//         char[] characters = input.toCharArray();
//         for (int i = characters.length; i > 0; i--) {
//             int randIndex = random.nextInt(i);
//             output.append(characters[randIndex]);
//             characters[randIndex] = characters[i - 1];
//         }
//         return output.toString();
//     }
// }
