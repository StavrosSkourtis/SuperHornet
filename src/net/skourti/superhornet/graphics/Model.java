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
public class Model implements Disposable{

    private int vao, vbo, ibo, tbo,cbo,nbo;
    private int count;

    public Model(){
        vao = vbo = ibo = tbo = cbo=nbo = -1;
    }
    
    public Model(int count) {
        this.count = count;
        vao = glGenVertexArrays();
    }
        
    public void createTexturedMesh(float[] vertices, byte[] indices, float[] textureCoordinates){
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.TCOORD_ATTRIB);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    public void createTexturedMesh(float[] vertices, float[] textureCoordinates){
        count = vertices.length/3;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.TCOORD_ATTRIB);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    
    
    public void createColorMesh(float [] vertices, byte[] indices , float[] color){
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);

        cbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, cbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(color), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.COLOR_ATTRIB, 4, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.COLOR_ATTRIB);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    public void createColorMesh(float [] vertices , float[] color){
        count = vertices.length/3;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);
        
        cbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, cbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(color), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.COLOR_ATTRIB, 4, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.COLOR_ATTRIB);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
    
    
    public void createModel(float [] vertices , float [] texCoords , float[] normals,byte [] indices){
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.VERTEX_ATTRIB);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(texCoords), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.TCOORD_ATTRIB);
        
        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(normals), GL_STATIC_DRAW);
        glVertexAttribPointer(ShaderProgram.NORMAL_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(ShaderProgram.NORMAL_ATTRIB);
        
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
        
    public void bind() {
        glBindVertexArray(vao);
        if (ibo > 0) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        }
    }

    public void unbind() {
        if (ibo > 0) {
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        }

        glBindVertexArray(0);
    }

    public void draw() {
        if (ibo > 0) {
            glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
        } else {
            glDrawArrays(GL_TRIANGLES, 0, count);
        }
    }

    public void render() {
        bind();
        draw();
    }

    @Override
    public void dispose() {
        unbind();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vbo);
        if(ibo>0)
            glDeleteBuffers(ibo);
        if(tbo>0)
            glDeleteBuffers(tbo);
        if(cbo>0)
            glDeleteBuffers(cbo);
    }

}
