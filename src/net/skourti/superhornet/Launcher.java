/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet;

import net.skourti.superhornet.screen.StartScreen;
import net.skourti.superhornet.graphics.Window;

/**
 *
 * @author Stavros
 */
public class Launcher{

    public static void main(String arg[]) {
        Window window = new Window();
        
        window.title = "Super Hornet";
        window.version = 1;
        window.version_string = "v0.1 pre-alpha";
        window.width = 1280;
        window.height = 720;
        window.fullscreen = false;
        
        
        window.addScreen("Start Screen",new StartScreen());
        
        window.create();
    }

}
