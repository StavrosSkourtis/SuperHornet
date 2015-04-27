/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.screen;

import com.hackoeur.jglm.Vec3;
import com.hackoeur.jglm.Vec4;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.input.Keyboard;
import net.skourti.superhornet.models.Ground;
import net.skourti.superhornet.models.Jet;
import net.skourti.superhornet.models.Skybox;
import net.skourti.superhornet.models.Terrain;
import net.skourti.superhornet.utils.FpsCounter;
import net.skourti.superhornet.utils.objparser.ObjLoader;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Stavros
 */
public class StartScreen extends Screen {

    Jet jet;

    @Override
    public void create() {

        new Skybox(this);
        new Ground(this);

//        camera.lookAt(new Vec3(0, 100, 15), new Vec3(0, 100, -20), new Vec3(0, 1, 0));
//        camera.mouseControl = true;
        jet = new Jet(this);
        
        
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void logic() {
        jetControl();
        positionCameraBehindJet();
    }

    Vec3 camPo = new Vec3(0, -20, 8);

    public void positionCameraBehindJet() {
        Vec3 pos = jet.getModelMatrix().multiply(camPo);
        Vec3 look = jet.getModelMatrix().multiply(jet.frontLookPoint);

//        System.out.println("Pos x:"+pos.x+" y:"+pos.y+" z:"+pos.z+" Target x:"+look.x+" y:"+look.y+" z:"+look.z);
//        System.out.println(jet.getModelMatrix());
        camera.lookAt(pos, look, new Vec3(0, 1, 0));

    }

    public void jetControl() {

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            jet.engineThrotle(500000);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            jet.forceFront(-30000, 0, 0);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            jet.forceFront(30000, 0, 0);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            jet.engineThrotle(-50000);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            jet.forceFront(0, 0, 90000);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
            jet.forceFront(0, 0, -90000);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            jet.forceRightSide(0, 0, 30000);
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            jet.forceLeftSide(0, 0, 30000);
        }
        if( Keyboard.isKeyDown(GLFW.GLFW_KEY_P)){
            jet.enginePower();
        }
       
        jet.engineThrust();
    }

}
