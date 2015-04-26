/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.collision.shapes.TriangleMeshShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec3;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.utils.ListUtils;
import net.skourti.superhornet.utils.MatrixConverter;
import net.skourti.superhornet.utils.objparser.ObjLoader;

/**
 *
 * @author Stavros
 */
public class Jet {

    public Vec3 frontLookPoint;
    private Model hornet;


    public Jet(Screen screen) {
        ObjLoader loader = new ObjLoader();

        //screen.addEntity(loader.load("res/farm/house.obj"));
        //screen.addEntity(loader.load("res/jets/fa22raptor/FA-22_Raptor.obj"));
        //screen.addEntity(loader.load("res/jets/cube.obj"));
        //screen.addEntity(loader.load("res/Knife/sword.obj"));
        //Model hornet = loader.load("res/jets/fa18superhornet/FA-18E_SuperHornet.obj");
        hornet = loader.load("res/jets/fa18superhornet/FA-18E_SuperHornet.obj");
//        hornet.useShader(ShaderProgram.colorShader);
//        Mesh mesh = new Mesh();
//        mesh.create(
//                new float[]{
//                    5.0f, -9.0f, -1.0f,
//                    -5.0f, -9.0f, 1.0f,
//                    -5.0f, 9.0f, 1.0f,
//                    5.0f, 9.0f, -1.0f,
//                    -5.0f, -9.0f, -1.0f,
//                    -5.0f, 9.0f, -1.0f,
//                    5.0f, -9.0f, 1.0f,
//                    -5.0f, -9.0f, -1.0f,
//                    5.0f, -9.0f, -1.0f,
//                    5.0f, 9.0f, -1.0f,
//                    5.0f, -9.0f, -1.0f,
//                    -5.0f, -9.0f, -1.0f,
//                    -5.0f, -9.0f, -1.0f,
//                    -5.0f, 9.0f, 1.0f,
//                    -5.0f, 9.0f, -1.0f,
//                    5.0f, -9.0f, 1.0f,
//                    -5.0f, -9.0f, 1.0f,
//                    -5.0f, -9.0f, -1.0f,
//                    -5.0f, 9.0f, 1.0f,
//                    -5.0f, -9.0f, 1.0f,
//                    5.0f, -9.0f, 1.0f,
//                    5.0f, 9.0f, 1.0f,
//                    5.0f, -9.0f, -1.0f,
//                    5.0f, 9.0f, -1.0f,
//                    5.0f, -9.0f, -1.0f,
//                    5.0f, 9.0f, 1.0f,
//                    5.0f, -9.0f, 1.0f,
//                    5.0f, 9.0f, 1.0f,
//                    5.0f, 9.0f, -1.0f,
//                    -5.0f, 9.0f, -1.0f,
//                    5.0f, 9.0f, 1.0f,
//                    -5.0f, 9.0f, -1.0f,
//                    -5.0f, 9.0f, 1.0f,
//                    5.0f,  9.0f, 1.0f,
//                    -5.0f, 9.0f, 1.0f,
//                    5.0f, -9.0f, 1.0f
//                },
//                new float[]{
//                    0.583f, 0.771f, 0.014f, 1f,
//                    0.609f, 0.115f, 0.436f, 1f,
//                    0.327f, 0.483f, 0.844f, 1f,
//                    0.822f, 0.569f, 0.201f, 1f,
//                    0.435f, 0.602f, 0.223f, 1f,
//                    0.310f, 0.747f, 0.185f, 1f,
//                    0.597f, 0.770f, 0.761f, 1f,
//                    0.559f, 0.436f, 0.730f, 1f,
//                    0.359f, 0.583f, 0.152f, 1f,
//                    0.483f, 0.596f, 0.789f, 1f,
//                    0.559f, 0.861f, 0.639f, 1f,
//                    0.195f, 0.548f, 0.859f, 1f,
//                    0.014f, 0.184f, 0.576f, 1f,
//                    0.771f, 0.328f, 0.970f, 1f,
//                    0.406f, 0.615f, 0.116f, 1f,
//                    0.676f, 0.977f, 0.133f, 1f,
//                    0.971f, 0.572f, 0.833f, 1f,
//                    0.140f, 0.616f, 0.489f, 1f,
//                    0.997f, 0.513f, 0.064f, 1f,
//                    0.945f, 0.719f, 0.592f, 1f,
//                    0.543f, 0.021f, 0.978f, 1f,
//                    0.279f, 0.317f, 0.505f, 1f,
//                    0.167f, 0.620f, 0.077f, 1f,
//                    0.347f, 0.857f, 0.137f, 1f,
//                    0.055f, 0.953f, 0.042f, 1f,
//                    0.714f, 0.505f, 0.345f, 1f,
//                    0.783f, 0.290f, 0.734f, 1f,
//                    0.722f, 0.645f, 0.174f, 1f,
//                    0.302f, 0.455f, 0.848f, 1f,
//                    0.225f, 0.587f, 0.040f, 1f,
//                    0.517f, 0.713f, 0.338f, 1f,
//                    0.053f, 0.959f, 0.120f, 1f,
//                    0.393f, 0.621f, 0.362f, 1f,
//                    0.673f, 0.211f, 0.457f, 1f,
//                    0.820f, 0.883f, 0.371f, 1f,
//                    0.982f, 0.099f, 0.879f, 1f},
//                null, null, null
//        );
//        
//        hornet.addMesh(mesh);
        hornet.rotate(-1.53f, new Vec3(1, 0, 0));
        hornet.translate(new Vec3(0, 100, 0));
        
        System.out.println("Check 1");
        
//
//        ConvexHullShape shape = new ConvexHullShape(ListUtils.arrayToVector3fList(hornet.getVertices()));
//        System.out.println("Check 2");
//        MotionState defaultState = new DefaultMotionState(new Transform(MatrixConverter.convert(hornet.getModel())));
//        System.out.println("Check 3");
//        RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(40f, defaultState, shape);
//        System.out.println("Check 4");
//        RigidBody body = new RigidBody(info);
//        
        
        Vector3f intertia = new Vector3f();
        
        BoxShape shape = new BoxShape(new Vector3f(5, 9f, 1));
        
        shape.calculateLocalInertia(3000f, intertia);
        
        MotionState defaultState = new DefaultMotionState(new Transform(MatrixConverter.convert(hornet.getModel())));
        
        RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(3000f, defaultState, shape,intertia);
        
        RigidBody body = new RigidBody(info);
        hornet.body = body;
        hornet.body.setFriction(0.1f);
        
        
        screen.addEntity(hornet);
        System.out.println("Check 5");
        frontLookPoint = new Vec3(0, 20, -10);
    }

    public Mat4 getModelMatrix() {
        return hornet.getModel();
    }

    public void translate(Vec3 vec) {
        hornet.translate(vec);
    }

    public void rotate(float angle, Vec3 axis) {
        hornet.rotate(angle, axis);
    }
    
    public void force(float x,float y,float z){
       Vec3 force = new Vec3(x, y, z);
       
       force = hornet.getModel().multiplyDirection(force);
               
       hornet.body.applyCentralForce(new Vector3f(force.x, force.y, force.z));
    }
    
    public void forceFront(float x,float y,float z){
        Vec3 front = new Vec3(0 ,5 , 0);
        Vec3 force = new Vec3(x, y, z);
        force = hornet.getModel().multiplyDirection(force);
        front = hornet.getModel().multiplyDirection(front);
        
        hornet.body.applyForce( new Vector3f(force.x, force.y, force.z),new Vector3f(front.x , front.y , front.z));
    }
    
    public void forceLeftSide(float x,float y, float z){
        Vec3 front = new Vec3(-5 ,0, 0);
        Vec3 force = new Vec3(x, y, z);
        force = hornet.getModel().multiplyDirection(force);
        front = hornet.getModel().multiplyDirection(front);
        
        hornet.body.applyForce( new Vector3f(force.x, force.y, force.z),new Vector3f(front.x , front.y , front.z));
    }
    
    public void forceRightSide(float x, float y ,float z){
        Vec3 front = new Vec3(5 ,0 , 0);
        Vec3 force = new Vec3(x, y, z);
        force = hornet.getModel().multiplyDirection(force);
        front = hornet.getModel().multiplyDirection(front);
        
        hornet.body.applyForce( new Vector3f(force.x, force.y, force.z),new Vector3f(front.x , front.y , front.z));
    }
    
    public void angle(float x,float y ,float z , float xPos){

    }
    
    
    public void stabilize(){
        Vector3f  v = new Vector3f();
        hornet.body.getVelocityInLocalPoint(new Vector3f(0,0,0), v);
        
        force(-v.x*10, -v.y*10, -v.z*10);
        
        System.out.println(v);
    }
    
    
    
    public void engineThrust(){
        
    }
    
    public void lift(){
        force(0, 0, 30000);   
    }
}
