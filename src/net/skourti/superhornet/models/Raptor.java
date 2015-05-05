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
public class Raptor extends Jet {

    public Raptor(Screen screen) {
        ObjLoader loader = new ObjLoader();

        jet = loader.load("res/jets/fa22raptor/FA-22_Raptor.obj");

        float flamesRightV[] = {
            -0.266812f, -8.604787f, -0.261641f,
            -0.266812f, -8.604787f, 0.331404f,
            -0.859858f, -8.604787f, 0.331404f,
            -0.859857f, -8.604787f, -0.261642f,
            -0.266812f, -8.604787f, -0.261641f,
            -0.266812f, -8.604787f, 0.331405f,
            -0.859858f, -8.604787f, 0.331404f,
            -0.859857f, -8.604787f, -0.261642f,
            -0.266812f, -8.604787f, 0.034881f,
            -0.563335f, -8.604787f, -0.261642f,
            -0.266812f, -8.604787f, -0.261641f,
            -0.563335f, -8.604787f, 0.331404f,
            -0.266812f, -8.604787f, 0.331404f,
            -0.859857f, -8.604787f, 0.034881f,
            -0.859858f, -8.604787f, 0.331404f,
            -0.859857f, -8.604787f, -0.261642f,
            -0.266812f, -8.604787f, 0.034882f,
            -0.563335f, -8.604787f, -0.261641f,
            -0.563335f, -8.604787f, 0.331404f,
            -0.859858f, -8.604787f, 0.034881f,
            -0.563335f, -8.604787f, -0.261641f,
            -0.859857f, -8.604787f, 0.034881f,
            -0.563335f, -8.604787f, 0.331404f,
            -0.266812f, -8.604787f, 0.034882f,
            -0.563335f, -8.604787f, 0.034881f,
            -0.563335f, -13.356618f, 0.034881f,
            -0.563335f, -13.356618f, 0.034881f};

        int flamesRightI[] = {
            39, 30, 41,
            52, 47, 34,
            44, 33, 40,
            46, 34, 42,
            47, 35, 43,
            37, 31, 43,
            48, 43, 35,
            38, 48, 45,
            38, 28, 37,
            49, 43, 31,
            42, 49, 41,
            34, 47, 49,
            50, 42, 30,
            40, 50, 39,
            33, 46, 50,
            51, 40, 29,
            38, 51, 36,
            32, 44, 51,
            52, 46, 33,
            45, 52, 44,
            35, 47, 52,
            37, 53, 41,
            36, 53, 37,
            36, 29, 39,
            53, 39, 41,
            46, 52, 34,
            51, 44, 40,
            50, 46, 42,
            49, 47, 43,
            48, 37, 43,
            45, 48, 35,
            32, 38, 45,
            48, 38, 37,
            41, 49, 31,
            30, 42, 41,
            42, 34, 49,
            39, 50, 30,
            29, 40, 39,
            40, 33, 50,
            36, 51, 29,
            28, 38, 36,
            38, 32, 51,
            44, 52, 33,
            32, 45, 44,
            45, 35, 52,
            31, 37, 41,
            28, 36, 37,
            53, 36, 39};

        float flamesLeftV[] = {
            0.885660f, -8.604787f, -0.261641f,
            0.885660f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, -0.261642f,
            0.885660f, -8.604787f, -0.261641f,
            0.885659f, -8.604787f, 0.331405f,
            0.292614f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, -0.261642f,
            0.885660f, -8.604787f, 0.034881f,
            0.589137f, -8.604787f, -0.261642f,
            0.885660f, -8.604787f, -0.261641f,
            0.589137f, -8.604787f, 0.331404f,
            0.885660f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, 0.034881f,
            0.292614f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, -0.261642f,
            0.885660f, -8.604787f, 0.034882f,
            0.589137f, -8.604787f, -0.261641f,
            0.589137f, -8.604787f, 0.331404f,
            0.292614f, -8.604787f, 0.034881f,
            0.589137f, -8.604787f, -0.261641f,
            0.292614f, -8.604787f, 0.034881f,
            0.589137f, -8.604787f, 0.331404f,
            0.885660f, -8.604787f, 0.034882f,
            0.589137f, -8.604787f, 0.034881f,
            0.589137f, -13.356618f, 0.034881f,
            0.589137f, -13.356618f, 0.034881f,};

        int flamesLeftI[] = {
            12, 3, 14,
            25, 20, 7,
            17, 6, 13,
            19, 7, 15,
            20, 8, 16,
            10, 4, 16,
            21, 16, 8,
            11, 21, 18,
            11, 1, 10,
            22, 16, 4,
            15, 22, 14,
            7, 20, 22,
            23, 15, 3,
            13, 23, 12,
            6, 19, 23,
            24, 13, 2,
            11, 24, 9,
            5, 17, 24,
            25, 19, 6,
            18, 25, 17,
            8, 20, 25,
            10, 26, 14,
            9, 26, 10,
            9, 2, 12,
            26, 12, 14,
            19, 25, 7,
            24, 17, 13,
            23, 19, 15,
            22, 20, 16,
            21, 10, 16,
            18, 21, 8,
            5, 11, 18,
            21, 11, 10,
            14, 22, 4,
            3, 15, 14,
            15, 7, 22,
            12, 23, 3,
            2, 13, 12,
            13, 6, 23,
            9, 24, 2,
            1, 11, 9,
            11, 5, 24,
            17, 25, 6,
            5, 18, 17,
            18, 8, 25,
            4, 10, 14,
            1, 9, 10,
            26, 9, 12
        };

        float[] color = {
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 180 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 180 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 190 / 255f, 0.8f,
            65 / 255f, 170 / 255f, 160 / 255f, 0.2f,
            65 / 255f, 170 / 255f, 200 / 255f, 0.2f,};

        for (int i = 0; i < flamesLeftI.length; i++) {
            flamesLeftI[i]--;
        }
        for (int i = 0; i < flamesRightI.length; i++) {
            flamesRightI[i] -= 26;
        }
        for (int i = 0; i < flamesRightV.length; i += 3) {
            flamesRightV[i + 1] += 0.5f;
        }
        for (int i = 0; i < flamesLeftV.length; i += 3) {
            flamesLeftV[i + 1] += 0.5f;
        }

        exhaustFlamesRight = new Mesh();
        exhaustFlamesRight.create(flamesRightV, color, null, null, flamesRightI);
        exhaustFlamesRight.useShader(ShaderProgram.colorShader);

        exhaustFlamesLeft = new Mesh();
        exhaustFlamesLeft.create(flamesLeftV, color, null, null, flamesLeftI);
        exhaustFlamesLeft.useShader(ShaderProgram.colorShader);

        jet.addMesh(exhaustFlamesLeft);
        jet.addMesh(exhaustFlamesRight);

        jet.rotate(
                -1.53f, new Vec3(1, 0, 0));
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

        this.flamesLeftI = flamesLeftI;
        this.flamesLeftV = flamesLeftV;
        this.flamesRightI = flamesRightI;
        this.flamesRightV = flamesRightV;
        this.color = color;

        System.out.println("Here");
    }
}
