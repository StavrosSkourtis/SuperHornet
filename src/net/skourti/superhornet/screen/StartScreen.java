/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.screen;

import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.graphics.Entity;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.input.Keyboard;
import net.skourti.superhornet.models.Skybox;
import net.skourti.superhornet.utils.FpsCounter;
import org.lwjgl.glfw.GLFW;
/**
 *
 * @author Stavros
 */
public class StartScreen extends Screen {

    @Override
    public void create() {

        Entity triangle = new Entity() {
            @Override
            public void create() {
                float[] vetices = {
                    1.0f, -1.0f, -10.0f, // vertex
                    1.0f, 1.0f, -10.0f,  // vertex 1
                    3.0f, 1.0f, -10.0f,   // vertex 2
                    3.0f, -1.0f, -10.0f,  // vertex 3
                };
                
                float [] color = {
                    0, 1 ,0,1,
                    1, 1 ,1,1,
                    1, 1 ,0,1,
                    0, 0 ,1,1,
                };

                float[] uv = {
                    0, 0,
                    0, 1,
                    1, 1,
                    1, 0,
                };

                byte[] indeces = {
                    0, 1, 2, 2, 3, 0,
                };
                
                
                mesh.createTexturedMesh(vetices, indeces, uv);
                shader = ShaderProgram.basic;
                texture = new Texture("res/pic1.png");

                
                /*
                mesh.createColorMesh(vetices, indeces, color);
                shader = ShaderProgram.colorShader;
                */
            }
        };
        addEntity(triangle); 
        
        camera.lookAt(new Vec3(0, 0, 4), new Vec3(0, 0, 0), new Vec3(0, 1, 0));
        camera.mouseControl = true;
        
        new Skybox(this);
        
        
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void logic() {
        
    }

}
