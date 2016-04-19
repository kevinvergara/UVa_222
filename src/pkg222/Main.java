
package pkg222;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }
    
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }
    
    void Begin()
    {
        String input;
        StringTokenizer idata;
        int cont = 0, contTotal = 1;
        double distanciaTotal = 0,capacidad = 0,millasXgalon = 0,dolares = 0,cantidadGasolineras = 0;
        double[] millas = new double[100];
        double[] precios = new double[100];
        
        while ((input = Main.ReadLn (255)) != null)
        {
          if(cont == 0){
            idata = new StringTokenizer (input);
            distanciaTotal = Double.parseDouble(idata.nextToken());
            if(distanciaTotal < 0){
                return ;
            }
          }else if(cont == 1){
              idata = new StringTokenizer (input);
              capacidad = Double.parseDouble(idata.nextToken());
              millasXgalon = Double.parseDouble(idata.nextToken());
              dolares = Double.parseDouble(idata.nextToken());
              cantidadGasolineras = Double.parseDouble(idata.nextToken());
          }else if(cont == 2){
            for(int i = 0;i < cantidadGasolineras; i++){
                if(i != 0){
                    input = Main.ReadLn (255);
                }
                idata = new StringTokenizer (input);
                millas[i] = Double.parseDouble(idata.nextToken());
                precios[i] = Double.parseDouble(idata.nextToken());
            }
            
            System.out.println("Data Set #"+contTotal);
            double resultado = Math.round((dolares+(recorrido(0,0,distanciaTotal, capacidad, millasXgalon, dolares, cantidadGasolineras, millas, precios)/100.00))*100.00)/100.00;
            String resultadoString = String.valueOf(resultado);
            
            if(resultadoString.length() < 5 && resultado > 9.99){
                resultadoString = resultadoString + 0;
            }else if(resultadoString.length() < 4 && resultado < 10){
                resultadoString = resultadoString + 0;
            }
            
            System.out.println("minimum cost = $"+resultadoString);
            
            
          }
          cont++;
          
          if(cont == 3){ 
              cont = 0;
              contTotal++;
          }
          
        }
    }
    
    double recorrido(double distancia, int estacion,double distanciaTotal, double capacidad, double millasXgalon, double dolares, double cantidadGasolineras, double[] millas, double[] precios){
        if(distancia + capacidad * millasXgalon >= distanciaTotal || estacion == cantidadGasolineras){
            return 0;
        }
        
        double mejor = 10000000;
        for(int i = estacion; i < cantidadGasolineras; i++) {
            double combustible_usado = (millas[i] - distancia) / millasXgalon;
            if(capacidad - combustible_usado < 0) break;
            if(capacidad - combustible_usado <= capacidad / 2) {
                mejor = Math.min(mejor, (200 + combustible_usado * precios[i] + recorrido(millas[i], i + 1, distanciaTotal, capacidad, millasXgalon, dolares, cantidadGasolineras, millas, precios)));
            } else if(capacidad - combustible_usado > (capacidad / 2) && (i + 1) < cantidadGasolineras && millas[i + 1] > distancia + capacidad * millasXgalon) {
                return (200 + (combustible_usado * precios[i]) + recorrido(millas[i], i + 1, distanciaTotal, capacidad, millasXgalon, dolares, cantidadGasolineras, millas, precios));
            }
        }
        
        return mejor;
 
    }
}
