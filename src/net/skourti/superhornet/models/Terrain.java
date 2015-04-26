/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.models;

import net.skourti.superhornet.graphics.Model;
import net.skourti.superhornet.graphics.Screen;
import net.skourti.superhornet.utils.objparser.ObjLoader;

/**
 *
 * @author Stavros
 */
public class Terrain {
    
    
    public Terrain(Screen screen){
        
        ObjLoader loader = new ObjLoader();
        
        Model map = loader.load("res/SnowTerrain/terrain.obj");
        map.scale(50);
        screen.addEntity(map);
        
    }
}
