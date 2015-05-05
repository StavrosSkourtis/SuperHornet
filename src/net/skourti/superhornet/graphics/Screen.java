/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.Transform;
import java.util.LinkedList;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
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
    

    private final LinkedList<Model> entities;
    RigidBody body ;
    private DynamicsWorld physicsWorld;
    public final Camera camera;

    public Screen() {
        /*
         Set the color to black
         */
        glClearColor(0, 0, 0, 1);
        BroadphaseInterface broadphase = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();
        physicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        physicsWorld.setGravity(new Vector3f(0, -10 /* m/s2 */, 0));
        
        /*
         initialize the entities list
         */
        entities = new LinkedList<>();

        camera = new Camera(1280, 720, 67f, 0.1f, 1000000000000f);

        //call the create method
        create();
    }

    public void render(long window , float delta) {
        physicsWorld.stepSimulation(1.0f / 60.0f);    
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

    public void addEntity(Model entity) {
        entities.add(entity);
        if(entity.body!=null)
           physicsWorld.addRigidBody(entity.body);
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
