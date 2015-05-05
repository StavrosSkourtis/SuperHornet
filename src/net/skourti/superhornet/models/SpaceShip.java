/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.hackoeur.jglm.Vec3;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.utils.MatrixConverter;
import net.skourti.superhornet.utils.objparser.ObjLoader;

/**
 *
 * @author Stavros
 */
public class SpaceShip extends Jet{
    public SpaceShip(Screen screen) {
        ObjLoader loader = new ObjLoader();

        jet = loader.load("res/jets/spaceship/dark_fighter_6.obj");


        exhaustFlamesRight = new Mesh();


        exhaustFlamesLeft = new Mesh();


        //jet.rotate(-1.53f, new Vec3(1, 0, 0));
        jet.translate(
                new Vec3(0, 100, 0));

        Vector3f intertia = new Vector3f();

        BoxShape shape = new BoxShape(new Vector3f(5, 9f, 1));

        shape.calculateLocalInertia(
                3000f, intertia);

        MotionState defaultState = new DefaultMotionState(new Transform(MatrixConverter.convert(jet.getModel())));

        RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(3000f, defaultState, shape, intertia);

        RigidBody body = new RigidBody(info);
        jet.body = body;

        jet.body.setFriction(
                0.3f);
        jet.body.setDamping(
                0.4f, 0.5f);

        jet.body.setActivationState(RigidBody.DISABLE_DEACTIVATION);

        screen.addEntity(jet);

        frontLookPoint = new Vec3(0, 20, -10);

        this.flamesLeftI = new int[100];
        this.flamesLeftV = new float[100];
        this.flamesRightI = new int[100];
        this.flamesRightV = new float[100];
        this.color = new float[100];

        System.out.println("Here");
    }
}
