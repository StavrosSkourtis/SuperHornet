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
import main.java.com.hackoeur.jglm.Vec2;

/**
 *
 * @author Stavros
 */
public class ObjParser {

    private ObjLoader loader;
    private String currentGroup;

    public void parse(ObjLoader loader, File file) {
        this.loader = loader;
        // number of ignored lines
        int ignored = 0;
        try {
            // create the reader
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            /*
             Loop through every line in the file
             */
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("g ") || line.startsWith("o ")) {
                    // create a new group
                    createNewGroup(line);
                } else if (line.startsWith("v ")) {
                    // line contains vertex positions
                    parseVertex(line);
                } else if (line.startsWith("vt ")) {
                    // line contains texture positions
                    parseTextureCoord(line);
                } else if (line.startsWith("vn ")) {
                    // line contains vertex normal
                    parseNormal(line);
                } else if (line.startsWith("f")) {
                    // line containts a face
                    parseFace(line);
                } else if (line.startsWith("mtllib")) {
                    // load material file
                    loadMaterialFile(file, line);
                } else if (line.startsWith("usemtl")) {
                    // set the current group to use the specified material
                    
                    useMaterial(line);
                } else {
                    ignored++;
                }
            }

            //close reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * parses a vertex
     *
     * @param line
     */
    private void parseVertex(String line) {
        String[] arg = line.split("\\s+");
        float rand = (float) Math.random() / 2;

        loader.vertices.add(new Vec3(Float.parseFloat(arg[1]), Float.parseFloat(arg[2]), Float.parseFloat(arg[3])));
    }

    /**
     * Parses a texture coordinate
     *
     * @param line
     */
    private void parseTextureCoord(String line) {
        String[] arg = line.split("\\s+");

        loader.uvs.add(new Vec2(Float.parseFloat(arg[1]), Float.parseFloat(arg[2])));
    }

    /**
     * Parses a normal
     *
     * @param line
     */
    private void parseNormal(String line) {
        String[] arg = line.split("\\s+");

        loader.normals.add(new Vec3(Float.parseFloat(arg[1]), Float.parseFloat(arg[2]), Float.parseFloat(arg[3])));
    }

    /**
     * Parses a face
     *
     * @param line
     */
    private void parseFace(String line) {
        String[] arg = line.split("\\s+");

        String[] par1 = arg[1].split("/");
        String[] par2 = arg[2].split("/");
        String[] par3 = arg[3].split("/");

        /*
         we subtract 1 because the indeces in the obj format are 1 based
         it is more convienient for me for them to be 0-based
         */
        float x, y, z;

        x = par1[0].equals("") ? 0 : Float.parseFloat(par1[0]);
        y = par1[1].equals("") ? 0 : Float.parseFloat(par1[1]);
        z = par1[2].equals("") ? 0 : Float.parseFloat(par1[2]);

        Vec3 v1 = new Vec3(x, y, z);

        x = par2[0].equals("") ? 0 : Float.parseFloat(par2[0]);
        y = par2[1].equals("") ? 0 : Float.parseFloat(par2[1]);
        z = par2[2].equals("") ? 0 : Float.parseFloat(par2[2]);

        Vec3 v2 = new Vec3(x, y, z);

        x = par3[0].equals("") ? 0 : Float.parseFloat(par3[0]);
        y = par3[1].equals("") ? 0 : Float.parseFloat(par3[1]);
        z = par3[2].equals("") ? 0 : Float.parseFloat(par3[2]);

        Vec3 v3 = new Vec3(x, y, z);

        loader.data.get(currentGroup).faces.add(new Face(v1, v2, v3));
    }

    /**
     * Loads the materials from the .mtl file
     *
     * @param file
     * @param line
     */
    public void loadMaterialFile(File file, String line) {
        String args[] = line.split("\\s+");

        MtlParser parser = new MtlParser();
        
        loader.materials = parser.parse(file.getParentFile().getAbsolutePath() + "/" + args[1]);
        
    }

    /**
     * Creates a new group
     *
     * @param line
     */
    public void createNewGroup(String line) {
        String args[] = line.split("\\s+");

        ObjDataGroup group = new ObjDataGroup();
        currentGroup = args[1];
        loader.groups.add(args[1]);
        loader.data.put(args[1], group);
    }
    
    
    /**
     * set the material of the current group
     * @param line 
     */
    public void useMaterial(String line){
        String args[] = line.split("\\s+");
        
        if(loader.data.get(currentGroup).material !=null)
            createNewGroup(line);
        
        loader.data.get(currentGroup).material = args[1];
    }
}
