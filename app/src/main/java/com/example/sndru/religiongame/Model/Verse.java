package com.example.sndru.religiongame.Model;

/**
 * @author Samuel Nuttall
 */
public class Verse
{
    private BookOfMormon.Book book;
    private Integer chapterNum;
    private Integer verseNum;
    private String text;
    
    
    
    public Verse()
    {
    }

    public int getChapterNum()
    {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum)
    {
        this.chapterNum = chapterNum;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getVerseNum()
    {
        return verseNum;
    }

    public void setVerseNum(int verseNum)
    {
        this.verseNum = verseNum;
    }
    
    
    public BookOfMormon.Book getBook()
    {
        return book;
    }
    
    public void setBook(BookOfMormon.Book book)
    {
        this.book = book;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(book + " " + chapterNum + ":" + verseNum);
        sb.append(text);

        return sb.toString();
    }
    
    public String bookChapVerse()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(book + " " + chapterNum + ":" + verseNum);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Verse verse1 = (Verse) o;
        
        if (getChapterNum() != verse1.getChapterNum()) return false;
        if (getVerseNum() != verse1.getVerseNum()) return false;
        if (getBook() != verse1.getBook()) return false;
        return getText().equals(verse1.getText());
    
    }
    
    @Override
    public int hashCode()
    {
        int result = getBook().hashCode();
        result = 31 * result + getChapterNum();
        result = 31 * result + getVerseNum();
        result = 31 * result + getText().hashCode();
        return result;
    }
    
    public boolean isInvalid()
    {
        if (book == null || chapterNum == null || verseNum == null || text == null || text.equals("") || text.equals(" "))
        {
            return true;
        }
        return false;
    }
    
   
}
