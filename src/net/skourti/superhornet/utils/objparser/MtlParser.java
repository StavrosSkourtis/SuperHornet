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

/**
 *
 * @author Stavros
 */
public class MtlParser {

    public HashMap<String, Material> parse(String path) {
        HashMap<String, Material> mats = new HashMap<>();
        String lastMaterial = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("newmtl")) {
                    /*
                     New material
                     */
                    Material material = new Material();
                    String params[] = line.split("\\s+");
                    
                    material.name = params[1];
                    mats.put(params[1], material);

                    lastMaterial = params[1];
                } else if (line.startsWith("Ka")) {
                    /*
                     Ka
                     */
                    String params[] = line.split("\\s+");

                    Vec3 v = new Vec3(Float.parseFloat(params[1]),
                            Float.parseFloat(params[2]),
                            Float.parseFloat(params[3]));

                    mats.get(lastMaterial).ka = v;

                } else if (line.startsWith("Kd")) {
                    /*
                     KD
                     */
                    String params[] = line.split("\\s+");

                    Vec3 v = new Vec3(Float.parseFloat(params[1]),
                            Float.parseFloat(params[2]),
                            Float.parseFloat(params[3]));

                    mats.get(lastMaterial).kd = v;
                } else if (line.startsWith("Ks")) {
                    /*
                     Ks
                     */
                    String params[] = line.split("\\s+");

                    Vec3 v = new Vec3(Float.parseFloat(params[1]),
                            Float.parseFloat(params[2]),
                            Float.parseFloat(params[3]));

                    mats.get(lastMaterial).ks = v;
                } else if (line.startsWith("illum")) {
                    /*
                     illum
                     */
                    String params[] = line.split("\\s+");

                    mats.get(lastMaterial).illum = Float.parseFloat(params[1]);
                } else if (line.startsWith("Ns")) {
                    /*
                     Ns
                     */
                    String params[] = line.split("\\s+");

                    mats.get(lastMaterial).illum = Float.parseFloat(params[1]);
                } else if (line.startsWith("map_Kd")) {
                    String params[] = line.split("\\s+");
                    
                    mats.get(lastMaterial).mapKd = new File(path).getParentFile().getAbsolutePath()+"/"+params[1];
                }

            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mats;
    }

}
