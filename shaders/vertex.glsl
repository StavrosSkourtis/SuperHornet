#version 330 core

// Input vertex data, different for all executions of this shader.
layout( location = 0 ) in vec3 position;

// Input vertex color 

layout( location = 2 ) in vec4 color;

out vec4 fragmentColor;

// Values that stay constant for the whole mesh.
uniform mat4 pr_matrix;

void main(){
        fragmentColor = color;
	
	gl_Position =  pr_matrix*vec4(position,1);
}

