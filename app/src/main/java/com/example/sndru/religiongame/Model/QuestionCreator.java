package com.example.sndru.religiongame.Model;

import android.util.Log;

import com.example.sndru.religiongame.Model.Questions.Option;
import com.example.sndru.religiongame.Model.Questions.Question;
import com.example.sndru.religiongame.Model.Questions.QuestionGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Samuel Nuttall
 */
public class QuestionCreator
{
    
    private static BookOfMormon mBookOfMormon = BookOfMormon.getInstance();
    private static QuestionGenerator mQuestionGenerator;
    
    //BoMFiles/BoMMasterFile.txt names.txt
    
    public QuestionCreator(InputStream[] bomStrings)
    {
       InputStream bom = bomStrings[0];
        InputStream names = bomStrings[1];
        BOMScanner verses = new BOMScanner();
        NameScanner scanner = new NameScanner();
        try
        {
            verses.getVerses(bom);
            scanner.getNames(names);
            mQuestionGenerator = new QuestionGenerator();
            System.out.println(verses.toString());
            
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            Log.e("QuestionCreator", ex.getMessage());
            System.out.println("File not Found");
        }
    }
    
    public QuestionCreator(BufferedReader BOMReader, BufferedReader nameReader)
    {
        BOMScanner verses = new BOMScanner();
        NameScanner scanner = new NameScanner();
        try
        {
            verses.getVersesALT(BOMReader);
            scanner.getNamesALT(nameReader);
            mQuestionGenerator = new QuestionGenerator();
            System.out.println(verses.toString());
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Log.e("QuestionCreator", ex.getMessage());
            //System.out.println("File not Found");
        }
    }
    
    public Question createQuestion()
    {
        return mQuestionGenerator.createQuestion();
    }
    
    
    private static void printBOM()
    {
        Map<BookOfMormon.Book, BoMBook> bookMap = mBookOfMormon.getBookMap();
        
        for (Map.Entry<BookOfMormon.Book, BoMBook> entry: bookMap.entrySet())
        {
            BoMBook bomBook = entry.getValue();
            
            Set<Verse> verses = bomBook.getVerses();
            
            for (Verse verse: verses)
            {
                System.out.println(verse.toString());
            }
            
        }
    }
    
    private static void askAQuestion()
    {
        Question question = mQuestionGenerator.createQuestion();
        
        System.out.println(question.toString());
        
        System.out.println("\nPlease enter a,b,c, or d: ");
        
        boolean userAnswered = false;
        while (userAnswered == false)
        {
            try
            {
                Option option = chooseOption(question, getInput());
                
                if (option.isCorrect() == true)
                {
                    System.out.println("Correct!!!!");
                    userAnswered = true;
                }
                else
                {
                    System.out.println("Wrong!!!!");
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        askAQuestion();
    }
    
    private static Option chooseOption(Question question, Character ans)
    {
        if (ans.equals('a'))
        {
            return question.getOptionA();
        }
        else if (ans.equals('b'))
        {
            return question.getOptionB();
        }
        else if (ans.equals('c'))
        {
            return question.getOptionC();
        }
        else if (ans.equals('d'))
        {
            return question.getOptionD();
        }
        return null;
    }
    
    private static char getInput() throws Main.InvalidInput
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if (input.length() != 1)
        {
            throw new Main.InvalidInput();
        }
        
        Character answerInput = input.charAt(0);
        // char guess = Character.toLowerCase(guessInput);
        if (!answerInput.equals('a') && !answerInput.equals('b')&& !answerInput.equals('c') && !answerInput.equals('d') )
        {
            throw new Main.InvalidInput();
        }
        
        
        return answerInput;
    }
    
    public static class InvalidInput extends Exception
    {
    }
}
