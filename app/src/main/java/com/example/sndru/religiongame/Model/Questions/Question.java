package com.example.sndru.religiongame.Model.Questions;

/**
 * @author Samuel Nuttall
 */
public class Question
{
    
    //TODO fix the question type and then you should be good
    private String questionType; //"name" or "where"
    private String verse;
    private Option optionA = new Option();
    private Option optionB = new Option();
    private Option optionC = new Option();
    private Option optionD = new Option();
    
    
    public Option getOptionA()
    {
        return optionA;
    }
    
    public void setOptionA(Option optionA)
    {
        this.optionA = optionA;
    }
    
    public Option getOptionB()
    {
        return optionB;
    }
    
    public void setOptionB(Option optionB)
    {
        this.optionB = optionB;
    }
    
    public Option getOptionC()
    {
        return optionC;
    }
    
    public void setOptionC(Option optionC)
    {
        this.optionC = optionC;
    }
    
    public Option getOptionD()
    {
        return optionD;
    }
    
    public void setOptionD(Option optionD)
    {
        this.optionD = optionD;
    }
    
    
    public String getQuestionType()
    {
        return questionType;
    }
    
    public void setQuestionType(int zeroOrOne)
    {
        if (zeroOrOne == 0)
        {
            questionType = "where";
            
        }
        else questionType = "name";
    }
    
    public String getVerse()
    {
        return verse;
    }
    
    public void setVerse(String verse)
    {
        this.verse = verse;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
    
        if (questionType == "where" && verse != null)
        {
            sb.append("Where in the Book of Mormon is the following verse located?\n\n");
            sb.append(verse);
        }
        else if (questionType == "name")
        {
            sb.append("Which of the following names fits in the blank(s)?\n\n");
        }
        
        sb.append(getVerse() + "\n");
       /* sb.append(
                "a) " + optionA.getText() + "\n" +
                "b) " + optionB.getText() + "\n" +
                "c) " + optionC.getText() + "\n" +
                "d) " + optionD.getText() + "\n"
                 );*/
        
        return sb.toString();
    }
}
