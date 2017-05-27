/*
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


*/
/**
 * @author Samuel Nuttall
 *//*

public class BOMScanner
{
    Set<String> verses = new LinkedHashSet<>();
    private Set<String> setBooks = new HashSet<String>();
    private Book curBook = null;


    public void getVerses(File bom) throws FileNotFoundException
    {
        Book.initializeBooksStringMap();
        java.util.Scanner scanner = new java.util.Scanner(new FileReader(bom));
        scanner.useDelimiter("#");

        while (scanner.hasNext())
        {
            String line = scanner.next();
            if (!line.contains(":"))
            {
                continue;
            } else
            {
                getHeader(line);

            }
        }

        scanner.close();

        //System.out.println(Verse.getCount());
    }

    private void getHeader(String header)
    {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (header.charAt(i) != ' ')
        {
            sb.append(header.charAt(i));
            i++;
        }
        String book = sb.toString();
        if (!setBooks.contains(book))
        {

            //System.out.println("Book: " + book);
            Book newBook = new Book(book);
            curBook = newBook;

            setBooks.add(book);
        }
        sb.delete(0,sb.length());

        StringBuilder sb2 = new StringBuilder();
        sb2.append(header);
        sb2.delete(0,i+1);
        header = sb2.toString();
        i = 0;
        while (header.charAt(i) != ':')
        {
            sb.append(header.charAt(i));
            i++;
        }
        int chapter = Integer.parseInt(sb.toString());

        StringBuilder sb3 = new StringBuilder();
        sb3.append(header);
        sb3.delete(0,i+1);
        header = sb3.toString();

        i=0;
        sb.delete(0,sb.length());
        while (Character.isDigit(header.charAt(i)))
        {
            sb.append(header.charAt(i));
            i++;
        }
        //System.out.println("Here is the verse NUM: " + sb.toString());
       int verseNum = Integer.parseInt(sb.toString());


        StringBuilder sb4 = new StringBuilder();
        sb4.append(header);
        sb4.delete(0,i+1);
        header = sb4.toString();

        /////////////////////////////////////////////////////////////////////////////////////////////

        Verse verse = new Verse(chapter, verseNum, header);
        curBook.addVerses(verse);

    }





}
*/
