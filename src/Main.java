import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, String> dataMap = new HashMap<>();
        DictionaryNew dN = new DictionaryNew();
        dN.dictionaryBuild();
        String filePath = "src/resources/dictionaryOut.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(";");
                if (keyValue.length >= 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    dataMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String word = buidNewEntry();
            checkWordInDataBase(word, dataMap);
            System.out.print("Продолжим? (для выхода введите 'q'): ");
            if (scanner.nextLine().equals("q")) {
                break;
            }
        }
        scanner.close();
    }


    static String buidNewEntry() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите слово: ");
        return scanner.nextLine();
    }

    static void checkWordInDataBase(String testWord, Map<String, String> db) {
        Scanner scanner = new Scanner(System.in);

        boolean exists = false;
        for (String wordInDataBase : db.keySet()) {
            if (wordInDataBase.equals(testWord)) {
                exists = true;
                System.out.println("Значение: " + db.get(wordInDataBase));
                System.out.println("Для удаления слова нажмите 'Y'");
                System.out.println("Для редактирования значения 'M': ");
                System.out.println("Следующее слово любая клавиша: ");

                switch (scanner.nextLine().toUpperCase()) {
                    case "Y":
                        db.remove(wordInDataBase);
                        break;
                    case "M":
                        System.out.print("Введите перевод слова: ");
                        db.put(testWord, scanner.nextLine());
                        break;
                }
                break;
            }
        }
        if (!exists) {
            System.out.print("Добавить слово в словарь? (для добавление нажмите 'Y'): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                System.out.print("Введите перевод слова: ");
                db.put(testWord, scanner.nextLine());
            }
        }
    }
}
