package com.example.sndru.religiongame.Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Samuel Nuttall
 */
public class BOMScanner
{
    private BookOfMormon mBookOfMormon = BookOfMormon.getInstance();
    private Map<BookOfMormon.Book, String> bookToStringMap = mBookOfMormon.getBooksStringMap();
    private Map<String, BookOfMormon.Book> stringToBookMap = mBookOfMormon.getStringBooksMap();
    private Map<BookOfMormon.Book, BoMBook> bookMap = mBookOfMormon.getBookMap();
    
    
   public void getVerses(InputStream bom) throws FileNotFoundException
    {
        
        System.out.println(bom);
        Scanner scanner = new Scanner(new BufferedInputStream(bom));
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (line.contains(" 0:") || !line.contains(":") || line.contains(":0"))
            {
                continue;
            } else
            {
                getVerse(line);
                
            }
        }
        scanner.close();
    }
    
    public void getVersesALT(BufferedReader BOMReader) throws IOException
    {
        String line;
        while ((line = BOMReader.readLine()) != null)
        {
          //  String line = scanner.nextLine();
            if (line.contains(" 0:") || !line.contains(":") || line.contains(":0"))
            {
                continue;
            } else
            {
                getVerse(line);
            
            }
        }
        BOMReader.close();
    }
    
    
    private void getVerse(String line)
    {
        
        String[] arr = line.split(" ");
        //System.out.println(line);
        
        //System.out.println();
        //findBook
        BoMBook bomBook = findBook(arr[0]);
        BookOfMormon.Book book = stringToBookMap.get(arr[0]);
        //parse chapter and verse number
        Verse verse = createVerse(arr[1]);
        verse.setBook(book);
        line = Pattern.compile(Pattern.quote(arr[0]) + " " + arr[1]).matcher(line).replaceFirst("");
        //System.out.println(line);
        verse.setText(line);
        try
        {
            if (verse.isInvalid() == true)
            {
                throw new Exception("Verse is invalid: " + verse.toString());
            }
        }
        catch (Exception ex)
        {
            //System.out.println("ERROR MESSAGE: " + ex.getMessage());
            ex.printStackTrace();
        }
    
        bomBook.addVerse(verse);
    }
    
    
    private BoMBook findBook(String bookString)
    {
        
        
        BookOfMormon.Book book = stringToBookMap.get(bookString);
        //System.out.println("Here is the Book " + book.toString());
        //The key was null
        try
        {
            BoMBook boMBook = BookOfMormon.getInstance().getBookMap().get(book);
        }
        catch (Exception ex)
        {
            //System.out.println("This is null bomBook: ");
            ex.printStackTrace();
        }
        
        
        BoMBook boMBook = BookOfMormon.getInstance().getBookMap().get(book);
        
        return boMBook;
    }
    
    private Verse createVerse(String chapVerse)
    {
        Verse verse = new Verse();
        try
        {
            
            String[] arr = chapVerse.split(":");
            verse.setChapterNum(Integer.parseInt(arr[0]));
            verse.setVerseNum(Integer.parseInt(arr[1]));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return verse;
    }
}
