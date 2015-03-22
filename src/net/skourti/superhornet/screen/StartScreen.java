/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.screen;

import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.graphics.Entity;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Screen;
import static net.skourti.superhornet.graphics.Screen.COLOR_SHADER;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import org.lwjgl.opengl.GL13;

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
                    -1.0f, -1.0f, 1.0f, // vertex 0
                    -1.0f, 1.0f, 1.0f,  // vertex 1
                    1.0f, 1.0f, 1.0f,   // vertex 2
                    1.0f, -1.0f, 1.0f,  // vertex 3
                    
                    -1.0f, -1.0f, -1.0f,// vertex 4
                    -1.0f, 1.0f, -1.0f, // vertex 5
                    1.0f, 1.0f, -1.0f,  // vertex 6
                    1.0f, -1.0f, -1.0f, // vertex 7
                };

                float[] uv = {
                    0, 0,
                    0, 1,
                    1, 1,
                    1, 0,

                };

                byte[] indeces = {
                    0, 1, 2, 2, 3, 0,
                    /*
                    0, 4, 5, 5, 1, 0,
                    4, 5, 6, 6, 7, 4,
                    3, 7, 6, 6, 2, 3,
                    0, 3, 7, 7, 4, 0,
                    1, 5, 2, 2, 6, 1*/
                };

                mesh = new Mesh(vetices, indeces, uv);
                shader = ShaderProgram.basic;
                texture = new Texture("res/pic1.png");
                texture.glTexture = GL13.GL_TEXTURE0;
                shader.setUniform1i("tex", texture.glTexture-GL13.GL_TEXTURE0);
            }
        };

        camera.lookAt(new Vec3(0, 0, -3), new Vec3(0, 0, 0), new Vec3(0, 1, 0));

        addEntity(triangle);
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
