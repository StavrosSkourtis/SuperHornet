/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec3;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Model;

/**
 *
 * @author Stavros
 */
abstract public class Jet {

    public Vec3 frontLookPoint;
    protected Model jet;
    protected Mesh exhaustFlamesLeft, exhaustFlamesRight;

    protected float flamesRightV[], flamesLeftV[], color[];
    protected int flamesLeftI[], flamesRightI[];

    public Mat4 getModelMatrix() {
        return jet.getModel();
    }

    public void translate(Vec3 vec) {
        jet.translate(vec);
    }

    public void rotate(float angle, Vec3 axis) {
        jet.rotate(angle, axis);
    }

    public void force(float x, float y, float z) {
        Vec3 force = new Vec3(x, y, z);

        force = jet.getModel().multiplyDirection(force);

        jet.body.applyCentralForce(new Vector3f(force.x, force.y, force.z));
    }

    public void forceFront(float x, float y, float z) {
        Vec3 front = new Vec3(0, 5, 0);
        Vec3 force = new Vec3(x, y, z);
        force = jet.getModel().multiplyDirection(force);
        front = jet.getModel().multiplyDirection(front);

        jet.body.applyForce(new Vector3f(force.x, force.y, force.z), new Vector3f(front.x, front.y, front.z));
    }

    public void forceFrontNonRelative(float x, float y, float z) {
        Vec3 front = new Vec3(0, 0, 0);
        Vec3 force = new Vec3(x, y, z);
        front = jet.getModel().multiplyDirection(front);

        jet.body.applyForce(new Vector3f(force.x, force.y, force.z), new Vector3f(front.x, front.y, front.z));
    }

    public void forceLeftSide(float x, float y, float z) {
        Vec3 front = new Vec3(-5, 0, 0);
        Vec3 force = new Vec3(x, y, z);
        force = jet.getModel().multiplyDirection(force);
        front = jet.getModel().multiplyDirection(front);

        jet.body.applyForce(new Vector3f(force.x, force.y, force.z), new Vector3f(front.x, front.y, front.z));
    }

    public void forceRightSide(float x, float y, float z) {
        Vec3 front = new Vec3(5, 0, 0);
        Vec3 force = new Vec3(x, y, z);
        force = jet.getModel().multiplyDirection(force);
        front = jet.getModel().multiplyDirection(front);

        jet.body.applyForce(new Vector3f(force.x, force.y, force.z), new Vector3f(front.x, front.y, front.z));
    }

    public void angle(float x, float y, float z, float xPos) {

    }

    public void stabilize() {
        //Vector3f  v = new Vector3f();
        //hornet.body.getVelocityInLocalPoint(new Vector3f(0,0,0), v);

        //force(-v.x*100, -v.y*100, -v.z*100);
        // System.out.println(v);
    }

    public float frontForce = 50000f;
    public boolean engine = true;

    public void engineThrotle(float force) {
        frontForce = force;
    }
    
    public float getHeight(){
        Vec3 temp = new Vec3(0,0,0);
        temp = jet.getModel().multiply(temp);
        return temp.y;        
    }
    
    public float getSpeed(){
        Vector3f speed = new Vector3f(0,0,0);
        jet.body.getLinearVelocity(speed);
        return   (float)Math.sqrt(  Math.pow(speed.x,2)+Math.pow(speed.z,2) +Math.pow(speed.y,2));
    }

    public void enginePower() {
        engine = !engine;
        System.out.println("Engine" + ((engine) ? " on" : "off"));

        /*
         Make the exhaust flames invisible
         */
        for (int i = 0; i < color.length; i += 4) {
            color[i + 3] = 0;
        }
        exhaustFlamesLeft.setColor(color);
        exhaustFlamesRight.setColor(color);
        exhaustFlamesLeft.recalculateData();
        exhaustFlamesRight.recalculateData();
    }

    public void engineThrust() {
        if (!engine) {
            frontForce = 0;
            return;
        }
        forceFrontNonRelative(0, -10000, 0);
        force(0, frontForce, 0);

        float flameLenght = -8.129322f - (frontForce == 0 ? 200000 : (frontForce + 450000)) / 1000000;

        float[] rightV = flamesRightV.clone();
        float[] leftV = flamesLeftV.clone();
        for (int i = 0; i < flamesRightV.length; i += 3) {

            if (rightV[i + 1] != -8.129322f) {
                leftV[i] += Math.random() / 16 - 0.0625/2;
                rightV[i] += Math.random() / 16 - 0.0625/2;
                rightV[i + 1] = flameLenght - (float) Math.random() / 4;
                leftV[i + 1] = flameLenght - (float) Math.random() / 4;
            }
        }

        for (int i = 0; i < color.length; i += 4) {
            color[i] = (float) Math.random() / 4 + 90 / 255f;
            color[i + 1] = (float) Math.random() / 4 + 210 / 255f;
            color[i + 2] = (float) Math.random() / 4 + 230 / 255f;
            color[i + 3] = (float) Math.random() / 2 + 0.5f;
        }

        exhaustFlamesLeft.setColor(color);
        exhaustFlamesRight.setColor(color);
        exhaustFlamesLeft.setVertices(leftV);
        exhaustFlamesRight.setVertices(rightV);
        exhaustFlamesLeft.recalculateData();
        exhaustFlamesRight.recalculateData();
        frontForce = 0f;
    }

    public void lift() {
        force(0, 0, 30000);
    }
}
