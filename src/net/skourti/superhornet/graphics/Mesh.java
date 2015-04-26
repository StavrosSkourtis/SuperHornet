/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.graphics;

import net.skourti.superhornet.utils.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author Stavros
 */
public class Mesh implements Disposable {
    float [] vertices,color,normals,uv;
    int [] indices;
    
    private Texture texture;
    private int vao, vbo, ibo, tbo, cbo, nbo;
    private int count;

    public Mesh() {
        vao = vbo = ibo = tbo = cbo = nbo = -1;
    }
    
    /**
     * Creates a mesh with the given parameters
     * vertices array cannot be null , the others can
     * @param vertices
     * @param color
     * @param normals
     * @param uv
     * @param indices 
     */
    public void create(float[] vertices, float[] color, float[] normals, float[] uv, int[] indices) {
        this.vertices = vertices;
        this.color = color;
        this.normals = normals;
        this.uv = uv;
        this.indices = indices;
        
        if (indices != null) {
            count = indices.length;
        } else {
            count = vertices.length / 3;
        }

        /*
         Generate Vertex Array buffer
         */
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        /*
         Bind the vertices to the vertex vbo
         */
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);

        /*
         If there are color data bind them
         */
        if (color != null) {
            cbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, cbo);
            glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(color), GL_STATIC_DRAW);
            glVertexAttribPointer(ShaderProgram.COLOR_ATTRIB, 4, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(ShaderProgram.COLOR_ATTRIB);
        }

        /*
         If there are normals bind them
         */
        if (normals != null) {
            nbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, nbo);
            glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(normals), GL_STATIC_DRAW);
            glVertexAttribPointer(ShaderProgram.NORMAL_ATTRIB, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(ShaderProgram.NORMAL_ATTRIB);
        }

        /*
         If there are texture coordinates bind them
         */
        if (uv != null) {
            tbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, tbo);
            glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(uv), GL_STATIC_DRAW);
            glVertexAttribPointer(ShaderProgram.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(ShaderProgram.TCOORD_ATTRIB);
        }

        /*
         If there are indeces bind them
         */
        if (indices != null) {
            ibo = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createIntBuffer(indices), GL_STATIC_DRAW);
            
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        }
         
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    public void setTexture(Texture texture){
        this.texture = texture;
    }

    /**
     * Binds vertex array buffer
     */
    public void bind() {
        glBindVertexArray(vao);
        if (ibo > 0) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        }
    }
    
    /**
     * Unbinds vertex array buffer
     */
    public void unbind() {
        if (ibo > 0) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        }

        glBindVertexArray(0);
    }
    
    /**
     * Draws the mesh
     */
    public void draw() {
        
        if (ibo > 0) {
            glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
        } else {
            glDrawArrays(GL_TRIANGLES, 0, count);
        }
    }
    
    /**
     * renders the mesh
     */
    public void render(ShaderProgram shader) {
        if (texture != null) {
            texture.bind(shader);
        }
        bind();
        draw();
    }
    
    
    /**
     * Disposes the mesh
     */
    @Override
    public void dispose() {
        unbind();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vbo);
        if (ibo > 0) {
            glDeleteBuffers(ibo);
        }
        if (tbo > 0) {
            glDeleteBuffers(tbo);
        }
        if (cbo > 0) {
            glDeleteBuffers(cbo);
        }
        if (nbo > 0 ){
            glDeleteBuffers(nbo);
        }  
    }

}
