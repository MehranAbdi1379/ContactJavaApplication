package UI.Service;

import Input.InputService;
import Models.Contact;
import Output.ContactOutputService;
import Services.ContactService;

import java.util.ArrayList;
import java.util.List;

public class UIContactService {
    public static void runApp(){
        ContactOutputService.showWelcomeMessage();
        runMainFunctionalities();
        //the program is finished.
        InputService.closeScanner();
        ContactOutputService.showThanksMessage();
    }
    public static void insert(){
        var firstName = getFirstName();
        var lastName = getLastName();
        var phoneNumber = getPhoneNumber();
        var city = getCity();
        var age = getAge();
        var favorite = getFavorite();

        var contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAge(age);
        contact.setCity(city);
        contact.setFavorite(favorite);
        contact.setPhoneNumber(phoneNumber);

        ContactService.insert(contact);
    }
    public static void listFavorites(){
        ContactOutputService.listContacts(ContactService.getFavorites());
    }
    public static void listAll(){
        ContactOutputService.listContacts(ContactService.getAll());
    }
    public static void search(){
        var functionalityIndex = "";
        ContactOutputService.showSearchFunctionalities();
        ContactOutputService.askForFunctionality();
        functionalityIndex = InputService.readLine();
        var searchCompleted = false;
        while(!functionalityIndex.equalsIgnoreCase("q") || searchCompleted)
        {
            searchCompleted = true;
            switch (functionalityIndex) {
                case "1" -> searchByFirstName();
                case "2" -> searchByLastName();
                case "3" -> searchByPhoneNumber();
                case "4" -> searchByCity();
                default -> searchCompleted = false;
            }
            if(!searchCompleted)
            {
                ContactOutputService.showSearchFunctionalities();
                ContactOutputService.askForFunctionality();
                functionalityIndex = InputService.readLine();
            }
        }
        editOrDelete();
    }
    public static void editOrDelete(){
        ContactOutputService.showEditAndRemoveFunctionalities();
        var functionalityIndex = InputService.readLine();
        while(!functionalityIndex.equalsIgnoreCase("q"))
        {
            switch (functionalityIndex) {
                case "1" -> update();
                case "2" -> deleteById();
            }
            ContactOutputService.showSearchFunctionalities();
            ContactOutputService.askForFunctionality();
            functionalityIndex = InputService.readLine();
        }
    }
    public static void update(){
        var id = getId();
        var contact = ContactService.getById(id);
        if(contact != null){
            var firstName = InputService.readLine();
            var lastName = InputService.readLine();
            var city = InputService.readLine();
            // TODO: finish this method
        }
    }
    public static void deleteById(){
        var ids = getIds();
        ContactService.deleteByIds(ids);
    }
    public static void searchByFirstName(){
        var firstName = getFirstName();
        ContactOutputService.listContacts(ContactService.searchByFirstName(firstName));
    }
    public static void searchByLastName(){
        var lastName = getLastName();
        ContactOutputService.listContacts(ContactService.searchByLastName(lastName));
    }
    public static void searchByPhoneNumber(){
        var phoneNumber = getPhoneNumber();
        ContactOutputService.listContacts(ContactService.searchByPhoneNumber(phoneNumber));
    }
    public static void searchByCity(){
        var city = getCity();
        ContactOutputService.listContacts(ContactService.searchByCity(city));
    }
    public static String getFirstName(){
        ContactOutputService.askForFirstName();
        var firstName = InputService.readLine();
        while(!ContactService.validateString(firstName))
        {
            ContactOutputService.listContacts(ContactService.searchByFirstName(firstName));
            ContactOutputService.askForFirstName();
            firstName = InputService.readLine();
        }
        return firstName;
    }
    public static String getLastName(){
        ContactOutputService.askForLastName();
        var lastName = InputService.readLine();
        while(!ContactService.validateString(lastName))
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForLastName();
            lastName = InputService.readLine();
        }
        return lastName;
    }
    public static String getPhoneNumber(){
        ContactOutputService.askForPhoneNumber();
        var phoneNumber = InputService.readLine();
        while(!ContactService.validatePhoneNumber(phoneNumber) || !ContactService.validateString(phoneNumber))
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForPhoneNumber();
            phoneNumber = InputService.readLine();
        }
        return phoneNumber;
    }
    public static String getCity(){
        ContactOutputService.askForCity();
        var city = InputService.readLine();
        while(!ContactService.validateString(city))
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForCity();
            city = InputService.readLine();
        }
        return city;
    }
    public static int getAge(){
        ContactOutputService.askForAge();
        var ageString = InputService.readLine();
        while(ContactService.validateInteger(ageString) == 0)
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForAge();
            ageString = InputService.readLine();
        }
        var age = ContactService.validateInteger(ageString);
        return age;
    }
    public static int getId(){
        ContactOutputService.askForId();
        var idString = InputService.readLine();
        while(ContactService.validateInteger(idString) == 0)
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForId();
            idString = InputService.readLine();
        }
        var id = ContactService.validateInteger(idString);
        return id;
    }
    public static List<Integer> getIds(){
        ContactOutputService.askForIds();
        var contactIds = InputService.readLine();
        var ids = new ArrayList<Integer>();
        for (String id: contactIds.split(",")) {
            if(ContactService.validateInteger(id) != 0)
                ids.add(Integer.parseInt(id.trim()));
        }
        return ids;
    }
    public static boolean getFavorite(){
        ContactOutputService.askForFavorite();
        var favoriteString = InputService.readLine();
        while(!ContactService.validateYesOrNo(favoriteString))
        {
            ContactOutputService.showErrorMessage();
            ContactOutputService.askForFavorite();
            favoriteString = InputService.readLine();
        }
        var favorite = ContactService.determineYesOrNo(favoriteString);
        return favorite;
    }
    public static void runMainFunctionalities()
    {
        var functionalityIndex = "";
        ContactOutputService.showFunctionalities();
        ContactOutputService.askForFunctionality();
        functionalityIndex = InputService.readLine();
        while(!functionalityIndex.equalsIgnoreCase("q"))
        {
            switch (functionalityIndex){
                case "1":
                    insert();
                    break;
                case "2":
                    search();
                    break;
                case "3":
                    listAll();
                    break;
                case "4":
                    listFavorites();
                    break;
                case "q":
                    ContactOutputService.showThanksMessage();
                    break;
                default:
                    ContactOutputService.showErrorMessage();
                    break;
            }

            ContactOutputService.showFunctionalities();
            ContactOutputService.askForFunctionality();
            functionalityIndex = InputService.readLine();
        }
    }
}
