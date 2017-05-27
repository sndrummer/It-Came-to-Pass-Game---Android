package com.example.sndru.religiongame.Model.Questions;

/**
 * @author Samuel Nuttall
 */
public class Option
{
    private boolean correct = false;
    private String text = null;
    
    public boolean isCorrect()
    {
        return correct;
    }
    
    public void setCorrect(boolean correct)
    {
        this.correct = correct;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
}
