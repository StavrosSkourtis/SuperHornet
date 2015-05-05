/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.input;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 *
 * @author Stavros
 */
public class Mouse extends GLFWScrollCallback {

    private static DoubleBuffer xPos, yPos;
    private static long window;
    private static double yoffset;

    public Mouse(long w) {
        window = w;
        xPos = BufferUtils.createDoubleBuffer(1);
        yPos = BufferUtils.createDoubleBuffer(1);
    }

    public static double getMouseX() {

        glfwGetCursorPos(window, xPos, yPos);

        return xPos.get(0);
    }

    public static double getMouseY() {
        glfwGetCursorPos(window, xPos, yPos);

        return yPos.get(0);
    }

    @Override
    public void invoke(long arg0, double arg1, double arg2) {
        yoffset += arg2;
    }
    
    public static float getMouseScroll(){
        return (float)yoffset;
    }


    
    

}
