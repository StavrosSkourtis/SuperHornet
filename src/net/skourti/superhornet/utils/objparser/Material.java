/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils.objparser;

import com.hackoeur.jglm.Vec3;

/**
 *
 * @author Stavros
 */
public class Material {
    public String name;
    public Vec3 ka;
    public Vec3 kd;
    public Vec3 ks;
    public float illum;
    public float ns;
    public String mapKd;
    public String map_bump;
    public String bump;
    public String map_opacity;
}
