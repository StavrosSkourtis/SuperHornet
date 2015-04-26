/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.utils.MatrixConverter;
import net.skourti.superhornet.utils.TextureLoader;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Stavros
 */
public class Ground {

    public Ground(Screen screen) {
        float size = 10000;
        Model groundModel = new Model();
        groundModel.useShader(ShaderProgram.textureShader);
        Mesh groundMesh = new Mesh();
        groundMesh.setTexture(new Texture("res/grass.png",GL11.GL_REPEAT));
        groundMesh.create(
                new float[]{
                    -size, 0, -size, // down left
                    -size, 0, size, // up left
                    size, 0, -size, // down right
                    size, 0, -size, // down right
                    size, 0, size, // up right
                    -size, 0, size // up left
                },
                null, null,
                new float[]{
                    0, 0,// down left
                    0, size/100,// up left
                    size/100, 0,// down right
                    size/100, 0,// down right
                    size/100, size/100,// up right
                    0, size/100,// up left
                },
                null
        );
        

        groundModel.addMesh(groundMesh);
        
        MotionState state2 = new DefaultMotionState(new Transform(MatrixConverter.convert(groundModel.getModel())));
        CollisionShape shape2 = new StaticPlaneShape(new Vector3f(0, 1, 0),1f);
        RigidBodyConstructionInfo info2 = new RigidBodyConstructionInfo(0f, state2, shape2);
        RigidBody body2 = new RigidBody(info2);
        body2.setFriction(0.3f);
        
        groundModel.body = body2;
        
        screen.addEntity(groundModel);

    }

}
