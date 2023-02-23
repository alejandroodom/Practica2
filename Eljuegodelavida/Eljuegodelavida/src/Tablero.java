import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente;

    public Tablero() {
        estadoActual = new int[DIMENSION][DIMENSION];
        estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

    public void leerEstadoActual() throws IOException {
        BufferedReader br = null;
            String a;
            int i = 0;

            while ((a = br.readLine()) != null) {
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = a.charAt(j) == '1' ? 1 : 0;
                }
                i++;
            }
    }

    public void generarEstadoActualPorMontecarlo() {
        Random r = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = r.nextInt(2);
            }
        }
    }

    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinasVivas = 0;
                if (estadoActual[i][j] == 1) {
                    if (vecinasVivas < 2 || vecinasVivas > 3) {
                        estadoSiguiente[i][j] = 0;
                    } else {
                        estadoSiguiente[i][j] = 1;
                    }
                } else {
                    if (vecinasVivas == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }
                }
            }
        }
        int[][] a = estadoActual;
        estadoActual = estadoSiguiente;
        estadoSiguiente = a;
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "estadoActual=" + Arrays.toString(estadoActual) +
                ", estadoSiguiente=" + Arrays.toString(estadoSiguiente) +
                '}';
    }
}