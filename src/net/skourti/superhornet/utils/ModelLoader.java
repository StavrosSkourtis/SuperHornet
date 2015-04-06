/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils;

import com.hackoeur.jglm.Vec3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import main.java.com.hackoeur.jglm.Vec2;
import net.skourti.superhornet.graphics.Face;
import net.skourti.superhornet.graphics.Model;

/**
 *
 * @author Stavros
 */
public class ModelLoader {

    public static Model loadOblFile(String path) {
        Model model = new Model();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            List<Vec3> vertex = new LinkedList<>();
            List<Vec3> vertexNormal = new LinkedList<>();
            List<Vec2> textureCoord = new LinkedList<>();
            List<Face> faces = new LinkedList<>();

            String line;

            while ((line = br.readLine()) != null) {
                String[] lineArgs = line.split("\\s+");
                if (line.startsWith("v ")) {
                    vertex.add(new Vec3(
                            Float.parseFloat(lineArgs[1]),
                            Float.parseFloat(lineArgs[2]),
                            Float.parseFloat(lineArgs[3])));
                } else if (line.startsWith("vn ")) {
                    vertexNormal.add(new Vec3(
                            Float.parseFloat(lineArgs[1]),
                            Float.parseFloat(lineArgs[2]),
                            Float.parseFloat(lineArgs[3])));
                } else if (line.startsWith("vt ")) {
                    textureCoord.add(new Vec2(
                            Float.parseFloat(lineArgs[1]),
                            Float.parseFloat(lineArgs[2])));
                } else if (line.startsWith("f ")) {
                    String[] faceArgs1 = lineArgs[1].split("/");

                    String[] faceArgs2 = lineArgs[2].split("/");

                    String[] faceArgs3 = lineArgs[3].split("/");

                    faces.add(new Face(
                            new Vec3(
                                    Float.parseFloat(faceArgs1[0]),
                                    Float.parseFloat(faceArgs2[0]),
                                    Float.parseFloat(faceArgs3[0])
                            ),
                            new Vec3(
                                    Float.parseFloat(faceArgs1[1]),
                                    Float.parseFloat(faceArgs2[1]),
                                    Float.parseFloat(faceArgs3[1])
                            ),
                            new Vec3(
                                    Float.parseFloat(faceArgs1[2]),
                                    Float.parseFloat(faceArgs2[2]),
                                    Float.parseFloat(faceArgs3[2])
                            )));
                }
            }
            br.close();
            
            List<Vec3> indeces = new LinkedList<>();
            for(int i=0 ; i < faces.size() ; i++){
                indeces.add(faces.get(i).vertexIndices);
            }
            
            model.createModel(
                    ListUtils.vec3ListToFloat(vertex),
                    ListUtils.vec2ListToFloat(textureCoord),
                    ListUtils.vec3ListToFloat(vertexNormal),
                    ListUtils.vec3ListToByte(indeces));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

}
