package UI.Service;

import Input.InputService;
import Models.Contact;
import Output.ContactOutputService;
import Services.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UIContactService {
    public static void runApp(){
        //initial contacts
        insertInitialContacts();

        //start the program
        ContactOutputService.showWelcomeMessage();
        runMainFunctionalities();


        //Stop the program
        InputService.closeScanner();
        ContactOutputService.showThanksMessage();
    }
    public static void insertInitialContacts(){
        String[] firstNames = {"John", "Alice", "Michael", "Emma", "William", "Sophia", "James", "Olivia", "David", "Emily"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Jones", "Miller", "Davis", "Garcia", "Martinez", "Lee", "Perez"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
            List<Contact> contacts = new ArrayList<>();
            Random random = new Random();

            for (int i = 1; i <= 30; i++) {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String phoneNumber = generatePhoneNumber();
                String city = cities[random.nextInt(cities.length)];
                int age = random.nextInt(50) + 18; // Random age between 18 and 67

                var contact = new Contact(firstName,lastName,phoneNumber,city,age);
                ContactService.insert(contact);
            }
    }
    private static String generatePhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("09");

        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }
    public static void insert(){
        var firstName = getFirstName();
        var lastName = getLastName();
        var phoneNumber = getPhoneNumber(false);
        var city = getCity();
        var age = getAge();
        var favorite = getFavorite();

        var contact = new Contact(firstName,lastName,phoneNumber,city,age);
        contact.setFavorite(favorite);

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
        while(!functionalityIndex.equalsIgnoreCase("q") && !searchCompleted)
        {
            searchCompleted = true;
            switch (functionalityIndex) {
                case "1" -> searchByFirstName();
                case "2" -> searchByLastName();
                case "3" -> searchByPhoneNumber();
                case "4" -> searchByCity();
                case "5" -> searchByAge();
                default -> searchCompleted = false;
            }
            if(!functionalityIndex.equalsIgnoreCase("q") && !searchCompleted)
            {
                ContactOutputService.showSearchFunctionalities();
                ContactOutputService.askForFunctionality();
                functionalityIndex = InputService.readLine();
            }
        }
        if(searchCompleted)
        {
            editOrDelete();
        }
    }
    public static void editOrDelete(){
        ContactOutputService.showUpdateAndDeleteFunctionalities();
        ContactOutputService.askForFunctionality();
        var functionalityIndex = InputService.readLine();
        while(!functionalityIndex.equalsIgnoreCase("q"))
        {
            switch (functionalityIndex) {
                case "1" -> update();
                case "2" -> deleteById();
                default -> ContactOutputService.showErrorMessage();
            }
            ContactOutputService.showUpdateAndDeleteFunctionalities();
            ContactOutputService.askForFunctionality();
            functionalityIndex = InputService.readLine();
        }
    }
    public static void update(){
        ContactOutputService.showUpdateFunctionality();
        var id = getId();
        var contact = ContactService.getById(id);
        if(contact != null){
            var firstName = getFirstNameForUpdate(contact);
            var lastName = getLastNameForUpdate(contact);
            var phoneNumber = getPhoneNumberForUpdate(contact);
            var city = getCityForUpdate(contact);
            var age = getAgeForUpdate(contact);
            var favorite = getFavoriteForUpdate(contact);

            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPhoneNumber(phoneNumber);
            contact.setCity(city);
            contact.setAge(age);
            contact.setFavorite(favorite);

            ContactService.update(contact);
        }
        else {
            ContactOutputService.showNoUserWithId();
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
        var phoneNumber = getPhoneNumber(true);
        ContactOutputService.listContacts(ContactService.searchByPhoneNumber(phoneNumber));
    }
    public static void searchByAge(){
        ContactOutputService.showSearchByAgeFunctionality();
        ContactOutputService.askForFunctionality();
        var functionalityIndex = InputService.readLine();
        while(!functionalityIndex.equalsIgnoreCase("q"))
        {
            switch (functionalityIndex) {
                case "1" -> ;
                case "2" -> ;
                case "3" -> ;
                case "4" -> ;
                default -> ContactOutputService.showErrorMessage();
            }
            ContactOutputService.showUpdateAndDeleteFunctionalities();
            ContactOutputService.askForFunctionality();
            functionalityIndex = InputService.readLine();
        }
        var age = getAge();
        ContactOutputService.listContacts(ContactService.searchByAge(age));
    }
    public static void searchByAgeByExactAge(){
        var age = getAge();

    }
    public static void searchByCity(){
        var city = getCity();
        ContactOutputService.listContacts(ContactService.searchByCity(city));
    }
    public static String getFirstNameForUpdate(Contact contact){
        ContactOutputService.askForFirstName();
        var firstName = InputService.readLine();
        if(!ContactService.validateString(firstName))
            return contact.getFirstName();
        return firstName;
    }
    public static String getLastNameForUpdate(Contact contact)
    {
        ContactOutputService.askForLastName();
        var lastName = InputService.readLine();
        if(!ContactService.validateString(lastName))
            return contact.getLastName();
        return lastName;
    }
    public static String getPhoneNumberForUpdate(Contact contact){
        ContactOutputService.askForPhoneNumber();
        var phoneNumber = InputService.readLine();
        if(!ContactService.validateString(phoneNumber) || !ContactService.validatePhoneNumber(phoneNumber))
            return contact.getPhoneNumber();
        return phoneNumber;
    }
    public static String getCityForUpdate(Contact contact)
    {
        ContactOutputService.askForCity();
        var city = InputService.readLine();
        if(!ContactService.validateString(city))
            return contact.getCity();
        return city;
    }
    public static int getAgeForUpdate(Contact contact){
        ContactOutputService.askForAge();
        var ageString = InputService.readLine();
        if(!ContactService.validateString(ageString) || ContactService.validateInteger(ageString) == 0)
            return contact.getAge();
        return ContactService.validateInteger(ageString);
    }
    public static boolean getFavoriteForUpdate(Contact contact){
        ContactOutputService.askForFavorite();
        var favoriteString = InputService.readLine();
        if(!ContactService.validateYesOrNo(favoriteString))
            return contact.isFavorite();
        return ContactService.determineYesOrNo(favoriteString);
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
    public static String getPhoneNumberForSearch(){
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
    public static String getPhoneNumber(boolean search)
    {
        ContactOutputService.askForPhoneNumber();
        var phoneNumber = InputService.readLine();
        if(search)
        {
            while(!ContactService.validateString(phoneNumber))
            {
                ContactOutputService.showErrorMessage();
                ContactOutputService.askForPhoneNumber();
                phoneNumber = InputService.readLine();
            }
        }
        else {
            while(!ContactService.validatePhoneNumber(phoneNumber) || !ContactService.validateString(phoneNumber))
            {
                ContactOutputService.showErrorMessage();
                ContactOutputService.askForPhoneNumber();
                phoneNumber = InputService.readLine();
            }
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
