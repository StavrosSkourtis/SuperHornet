#version 130


in vec2 tc;


out vec4 color;

uniform sampler2D tex;

void main() {
    color = texture( tex, tc );
}
