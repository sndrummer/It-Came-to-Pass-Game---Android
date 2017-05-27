package com.example.sndru.religiongame.Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Samuel Nuttall
 */
public class NameScanner
{
    
    public void getNames(InputStream names) throws FileNotFoundException
    {
       
        Scanner scanner = new Scanner(new BufferedInputStream(names));
        
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (line.contains(","))
            {
                //System.out.println("Entering else case for: " + line);
                BookOfMormon.getPersonNames().add(getName(line));
            }
        }
        
        scanner.close();
    }
    
    public void getNamesALT(BufferedReader NamesReader) throws IOException
    {
        String line;
        while ((line = NamesReader.readLine()) != null)
        {
            if (line.contains(","))
            {
                BookOfMormon.getPersonNames().add(getName(line));
            }
        }
        NamesReader.close();
    }
    
    private String getName(String line)
    {
        String[] arr = line.split(",");
        return arr[0];
    }
}
