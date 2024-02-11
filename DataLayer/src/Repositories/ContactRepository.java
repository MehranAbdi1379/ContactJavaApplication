package Repositories;

import Models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    public ContactRepository() {
        var contact = new Contact();
        contact.setFirstName("Mehran");
        contact.setLastName("Abdi");
        contact.setAge(23);
        contact.setPhoneNumber("09033294671");
        contact.setCity("Tabriz");
        contact.setFavorite(true);
        insert(contact);

        contact.setFirstName("Ali");
        contact.setLastName("Hoseyni");
        contact.setAge(32);
        contact.setPhoneNumber("09784658978");
        contact.setCity("Sahand");
        contact.setFavorite(false);
        insert(contact);

        contact.setFirstName("Abbas");
        contact.setLastName("Abdi");
        contact.setAge(52);
        contact.setPhoneNumber("09784653123");
        contact.setCity("Tabriz");
        contact.setFavorite(true);
        insert(contact);

        contact.setFirstName("Meri");
        contact.setLastName("Kami");
        contact.setAge(14);
        contact.setCity("Shiraz");
        contact.setFavorite(false);
        insert(contact);

        contact.setFirstName("Amir");
        contact.setLastName("Golami");
        contact.setAge(76);
        contact.setCity("Tehran");
        contact.setFavorite(true);
        insert(contact);
    }

    public static void insert(Contact contact)
    {
        contact.setId(Contacts.size()+1);
        Contacts.add(contact);
    }
    public static boolean update(Contact contact)
    {
        for (var tempContact: Contacts)
        {
            if(tempContact.getId() == contact.getId())
            {
                Contacts.set(Contacts.indexOf(tempContact),contact);
                return true;
            }
        }
        return false;
    }
    public static boolean delete(int id){
        for (var contact: Contacts
             ) {
            if(id == contact.getId()) {
                contact.setDeleted(true);
                return true;
            }
        }
        return false;
    }
    public static Contact getById(int id){
        for (var tempContact: Contacts
             ) {
            if(tempContact.getId() == id)
                return tempContact;
        }
        return null;
    }
    public static List<Contact> getAll(){
        var resultContacts = new ArrayList<Contact>();
        for (var contact: Contacts
             ) {
            if(!contact.isDeleted())
                resultContacts.add(contact);
        }
        return resultContacts;
    }
    protected static List<Contact> Contacts = new ArrayList<>();
}
