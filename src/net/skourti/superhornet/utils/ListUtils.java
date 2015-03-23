/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils;

import com.hackoeur.jglm.Vec3;
import java.util.List;
import main.java.com.hackoeur.jglm.Vec2;

/**
 *
 * @author Stavros
 */
public class ListUtils {
    
    
    public static float[] vec3ListToFloat(List<Vec3> list){
        float [] array = new float[list.size()*3];
        
        for(int i=0; i < array.length ; i+=3){
            array [i] = list.get(i).x;
            array [i+1] = list.get(i).y;
            array [i+2] = list.get(i).z;
        }
        
        return array;        
    }
    
    public static float[] vec2ListToFloat(List<Vec2> list){
        float [] array = new float[list.size()*2];
        
        for(int i=0; i < array.length ; i+=2){
            array [i] = list.get(i).x;
            array [i+1] = list.get(i).y;
        }
        
        return array;        
    }
    
    public static byte[] vec3ListToByte(List<Vec3> list){
        byte [] array = new byte[list.size()*3];
        
        for(int i=0; i < array.length ; i+=3){
            array [i] = (byte)list.get(i).x;
            array [i+1] =(byte)list.get(i).y;
            array [i+2] = (byte)list.get(i).z;
        }
        
        return array; 
    }
}
