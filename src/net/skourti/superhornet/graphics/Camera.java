/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec3;



/**
 *
 * @author Stavros
 */
public class Camera {
    public Mat4 combinedMatrix;
    public Mat4 viewMatrix;
    public Mat4 projectionMatrix;
    
    
    public Camera(float viewportWidth,float viewportHeight, float fov,float near,float far){
        projectionMatrix = Matrices.perspective(fov, viewportWidth/viewportHeight, near, far);
        viewMatrix = Matrices.lookAt(new Vec3(0,0,-10), new Vec3(0, 0, 0), new Vec3(0,1,0));
    }
    
    public void lookAt(Vec3 position , Vec3 target , Vec3 up){
        viewMatrix = Matrices.lookAt(position, target, up);
    }
    
    public void update(){
        combinedMatrix = projectionMatrix.multiply(viewMatrix);
    }
    
    
    
}
