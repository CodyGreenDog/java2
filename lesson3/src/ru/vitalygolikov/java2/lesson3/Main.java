package ru.vitalygolikov.java2.lesson3;

import java.util.*;

public class Main {



    public static void main(String[] args)
    {
        /*
         * 1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
         * Найти список слов, из которых состоит текст (дубликаты не считать);
         * Посчитать сколько раз встречается каждое слово (использовать HashMap);
         */
        String[] wordsArray = {"Java", "learning", "second", "Java", "2", "3", "2", "3",
                "second", "learning", "third", "third", "a", "a", "a", "b",
                "Java", "Java", "IDE", "window"};

        printWordList(wordsArray);
        printWordCount(wordsArray);

        /*
         *
         * 2. Написать простой класс PhoneBook(внутри использовать HashMap):
         * В качестве ключа использовать фамилию
         * В каждой записи всего два поля: phone, e-mail
         * Отдельный метод для поиска номера телефона по фамилии (ввели фамилию,
         * получили ArrayList телефонов), и отдельный метод для поиска e-mail по фамилии.
         *
          */


        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", "+7000000", "ivanov0@ml.com");
        phoneBook.add("Petrov", "+711111", "petrov0@ml.com");
        phoneBook.add("Sidorov", "+722222", "sidorov0@ml.com");
        phoneBook.add("Golovin", "+733333", "golovin0@ml.com");
        phoneBook.add("Dunets", "+744444", "dunets0@ml.com");
        phoneBook.add("Ivanov", "+755555", "ivanov1@ml.com");
        phoneBook.add("Borodin", "+766666", "borodin0@ml.com");
        phoneBook.add("Borodin", "+777777", "borodin1@ml.com");
        phoneBook.add("Godunov", "+788888", "godunov0@ml.com");
        phoneBook.add("Isaev", "+799999", "isaev0@ml.com");

        ArrayList<String> lastNames = phoneBook.getNameList();

        System.out.println("\nPhone Book");
        for(String lN : lastNames )
        {
            System.out.println("lN");
            phoneBook.printPhone(lN);
            phoneBook.printEMail(lN);
            System.out.println();
        }

    }

    private static void printWordList(String[] wordsArray)
    {
        System.out.println("List of Words");
        HashSet<String> wordSet = getWordSet(wordsArray);
        System.out.println( wordSet.toString() );
    }

    private static void printWordCount(String[] wordArray)
    {
        System.out.println("Words Counter");

        HashMap<String, Integer> wordMap = getWordMap(wordArray);
        System.out.println( wordMap.toString() );
    }

    /*****************Utils**************/
    private static HashSet<String> getWordSet(String[] wordsArray)
    {
        HashSet<String> wordList = new HashSet<>();
        wordList.addAll( Arrays.asList(wordsArray) );

        return wordList;
    }





    private static HashMap<String, Integer> getWordMap(String[] wordsArray)
    {
        HashMap<String, Integer> wordMap = new HashMap<>();

        for(int i = 0; i < wordsArray.length; i++)
        {
            wordMap.put(wordsArray[i], wordMap.getOrDefault( wordsArray[i], 0 ) + 1 );
        }

        return wordMap;
    }


}
