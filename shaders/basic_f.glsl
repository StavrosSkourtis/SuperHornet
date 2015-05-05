#version 330 core

in DATA{
    vec2 tc;
} vs_out;

out vec4 color;

uniform sampler2D tex;

void main() {
    color = texture( tex, vs_out.tc );
}
