package Repositories;

import Models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
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
