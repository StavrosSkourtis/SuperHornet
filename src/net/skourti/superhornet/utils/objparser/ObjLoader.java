/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils.objparser;

import com.hackoeur.jglm.Vec3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import main.java.com.hackoeur.jglm.Vec2;
import net.skourti.superhornet.graphics.Mesh;
import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.ShaderProgram;
import net.skourti.superhornet.graphics.Texture;
import net.skourti.superhornet.utils.ListUtils;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Stavros
 */
public class ObjLoader {

    HashMap<String, Material> materials;
    HashMap<String, ObjDataGroup> data;
    LinkedList<String> groups;

    /*
     Input data
     */
    LinkedList<Vec3> vertices = new LinkedList<>();
    LinkedList<Vec3> normals = new LinkedList<>();
    LinkedList<Vec2> uvs = new LinkedList<>();
    LinkedList<Face> faces = new LinkedList<>();

    /*
     Output data
     */
    LinkedList<Vec3> finalVert = new LinkedList<>();
    LinkedList<Vec3> finalNorm = new LinkedList<>();
    LinkedList<Vec2> finalText = new LinkedList<>();
    LinkedList<Integer> indeces = new LinkedList<>();

    public ObjLoader() {
        data = new HashMap<>();
        groups = new LinkedList<>();
    }

    /**
     * Loads a obj model
     *
     * @param path
     * @return
     */
    public Model load(String path) {
        return load(new File(path));
    }

    /**
     * Loads a obj model
     *
     * @param file
     * @return
     */
    public Model load(File file) {

        /*
         Parse the file
         */
        ObjParser parser = new ObjParser();
        parser.parse(this, file);

        Model model = new Model();
        model.useShader(ShaderProgram.modelShader);

        for (String group : groups) {
            ObjDataGroup d = data.get(group);

            faces = d.faces;
            calculateWithoutIndeces();

            Mesh mesh = new Mesh();

            float v[] = ListUtils.vec3ListToFloat(finalVert);
            float n[] = ListUtils.vec3ListToFloat(finalNorm);
            float t[] = ListUtils.vec2ListToFloat(finalText);
            
            //ListUtils.absolute(t);
            ListUtils.uvInvert(t);
            
            mesh.create(v, null, n, t, null);

            try {
                
                Material temp = materials.get(d.material);
 
                String texturePath = temp.mapKd;
                
                System.out.println(texturePath);
                mesh.setTexture(new Texture(texturePath,GL11.GL_REPEAT));
            } catch (NullPointerException ex) {
               
            }
            model.addMesh(mesh);
        }

        return model;
    }

    
    private void calculateWithoutIndeces() {
        finalText.clear();
        finalNorm.clear();
        finalText.clear();

        for (Face face : faces) {
            finalVert.add(vertices.get((int) face.vertex1.x - 1));
            finalNorm.add(normals.get((int) face.vertex1.z - 1));
            finalText.add(uvs.get((int) face.vertex1.y - 1));

            finalVert.add(vertices.get((int) face.vertex2.x - 1));
            finalNorm.add(normals.get((int) face.vertex2.z - 1));
            finalText.add(uvs.get((int) face.vertex2.y - 1));

            finalVert.add(vertices.get((int) face.vertex3.x - 1));
            finalNorm.add(normals.get((int) face.vertex3.z - 1));
            finalText.add(uvs.get((int) face.vertex3.y - 1));
        }
    }

    private void createIndecesOnly() {
        for (Face face : faces) {
            indeces.add((int) (face.vertex1.x - 1));
            indeces.add((int) (face.vertex2.x - 1));
            indeces.add((int) (face.vertex3.x - 1));
        }

    }

}
