#version 330 core

layout(location = 0 ) in vec3 position;
layout(location = 1 ) in vec2 tc;

uniform  mat4 pr_matrix;

out DATA{
    vec2 tc;
} vs_out;

void main() {
    gl_Position = pr_matrix* vec4(position,1);
    
    vs_out.tc = tc;
}
