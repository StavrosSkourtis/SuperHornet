/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils;

import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Stavros
 */
public class FpsCounter {
    private static int fps;
    
    private static double lastSecond;
    private static int counter;
    
    public static void count(){
        double currentSecond = GLFW.glfwGetTime();
        
        if(currentSecond-lastSecond<1)
            counter++;
        else{
            fps = counter;
            lastSecond = currentSecond;
            counter = 0;
        }
        
    }    
    
    public static int getFps(){
        return fps;
    }
    
}
