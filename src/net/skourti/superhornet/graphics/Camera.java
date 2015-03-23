/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec3;
import net.skourti.superhornet.input.Keyboard;
import net.skourti.superhornet.input.Mouse;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Stavros
 */
public class Camera {
    
    public Mat4 combinedMatrix;
    public Mat4 viewMatrix;
    public Mat4 projectionMatrix;
    
    public Vec3 direction;
    public Vec3 position;
    public Vec3 target;
    public Vec3 up;
    
    public boolean mouseControl;
    public float fov;
    public float near;
    public float far;
    public float aspectRatio;
    
    public Camera(float viewportWidth, float viewportHeight, float fov, float near, float far) {
        
        this.fov = fov;      
        this.near = near;
        this.far = far;
        this.aspectRatio = viewportWidth/viewportHeight;
        
        projectionMatrix = Matrices.perspective(fov, aspectRatio, near, far);
        direction = new Vec3();
        position = new Vec3();
        target = new Vec3();
        up = new Vec3(0, 1, 0);
        
        mouseControl = false;
        
    }
    
    public void lookAt(Vec3 position, Vec3 target, Vec3 up) {
        this.position = position;
        this.target = target;
        this.up = up;
        viewMatrix = Matrices.lookAt(position, target, up);
    }
    
    public void update() {
        if (mouseControl) {
            mouseControl();
            lookAt(position, position.add(direction), up);
        }else{
            lookAt(position, target, up);
        }
        combinedMatrix = projectionMatrix.multiply(viewMatrix);
    }
    
    private float speed = 3.0f;
    private float mouseSpeed = 0.001f;
    private float horizondalAngle = 0.0f;
    private float verticalAngle = 3.14f;
    
    private void mouseControl() {
        float x = (float) Mouse.getMouseX();
        float y = (float) Mouse.getMouseY();
        
        horizondalAngle += mouseSpeed * Window.delta * (1280 / 2 - x);
        verticalAngle += mouseSpeed * Window.delta * (720 / 2 - y);
        
        direction = new Vec3(
                (float) (Math.cos(verticalAngle) * Math.sin(horizondalAngle)),
                (float) Math.sin(verticalAngle),
                (float) (Math.cos(verticalAngle) * Math.cos(horizondalAngle))
        );
        
        Vec3 right = new Vec3(
                (float) Math.sin(horizondalAngle - 3.14f / 2.0f),
                0,
                (float) Math.cos(horizondalAngle - 3.14f / 2.0f)
        );
        
        up = direction.cross(right);
        
        if (Keyboard.isKeyDownRepeat(GLFW.GLFW_KEY_UP)) {
            position = position.add((direction.multiply(Window.delta)).multiply(speed));
        }
        if (Keyboard.isKeyDownRepeat(GLFW.GLFW_KEY_DOWN)) {
            position = position.subtract((direction.multiply(Window.delta)).multiply(speed));
        }
        if (Keyboard.isKeyDownRepeat(GLFW.GLFW_KEY_LEFT)) {
            position = position.subtract((right.multiply(Window.delta)).multiply(speed));
        }
        if (Keyboard.isKeyDownRepeat(GLFW.GLFW_KEY_RIGHT)) {
            position = position.add((right.multiply(Window.delta)).multiply(speed));
        }
    }
    
}
