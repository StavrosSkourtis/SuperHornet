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
public class SuperHornet extends Jet {

    public SuperHornet(Screen screen) {
        ObjLoader loader = new ObjLoader();

        jet = loader.load("res/jets/superhornet/hornet.obj");

        float flamesLeftV[] = {
             -0.583733f, -8.845737f, -0.324868f,
             -0.583733f, -8.129322f, -0.324868f,
             -0.513851f, -8.845737f, -0.317985f,
             -0.513851f, -8.129322f, -0.317985f,
             -0.446653f, -8.845737f, -0.297601f,
             -0.446653f, -8.129322f, -0.297601f,
             -0.384724f, -8.845737f, -0.264499f,
             -0.384724f, -8.129322f, -0.264499f,
             -0.330442f, -8.845737f, -0.219951f,
             -0.330442f, -8.129322f, -0.219951f,
             -0.285894f, -8.845737f, -0.165670f,
             -0.285894f, -8.129322f, -0.165670f,
             -0.252793f, -8.845737f, -0.103740f,
             -0.252793f, -8.129322f, -0.103740f,
             -0.232408f, -8.845737f, -0.036543f,
             -0.232408f, -8.129322f, -0.036543f,
             -0.225526f, -8.845737f, 0.033340f,
             -0.225526f, -8.129322f, 0.033340f,
             -0.232408f, -8.845737f, 0.103223f,
             -0.232408f, -8.129322f, 0.103223f,
             -0.252792f, -8.845737f, 0.170420f,
             -0.252792f, -8.129322f, 0.170420f,
             -0.285894f, -8.845737f, 0.232350f,
             -0.285894f, -8.129322f, 0.232350f,
             -0.330442f, -8.845737f, 0.286631f,
             -0.330442f, -8.129322f, 0.286631f,
             -0.384724f, -8.845737f, 0.331179f,
             -0.384724f, -8.129322f, 0.331179f,
             -0.446653f, -8.845737f, 0.364281f,
             -0.446653f, -8.129322f, 0.364281f,
             -0.513851f, -8.845737f, 0.384665f,
             -0.513851f, -8.129322f, 0.384665f,
             -0.583734f, -8.845737f, 0.391548f,
             -0.583734f, -8.129322f, 0.391548f,
             -0.653617f, -8.845737f, 0.384665f,
             -0.653617f, -8.129322f, 0.384665f,
             -0.720814f, -8.845737f, 0.364281f,
             -0.720814f, -8.129322f, 0.364281f,
             -0.782743f, -8.845737f, 0.331179f,
             -0.782743f, -8.129322f, 0.331179f,
             -0.837025f, -8.845737f, 0.286631f,
             -0.837025f, -8.129322f, 0.286631f,
             -0.881573f, -8.845737f, 0.232350f,
             -0.881573f, -8.129322f, 0.232350f,
             -0.914675f, -8.845737f, 0.170420f,
             -0.914675f, -8.129322f, 0.170420f,
             -0.935059f, -8.845737f, 0.103223f,
             -0.935059f, -8.129322f, 0.103223f,
             -0.941941f, -8.845737f, 0.033340f,
             -0.941941f, -8.129322f, 0.033340f,
             -0.935059f, -8.845737f, -0.036543f,
             -0.935059f, -8.129322f, -0.036543f,
             -0.914674f, -8.845737f, -0.103741f,
             -0.914674f, -8.129322f, -0.103741f,
             -0.881572f, -8.845737f, -0.165670f,
             -0.881572f, -8.129322f, -0.165670f,
             -0.837024f, -8.845737f, -0.219952f,
             -0.837024f, -8.129322f, -0.219952f,
             -0.782743f, -8.845737f, -0.264499f,
             -0.782743f, -8.129322f, -0.264499f,
             -0.720813f, -8.845737f, -0.297601f,
             -0.720813f, -8.129322f, -0.297601f,
             -0.653616f, -8.845737f, -0.317985f,
             -0.653616f, -8.129322f, -0.317985f,
        };

        int flamesLeftI[] = {
              2,  4,  3,
              4,  6,  5,
              6,  8,  7,
              8, 10,  9,
             10, 12, 11,
             12, 14, 13,
             14, 16, 15,
             16, 18, 17,
             18, 20, 19,
             20, 22, 21,
             22, 24, 23,
             24, 26, 25,
             26, 28, 27,
             28, 30, 29,
             30, 32, 31,
             32, 34, 33,
             34, 36, 35,
             36, 38, 37,
             38, 40, 39,
             40, 42, 41,
             42, 44, 43,
             44, 46, 45,
             46, 48, 47,
             48, 50, 49,
             50, 52, 51,
             52, 54, 53,
             54, 56, 55,
             56, 58, 57,
             58, 60, 59,
             60, 62, 61,
             14, 54, 36,
             64,  2,  1,
             62, 64, 63,
              1,  2,  3,
              3,  4,  5,
              5,  6,  7,
              7,  8,  9,
              9, 10, 11,
             11, 12, 13,
             13, 14, 15,
             15, 16, 17,
             17, 18, 19,
             19, 20, 21,
             21, 22, 23,
             23, 24, 25,
             25, 26, 27,
             27, 28, 29,
             29, 30, 31,
             31, 32, 33,
             33, 34, 35,
             35, 36, 37,
             37, 38, 39,
             39, 40, 41,
             41, 42, 43,
             43, 44, 45,
             45, 46, 47,
             47, 48, 49,
             49, 50, 51,
             51, 52, 53,
             53, 54, 55,
             55, 56, 57,
             57, 58, 59,
             59, 60, 61,
             14,  6,  4,
              6, 10,  8,
             10, 14, 12,
             14, 36, 16,
             18, 22, 20,
             28, 26, 24,
             32, 30, 28,
             36, 34, 32,
             40, 38, 36,
             48, 42, 40,
             42, 46, 44,
             52, 50, 48,
             48, 54, 52,
             54, 58, 56,
             64, 62, 60,
              4,  2, 64,
             14, 10,  6,
              4, 54, 14,
             18, 28, 22,
             36, 32, 28,
             48, 40, 36,
             42, 48, 46,
              4, 58, 54,
             58, 64, 60,
             28, 18, 16,
             22, 28, 24,
             54, 48, 36,
              4, 64, 58,
             36, 28, 16,
             63, 64,  1,
             61, 62, 63,
        };
        
        float flamesRightV[] = {
             0.601870f, -8.845737f, -0.324868f,
             0.601870f, -8.129322f, -0.324868f,
             0.671753f, -8.845737f, -0.317985f,
             0.671753f, -8.129322f, -0.317985f,
             0.738951f, -8.845737f, -0.297601f,
             0.738951f, -8.129322f, -0.297601f,
             0.800880f, -8.845737f, -0.264499f,
             0.800880f, -8.129322f, -0.264499f,
             0.855162f, -8.845737f, -0.219951f,
             0.855162f, -8.129322f, -0.219951f,
             0.899710f, -8.845737f, -0.165670f,
             0.899710f, -8.129322f, -0.165670f,
             0.932811f, -8.845737f, -0.103740f,
             0.932811f, -8.129322f, -0.103740f,
             0.953196f, -8.845737f, -0.036543f,
             0.953196f, -8.129322f, -0.036543f,
             0.960078f, -8.845737f, 0.033340f,
             0.960078f, -8.129322f, 0.033340f,
             0.953196f, -8.845737f, 0.103223f,
             0.953196f, -8.129322f, 0.103223f,
             0.932811f, -8.845737f, 0.170420f,
             0.932811f, -8.129322f, 0.170420f,
             0.899710f, -8.845737f, 0.232350f,
             0.899710f, -8.129322f, 0.232350f,
             0.855162f, -8.845737f, 0.286631f,
             0.855162f, -8.129322f, 0.286631f,
             0.800880f, -8.845737f, 0.331179f,
             0.800880f, -8.129322f, 0.331179f,
             0.738951f, -8.845737f, 0.364281f,
             0.738951f, -8.129322f, 0.364281f,
             0.671753f, -8.845737f, 0.384665f,
             0.671753f, -8.129322f, 0.384665f,
             0.601870f, -8.845737f, 0.391548f,
             0.601870f, -8.129322f, 0.391548f,
             0.531987f, -8.845737f, 0.384665f,
             0.531987f, -8.129322f, 0.384665f,
             0.464790f, -8.845737f, 0.364281f,
             0.464790f, -8.129322f, 0.364281f,
             0.402861f, -8.845737f, 0.331179f,
             0.402861f, -8.129322f, 0.331179f,
             0.348579f, -8.845737f, 0.286631f,
             0.348579f, -8.129322f, 0.286631f,
             0.304031f, -8.845737f, 0.232350f,
             0.304031f, -8.129322f, 0.232350f,
             0.270929f, -8.845737f, 0.170420f,
             0.270929f, -8.129322f, 0.170420f,
             0.250545f, -8.845737f, 0.103223f,
             0.250545f, -8.129322f, 0.103223f,
             0.243663f, -8.845737f, 0.033340f,
             0.243663f, -8.129322f, 0.033340f,
             0.250545f, -8.845737f, -0.036543f,
             0.250545f, -8.129322f, -0.036543f,
             0.270930f, -8.845737f, -0.103741f,
             0.270930f, -8.129322f, -0.103741f,
             0.304032f, -8.845737f, -0.165670f,
             0.304032f, -8.129322f, -0.165670f,
             0.348580f, -8.845737f, -0.219952f,
             0.348580f, -8.129322f, -0.219952f,
             0.402861f, -8.845737f, -0.264499f,
             0.402861f, -8.129322f, -0.264499f,
             0.464791f, -8.845737f, -0.297601f,
             0.464791f, -8.129322f, -0.297601f,
             0.531988f, -8.845737f, -0.317985f,
             0.531988f, -8.129322f, -0.317985f,
            };

        int flamesRightI[] = {
              66,  68,  67,
              68,  70,  69,
              70,  72,  71,
              72,  74,  73,
              74,  76,  75,
              76,  78,  77,
              78,  80,  79,
              80,  82,  81,
              82,  84,  83,
              84,  86,  85,
              86,  88,  87,
              88,  90,  89,
              90,  92,  91,
              92,  94,  93,
              94,  96,  95,
              96,  98,  97,
              98, 100,  99,
             100, 102, 101,
             102, 104, 103,
             104, 106, 105,
             106, 108, 107,
             108, 110, 109,
             110, 112, 111,
             112, 114, 113,
             114, 116, 115,
             116, 118, 117,
             118, 120, 119,
             120, 122, 121,
             122, 124, 123,
             124, 126, 125,
              84, 116, 100,
             128,  66,  65,
             126, 128, 127,
              65,  66,  67,
              67,  68,  69,
              69,  70,  71,
              71,  72,  73,
              73,  74,  75,
              75,  76,  77,
              77,  78,  79,
              79,  80,  81,
              81,  82,  83,
              83,  84,  85,
              85,  86,  87,
              87,  88,  89,
              89,  90,  91,
              91,  92,  93,
              93,  94,  95,
              95,  96,  97,
              97,  98,  99,
              99, 100, 101,
             101, 102, 103,
             103, 104, 105,
             105, 106, 107,
             107, 108, 109,
             109, 110, 111,
             111, 112, 113,
             113, 114, 115,
             115, 116, 117,
             117, 118, 119,
             119, 120, 121,
             121, 122, 123,
             123, 124, 125,
              84,  70,  68,
              70,  74,  72,
              80,  78,  76,
              84,  82,  80,
              92,  86,  84,
              92,  90,  88,
              96,  94,  92,
             100,  98,  96,
             104, 102, 100,
             108, 106, 104,
             112, 110, 108,
             116, 114, 112,
              68, 118, 116,
             118, 122, 120,
             128, 126, 124,
              68,  66, 128,
              76,  74,  70,
              70,  80,  76,
              92,  88,  86,
              84,  96,  92,
             108, 104, 100,
             100, 112, 108,
              68, 122, 118,
             122, 128, 124,
              84,  80,  70,
             100,  96,  84,
             116, 112, 100,
              68, 128, 122,
             116,  84,  68,
             127, 128,  65,
             125, 126, 127,
        };

        float[] color = new float[(flamesLeftV.length/3 )*4];
        
        for (int i = 0; i < flamesLeftI.length; i++) {
            flamesLeftI[i]--;
        }
        for (int i = 0; i < flamesRightI.length; i++) {
            flamesRightI[i] -= 65;
        }
        for (int i = 0; i < flamesRightV.length; i += 3) {
            //flamesRightV[i + 1] += 0.5f;
        }
        for (int i = 0; i < flamesLeftV.length; i += 3) {
            //flamesLeftV[i + 1] += 0.5f;
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
        jet.body.setDamping( 0.3f, 0.3f);
        jet.body.setActivationState(RigidBody.DISABLE_DEACTIVATION);

        screen.addEntity(jet);

        frontLookPoint = new Vec3(0, 20, -10);
        
        
        this.flamesLeftI = flamesLeftI;
        this.flamesLeftV = flamesLeftV;
        this.flamesRightI = flamesRightI;
        this.flamesRightV = flamesRightV;
        this.color = color;
    }

}
