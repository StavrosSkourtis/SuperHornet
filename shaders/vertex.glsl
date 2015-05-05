#version 130

// Input vertex data, different for all executions of this shader.
in vec3 position;
in vec2 textureUv;
// Input vertex color 
in vec4 color;
in vec3 normal;

out vec4 fragmentColor;

// Values that stay constant for the whole mesh.
uniform mat4 pr_matrix;

void main(){
        fragmentColor = color;
	
	gl_Position =  pr_matrix*vec4(position,1);
}

