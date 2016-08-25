import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Text extends Actor{
    public Text(String text, int size, Color textColor, Color bgColor){
        GreenfootImage textImage 
                = new GreenfootImage(text, size, textColor, bgColor);
        setImage(textImage);
    }
}
