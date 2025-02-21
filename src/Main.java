import java.util.Arrays;

public class Main {
    // Generate a 2^n-dimensional Hadamard Matrix (CDMA orthogonal codes)
    public static int[][] generateHadamardMatrix(int n) {
        int size = (int) Math.pow(2, n);
        int[][] hadamard = new int[size][size];

        // Start with H1
        hadamard[0][0] = 1;

        // Iteratively construct higher order matrices
        for (int k = 1; k < size; k *= 2) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    // Top-left quadrant (copy of Hk)
                    hadamard[i][j] = hadamard[i][j];
                    // Top-right quadrant (copy of Hk)
                    hadamard[i][j + k] = hadamard[i][j];
                    // Bottom-left quadrant (copy of Hk)
                    hadamard[i + k][j] = hadamard[i][j];
                    // Bottom-right quadrant (negated Hk)
                    hadamard[i + k][j + k] = -hadamard[i][j];
                }
            }
        }

        return hadamard;
    }

    // Calculate the data for a given station index using the CDMA code
    public static int calculateData(int[] cdmaCode, int[][] hadamardMatrix, int stationIndex) {
        int[] stationCode = hadamardMatrix[stationIndex];
        int data = 0;

        // Perform the dot product between the CDMA code and the station code
        for (int i = 0; i < cdmaCode.length; i++) {
            data += cdmaCode[i] * stationCode[i];
        }

        // Normalize the result by dividing by the length of the code
        return data / cdmaCode.length;
    }

    // Helper function to print a matrix (for debugging)
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        //CDMA cdma = new CDMA();

        // Example: Number of devices (2^n means n=6 for 64 devices)
        int n = 6;
        int[][] hadamardMatrix = generateHadamardMatrix(n);

        // Provided CDMA code
        int[] cdmaCode =

        // Calculate the data at the 24th station (index 23 due to 0-based indexing)
        int stationIndex = 23; // 24th device
        int data = calculateData(cdmaCode, hadamardMatrix, stationIndex);

        // Output the result
        System.out.println("Data at 24th station: " + data);
    }
}