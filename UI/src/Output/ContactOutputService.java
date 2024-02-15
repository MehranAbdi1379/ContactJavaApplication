package Output;

import Models.Contact;

import java.util.List;

public class ContactOutputService {
    public static void showWelcomeMessage(){
        System.out.println( "---------------------------------\n" +
                            "------------Welcome--------------\n" +
                            "--------The Contact App----------\n" +
                            "---------------------------------\n" +
                            "Editing and Removing are available\n" +
                            "---after searching the contacts--\n" +
                            "---------------------------------");
    }
    public static void showThanksMessage(){
        System.out.println("Thanks for using this application.\n" +
                            "See you next time :)");
    }
    public static void showNoUserWithId(){ System.out.println("There is no user with this id.");}
    public static void showErrorMessage(){
        System.out.println("Please enter a valid value.");
    }
    public static void showFunctionalities(){
        System.out.println("You can Add(1), Search(2), List All(3), List Favorite(4) Contacts.");
    }
    public static void showSearchFunctionalities(){
        System.out.println("You can search by First Name(1), Last Name(2), Phone Number(3), City(4) or Age(5).");
    }
    public static void showUpdateAndDeleteFunctionalities(){
        System.out.println("You can Edit(1) or Remove(2) your contacts.");
    }
    public static void showUpdateFunctionality(){
        System.out.println("If you don't want to edit a field, just leave it empty and proceed.");
    }
    public static void showSearchByAgeFunctionality(){
        System.out.println("You can search by age in 3 different ways. \n" +
                "Search for exact age(1), equal or less than the age(2), equal or more than age(3), between minimum and maximum age(4).");
    }
    public static void showEditEmptyMessage(){
        System.out.println("If you don't want to change a property of the contact, leave it empty and press enter.");
    }
    public static void askForFirstName(){
        System.out.print("Please enter the First Name: ");
    }
    public static void askForLastName(){
        System.out.print("Please enter the Last Name: ");
    }
    public static void askForPhoneNumber(){
        System.out.print("Please enter the Phone Number: ");
    }
    public static void askForCity(){
        System.out.print("Please enter the City: ");
    }
    public static void askForFavorite(){
        System.out.print("Is this contact in the favorites (yes/no): ");
    }
    public static void askForAge(){
        System.out.print("Please enter the Age: ");
    }
    public static void askForIds() { System.out.print( "Please enter contact Ids to remove.\n" +
                                                        "Format of input: 2,4,6\n" +
                                                        "Contact Ids: ");}
    public static void askForId() { System.out.print("Please enter Id of the contact: ");}
    public static void askForFunctionality(){
        System.out.print("Please enter the number of the functionality (Q for quit): ");
    }
    public static void printContact(Contact contact){
        System.out.println("Id: " + contact.getId() + ", Name: "+contact.getFirstName()+ " " +
                contact.getLastName()+", Phone Number: "+contact.getPhoneNumber()+"" +
                ", City: " + contact.getCity() + ", Age: " + contact.getAge() + ", Favorite: "+(contact.isFavorite()?"Yes":"No"));
    }
    public static void listContacts(List<Contact> contacts){
        for (var contact: contacts) {
            printContact(contact);
        }
    }
}
