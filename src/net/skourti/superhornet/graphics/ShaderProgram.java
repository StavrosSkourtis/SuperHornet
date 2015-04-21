/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec3;
import java.util.HashMap;
import java.util.Map;
import net.skourti.superhornet.utils.BufferUtils;
import net.skourti.superhornet.utils.FileUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Stavros
 */
public final class ShaderProgram implements Disposable {
    
    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;
    public static final int COLOR_ATTRIB = 2;
    public static final int NORMAL_ATTRIB = 3;
    
    public static ShaderProgram modelShader;
    public static ShaderProgram colorShader;
    public static ShaderProgram textureShader;
    
    public static void loadAll(){
        colorShader = new ShaderProgram("shaders/vertex.glsl", "shaders/fragment.glsl");
        textureShader = new ShaderProgram("shaders/basic_v.glsl","shaders/basic_f.glsl");
        modelShader = new ShaderProgram("shaders/model_v.glsl","shaders/model_f.glsl");
    }
    
    public static void disposeAll(){
        colorShader.dispose();
        textureShader.dispose();
        modelShader.dispose();
    }
    
    
    public int ID;
    private int vertexShaderId;
    private int fragmentShaderId;
    private boolean enabled = false;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    /**
     * Creates the shader program and generates the id.
     *
     * @param vertexShader
     * @param fragmentShader
     */
    public ShaderProgram(String vertexShader, String fragmentShader) {
        this();
        attachVertexShader(vertexShader);
        attachFragmentShader(fragmentShader);

        link();
    }

    public ShaderProgram() {
        ID = glCreateProgram();
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }

        int result = glGetUniformLocation(ID, name);
        if (result == -1) {
            System.err.println("Could not find uniform variable '" + name + "'!");
        } else {
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) {
            bind();
        }
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) {
            bind();
        }
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) {
            bind();
        }
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vec3 vector) {
        if (!enabled) {
            bind();
        }
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Mat4 matrix) {
        if (!enabled) {
            bind();
        }
        glUniformMatrix4(getUniform(name), false, BufferUtils.createFloatBuffer(matrix.getFloatArray()));
    }

    public void bind() {
        glUseProgram(ID);
        enabled = true;
    }

    public void unbind() {
        glUseProgram(0);
        enabled = false;
    }

    /**
     * Attach the vertex shader to the program
     *
     * @param path the location of the shader file.
     */
    public void attachVertexShader(String path) {
        String vertexShader = FileUtils.readFile(path);

        //create the shader and give opengl it's source
        vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderId, vertexShader);

        glCompileShader(vertexShaderId);

        if (glGetShaderi(vertexShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Error creating vertex shader\n"
                    + glGetShaderInfoLog(vertexShaderId, glGetShaderi(vertexShaderId, GL_INFO_LOG_LENGTH)));
        }

        // Attach the shader
        glAttachShader(ID, vertexShaderId);
    }

    /**
     * Attach the fragment shader to the program
     *
     * @param path the location of the shader file
     */
    public void attachFragmentShader(String path) {
        String fragmentShader = FileUtils.readFile(path);

        fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderId, fragmentShader);

        glCompileShader(fragmentShaderId);

        if (glGetShaderi(fragmentShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Error creating vertex shader\n"
                    + glGetShaderInfoLog(fragmentShaderId, glGetShaderi(fragmentShaderId, GL_INFO_LOG_LENGTH)));
        }

        glAttachShader(ID, fragmentShaderId);
    }

    /**
     * Link the shader program
     */
    public void link() {
        // Link this program
        glLinkProgram(ID);
        glValidateProgram(ID);
        // Check for linking errors
        if (glGetProgrami(ID, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Unable to link shader program:");
        }
    }


    /**
     * Dispose the shader
     */
    @Override
    public void dispose() {
        unbind();

        glDetachShader(ID, vertexShaderId);
        glDetachShader(ID, fragmentShaderId);
        glDeleteShader(vertexShaderId);
        glDeleteShader(fragmentShaderId);
        glDeleteProgram(ID);
    }

}
