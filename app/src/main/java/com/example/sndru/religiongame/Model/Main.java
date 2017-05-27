package com.example.sndru.religiongame.Model;

import com.example.sndru.religiongame.Model.Questions.Option;
import com.example.sndru.religiongame.Model.Questions.Question;
import com.example.sndru.religiongame.Model.Questions.QuestionGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Samuel Nuttall
 */
public class Main
{
    private static BookOfMormon mBookOfMormon = BookOfMormon.getInstance();
    private static QuestionGenerator mQuestionGenerator;
    public static Question mQuestion;
    
    public static Question getQuestion()
    {
        return mQuestion;
    }
    
    //BoMFiles/BoMMasterFile.txt names.txt
    public static void main(String[] args)
    {
        
        File bom = new File(args[0]);
        File names = new File(args[1]);
        BOMScanner verses = new BOMScanner();
        NameScanner scanner = new NameScanner();
        try
        {
           // verses.getVerses(bom);
            //scanner.getNames(names);
            mQuestionGenerator = new QuestionGenerator();
            
            askAQuestion();
        }
        catch (Exception ex)
        {
            //System.out.println("File not Found");
            ex.printStackTrace();
        }
        
        //System.out.println("Starting to Print BoM");
       // printBOM();
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
    
    private static char getInput() throws InvalidInput
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if (input.length() != 1)
        {
            throw new InvalidInput();
        }
        
        Character answerInput = input.charAt(0);
       // char guess = Character.toLowerCase(guessInput);
        if (!answerInput.equals('a') && !answerInput.equals('b')&& !answerInput.equals('c') && !answerInput.equals('d') )
        {
            throw new InvalidInput();
        }
        
        
        return answerInput;
    }
    
    public static class InvalidInput extends Exception
    {
    }
}
