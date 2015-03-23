/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.hackoeur.jglm.Vec3;
import main.java.com.hackoeur.jglm.Vec2;

/**
 *
 * @author Stavros
 */
public class Face {
    public Vec3 vertexIndices;
    public Vec3 textureIndices;
    public Vec3 normalIndices;
    
    public Face(){
    }
    
    public Face(Vec3 vertexIndices ,Vec3 textureIndices ,Vec3 normalIndices){
        this.vertexIndices = vertexIndices;
        this.textureIndices =textureIndices;
        this.normalIndices = normalIndices;
    }
}
