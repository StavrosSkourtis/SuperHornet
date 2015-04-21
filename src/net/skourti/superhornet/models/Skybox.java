/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.graphics.Entity;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import org.lwjgl.opengl.GL13;

/**
 *
 * @author Stavros
 */
public class Skybox {

    public Skybox(Screen screen ) {
        final float d =1;
        String box = "alpine";
        
        Entity front = new Entity() {
            @Override
            public void create() {
                float[] vetices = {
                    -50.0f*d, -50.0f*d, -50*d, // bottom left
                    -50.0f*d, 50.0f*d, -50*d, //  top left
                    50.0f*d, 50.0f*d, -50*d, //  top right
                    50.0f*d, -50.0f*d, -50*d, // bottom right
                };

                float[] uv = {
                    0, 1,
                    0, 0,
                    1, 0,
                    1, 1
                };

                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};

                mesh.create(vetices,null , null, uv,indeces);
                shader = ShaderProgram.textureShader;
                texture = new Texture("res/sky/"+box+"_front.jpg");
                drawMode = SKYBOX;
            }
        };

        Entity back = new Entity() {
            @Override
            public void create() {
                float[] vetices = {
                    -50.0f*d, -50.0f*d, 50*d, // bottom left
                    -50.0f*d, 50.0f*d, 50*d, //  top left
                    50.0f*d, 50.0f*d, 50*d, //  top right
                    50.0f*d, -50.0f*d, 50*d, // bottom right
                };

                float[] uv = {
                    1, 1,
                    1, 0,
                    0, 0,
                    0, 1
                };

                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};

                mesh.create(vetices,null , null, uv,indeces);
                shader = ShaderProgram.textureShader;
                texture = new Texture("res/sky/"+box+"_back.jpg");
                drawMode = SKYBOX;
            }
        };

        Entity right = new Entity() {
            @Override
            public void create() {
                float[] vetices = {
                    50.0f*d, -50.0f*d, -50.0f*d, // vertex 0
                    50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    50.0f*d, -50.0f*d, 50f*d, // vertex 3
                };

                float[] uv = {
                    0, 1,
                    0, 0,
                    1, 0,
                    1, 1
                };

                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};

                mesh.create(vetices,null , null, uv,indeces);
                shader = ShaderProgram.textureShader;
                texture = new Texture("res/sky/"+box+"_right.jpg");
                drawMode = SKYBOX;
            }
        };

        Entity left = new Entity() {

            public void create() {
                float[] vetices = {
                    -50.0f*d, -50.0f*d, -50.0f*d, // vertex 0
                    -50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    -50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    -50.0f*d, -50.0f*d, 50f*d, // vertex 3
                };

                float[] uv = {
                    1, 1,
                    1, 0,
                    0, 0,
                    0, 1
                };

                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};

                mesh.create(vetices,null , null, uv,indeces);
                shader = ShaderProgram.textureShader;
                texture = new Texture("res/sky/"+box+"_left.jpg");
                drawMode = SKYBOX;
            }

        };

        Entity top = new Entity() {
            @Override
            public void create() {

                float[] vetices = {
                    -50.0f*d, 50.0f*d, -50.0f*d, // vertex 0
                    50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    -50.0f*d, 50.0f*d, 50f*d, // vertex 3
                };

                float[] uv = {
                    0, 1,
                    1, 1,
                    1, 0,
                    0, 0};

                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};

                mesh.create(vetices,null , null, uv,indeces);
                shader = ShaderProgram.textureShader;
                texture = new Texture("res/sky/"+box+"_top.jpg");
                drawMode = SKYBOX;
            }
        };
        
        
        /*
            Add skyboxes
        */
        screen.addEntity(top);

        screen.addEntity(back);

        screen.addEntity(left);

        screen.addEntity(right);

        screen.addEntity(front);

    }

}
