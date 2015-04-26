/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet;

import net.skourti.superhornet.screen.StartScreen;
import net.skourti.superhornet.graphics.Window;
import net.skourti.superhornet.screen.TestScreen;

/**
 *
 * @author Stavros
 */
public class Launcher{

    public static void main(String arg[]) {
        Window.title = "Super Hornet";
        Window.version = 1;
        Window.version_string = "v0.1 pre-alpha";
        Window.width = 1280;
        Window.height = 720;
        Window.fullscreen = false;
        Window window = new Window();
        
        window.addScreen("Start Screen",new StartScreen());
        //window.addScreen("Test Screen",new TestScreen());
        
        window.create();
    }

}
