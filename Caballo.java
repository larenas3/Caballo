import java.util.Scanner;
/**
 * Este programa halla la menor cantidad de saltos que debe dar un caballo
 * para llegar desde una casilla de origen hasta una casilla de destino
 * indicadas por el usuario.
 * 
 * @author Lida Heliana Arenas Mogollón
 * @version 8/8/2017
 */
public class Caballo
{
    //Variable de entrada por teclado
    private Scanner entrada;
    
    //Variables que indican el tamaño del tablero
    private int numFil = 8, numCol = 8;
    
    //Variables que indican la coordenada de inicio
    private int iniX, iniY;
    
    //Variables que indican la coordenada de destino
    private int finX, finY;
    
    //Variables que guardan las coordenadas del caballo
    private int cabX, cabY;
    
    //Variable que guarda el numero de pasos y la direccion de cada paso
    private int pasos, dirX = 1, dirY = 1;
    
    //Método principal
    public static void main(String args[]){
        Caballo c = new Caballo();
    }
    
    //Constructor de la clase Caballo
    public Caballo(){
        //Inicializacion de variables
        entrada = new Scanner(System.in);
        pasos = 0;
        //Lectura de coordenadas
        leerCoordenadas();
        //Las coordenadas iniciales del caballo son las mismas
        //de la casilla de inicio
        cabX = iniX;
        cabY = iniY;
        int contador = 0;

        //Se verifica si el caballo llegó a su destino
        while((cabX != finX || cabY != finY) && contador < numFil*numCol){
            darPaso();
            pasos++;
            contador++;
        }
        //imprime la cantidad de pasos
        System.out.printf("%d pasos", pasos);
    }
    
    //metodo que pide las coordenadas al usuario
    public void leerCoordenadas(){
        do{
            iniX = entrada.nextInt();
            iniY = entrada.nextInt();
            finX = entrada.nextInt();
            finY = entrada.nextInt();
        }while(!coordenadaValida(iniX,iniY) && !coordenadaValida(finX,finY));
    }
    
    //metodo que valida que la coordenada dada sea valida
    public boolean coordenadaValida(int x, int y){
        return (x>=0 && x<numCol) && (y>=0 && y<numFil);
    }

    //Metodo que permite al caballo avanzar un paso
    public void darPaso(){
        //Variables en las que se calcula la dirección y el incremento
        //para el siguiente paso
        int difX, difY, incX, incY;
        
        //Obtener las distancias de X y Y entre el caballo y el destino
        difX = finX - cabX;
        difY = finY - cabY;
        
        //Determinar la dirección del movimiento y el incremento en cada
        //coordenada
        if(difX != 0){
            dirX = difX / Math.abs(difX);
        }else{
            dirX *= -1;
        }
        if(difY != 0){
            dirY = difY / Math.abs(difY);
        }else{
            dirY *= -1;
        }
        if(difX >= difY){
            incX = 2;
            incY = 1;
        }else{
            incX = 1;
            incY = 2;
        }
        
        //Verificar que el nuevo paso sea dentro del tablero
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            incX=1;
            incY=2;
        }
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            dirX *= -1;
        }
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            incX=2;
            incY=1;
        }
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            dirY *= -1;
        }
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            incX=1;
            incY=2;
        }
        if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
            dirX *= -1;
        }
        //Varificar que el proximo paso es a una casilla valida
        if(!casillaValida(cabX+incX*dirX, cabY+incY*dirY)){
            if(Math.abs(incX) == 1){
                dirX *= -1;
                if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
                    dirX *= -1;
                    dirY *= -1;
                    incX = 2;
                    incY = 1;
                }
            }else{
                dirY *= -1;
                if(!coordenadaValida(cabX+incX*dirX, cabY+incY*dirY)){
                    dirX *= -1;
                    dirY *= -1;
                    incX = 1;
                    incY = 2;
                }
            }
        }
        
        //Guardar el avance del caballo
        cabX += incX * dirX;
        cabY += incY * dirY;

        //Verificar caso especial
        if((Math.abs(difX)==2&&difY==0)||(Math.abs(difY)==2&&difX==0)){
            pasos++;
            cabX = finX;
            cabY = finY;
        }
    }
    
    public boolean casillaValida(int x, int y){
        if(Math.abs(finX-x)==1&&(Math.abs(finY-y)==1||Math.abs(finY-y)==0)){
            return false;
        }
        if(Math.abs(finY-y)==1&&(Math.abs(finX-x)==1||Math.abs(finX-x)==0)){
            return false;
        }
        if(Math.abs(finX-x)==2&&Math.abs(finY-y)==2){
            return false;
        }
        return true;
    }
}
