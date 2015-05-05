/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils.objparser;

import com.hackoeur.jglm.Vec3;
import main.java.com.hackoeur.jglm.Vec2;

/**
 *
 * @author Stavros
 */
public class Face {
    public Vec3 vertex1;
    public Vec3 vertex2;
    public Vec3 vertex3;
    
    public Face(){
    }
    
    public Face(Vec3 v1 ,Vec3 v2 ,Vec3 v3){
        this.vertex1 = v1;
        this.vertex2 =v2;
        this.vertex3 = v3;
    }
}
