import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequentialBucketSort {
    // Función de ordenación por inserción para ordenar buckets individuales
    public static void insertionSort(List<Float> bucket) {
        for (int i = 1; i < bucket.size(); ++i) {
            float key = bucket.get(i); // Guardar el valor a insertar
            int j = i - 1;
            // Desplazar elementos que son mayores que el valor a insertar hacia adelante
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            // Insertar el valor en su posición correcta
            bucket.set(j + 1, key);
        }
    }

    // Función para ordenar arr[] de tamaño n usando bucket sort
    public static void bucketSort(float[] arr, int numBuckets, float minValor, float maxValor) {
        int n = arr.length; // Obtener el tamaño del array
        float rango = (maxValor - minValor) / numBuckets; // Calcular el rango de cada bucket

        // Crear numBuckets buckets vacíos
        List<Float>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>(); // Inicializar cada bucket como una lista vacía
        }

        // Poner los elementos del array en diferentes buckets
        for (int i = 0; i < n; i++) {
            int bi = (int) ((arr[i] - minValor) / rango); // Calcular el índice del bucket para cada elemento
            if (bi >= numBuckets) {
                bi = numBuckets - 1; // Asegurar que los valores máximos caigan en el último bucket
            }
            buckets[bi].add(arr[i]); // Agregar el elemento al bucket correspondiente
        }

     // Ordenar cada bucket individualmente
        for (int i = 0; i < numBuckets; i++) {
            long inicio = System.nanoTime();
            insertionSort(buckets[i]);
            /*
            try {
                Thread.sleep(300);  // Simular operación costosa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            long fin = System.nanoTime();
            long duracion = fin - inicio;
            System.out.println("Bucket " + i + " ordenado en: " + duracion / 1000 + " ms");
        }
        // Concatenar todos los buckets en arr[]
        int index = 0;
        for (int i = 0; i < numBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j); // Copiar elementos ordenados de cada bucket al array original
            }
        }
    }

    // Probamos la funcion en el main
    public static void main(String[] args) {
        
        int tamanio = 12; // Definir el tamaño del array

        // Generar números aleatorios en el array
        float[] arr = new float[tamanio]; // Crear un array de floats con el tamaño definido
        Random rand = new Random(); // Crear un objeto Random para generar números aleatorios
        float minValor = 0.0f; // Definir el valor mínimo para los números aleatorios
        float maxValor = 50.0f; // Definir el valor máximo para los números aleatorios
        for (int i = 0; i < tamanio; i++) {
            arr[i] = minValor + rand.nextFloat() * (maxValor - minValor); // Llenar el array con números aleatorios en el rango definido
        }

        // Número de buckets
        int numBuckets = 5; // Definir el número de buckets 

        System.out.println("Array original:");
        for (float num : arr) {
            System.out.print(num + " / "); // Imprimir el array original 
        }
        System.out.println();

        // Ordenar el array usando bucket sort
        bucketSort(arr, numBuckets, minValor, maxValor); // Llamar a la función bucketSort para ordenar el array

        System.out.println("\nEl array ordenado es:");
        for (float num : arr) {
            System.out.print(num + " / "); // Imprimir el array ordenado
        }
    }
}
