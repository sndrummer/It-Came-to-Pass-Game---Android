/*
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

*/
/**
 * @author Samuel Nuttall
 *//*

public class Book
{
    private String name;
    private Set<Verse> verses;
    public enum Books
    {
        Nephi1,Nephi2,Jacob,Enos, Jarom, Omni, WordsOfMormon, Mosiah, Alma, Helaman, Nephi3, Nephi4,
        Mormon, Ether, Moroni
    }

    public static Map<Books,String> booksStringMap = new TreeMap<>();


    public static void initializeBooksStringMap()
    {
        booksStringMap.put(Books.Nephi1, "1 Nephi");
        booksStringMap.put(Books.Nephi2, "2 Nephi");
        booksStringMap.put(Books.Nephi3, "3 Nephi");
        booksStringMap.put(Books.Nephi4, "4 Nephi");
        booksStringMap.put(Books.Jacob, "Jacob");
        booksStringMap.put(Books.Enos, "Enos");
        booksStringMap.put(Books.Jarom, "Jarom");
        booksStringMap.put(Books.Omni, "Omni");
        booksStringMap.put(Books.WordsOfMormon, "Words of Mormon");
        booksStringMap.put(Books.Mosiah, "Mosiah");
        booksStringMap.put(Books.Alma, "Alma");
        booksStringMap.put(Books.Helaman, "Helaman");
        booksStringMap.put(Books.Mormon, "Mormon");
        booksStringMap.put(Books.Ether, "Ether");
        booksStringMap.put(Books.Moroni, "Moroni");

    }



    public Book(String book)
    {
        name = book;
        verses = new TreeSet<>(

                new Comparator<Verse>()
                {
                    @Override
                    public int compare(Verse v1, Verse v2)
                    {
                        if (v1.getChapter() < v2.getChapter())
                        {
                            return  -1;
                        }
                        else if (v2.getChapter() < v1.getChapter())
                        {
                            return 1;
                        }
                        else if (v1.getVerse() < v2.getVerse())
                        {
                            return -1;
                        }
                        else if (v2.getVerse() < v1.getVerse())
                        {
                            return 1;
                        }
                        return 0;
                    }
                }
        );
    }
    public static boolean nameFound(String name)
    {
        if (booksStringMap.containsValue(name))
        {
            return true;
        }
        return false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Verse> getVerses()
    {
        return verses;
    }

    public void addVerse(Verse verse)
    {
        verses.add(verse);
        verse.setBook(name);
        //System.out.print(verse.toString());
    }


}
*/
