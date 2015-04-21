/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import net.skourti.superhornet.graphics.Entity;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.utils.objparser.ObjLoader;

/**
 *
 * @author Stavros
 */
public class Jet extends Entity {

    @Override
    public void create() {
        ObjLoader loader = new ObjLoader();
        
        mesh = loader.load("res/jets/fa18superhornet/FA-18E_SuperHornet.obj");
        texture = new Texture("res/jets/fa18superhornet/FA-18E_SuperHornet_P01.png");
        
        
        //mesh = loader.load("E:/Copy/Code/Personal/SuperHornet/res/jets/fa22raptor/FA-22_Raptor.obj");
        //texture = new Texture("E:/Copy/Code/Personal/SuperHornet/res/jets/fa22raptor/FA-22_Raptor_P01.png");
        
          
        //mesh = loader.load("E:/Copy/Code/Personal/SuperHornet/res/cube.obj");
        //texture = new Texture("E:/Copy/Code/Personal/SuperHornet/res/jets/sphere/sphere.jpg");
        
        shader = ShaderProgram.textureShader;
    }

}
