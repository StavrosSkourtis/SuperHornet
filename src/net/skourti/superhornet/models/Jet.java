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
        //hornet = loader.load("res/jets/fa18superhornet/FA-18E_SuperHornet.obj");
        hornet =loader.load("res/jets/superhornet/hornet.obj");
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
        hornet.body.setFriction(0.3f);
        hornet.body.setDamping(0.4f, 0.5f);
        hornet.body.setGravity(new Vector3f(0 , -20, 0));
        
        
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
        //Vector3f  v = new Vector3f();
       //hornet.body.getVelocityInLocalPoint(new Vector3f(0,0,0), v);
        
        //force(-v.x*100, -v.y*100, -v.z*100);
        
       // System.out.println(v);
    }
    
    public float frontForce = 50000f;
    public boolean engine = true;
    
    public void engineThrotle(float force){
        frontForce += force;
    }
    public void enginePower(){
        engine = !engine;
        System.out.println("Engine"+((engine)?" on":"off"));
    }
    
    public void engineThrust(){
        if(!engine){
            return;
        }
        force(0, frontForce, 0);
        frontForce = 50000f;
    }
    
    public void lift(){
        force(0, 0, 30000);   
    }
}
