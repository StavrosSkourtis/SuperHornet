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
abstract public class Entity implements Disposable {

    public Mesh mesh;
    public Mat4 traslationMatrix;
    public Mat4 rotationMatrix;
    public Mat4 scaleMatrix;
    public float angle;
    public Texture texture;
    public ShaderProgram shader;

    public Entity() {
        traslationMatrix = new Mat4(1.0f);
        rotationMatrix = new Mat4(1.0f);
        scaleMatrix = new Mat4(1.0f);
        create();
    }

    abstract public void create();

    public void rotate(float angle, Vec3 axis) {
        this.angle += angle;

        rotationMatrix = Matrices.rotate(this.angle, axis);
    }

    public void setAngle(float angle, Vec3 axis) {
        this.angle = angle;
        rotationMatrix = Matrices.rotate(this.angle, axis);
    }

    public Mat4 getModel() {
        Mat4 comb = (traslationMatrix.multiply(rotationMatrix)).multiply(scaleMatrix);
        return comb;
    }

    public void render(Mat4 combined) {
        if (texture != null) {
            texture.bind();
        }
        shader.bind();
        shader.setUniformMat4f("pr_matrix", combined.multiply(getModel()));
        mesh.render();
        shader.unbind();
    }

    @Override
    public void dispose() {
        mesh.dispose();
    }
}
