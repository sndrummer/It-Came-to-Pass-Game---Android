package com.example.sndru.religiongame.Model.Questions;

import android.util.Log;

import com.example.sndru.religiongame.Model.BoMBook;
import com.example.sndru.religiongame.Model.BookOfMormon;
import com.example.sndru.religiongame.Model.RandomMinMax;
import com.example.sndru.religiongame.Model.Verse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * @author Samuel Nuttall
 */
public class QuestionGenerator
{
    private RandomMinMax rand = new RandomMinMax();
    private static List<Verse> itCameToPassVerses = new ArrayList<>();
    private static List<String> names = BookOfMormon.getPersonNames();
    private BookOfMormon mBookOfMormon = BookOfMormon.getInstance();
    // private Question question;
    
    public QuestionGenerator()
    {
        retrieveCTPVerses();
    }
    
    
    public Question createQuestion()
    {
        Question question = new Question();
        
        try
        {
            question.setQuestionType(rand.getRandInt(0, 1));
            if (question.getQuestionType().equals("where"))
            {
                Verse questionVerse = randVerse();
                question.setVerse(questionVerse.getText());
                Option option = determineCorrect(question);
                option.setText(questionVerse.bookChapVerse());
                generateOtherAns("where", question);
            } else if (question.getQuestionType().equals("name"))
            {
                String name = null;
                
                while (question.getVerse() == null)
                {
                    name = randName();
                    question.setVerse(findRandVerseWithName(name, question));
                    Option option = determineCorrect(question);
                    option.setText(name);
                    generateOtherAns("name", question);
                    
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return question;
    }
    
    
    private String findRandVerseWithName(String name, Question question)
    {
        for (Verse verse : itCameToPassVerses)
        {
            if (verse.getText().contains(name) && checkNeighboringChars(name, verse.getText())) //check for names that have letters after
            {
               /* int index = verse.getText().indexOf(name);
                
                while (index >= 0)
                {
    
                    String text = verse.getText();
                    String regex = "\\b" + name + "\\b";
                    text = text.replaceAll(regex, "");
                    Character c = text.charAt(index);
                    if (Character.isLetter(c))
                    {
                        break;
                    }
                    index = verse.getText().indexOf(name, index + 1);
                }*/
            
                return getVerseWithBlank(verse, name);
                
            }
        }
        return null;
    }
    
    //TODO fix this method it crashes the app
    private boolean checkNeighboringChars(String name, String verseText)
    {
        int index = verseText.indexOf(name);
    
        while (index >= 0)
        {
        
            String text = verseText;
            String regex = "\\b" + name + "\\b";
            text = text.replaceAll(regex, "");
            Character c = text.charAt(index);
            if (Character.isLetter(c))
            {
                return false;
            }
            index = verseText.indexOf(name, index + 1);
        }
        return true;
    }
    
    private String getVerseWithBlank(Verse verse, String name)
    {
        
        String verseWBlank = verse.getText();
        
        verseWBlank = Pattern.compile(Pattern.quote(name)).matcher(verse.getText()).replaceAll("_____");
        
        return verseWBlank;
    }
    
    private void generateOtherAns(String nameOrWhere, Question question)
    {
        Set<String> added = new TreeSet<>();
        if (nameOrWhere.equals("name"))
        {
            if (!question.getOptionA().isCorrect())
            {
                String name = randName();
                if (!added.contains(name))
                {
                    question.getOptionA().setText(name);
                    added.add(name);
                }
            }
            if (!question.getOptionB().isCorrect())
            {
                String name = randName();
                if (!added.contains(name))
                {
                    question.getOptionB().setText(name);
                    added.add(name);
                }
            }
            if (!question.getOptionC().isCorrect())
            {
                String name = randName();
                if (!added.contains(name))
                {
                    question.getOptionC().setText(name);
                    added.add(name);
                }
            }
            if (!question.getOptionD().isCorrect())
            {
                String name = randName();
                if (!added.contains(name))
                {
                    question.getOptionD().setText(name);
                    added.add(name);
                }
            }
        } else
        {
            if (!question.getOptionA().isCorrect())
            {
                String chapVerse = randVerse().bookChapVerse();
                if (!added.contains(chapVerse))
                {
                    question.getOptionA().setText(chapVerse);
                    added.add(chapVerse);
                }
            }
            if (!question.getOptionB().isCorrect())
            {
                String chapVerse = randVerse().bookChapVerse();
                if (!added.contains(chapVerse))
                {
                    question.getOptionB().setText(chapVerse);
                    added.add(chapVerse);
                }
                
            }
            if (!question.getOptionC().isCorrect())
            {
                String chapVerse = randVerse().bookChapVerse();
                if (!added.contains(chapVerse))
                {
                    question.getOptionC().setText(chapVerse);
                    added.add(chapVerse);
                }
                
            }
            if (!question.getOptionD().isCorrect())
            {
                String chapVerse = randVerse().bookChapVerse();
                if (!added.contains(chapVerse))
                {
                    question.getOptionD().setText(chapVerse);
                    added.add(chapVerse);
                }
                
            }
        }
    }
    
    private String bookAndVerseToString(BookOfMormon.Book book)
    {
        return BookOfMormon.getInstance().getBooksStringMap().get(book);
    }
    
    private Verse randVerse()
    {
        //System.out.println("Starting randVerse");
        Verse verse = itCameToPassVerses.get(rand.getRandInt(0, itCameToPassVerses.size() - 1));
        //System.out.println(verse.getText());
        return verse;
        
    }
    
    private String randName()
    {
        return names.get(rand.getRandInt(0, names.size() - 1));
    }
    
    //TODO write a method to find the write answer so int 1-4
    
    private Option determineCorrect(Question question) throws Exception
    {
        int correct = rand.getRandInt(1, 4);
        
        if (correct == 1)
        {
            question.getOptionA().setCorrect(true);
            return question.getOptionA();
        } else if (correct == 2)
        {
            question.getOptionB().setCorrect(true);
            return question.getOptionB();
        } else if (correct == 3)
        {
            question.getOptionC().setCorrect(true);
            return question.getOptionC();
            
        } else if (correct == 4)
        {
            question.getOptionD().setCorrect(true);
            return question.getOptionD();
        } else throw new Exception("Error no correct answer");
    }
    
    
    //Came to pass verses
    private void retrieveCTPVerses()
    {
        Map<BookOfMormon.Book, BoMBook> bookMap = mBookOfMormon.getBookMap();
        
        for (Map.Entry<BookOfMormon.Book, BoMBook> entry : bookMap.entrySet())
        {
            BoMBook bomBook = entry.getValue();
            
            Set<Verse> verses = bomBook.getVerses();
            
            for (Verse verse : verses)
            {
                if (verse.getText().toLowerCase().contains("came to pass"))
                {
                    itCameToPassVerses.add(verse);
                    //System.out.println(verse.getText());
                }
            }
            
        }
    }
    
    
}
