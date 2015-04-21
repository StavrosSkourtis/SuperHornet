/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils.objparser;

import com.hackoeur.jglm.Vec3;
import java.util.LinkedList;
import main.java.com.hackoeur.jglm.Vec2;

/**
 *
 * @author Stavros
 */
public class ObjDataGroup {
    String material;
    LinkedList<Face> faces;
    
    public ObjDataGroup(){
        faces = new LinkedList<>();
    }
}
