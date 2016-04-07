
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
        int primeraLinea = 0,cont = 0;
        float distancia = 0,capacidad = 0,millasXgalon = 0,dolares = 0,cantidadGasolineras = 0;

        while ((input = Main.ReadLn (255)) != null)
        {
          if(cont == 0){
            idata = new StringTokenizer (input);
            distancia = Float.parseFloat(idata.nextToken());
            if(distancia < 0){
                return ;
            }
          }else if(cont == 1){
              idata = new StringTokenizer (input);
              capacidad = Float.parseFloat(idata.nextToken());
              millasXgalon = Float.parseFloat(idata.nextToken());
              dolares = Float.parseFloat(idata.nextToken());
              cantidadGasolineras = Float.parseFloat(idata.nextToken());
          }else if(cont == 2){
            for(int i = 0;i < cantidadGasolineras; i++){
                input = Main.ReadLn (255);
                
            }
          }
          System.out.println (distancia);
          
          
          if(cont == (cantidadGasolineras + 1)){
              cont = -1;
          }
          cont++;
          
        }
    }
}
