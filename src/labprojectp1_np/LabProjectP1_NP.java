/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labprojectp1_np;

import java.util.Scanner;
//HS,js,DHLS,!S
/**
 *
 * @author tatig
 */
public class LabProjectP1_NP {
    public static Scanner leer = new Scanner(System.in);
    public static String obstaculo = "";
    public static boolean caja=false;
    public static int steeb_i = 3, steeb_j =13; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Investigar: split && equals, variables globales
        
        
        
        
        
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
