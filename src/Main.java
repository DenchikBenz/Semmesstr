import java.io.IOException;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
    Generator.testGenerate();
    int counter = 0;
        try {
            FileWriter out = new FileWriter("out.txt");
            BufferedWriter writer = new BufferedWriter(out);
            FileReader fr = new FileReader("myFile.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(" ");
                int[] array = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    array[i] = Integer.parseInt(str[i]);
                }
                double startTime = System.nanoTime();
                int iterations = Timsort.sort(array);
                double endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1000000;
                System.out.print("Время выполнения алгоритма: " + executionTime + " миллисекунд. ");
                System.out.println("Длина массива " + array.length);
                System.out.println(Arrays.toString(array));
                writeDataToFile(writer,array.length,executionTime,iterations);
                counter += 1;
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeDataToFile(BufferedWriter writer, int one, double two, int three) throws IOException {
        writer.write(Integer.toString(one));
        writer.write(" ");
        writer.write(Double.toString(two));
        writer.write(" ");
        writer.write(Integer.toString(three));
        writer.write(" ");
        writer.newLine();
    }
}