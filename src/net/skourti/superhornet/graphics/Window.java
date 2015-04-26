/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import net.skourti.superhornet.input.Keyboard;
import net.skourti.superhornet.input.Mouse;
import net.skourti.superhornet.utils.FpsCounter;
import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 *
 * @author Stavros
 */
public class Window implements Disposable {

    // The window title
    public static String title = "Graphics Project";
    // The version of this application as a string
    public static String version_string = "0.1 development";
    // The version of this app as a number
    public static int version = 0;
    // The height of the window in pixels
    public static int height = 720;
    // The width of the window in pixels
    public static int width = 1280;
    // Boolean variable that show is the window will be full screen
    public static boolean fullscreen = false;

    public static boolean vsync = true;

    // variable that holds the window id , used for glfw
    private long window;
    private GLFWErrorCallback errorCallback;
    private Keyboard keyCallback;
    private Mouse mouse;

    private boolean exitLoop = false;

    private final HashMap<String, Screen> screens;
    private Screen currentScreen;

    /**
     * Constructor, creates the window and initializes OpenGl
     */
    public Window() {
        screens = new HashMap<>();
        initGlfw();
        initGl();
    }
    
    public static float delta;
    /**
     * stars the OpenGl thread
     */
    public void create() {
        double previousFrame = glfwGetTime(),frameTime;
        while (!exitLoop && (glfwWindowShouldClose(window) == GL_FALSE)) {
            /*
                Delta time calculation
            */
            frameTime = glfwGetTime();
            delta = (float)(frameTime - previousFrame);
            previousFrame = frameTime;
            
            
            currentScreen.logic();
            currentScreen.render(window, delta);
            
            FpsCounter.count();
        }

        // Destory the window
        dispose();
    }
    
    
    private float getDeltaTime(){
        
        return 0;
    }
        

    /**
     * Disposes the screen and destroys the window
     */
    @Override
    public void dispose() {
        currentScreen.dispose();
        ShaderProgram.disposeAll();

        keyCallback.release();

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void initGl() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Enable depth test
        glEnable(GL_DEPTH_TEST);
        // Accept fragment if it closer to the camera than the former one
        glDepthFunc(GL_LESS);

        ShaderProgram.loadAll();
    }

    /**
     * initializes GLFW
     */
    private void initGlfw() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() != GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        mouse = new Mouse(window);
        glfwSetScrollCallback(window, mouse);
        keyCallback = new Keyboard(window);
        glfwSetKeyCallback(window, keyCallback);

        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (GLFWvidmode.width(vidmode) - width) / 2,
                (GLFWvidmode.height(vidmode) - height) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        
        // Set v-sync
        if (vsync) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }

        // Make the window visible
        glfwShowWindow(window);
        GLContext.createFromCurrent();
    }

    /**
     * Changes the screen
     *
     * @param screen
     */
    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
        if (currentScreen == null) {
            currentScreen = screen;
            currentScreen.resume();
        }
    }

    public void setScreen(String name) {
        currentScreen.pause();
        currentScreen = screens.get(name);
        currentScreen.resume();
    }

    /**
     * Stops the game loop
     */
    public void close() {
        exitLoop = true;
    }

}
