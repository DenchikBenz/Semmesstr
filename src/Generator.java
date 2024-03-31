import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Generator {
    public static void testGenerate() throws IOException {
        File myFile = new File("myFile.txt");
        myFile.createNewFile();

        int size = 200;

        try {
            FileWriter writer = new FileWriter("myFile.txt");
            for (int i = 0; i < 70; i++) {
                int[] array = new int[size];
                for (int j = 0; j < size; j++) {
                    array[j] = (int) (Math.random() * size);
                    writer.write(array[j] + " ");
                }
                writer.write("\n");
                size += 200;
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
