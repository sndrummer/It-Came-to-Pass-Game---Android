package com.example.sndru.religiongame.Model;

import java.util.Random;


public class RandomMinMax
{
    public int getRandInt(Integer min, Integer max)
    {
        if ((max + 1 - min) <= 0)
        {
            System.out.println("Max is " + max + ", min is " + min);
        }
        Random random = new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        return randomNumber;
    }
}
