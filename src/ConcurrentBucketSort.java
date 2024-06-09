import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcurrentBucketSort extends Thread {

    private List<Float> bucket;

    // Constructor de la clase ConcurrentBucketSort que inicializa el bucket
    public ConcurrentBucketSort(List<Float> bucket) {
        this.bucket = bucket;
    }

    // El método run() se ejecuta cuando se inicia el hilo
    public void run() {
        long startTime = System.nanoTime();  // Tiempo de inicio
        insertionSort(bucket);  // Ordena el bucket usando ordenación por inserción
        long endTime = System.nanoTime();  // Tiempo de finalización
        long duration = endTime - startTime;  // Duración de la ordenación
        System.out.println("Hilo " + Thread.currentThread().getName() + " tardo: " + duration / 1000 + " microsegundos");
    }

    // Función de ordenación por inserción para ordenar buckets individuales
    public static void insertionSort(List<Float> bucket) {
        for (int i = 1; i < bucket.size(); ++i) {  // Recorre cada elemento del bucket desde el segundo
            float key = bucket.get(i);  // Almacena el valor actual
            int j = i - 1;
            // Mueve los elementos del bucket que son mayores que el key a una posición adelante de su posición actual
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);  // Coloca el key en su posición correcta
        }
    }

    // Función para ordenar un array de tamaño n usando bucket sort concurrente
    public static void bucketSort(float[] arr, int numBuckets, float minValue, float maxValue){
        int n = arr.length;
        float range = (maxValue - minValue) / numBuckets;  // Calcula el rango de valores para cada bucket

        // Crear numBuckets buckets vacíos
        List<Float>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Poner los elementos del array en diferentes buckets
        for (int i = 0; i < n; i++) {
            int bi = (int) ((arr[i] - minValue) / range);  // Calcula el índice del bucket para el elemento actual
            if (bi >= numBuckets) {
                bi = numBuckets - 1;  // Asegura que el índice del bucket esté dentro de los límites
            }
            buckets[bi].add(arr[i]);  // Agrega el elemento al bucket correspondiente
        }

        // Crear y ejecutar hilos para ordenar cada bucket
        Thread[] threads = new Thread[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            threads[i] = new ConcurrentBucketSort(buckets[i]);  // Crea un nuevo hilo para cada bucket
            threads[i].start();  // Inicia el hilo para ordenar el bucket
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < numBuckets; i++) {
            try {
                threads[i].join();  // Espera a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace();  // Maneja la excepción en caso de interrupción
            }
        }

        // Concatenar todos los buckets en arr[]
        int index = 0;
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);  // Copia los elementos ordenados de los buckets de vuelta al array
            }
        }
    }

    // Probamos la funcion en el main
    public static void main(String[] args) throws InterruptedException {
        // Tamaño dinámico del array
        int size = 1000;

        // Generar números aleatorios en el array
        float[] arr = new float[size];
        Random rand = new Random();
        float minValue = 0.0f;
        float maxValue = 50.0f;
        for (int i = 0; i < size; i++) {
            arr[i] = minValue + rand.nextFloat() * (maxValue - minValue);  // Genera un número aleatorio en el rango especificado
        }

        // Número de buckets
        int numBuckets = 5;

        System.out.println("Array original:");
        for (float num : arr) {
            System.out.print(num + " / ");  // Imprime el array original
        }
        System.out.println();
        System.out.println();

        // Ordenar el array usando bucket sort concurrente
        bucketSort(arr, numBuckets, minValue, maxValue);

        System.out.println("\nEl array ordenado es:");
        for (float num : arr) {
            System.out.print(num + " / ");  // Imprime el array ordenado
        }
    }
}
