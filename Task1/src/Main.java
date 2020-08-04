import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /* Make the function to calculate Catalan Numbers */
    public static int catalanNumber(int num) {
        int[] n = new int[num + 1]; // Make an array bigger than input
        n[0] = 1;
        for (int i = 1; i <= num; i++) {
            n[i] = 0;
            for (int j = 0; j < i; j++) {
                n[i] += n[j] * n[i - j - 1]; // Initialize recursive formula
            }
        }
        return n[num];
    }
    /* Head method */
    public static void main(String[] args) throws IOException {
        Main cn = new Main();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        reader.close(); // Close reader to avoid drain of resources
        System.out.println(cn.catalanNumber(num));
    }
}
