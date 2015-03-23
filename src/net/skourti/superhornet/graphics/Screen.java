/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import java.util.LinkedList;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Stavros
 */
abstract public class Screen implements Disposable {

    public static final int COLOR_SHADER = 0;
    public static final int TEXTURE_SHADER = 1;

    private final LinkedList<Entity> entities;
    
    public final Camera camera;

    public Screen() {
        /*
         Set the color to black
         */
        glClearColor(0, 0, 0, 1);
        /*
         initialize the entities list
         */
        entities = new LinkedList<>();

        camera = new Camera(1280, 720, 67f, 0.1f, 1000f);

        //call the create method
        create();
    }

    public void render(long window , float delta) {

        /*
         Set clear color and clear the screen;
         */
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        /*
         Update the camera
         */
        camera.update();

        for (int i = 0; i < entities.size(); i++) {

            entities.get(i).render(camera);

        }
        
        
        /*
         glfw stuff
         */
        glfwPollEvents();
        glfwSwapBuffers(window); // swap the color buffers

    }

    public void setColor(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Disposes the screen and all its entities
     *
     */
    @Override
    public void dispose() {

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).dispose();
        }

    }

    abstract public void create();

    abstract public void resume();

    abstract public void pause();

    abstract public void logic();

}
