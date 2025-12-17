/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labprojectp1_tatianagarcia;

import java.util.Scanner;
//HS,js,DHLS,!S
/**
 *
 * @author tatig
 */
public class LabProjectP1_TatianaGarcia {
    public static Scanner leer = new Scanner(System.in);
    public static String obstaculo = "";
    public static boolean caja=false;
    public static int steeb_i = 3, steeb_j =13; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Investigar: split && equals, variables globales
        char c;
        do{
            System.out.println( "\n\u001B[33m" + "Bienvenid@ a la entrega de paquetes de DHL\n" + "\u001B[0m");
            System.out.println("Debes hacer que "+"\u001B[33m" + "Steeb 'S'" + "\u001B[0m" +" entregue \ncada una de las cajas de dulce representadas con "+"\u001B[34m" + "'j'" + "\u001B[0m"+" "
                        + "\na los almacenes de "+"\u001B[33m" + "DHL ('D','H','L')" + "\u001B[0m" +".\nPero cuidado, hay obstaculos "+"\u001B[31m" + "'X','O','o','0','^'" + "\u001B[0m"+"\u001B[32m" + ",'/','\\','|'" + "\u001B[0m"
                        + "\nque te causaran un "+"\u001B[31m" + "GAME OVER" + "\u001B[0m"+" \n");
            
            
            String[][] mat = new String[24][24];
            String[][] matriz = generarMatriz(mat);
            steeb_i = 3; steeb_j =13; 
            
            
            boolean win = false;
            boolean lost = false; 
            
            
            while(!win&&!lost){
                printMatriz(matriz);

                System.out.println("\nComandos: \nU:Arriba\nD:Abajo"
                        + "\nL:Izquierda\nR:Derecha\nF:Recoger/Poner caja");

                String cadena;
                do{
                    System.out.print("\nIngrese los comandos separados por ',':");
                    cadena = leer.next();
                }while(!(cadena.contains(",")));
                System.out.println("");
                
                String array[]= cadena.split(",");
                matriz = readCommand(array, matriz);
                boolean delivered = searchDelivered(matriz);
                lost = searchLost(matriz);
                
                if (!lost&&delivered) {
                    win = true; 
                    System.out.println("\u001B[32m" + "\n\nMission Accomplished" + "\u001B[0m");
                    printMatriz(matriz);
                    System.out.println("\u001B[32m" + "\n\nFelicidades han logrado que Steve entrege los dulces a DHL para esta navidad" + "\u001B[0m");
                    System.out.println("");
                }
            }
            if (lost) {
                System.out.println("\u001B[31m" + "\n\nGAME OVER: Mission Failed" + "\u001B[0m");
                printMatriz(matriz);
                if (obstaculo=="adorno") {
                    System.out.println("\u001B[31m" + "\n\nUps, parece que has chocado con un adorno del arbol de Navidad" + "\u001B[0m");
                    
                }else if (obstaculo=="arbol") {
                    System.out.println("\u001B[31m" + "\n\nOh no, hubo un choque contra el arbol de Navidad" + "\u001B[0m");
                    
                }else{
                    System.out.println("\u001B[31m" + "\n\nUpa, has chocado contra una pared :( " + "\u001B[0m");
                    
                }

            }
            leer = new Scanner(System.in);
            System.out.print("Desea volver a jugar? [s/n]: ");
            String cadena = leer.nextLine();
            c = cadena.toLowerCase().charAt(0);
        }while(c =='s');
        
        
        
        
    }
    //Acciones/Verificaciones de Comandos
    public static boolean searchDelivered(String[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                String s = matriz[i][j];
                if (s=="D"||s=="H"||s=="L"||s=="j") {
                    return false;
                }
            }
        }
        return true;  
    }
    public static boolean searchLost(String [][]mapa){
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                String s = mapa[i][j];
                if (s=="!S") {
                    return true;
                }
            }
        }
        return false;  
    }
    
    public static String [][] readCommand(String[] comando, String [][] mapa){
//        System.out.println("\nComandos: \nU:Arriba\nD:Abajo"
//                        + "\nL:Izquierda\nR:Derecha\nF:Recoger/Poner cajas");
        for (int i = 0; i < comando.length; i++) {
            String command = comando[i];
            if (command.equals("U")) {
                if (!(steeb_i==0)) {
                    
                    obstaculo = searchObstacle((steeb_i-1),steeb_j,mapa);
                    if (obstaculo=="") {
                        mapa = isObject((steeb_i-1),steeb_j,mapa);
                        mapa = movement((steeb_i),steeb_j,mapa);
                        
                    }else{
                        mapa[(steeb_i-1)][steeb_j] = "!S";
                        mapa[(steeb_i)][steeb_j] = " ";
                        break;
                    }
                    steeb_i--;
                }
                
            }else if (command.equals("D")) {
                if (!(steeb_i==23)) {
                    
                    obstaculo = searchObstacle((steeb_i+1),steeb_j,mapa);
                    if (obstaculo=="") {
                        mapa = isObject((steeb_i+1),steeb_j,mapa);
                        mapa = movement((steeb_i),steeb_j,mapa);
                        
                    }else{
                        mapa[(steeb_i+1)][steeb_j] = "!S";
                        mapa[(steeb_i)][steeb_j] = " ";
                        break;
                    }
                    steeb_i++;
                }
                
            }else if (command.equals("L")) {
                if (!(steeb_j==0)) {
                    
                    obstaculo = searchObstacle((steeb_i),(steeb_j-1),mapa);
                    if (obstaculo=="") {
                        mapa = isObject((steeb_i),(steeb_j-1),mapa);
                        mapa = movement((steeb_i),steeb_j,mapa);
                        
                    }else{
                        mapa[(steeb_i)][(steeb_j-1)] = "!S";
                        mapa[(steeb_i)][steeb_j] = " ";
                        break;
                    }
                    steeb_j--;
                }
                
            }else if (command.equals("R")) {
                if (!(steeb_j==23)) {
                    
                    obstaculo = searchObstacle((steeb_i),(steeb_j+1),mapa);
                    if (obstaculo=="") {
                        mapa = isObject((steeb_i),(steeb_j+1),mapa);
                        mapa = movement((steeb_i),steeb_j,mapa);
                        
                    }else{
                        mapa[(steeb_i)][(steeb_j+1)] = "!S";
                        mapa[(steeb_i)][steeb_j] = " ";
                        break;
                    }
                    steeb_j++;
                }
                
            }else if (command.equals("F")) {
                mapa = Game((steeb_i),steeb_j,mapa);
            }
            //printMatriz(mapa);
        }
        //printMatriz(mapa);
        return mapa;
    }
    public static String [][] Game (int i, int j, String [][]mapa){
        
        String object = mapa[i][j];
        if (object =="jS") {
            if (!caja) {
                mapa[i][j]="S";
                caja = true;
            }
            else{
                System.out.println("\u001B[31m" + "No hay espacio para mas de una caja en el vehiculo" + "\u001B[0m");
            }
        }
        else if (object =="DS"||object =="HS"||object =="LS") {
            if (caja) {
                mapa[i][j]="S";
                caja = false;
            }else{
                System.out.println("\u001B[31m" + "No tienes una caja para entregar" + "\u001B[0m");
            }
        }else{
            if (caja) {
                mapa[i][j]="jS";
            }else{
                System.out.println("\u001B[31m" + "No se puede interactuar en esta casila" + "\u001B[0m");
            }
        }
        
        return mapa;
    }
    public static String [][] movement (int i, int j, String [][]mapa){
        String object = mapa[i][j];
        if (object =="jS") {
            mapa[i][j]="j";
        }
        else if (object =="DS") {
            mapa[i][j]="D";
        }
        else if(object =="HS") {
            mapa[i][j]="H";
        }
        else if (object =="LS") {
            mapa[i][j]="L";
        }else{
            mapa[i][j]=" ";
        }
        
        return mapa;
    }
    public static String [][] isObject(int i, int j, String [][]mapa){
        String object = mapa[i][j];
        if (object =="j") {
            mapa[i][j]="jS";
        }
        else if (object =="D") {
            mapa[i][j]="DS";
        }
        else if(object =="H") {
            mapa[i][j]="HS";
        }
        else if (object =="L") {
            mapa[i][j]="LS";
        }
        else{
            mapa[i][j]="S";
        }
        
        return mapa;
    }
    
    public static String searchObstacle(int i, int j, String [][]mapa){
        String obstaculo = mapa[i][j];
        if (obstaculo=="/"||obstaculo=="|"||obstaculo=="\\") {
            return "arbol";
        }else if (obstaculo=="O"||obstaculo=="o"||obstaculo=="0"||obstaculo=="^"){
            return "adorno";
        }else if (obstaculo=="X"){
            return "pared";
        }else{
            return "";
        }
    }
    
    public static void printMatriz(String[][]matriz){
        System.out.println("");
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                String s = matriz[i][j];
                if (s.equals("/") || s.equals("-") || s.equals("|") || s.equals("\\")) {
                    System.out.print("[" + "\u001B[32m" + s + "\u001B[0m" + "]");//verde
                } else if (s.equals("O") || s.equals("o") || s.equals("0") || s.equals("^")||s.equals("X")) {
                    System.out.print("[" + "\u001B[31m" + s + "\u001B[0m" + "]");//rojo
                } else if (s.equals("D")||s.equals("H")||s.equals("L")||s.equals("S")||s.equals("!S")
                        ||s.equals("DS")||s.equals("HS")||s.equals("LS")) {
                    System.out.print("[" + "\u001B[33m" + s + "\u001B[0m" + "]");//amarillo
                } else if (s.equals("S")||s.equals("!S")) {
                    System.out.print("[" + "\u001B[36m" + s + "\u001B[0m" + "]");//turquesa
                } else if (!s.equals(" ")) {
                    System.out.print("[" + "\u001B[34m" + s + "\u001B[0m" + "]");//azul
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }
    }
    
    public static String[][] generarMatriz(String[][] mat) {
        mat = new String[24][24];
        
        int c = 2;
        
        // Inicializar matriz
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                mat[i][j] = " ";
            }
        }
        
        int centro = 12;
        // Tronco
        mat[18-c][centro] = "|";
        mat[19-c][centro] = "|";
        mat[17-c][centro-1] = "|";
        mat[18-c][centro-1] = "|";
        mat[19-c][centro-1] = "|";

        for (int j = 4; j < 20; j++) {
            mat[16-c][j] = "-";
            if ((j)==8||(j)==12) {
                mat[16-c][j] = " ";
            }
        }
        
        int con=1;
        for (int i = 8; i < 16; i++) {
            
            mat[i-c][centro - con] = "/";
            mat[i-c][centro-1+con] = "\\";
            if ((i-c)==7) {
                mat[i-c][centro - con] = " ";
            }
            con++;
        }
        for (int i = 20; i < 24; i++) {
            mat[1][i]="X";
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (!((i==1||i==2)&&j==2)) {
                    mat[i][j]="X";
                }
            }
        }
        for (int i = 8; i < 11; i++) {
            for (int j = 22; j < 24; j++) {
                if (!((j==21||j==22)&&i==9)) {
                    mat[i][j]="X";
                }
            }
        }
        for (int i = 11; i < 13; i++) {
            for (int j = 1; j < 4; j++) {
                if (!((j==21||j==22)&&i==9)) {
                    mat[i][j]="X";
                }
            }
        }
        mat[21][3]="X";mat[20][3]="X";mat[21][4]="X";
        mat[19][20]="X";mat[19][21]="X";mat[18][21]="X";


        mat[9-c][centro] = "O";
        mat[10-c][centro - 1] = "j";
        mat[10-c][centro + 1] = " ";
        mat[11-c][centro - 2] = "o";
        mat[11-c][centro] = " ";
        mat[11-c][centro + 2] = "j";
        mat[12-c][centro - 3] = "0";
        mat[12-c][centro - 1] = "j";
        mat[12-c][centro + 1] = " ";
        mat[12-c][centro + 3] = "j";
        mat[13-c][centro - 4] = "^";
        mat[13-c][centro - 2] = "^";
        mat[13-c][centro] = "o";
        mat[13-c][centro + 2] = "o";
        mat[14-c][centro - 5] = "O";
        mat[14-c][centro - 3] = "j";
        mat[14-c][centro - 1] = "O";
        mat[14-c][centro + 1] = "^";
        mat[15-c][centro - 6] = "0";
        mat[15-c][centro - 2] = "o";
        mat[15-c][centro] = "j";
        mat[15-c][centro + 2] = "O";
        mat[15-c][centro + 4] = "0";
        
        mat[2][2] = "D";mat[0][23]="H";mat[12][2]="L";
        mat[9][22]= "D";mat[20][4]= "H";mat[18][20]="L";
        mat[3][13]="S";
        
        
        return mat;
    }

}
