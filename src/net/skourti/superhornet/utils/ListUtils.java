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

    /**
     * Converts a list of 3d vectors to float array
     * @param list
     * @return 
     */
    public static float[] vec3ListToFloat(List<Vec3> list) {
        float[] array = new float[list.size() * 3];

        
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            array[j] = list.get(i).x;
            j++;
            array[j] = list.get(i).y;
            j++;
            array[j] = list.get(i).z;
            j++;
        }

        return array;
    }
    
    /**
     * Converts a list of 2d vectors to a float array
     * @param list
     * @return 
     */
    public static float[] vec2ListToFloat(List<Vec2> list) {
        float[] array = new float[list.size() * 2];

        
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            array[j] = list.get(i).x;
            j++;
            array[j] = list.get(i).y;
            j++;
        }

        return array;
    }
    
    /**
     * Converts a list of bytes to a byte array
     * @param list
     * @return 
     */
    public static byte[] listToByte(List<Byte> list) {
        byte[] bytes = new byte[list.size()];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }
    
    
    /**
     * Converts a list of integers to a integer array
     * @param list
     * @return 
     */
    public static int[] listToInt(List<Integer> list) {
        int[] ints = new int[list.size()];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = list.get(i);
        }

        return ints;
    }
    
    
    /**
     * converts a list of floats to a float array
     * @param list
     * @return 
     */
    public static float[] listToFloat(List<Float> list) {
        float[] floats = new float[list.size()];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = list.get(i);
        }

        return floats;
    }

    /**
     * prints an array of floats
     * @param array 
     */
    public static void print(float[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    
    /**
     * prints an array of integers
     * @param array 
     */
    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    
    /**
     * used for texture coordinate to invert the y value
     *
     * @param uv
     */
    public static void uvInvert(float[] uv) {
        for (int i = 1; i < uv.length; i += 2) {
            uv[i] = 1.0f - uv[i];
        }
    }
    public static void uv2Invert(float [] uv){
        for (int i = 0; i < uv.length; i += 2) {
            uv[i] = 1.0f - uv[i];
        }
    }
    
    public static void absolute(float [] uv){
        for (int i = 0; i < uv.length; i ++) {
            uv[i] = uv[i]<0?-uv[i]:uv[i];
        }
    }
}
