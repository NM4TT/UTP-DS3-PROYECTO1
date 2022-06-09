/**
 * 
 * hecho por MANUEL MATUTE
 */

import java.util.*;

public class Peces {
    
    private int posicionX;
    private int posicionY;
    private int punto;
    private int[][] perimetro;
    private Random rnd;

    public Peces(){

        rnd = new Random();
        perimetro = new int[8][2];
        posicionX = 0;
        posicionY = 0;
        punto = 0;
        

    }

    public void moverCardumen(){
        punto = rnd.nextInt(100);
        posicionX = 400+45*(punto%10);
        posicionY = 40+45*(punto/10);

        //Definicion de perimetro de asustar cardumen
        perimetro[0][0] = posicionX - 45;
        perimetro[0][1] = posicionY - 45;
        perimetro[1][0] += perimetro[0][0] + 45;
        perimetro[1][1] = perimetro[0][1];
        perimetro[2][0] += perimetro[0][0] + 45*2;
        perimetro[2][1] = perimetro[0][1];

        perimetro[3][0] = perimetro[0][0];
        perimetro[3][1] = perimetro[0][1] + 45;
        perimetro[4][0] = perimetro[0][0] + 45*2;
        perimetro[4][1] = perimetro[3][1];

        perimetro[5][0] = perimetro[0][0];
        perimetro[5][1] = perimetro[0][1] + 45*2;
        perimetro[6][0] = perimetro[5][0] + 45;
        perimetro[6][1] =  perimetro[5][1];
        perimetro[7][0] = perimetro[6][0] + 45;
        perimetro[7][1] = perimetro[5][1];
    }
    
    public void setPosicionX(int x){
        posicionX = x;
    }

    public void setPosicionY(int y){
        posicionY = y;
    }

    public void setPunto(int n){
        punto = n;
    }

    public void setPerimetro(int[][] p){
        perimetro = p;
    }
    
    public int getPosicionX(){
        return posicionX;
    }

    public int getPosicionY(){
        return posicionY;
    }

    public int getPunto(){
        return punto;
    }

    public int[][] getPerimetro(){
        return perimetro;
    }

}
