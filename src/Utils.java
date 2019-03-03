import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> result = new ArrayList<>();
        String[] lines = data.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] temp;
            String line;

            if (lines[i].contains("\"")) {
                temp = lines[i].split("\"");
                temp[1] = temp[1].replace(",", "");
                line = temp[0] + temp[1] + temp[2];
            } else {
                line = lines[i];
            }

            temp = line.split(",");

            ElectionResult test = new ElectionResult(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), Double.parseDouble(temp[4]), Double.parseDouble(temp[5]), temp[6], temp[7], temp[8], temp[9], Integer.parseInt(temp[10]));
            result.add(test);
            System.out.println();
        }

        return result;

    }
}
