package com.example.sndru.religiongame.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sndru.religiongame.Model.BoMBook;
import com.example.sndru.religiongame.Model.BookOfMormon;
import com.example.sndru.religiongame.Model.Main;
import com.example.sndru.religiongame.Model.QuestionCreator;
import com.example.sndru.religiongame.Model.Questions.Option;
import com.example.sndru.religiongame.Model.Questions.Question;
import com.example.sndru.religiongame.Model.Verse;
import com.example.sndru.religiongame.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "ReligionGameActivity";
    private static final String KEY_INDEX = "index";
    
    private RadioGroup mRadioGroup;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;
    
    private Button mAnswerButton;
    private Button mNextButton;
    
    private TextView mQuestionTextView;
    private String[] args = {"BoMFiles/BoMMasterFile.txt", "names.txt"};
    private QuestionCreator questionCreator;
    private Question curQuestion;
    
    public MainActivity()
    {
        
        
    }
    
    //TODO these are from the BOMScanner class do something with them later
    private BookOfMormon mBookOfMormon = BookOfMormon.getInstance();
    
    private File getFilesFromAssets(String fileName) throws FileNotFoundException, IOException
    {
        AssetManager am = getAssets();
        InputStream inputStream = am.open(fileName);
        File file = createFileFromInputStream(inputStream, fileName);
        
        return file;
        
    }
    
    private File createFileFromInputStream(InputStream inputStream, String fileName)
    {
        try
        {
            File f = new File("Book_of_Mormon");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;
            
            while ((length = inputStream.read(buffer)) > 0)
            {
                outputStream.write(buffer, 0, length);
            }
            
            outputStream.close();
            inputStream.close();
            
            return f;
        }
        catch (IOException e)
        {
            //Logging exception
        }
        
        return null;
    }
    
    private Question getQuestion()
    {
        // main.main(args);
        // curQuestion = main.getQuestion();
        curQuestion = questionCreator.createQuestion();
        //Log.d("Question", curQuestion.toString());
        return curQuestion;
    }
    
    private void startNewQuestion()
    {
        
        curQuestion = getQuestion();
        mQuestionTextView.setText(curQuestion.toString());
        mQuestionTextView.setMovementMethod(new ScrollingMovementMethod());
        //AssignButtonValues
        assignOptionButtons(curQuestion);
    }
    
    private void assignOptionButtons(Question question)
    {
        optionA.setText(question.getOptionA().getText());
        optionB.setText(question.getOptionB().getText());
        optionC.setText(question.getOptionC().getText());
        optionD.setText(question.getOptionD().getText());
    }
    
    private void checkAnswer(RadioButton button)
    {
        Option selected = getOptFromButton(button);
        
        if (selected != null)
        {
            if (selected.isCorrect())
            {
                Toast.makeText(MainActivity.this, "Correct!!!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(MainActivity.this, "Wrong!!!", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    private Option getOptFromButton(RadioButton button)
    {
        if (button.equals(optionA))
        {
            return curQuestion.getOptionA();
        } else if (button.equals(optionB))
        {
            return curQuestion.getOptionB();
        } else if (button.equals(optionC))
        {
            return curQuestion.getOptionC();
        } else if (button.equals(optionD))
        {
            return curQuestion.getOptionD();
        } else
        {
            Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        optionA = (RadioButton) findViewById(R.id.optionA);
        optionB = (RadioButton) findViewById(R.id.optionB);
        optionC = (RadioButton) findViewById(R.id.optionC);
        optionD = (RadioButton) findViewById(R.id.optionD);
        mRadioGroup = (RadioGroup) findViewById(R.id.answer_radio_group);
        mAnswerButton = (Button) findViewById(R.id.answer_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        
        if (savedInstanceState == null)
        {
            try
            {
                // File file = getFilesFromAssets("BOM.txt");
                
                
                AssetManager am = getAssets();
                BufferedReader bomReader = new BufferedReader(
                        new InputStreamReader(getAssets().open("BOM.txt")));
    
                BufferedReader nameReader = new BufferedReader(
                        new InputStreamReader(getAssets().open("names.txt")));
                
                
                //InputStream bom = am.open("BOM.txt");
                //InputStream names = am.open("names.txt");
               // InputStream[] iStreams = {bom, names};
                //questionCreator = new QuestionCreator(iStreams);
                questionCreator = new QuestionCreator(bomReader, nameReader);
                startNewQuestion();
                
                
                //Set Options
                
                setAnswerListener();
                setNextButtonList();
            }
            catch (Exception ex)
            {
                Log.e("ERROR, ", ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        
    }
    
    
    private void setAnswerListener()
    {
        mAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mRadioGroup.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(MainActivity.this, "Please choose one of the options", Toast.LENGTH_SHORT).show();
                    // no radio buttons are checked
                } else
                {
                    
                    
                    RadioButton checkedRadioButton = (RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId());
                    checkAnswer(checkedRadioButton);
                    mRadioGroup.clearCheck();
                }
            }
        });
    }
    
    private void setNextButtonList()
    {
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startNewQuestion();
            }
        });
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("Question", mQuestionTextView.getText().toString());
    }
}
