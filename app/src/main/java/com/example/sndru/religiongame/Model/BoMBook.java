package com.example.sndru.religiongame.Model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Samuel Nuttall
 */
public class BoMBook
{
    
    private BookOfMormon.Book book;
    private Set<Verse> verses;
    
    public BoMBook(BookOfMormon.Book book)
    {
        this.book = book;
        verses = new TreeSet<>(
                
                new Comparator<Verse>()
                {
                    @Override
                    public int compare(Verse v1, Verse v2)
                    {
                        if (v1.getChapterNum() < v2.getChapterNum())
                        {
                            return  -1;
                        }
                        else if (v2.getChapterNum() < v1.getChapterNum())
                        {
                            return 1;
                        }
                        else if (v1.getVerseNum() < v2.getVerseNum())
                        {
                            return -1;
                        }
                        else if (v2.getVerseNum() < v1.getVerseNum())
                        {
                            return 1;
                        }
                        return 0;
                    }
                }
        );
    }
    
   
    
    public Set<Verse> getVerses()
    {
        return verses;
    }
    
    public void addVerse(Verse verse)
    {
        verses.add(verse);
        verse.setBook(book);
        //System.out.print(verse.toString());
    }
    
    
    public BookOfMormon.Book getBook()
    {
        return book;
    }
    
    
}
