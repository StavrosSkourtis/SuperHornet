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
import net.skourti.superhornet.utils.objparser.ObjLoader;

/**
 *
 * @author Stavros
 */
public class Jet {

    public Jet(Screen screen) {
        ObjLoader loader = new ObjLoader();
        
        //screen.addEntity(loader.load("res/farm/house.obj"));
        //screen.addEntity(loader.load("res/jets/fa22raptor/FA-22_Raptor.obj"));
        //screen.addEntity(loader.load("res/jets/cube.obj"));
        //screen.addEntity(loader.load("res/Knife/sword.obj"));
        
        
        Model hornet = loader.load("res/jets/fa18superhornet/FA-18E_SuperHornet.obj");
        hornet.rotate(-1f, new Vec3(1,0,0));
        hornet.translate(new Vec3(0 , 0 , -20));
        screen.addEntity(hornet);
        
    }

}
