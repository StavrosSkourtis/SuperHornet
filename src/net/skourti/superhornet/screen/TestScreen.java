/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.screen;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.collision.shapes.TriangleIndexVertexArray;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.hackoeur.jglm.Vec3;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.models.Jet;
import net.skourti.superhornet.utils.MatrixConverter;

/**
 *
 * @author Stavros
 */
public class TestScreen extends Screen {

    @Override
    public void create() {

        Model square = new Model();
        square.useShader(ShaderProgram.colorShader);

        Mesh mesh = new Mesh();
        mesh.create(
                new float[]{
                    1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, 1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,
                    1.0f, 1.0f, 1.0f,
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f,  1.0f, 1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f
                },
                new float[]{
                    0.583f, 0.771f, 0.014f, 1f,
                    0.609f, 0.115f, 0.436f, 1f,
                    0.327f, 0.483f, 0.844f, 1f,
                    0.822f, 0.569f, 0.201f, 1f,
                    0.435f, 0.602f, 0.223f, 1f,
                    0.310f, 0.747f, 0.185f, 1f,
                    0.597f, 0.770f, 0.761f, 1f,
                    0.559f, 0.436f, 0.730f, 1f,
                    0.359f, 0.583f, 0.152f, 1f,
                    0.483f, 0.596f, 0.789f, 1f,
                    0.559f, 0.861f, 0.639f, 1f,
                    0.195f, 0.548f, 0.859f, 1f,
                    0.014f, 0.184f, 0.576f, 1f,
                    0.771f, 0.328f, 0.970f, 1f,
                    0.406f, 0.615f, 0.116f, 1f,
                    0.676f, 0.977f, 0.133f, 1f,
                    0.971f, 0.572f, 0.833f, 1f,
                    0.140f, 0.616f, 0.489f, 1f,
                    0.997f, 0.513f, 0.064f, 1f,
                    0.945f, 0.719f, 0.592f, 1f,
                    0.543f, 0.021f, 0.978f, 1f,
                    0.279f, 0.317f, 0.505f, 1f,
                    0.167f, 0.620f, 0.077f, 1f,
                    0.347f, 0.857f, 0.137f, 1f,
                    0.055f, 0.953f, 0.042f, 1f,
                    0.714f, 0.505f, 0.345f, 1f,
                    0.783f, 0.290f, 0.734f, 1f,
                    0.722f, 0.645f, 0.174f, 1f,
                    0.302f, 0.455f, 0.848f, 1f,
                    0.225f, 0.587f, 0.040f, 1f,
                    0.517f, 0.713f, 0.338f, 1f,
                    0.053f, 0.959f, 0.120f, 1f,
                    0.393f, 0.621f, 0.362f, 1f,
                    0.673f, 0.211f, 0.457f, 1f,
                    0.820f, 0.883f, 0.371f, 1f,
                    0.982f, 0.099f, 0.879f, 1f},
                null, null, null
        );
        square.addMesh(mesh);
        square.translate(new Vec3(0, 4, 0));
        square.rotate(-0.3f, new Vec3(0, 0, 1));
        MotionState state = new DefaultMotionState(new Transform(MatrixConverter.convert(square.getModel())));
        
        CollisionShape shape = new BoxShape(new Vector3f(1, 1, 1));
        Vector3f localIntertia = new Vector3f();
        
        shape.calculateLocalInertia(10f, localIntertia);
        
        RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(40, state, shape, localIntertia);
        RigidBody body = new RigidBody(info);
        
        
        
        
        square.body = body;
        addEntity(square);

        Model floor = new Model();
        floor.useShader(ShaderProgram.colorShader);

        Mesh floorMesh = new Mesh();
        floorMesh.create(
                new float[]{
                    -10, 0, -10,
                    -10, 0,  10,
                     10, 0, -10,
                    
                     10, 0, -10,
                     10, 0,  10,
                    -10, 0,  10},
                new float[]{
                    1, 1, 1, 1,
                    1, 1, 1, 1,
                    1, 1, 1, 1,
                    1, 1, 1, 1,
                    1, 1, 1, 1,
                    1, 1, 1, 1},
                null, null, null);
        floor.translate(new Vec3(0, -10, 0));
        floor.addMesh(floorMesh);
        Vector3f localIntertia2 = new Vector3f();
        
        MotionState state2 = new DefaultMotionState(new Transform(MatrixConverter.convert(floor.getModel())));
        //CollisionShape shape2 = new BoxShape(new Vector3f(10, 5, 10));
        CollisionShape shape2 = new StaticPlaneShape(new Vector3f(0,1,0), 1f);
       
        //shape2.calculateLocalInertia(1, localIntertia2);
        RigidBodyConstructionInfo info2 = new RigidBodyConstructionInfo(0f, state2, shape2,new Vector3f(0,0,0));
        RigidBody body2 = new RigidBody(info2);
        body2.setFriction(0.3f);
        floor.body = body2;
        addEntity(floor);
       
        
       
        
        
        camera.lookAt(new Vec3(0, 0, 20), new Vec3(0, 0, 0), new Vec3(0, 1, 0));
        camera.mouseControl = true;

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void logic() {

    }

}
