/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.screen;

import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.input.Keyboard;
import net.skourti.superhornet.models.Jet;
import net.skourti.superhornet.models.Skybox;
import net.skourti.superhornet.utils.FpsCounter;
import net.skourti.superhornet.utils.objparser.ObjLoader;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Stavros
 */
public class StartScreen extends Screen {

    @Override
    public void create() {

        new Skybox(this);

        camera.lookAt(new Vec3(0, 0, 4), new Vec3(0, 0, 0), new Vec3(0, 1, 0));
        camera.mouseControl = true;

        Jet jet = new Jet(this);
 
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
