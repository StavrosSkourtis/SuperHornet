#version 330 core

// Input vertex data, different for all executions of this shader.
in vec3 position;

// Input vertex color 

in vec4 color;

out vec4 fragmentColor;

// Values that stay constant for the whole mesh.
uniform mat4 MVP;

void main(){
        fragmentColor = color;
	
	gl_Position =  MVP*vec4(position,1);
}

