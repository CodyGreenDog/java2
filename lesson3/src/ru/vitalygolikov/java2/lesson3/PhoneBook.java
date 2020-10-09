package ru.vitalygolikov.java2.lesson3;

import java.lang.reflect.Array;
import java.util.*;

public class PhoneBook {

    private HashSet<Person> persons;
    private HashSet<String> lastNames;
    private HashMap<String, ArrayList<String> > phones;
    private HashMap<String, ArrayList<String> > eMails;

    public PhoneBook()
    {
        lastNames = new HashSet<>();
        persons = new HashSet<>();
        phones = new HashMap<>();
        eMails = new HashMap<>();
    }

    public void add(String lastName, String phone, String eMail)
    {
        persons.add( new Person(lastName, phone, eMail) );

        lastNames.add(lastName);

        ArrayList<String> phoneArray = phones.get(lastName);
        if( phoneArray == null ) { phoneArray = new ArrayList<>(); }
        phoneArray.add(phone);
        phones.put(lastName, phoneArray );

        ArrayList<String> eMailArray = eMails.get(lastName);
        if(eMailArray == null) { eMailArray = new ArrayList<>(); }
        eMailArray.add(eMail);

        eMails.put(lastName, eMailArray);

    }




    public ArrayList<String> getPhone(String lastName)
    {
        return phones.get(lastName);
    }
    public ArrayList<String> getEMail(String lastName)
    {
        return eMails.get(lastName);
    }


    public void printPhone(String lastName)
    {
        ArrayList<String> tmpPhones = getPhone(lastName);
        System.out.println( tmpPhones.toString() );
    }

    public void printEMail(String lastName)
    {
        ArrayList<String> tmpEMails = getEMail(lastName);
        System.out.println( tmpEMails.toString() );
    }

    public ArrayList<String> getNameList()
    {

        return  new ArrayList<>(lastNames);

    }

    public class Person {
        private String lastName;
        private String phone;
        private String eMail;

        public Person(String lastName, String phone, String eMail)
        {
            this.lastName = lastName;
            this.phone = phone;
            this.eMail = eMail;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash( phone, eMail);
        }
    }

}




