
varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_sampler2D;
uniform sampler2D u_texture1;
uniform vec4 u_oldcolor;
uniform vec4 u_newcolor;
uniform vec2 u_cameraPos;

vec2 test;

void main(){
	
	
	test.x = test.x + 0.001f;
	test.y = test.y + 0.001f;
	
	vec4 color = texture2D( u_sampler2D , v_texCoord0  ) ;
	vec4 color2 = texture2D( u_texture1 , v_texCoord0  );
	float threshold = 0.005f;
	if(color.r <= (u_oldcolor.r + threshold) && color.g <= (u_oldcolor.g + threshold) && color.b <= (u_oldcolor.b + threshold)  
	&& color.r >= (u_oldcolor.r - threshold) && color.g >= (u_oldcolor.g - threshold) && color.b >= (u_oldcolor.b - threshold)){
		color.rgb = u_newcolor.rgb * color2.rgb ;	
	}
	
	gl_FragColor = color ;
	
}