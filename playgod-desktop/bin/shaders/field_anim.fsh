
varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_sampler2D;
uniform sampler2D u_overlay;

uniform vec2 u_flow;

void main(){



	vec4 color = texture2D( u_sampler2D , vec2(v_texCoord0.x + u_flow.x, v_texCoord0.y + u_flow.y)) ;
	vec4 color2 = texture2D( u_overlay , vec2(v_texCoord0.y - u_flow.y, v_texCoord0.x - u_flow.x)) ;
	//color2.a = (color2.r + color2.g + color2.b) / 3;
	
	gl_FragColor = clamp( color2 , normalize(color * color2) , color );
	
}