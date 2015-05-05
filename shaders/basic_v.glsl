#version 130

in vec3 position;
in vec2 textureUv;
in vec4 color;
in vec3 normal;

uniform  mat4 pr_matrix;


out vec2 tc;

void main() {
    gl_Position = pr_matrix* vec4(position,1);
    
    tc = textureUv;
}
