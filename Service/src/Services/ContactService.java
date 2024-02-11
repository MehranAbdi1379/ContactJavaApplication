package Services;

import Models.Contact;
import Repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    public static boolean validateString(String string){
        return !string.isEmpty();
    }
    public static int validateInteger(String string)
    {
        try {
            var result = Integer.parseInt(string);
            return result;
        }
        catch (Exception ex)
        {
            return 0;
        }
    }
    public static boolean validateYesOrNo(String bool){
        if(bool.equalsIgnoreCase("yes") || bool.equalsIgnoreCase("no"))
            return true;
        return false;
    }
    public static boolean determineYesOrNo(String bool)
    {
        if(bool.equalsIgnoreCase("yes"))
            return true;
        return false;
    }
    public static boolean validatePhoneNumber(String phoneNumber){
        for (var contact: ContactRepository.getAll()) {
            if(contact.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                return false;
        }
        return true;
    }
    public static List<Contact> searchByFirstName(String firstName){
        var result = new ArrayList<Contact>();
        for (var contact: ContactRepository.getAll()) {
            if(contact.getFirstName().equalsIgnoreCase(firstName))
                result.add(contact);
        }
        return result;
    }
    public static List<Contact> searchByLastName(String lastName){
        var result = new ArrayList<Contact>();
        for (var contact: ContactRepository.getAll()) {
            if(contact.getLastName().equalsIgnoreCase(lastName))
                result.add(contact);
        }
        return result;
    }
    public static List<Contact> searchByPhoneNumber(String phoneNumber){
        var result = new ArrayList<Contact>();
        for (var contact: ContactRepository.getAll()) {
            if(contact.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                result.add(contact);
        }
        return result;
    }
    public static List<Contact> searchByCity(String city){
        var result = new ArrayList<Contact>();
        for (var contact: ContactRepository.getAll()) {
            if(contact.getCity().equalsIgnoreCase(city))
                result.add(contact);
        }
        return result;
    }
    public static List<Contact> getFavorites(){
        var favoriteContacts = new ArrayList<Contact>();
        for (var contact: ContactRepository.getAll()) {
            if(contact.isFavorite())
                favoriteContacts.add(contact);
        }
        return favoriteContacts;
    }
    public static void deleteByIds(List<Integer> ids){
        for (var id: ids) {
            ContactRepository.delete(id);
        }
    }
    public static void insert(Contact contact){
        ContactRepository.insert(contact);
    }
    public static void update(Contact contact)
    {
        ContactRepository.update(contact);
    }
    public static void delete(int id)
    {
        ContactRepository.delete(id);
    }
    public static List<Contact> getAll()
    {
        return ContactRepository.getAll();
    }
    public static Contact getById(int id)
    {
        return ContactRepository.getById(id);
    }
}
