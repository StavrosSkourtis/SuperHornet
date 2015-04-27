/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import com.hackoeur.jglm.Vec3;
import java.util.LinkedList;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.utils.ListUtils;
import net.skourti.superhornet.utils.MatrixConverter;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Stavros
 */
public class Model implements Disposable {

    public static final int SKYBOX = 0;
    public static final int RELATIVE_MODEL = 1;
    
    public RigidBody body;
    public int drawMode;
    public LinkedList<Mesh> meshes;
    public Mat4 traslationMatrix;
    public Mat4 rotationMatrix;
    public Mat4 scaleMatrix;
    public float angle;
    private ShaderProgram shader;

    public Model() {
        drawMode = RELATIVE_MODEL;
        traslationMatrix = new Mat4(1.0f);
        rotationMatrix = new Mat4(1.0f);
        scaleMatrix = new Mat4(1.0f);
        meshes = new LinkedList<>();
        create();
        
    }

    public void create(){}

    public void rotate(float angle, Vec3 axis) {
        //this.angle += angle;
        rotationMatrix = rotationMatrix.multiply(Matrices.rotate(angle, axis));
    }

    public void setAngle(float angle, Vec3 axis) {
        //this.angle = angle;
        //rotationMatrix = Matrices.rotate(this.angle, axis);
    }

    public void translate(Vec3 position) {
        traslationMatrix = traslationMatrix.translate(position);
    }

    public void scale(float ammount) {
        scaleMatrix = scaleMatrix.scale(ammount);
    }
    
    public Mat4 getModel() {
        Mat4 comb = null;
        if(body !=null){
            float matrix [] = new float[16];
            
            body.getWorldTransform(new Transform()).getOpenGLMatrix(matrix);
            
           
            comb = new Mat4(matrix);
        }else
            comb = (traslationMatrix.multiply(rotationMatrix)).multiply(scaleMatrix);
        return comb;
    }

    public void render(Camera camera) {
        shader.bind();
        if (drawMode == SKYBOX) {
            GL11.glDepthFunc(0);
            traslationMatrix = new Mat4(1.0f);
            traslationMatrix = traslationMatrix.translate(camera.position);
            shader.setUniformMat4f("pr_matrix", camera.combinedMatrix.multiply(getModel()));
            
        } else {
            shader.setUniformMat4f("pr_matrix", camera.combinedMatrix.multiply(getModel()));
        }
        for( int i = 0 ; i<meshes.size() ; i ++)
            meshes.get(i).render(shader,camera,getModel());
        if (drawMode == SKYBOX) {
            GL11.glDepthFunc(1);
        }
        shader.unbind();
    }

    @Override
    public void dispose() {
        for (int i = 0; i < meshes.size() ; i++) {
            meshes.get(i).dispose();
        }
    }

    public void useShader(ShaderProgram shader) {
        this.shader = shader;
    }
    
    public void addMesh(Mesh mesh){
        meshes.add(mesh);
    }
    
    public float[] getVertices(){
        LinkedList<Float> v = new LinkedList<>();
        
        for (int i = 0; i < meshes.size(); i++) {
            ListUtils.append(v,meshes.get(i).vertices);
        }
        
        return ListUtils.listToFloat(v);
        
    }
}
