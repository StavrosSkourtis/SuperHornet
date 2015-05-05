/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 *
 * @author Stavros
 */
public class Keyboard extends GLFWKeyCallback {

    private static long window;
    private static boolean[] down = new boolean[65536];
    private static boolean[] up = new boolean[65536];
    
    public Keyboard(long wind) {
        window = wind;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        down[key] = action !=GLFW.GLFW_RELEASE;
    }
    
    public static boolean isKeyDown(int key) {
        return down[key];
    }
    

}
