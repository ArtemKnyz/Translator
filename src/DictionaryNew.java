import java.io.*;

public class DictionaryNew {
    String filePath = "src/resources/dictionary.txt";
    String outputFilePath = "src/resources/dictionaryOut.txt";

    void dictionaryBuild() {
        char unwantedChar = '"';

        try (
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line.replaceAll(String.valueOf(unwantedChar), "");
                String modifiedLine1 = modifiedLine.replaceAll("\\[.*?];", " ");

                writer.write(modifiedLine1);
                writer.newLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
