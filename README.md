# BucketSort – Comparación Secuencial vs Concurrente

Este proyecto forma parte de un trabajo práctico de la materia de Programación Concurrente. El objetivo principal es **comparar la implementación del algoritmo Bucket Sort en dos enfoques**:

1. **Versión secuencial** (un solo hilo).
2. **Versión concurrente** (varios hilos trabajando sobre los buckets en paralelo).

De esta forma se puede medir el impacto del paralelismo sobre un mismo conjunto de datos y analizar en qué casos conviene aplicar concurrencia. 

## 🧠 ¿Qué es Bucket Sort?
Bucket Sort es un algoritmo de ordenamiento que:
- Distribuye los elementos en varios “buckets” (cubetas) según su valor.
- Ordena cada bucket.
- Y luego concatena todos los buckets ordenados.

En la versión concurrente, **cada bucket puede ordenarse en paralelo** para intentar reducir el tiempo total.

## 🎯 Objetivos del TP
- Implementar Bucket Sort de forma **clásica (sec uencial)**.
- Implementar Bucket Sort de forma **concurrente** usando hilos.
- **Comparar tiempos de ejecución** entre ambas versiones.
- Practicar el uso de **threads en Java**.

## 🛠️ Tecnologías
- **Lenguaje:** Java
- **IDE sugerido:** Eclipse (el repo incluye `.project` y `.classpath`)

## 📁 Estructura del proyecto
- `src/` → código fuente Java
- `.project`, `.classpath` → archivos de Eclipse
- `bin/` → clases compiladas (opcional en el repo)

## Cómo ejecutar

### Opción 1: Eclipse
1. Abrir **Eclipse**.
2. `File > Import > Existing Projects into Workspace`.
3. Seleccionar la carpeta del repositorio (`BucketSort`).
4. Ejecutar la clase `main` (la que corre las pruebas/comparaciones).

### Opción 2: Línea de comandos (si tenés el JDK configurado)
1. Compilar:
   ```bash
   javac -d bin src/**/*.java

Resultados esperados

Imprimir en consola los tiempos de la versión secuencial y de la versión concurrente.
Posibilidad de variar:cantidad de elementos, cantidad de buckets, cantidad de hilos.
