/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils;

import com.hackoeur.jglm.Mat4;
import javax.vecmath.Matrix4f;

/**
 *
 * @author Stavros
 */
public class MatrixConverter {

    public static Mat4 convert(Matrix4f mat) {
        return new Mat4(
                mat.m00, mat.m10, mat.m20, mat.m30,
                mat.m01, mat.m11, mat.m21, mat.m31,
                mat.m02, mat.m12, mat.m22, mat.m32,
                mat.m03, mat.m13, mat.m23, mat.m33
        );

    }

    public static Matrix4f convert(Mat4 mat) {
        float[] f = new float[]{
            mat.m00, mat.m10, mat.m20, mat.m30,
            mat.m01, mat.m11, mat.m21, mat.m31,
            mat.m02, mat.m12, mat.m22, mat.m32,
            mat.m03, mat.m13, mat.m23, mat.m33
        };

        return new Matrix4f(f);

    }
}
