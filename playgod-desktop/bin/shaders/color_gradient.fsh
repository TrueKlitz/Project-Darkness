
varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_sampler2D;
uniform vec4 u_oldcolor;
uniform vec4 u_newcolor;
uniform vec3 u_noise;

void main(){

	vec4 color = texture2D( u_sampler2D , v_texCoord0 ) ;
	float threshold = 0.005f;
	if(color.r <= (u_oldcolor.r + threshold) && color.g <= (u_oldcolor.g + threshold) && color.b <= (u_oldcolor.b + threshold)  
	&& color.r >= (u_oldcolor.r - threshold) && color.g >= (u_oldcolor.g - threshold) && color.b >= (u_oldcolor.b - threshold)){
		color.rgb = u_newcolor.rgb + vec3(v_texCoord0, 0.1);
	}
	
	gl_FragColor = color ;
	
}