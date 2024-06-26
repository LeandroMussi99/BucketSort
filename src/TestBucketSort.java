import java.util.Random;

public class TestBucketSort {
    public static void main(String[] args) throws InterruptedException {
        int size = 10000;
        float[] arr = new float[size];
        float minValue = 0.0f;
        float maxValue = 1000000.0f;
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = minValue + rand.nextFloat() * (maxValue - minValue);
        }

        float[] arrCopy = arr.clone();
        
     

        // Medir el tiempo para el BucketSort secuencial
        long startTime = System.nanoTime();
        SequentialBucketSort.bucketSort(arr, 5, minValue, maxValue);
        long endTime = System.nanoTime();
        long durationSequential = endTime - startTime;
        System.out.println("\n\nTiempo de ejecucion secuencial: " + durationSequential / 1000 + " microsegundos \n");

        

        // Medir el tiempo para el BucketSort concurrente
        startTime = System.nanoTime();
        ConcurrentBucketSort.bucketSort(arrCopy, 5, minValue, maxValue);
        endTime = System.nanoTime();
        long durationConcurrent = endTime - startTime;
        System.out.println("\nTiempo de ejecucion concurrente: " + durationConcurrent / 1000 + " microsegundos");

        
    }
}
