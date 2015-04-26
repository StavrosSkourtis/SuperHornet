/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Mesh;
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
        final float d =10000;
        String box = "alpine";
        
        Model skybox = new Model() {
            @Override
            public void create() {
                float[] vFront = {
                    -50.0f*d, -50.0f*d, -50*d, // bottom left
                    -50.0f*d, 50.0f*d, -50*d, //  top left
                    50.0f*d, 50.0f*d, -50*d, //  top right
                    50.0f*d, -50.0f*d, -50*d, // bottom right
                };

                float[] uvFront = {
                    0, 1,
                    0, 0,
                    1, 0,
                    1, 1
                };
                
                float[] vBack = {
                    -50.0f*d, -50.0f*d, 50*d, // bottom left
                    -50.0f*d, 50.0f*d, 50*d, //  top left
                    50.0f*d, 50.0f*d, 50*d, //  top right
                    50.0f*d, -50.0f*d, 50*d, // bottom right
                };

                float[] uvBack = {
                    1, 1,
                    1, 0,
                    0, 0,
                    0, 1
                };
                
                 float[] vRight = {
                    50.0f*d, -50.0f*d, -50.0f*d, // vertex 0
                    50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    50.0f*d, -50.0f*d, 50f*d, // vertex 3
                };

                float[] uvRight = {
                    0, 1,
                    0, 0,
                    1, 0,
                    1, 1
                };
                
                float[] vLeft  = {
                    -50.0f*d, -50.0f*d, -50.0f*d, // vertex 0
                    -50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    -50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    -50.0f*d, -50.0f*d, 50f*d, // vertex 3
                };

                float[] uvLeft = {
                    1, 1,
                    1, 0,
                    0, 0,
                    0, 1
                };
                
                float[] vTop = {
                    -50.0f*d, 50.0f*d, -50.0f*d, // vertex 0
                    50.0f*d, 50.0f*d, -50.0f*d, // vertex 1
                    50.0f*d, 50.0f*d, 50f*d, // vertex 2
                    -50.0f*d, 50.0f*d, 50f*d, // vertex 3
                };

                float[] uvTop = {
                    0, 1,
                    1, 1,
                    1, 0,
                    0, 0};
                
                int[] indeces = {
                    0, 1, 2, 2, 3, 0,};
                
                Mesh front = new Mesh();
                Mesh left = new Mesh();
                Mesh right = new Mesh();
                Mesh top = new Mesh();
                Mesh back = new Mesh();
                
                front.create(vFront,null , null, uvFront,indeces);
                front.setTexture(new Texture("res/sky/"+box+"_front.jpg"));
                
                top.create(vTop, null, null, uvTop, indeces);
                top.setTexture(new Texture("res/sky/"+box+"_top.jpg"));
                
                back.create(vBack, null, null, uvBack, indeces);
                back.setTexture(new Texture("res/sky/"+box+"_back.jpg"));
                
                left.create(vLeft, null, null, uvLeft, indeces);
                left.setTexture(new Texture("res/sky/"+box+"_left.jpg"));
                
                right.create(vRight, null, null, uvRight, indeces);
                right.setTexture(new Texture("res/sky/"+box+"_right.jpg"));
                
                
                
                addMesh(top);
                addMesh(left);
                addMesh(right);
                addMesh(back);
                addMesh(front);
                drawMode = SKYBOX;
                useShader(ShaderProgram.textureShader);
            }
        };

  
        screen.addEntity(skybox);
    }

}
