package com.gbstudios.world;

public class Camera {
	public static int x = 0;
	public static int y = 0;
	
	//tirar fundo preto
	public static int clamp(int Atual,int Min,int Max){
		if(Atual < Min){
			Atual = Min;
		}
		
		if(Atual > Max) {
			Atual = Max;
		}
		
		return Atual;
	}
}