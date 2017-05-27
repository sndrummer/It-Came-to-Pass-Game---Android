package com.example.sndru.religiongame.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Samuel Nuttall
 */
public class BookOfMormon
{
    private static BookOfMormon instance;
    
    
    private static BoMBook Nephi1 = new BoMBook(Book.Nephi1);
    private static BoMBook Nephi2 = new BoMBook(Book.Nephi2);
    private static BoMBook Nephi3 = new BoMBook(Book.Nephi3);
    private static BoMBook Nephi4 = new BoMBook(Book.Nephi4);
    private static BoMBook Jacob = new BoMBook(Book.Jacob);
    private static BoMBook Enos = new BoMBook(Book.Enos);
    private static BoMBook Jarom = new BoMBook(Book.Jarom);
    private static BoMBook Omni = new BoMBook(Book.Omni);
    private static BoMBook WordsOfMormon = new BoMBook(Book.WordsOfMormon);
    private static BoMBook Mosiah = new BoMBook(Book.Mosiah);
    private static BoMBook Alma = new BoMBook(Book.Alma);
    private static BoMBook Helaman = new BoMBook(Book.Helaman);
    private static BoMBook Mormon = new BoMBook(Book.Mormon);
    private static BoMBook Ether = new BoMBook(Book.Ether);
    private static BoMBook Moroni = new BoMBook(Book.Moroni);
    
    private static Map<Book, String> sBooksStringMap = new TreeMap<>();
    private static Map<String, Book> sStringBooksMap = new TreeMap<>();
    private static Map<Book, BoMBook> bookMap = new TreeMap<>();
    
    //public static Set<BoMBook> sBoMBooks;
    
    
    /*
    ** NAMES **
     */
    private static List<String> personNames = new ArrayList<>();
    
    private BookOfMormon()
    {
    }
    
    public static BookOfMormon getInstance()
    {
        if (instance == null)
        {
            instance = new BookOfMormon();
            initializeBooksStringMap();
            initializeStringBooksMap();
            initializeBoMBooks();
        }
        return instance;
    }
    
    public enum Book
    {
        Nephi1, Nephi2, Jacob, Enos, Jarom, Omni, WordsOfMormon, Mosiah, Alma, Helaman, Nephi3, Nephi4,
        Mormon, Ether, Moroni
    }
    
    
    private static void initializeBooksStringMap()
    {
        sBooksStringMap.put(Book.Nephi1, "1 Nephi");
        sBooksStringMap.put(Book.Nephi2, "2 Nephi");
        sBooksStringMap.put(Book.Nephi3, "3 Nephi");
        sBooksStringMap.put(Book.Nephi4, "4 Nephi");
        sBooksStringMap.put(Book.Jacob, "Jacob");
        sBooksStringMap.put(Book.Enos, "Enos");
        sBooksStringMap.put(Book.Jarom, "Jarom");
        sBooksStringMap.put(Book.Omni, "Omni");
        sBooksStringMap.put(Book.WordsOfMormon, "Words of Mormon");
        sBooksStringMap.put(Book.Mosiah, "Mosiah");
        sBooksStringMap.put(Book.Alma, "Alma");
        sBooksStringMap.put(Book.Helaman, "Helaman");
        sBooksStringMap.put(Book.Mormon, "Mormon");
        sBooksStringMap.put(Book.Ether, "Ether");
        sBooksStringMap.put(Book.Moroni, "Moroni");
        
    }
    
    private static void initializeStringBooksMap()
    {
        sStringBooksMap.put("NE1", Book.Nephi1);
        sStringBooksMap.put("NE2", Book.Nephi2);
        sStringBooksMap.put("NE3", Book.Nephi3);
        sStringBooksMap.put("NE4", Book.Nephi4);
        sStringBooksMap.put("JAC", Book.Jacob);
        sStringBooksMap.put("ENO", Book.Enos);
        sStringBooksMap.put("JAR", Book.Jarom);
        sStringBooksMap.put("OMN", Book.Omni);
        sStringBooksMap.put("WOM", Book.WordsOfMormon);
        sStringBooksMap.put("MSH", Book.Mosiah);
        sStringBooksMap.put("ALM", Book.Alma);
        sStringBooksMap.put("HEL", Book.Helaman);
        sStringBooksMap.put("MOR", Book.Mormon);
        sStringBooksMap.put("ETH", Book.Ether);
        sStringBooksMap.put("MNI", Book.Moroni);
        
    }
    
    private static void initializeBoMBooks()
    {
        bookMap.put(Book.Nephi1, Nephi1);
        bookMap.put(Book.Nephi2, Nephi2);
        bookMap.put(Book.Nephi3, Nephi3);
        bookMap.put(Book.Nephi4, Nephi4);
        bookMap.put(Book.Jacob, Jacob);
        bookMap.put(Book.Enos, Enos);
        bookMap.put(Book.Jarom, Jarom);
        bookMap.put(Book.Omni, Omni);
        bookMap.put(Book.WordsOfMormon, WordsOfMormon);
        bookMap.put(Book.Mosiah, Mosiah);
        bookMap.put(Book.Alma, Alma );
        bookMap.put(Book.Helaman, Helaman);
        bookMap.put(Book.Mormon, Mormon);
        bookMap.put(Book.Ether, Ether);
        bookMap.put(Book.Moroni, Moroni);
        
    }
    
    /*private static void initializeBookMap()
    {
        bookMap.put(Book.Nephi1, new BoMBook(Book.Nephi1));
        bookMap.put(Book.Nephi2, new BoMBook(Book.Nephi2));
        bookMap.put(Book.Nephi3, new BoMBook(Book.Nephi3));
        bookMap.put(Book.Nephi4, new BoMBook(Book.Nephi4));
        bookMap.put(Book.Jacob, new BoMBook(Book.Jacob));
        bookMap.put(Book.Enos, new BoMBook(Book.Enos));
        bookMap.put(Book.Jarom, new BoMBook(Book.Jarom));
        bookMap.put(Book.Omni, new BoMBook(Book.Omni));
        bookMap.put(Book.WordsOfMormon, new BoMBook(Book.WordsOfMormon));
        bookMap.put(Book.Mosiah, new BoMBook(Book.Mosiah));
        bookMap.put(Book.Alma, new BoMBook(Book.Alma));
        bookMap.put(Book.Helaman, new BoMBook(Book.Helaman));
        bookMap.put(Book.Mormon, new BoMBook(Book.Mormon));
        bookMap.put(Book.Ether, new BoMBook(Book.Ether));
        bookMap.put(Book.Moroni, new BoMBook(Book.Moroni));
        
        //System.out.println("Bookmap size is " + bookMap.size() + " after initialization");
    }*/
    
    public boolean nameFound(String name)
    {
        if (sBooksStringMap.containsValue(name))
        {
            return true;
        }
        return false;
    }
    
    public Map<Book, String> getBooksStringMap()
    {
        return sBooksStringMap;
    }
    
    public Map<String, Book> getStringBooksMap()
    {
        return sStringBooksMap;
    }
    
    public Map<Book, BoMBook> getBookMap()
    {
        return bookMap;
    }
    
    //NAMES
    
    
    public static List<String> getPersonNames()
    {
        return personNames;
    }
    
    public static void setPersonNames(List<String> personNames)
    {
        BookOfMormon.personNames = personNames;
    }
}
