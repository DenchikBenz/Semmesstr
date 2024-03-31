import java.util.Arrays;

public class Timsort {
    // Минимальный размер прогона. Это значение выбрано для оптимизации алгоритма
    // на различных наборах данных. Слишком маленькое значение увеличит количество прогонов,
    // а слишком большое - снизит эффективность сортировки вставками.
    private static final int MIN_MERGE = 32;
    private static int iterationsCount = 0;

    //Основной метод для сортировки массива.
    // arr Массив для сортировки.
    public static int sort(int[] arr) {
        iterationsCount = 0;
        int n = arr.length;

        // Если массив слишком мал, используем сортировку вставками и возвращаемся.
        if (n < MIN_MERGE) {
            insertionSort(arr, 0, n);
            return iterationsCount;
        }

        // Вычисляем минимальный размер прогона для текущего массива.
        int minRun = minRunLength(MIN_MERGE);
        for (int i = 0; i < n; i += minRun) {
            // Сортируем прогоны с помощью сортировки вставками.
            insertionSort(arr, i, Math.min(i + MIN_MERGE, n));
        }

        // Последовательно увеличиваем размер прогонов и сливаем их.
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
        return iterationsCount;
    }

    //Сортировка вставками для сортировки прогонов.
    // left Левая граница подмассива для сортировки.
    //right Правая граница подмассива для сортировки (включительно).
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            int temp = arr[i];
            int j = i - 1;
            // Сдвигаем элементы, которые больше temp, на одну позицию вправо,
            // чтобы освободить место для вставки temp на правильное место.
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
                iterationsCount ++;
            }
            arr[j + 1] = temp;
            iterationsCount ++;
        }
    }

    //Слияние двух отсортированных подмассивов.
   // arr Исходный массив.
    //  l Левая граница первого подмассива.
    // m Правая граница первого подмассива (или середина слияния).
    // r Правая граница второго подмассива.
    private static void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];

        // Копируем данные во временные массивы.
        for (int i = 0; i < len1; i++) {
            left[i] = arr[l + i];
            iterationsCount ++;
        }
        for (int i = 0; i < len2; i++) {
            right[i] = arr[m + 1 + i];
            iterationsCount ++;
        }
        // Сливаем временные массивы обратно в arr[l..r].
        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) {
            arr[k++] = left[i] <= right[j] ? left[i++] : right[j++];
            iterationsCount ++;
        }

        // Копируем оставшиеся элементы left[], если они есть.
        while (i < len1) {
            arr[k++] = left[i++];
            iterationsCount ++;
        }
        // Копируем оставшиеся элементы right[], если они есть.
        while (j < len2) {
            arr[k++] = right[j++];
        }
    }

    //Вычисляет длину минимального прогона так, чтобы он был больше или равен MIN_MERGE.
     //n Размер массива.
     //return Оптимальная длина прогона.
    private static int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
            iterationsCount ++;
        }
        return n + r;
    }
}