import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private static ArrayList<ElectionResult> result = new ArrayList<>();
    private List<State> states;

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
        }

        return result;

    }

    public static void parseElection2016(List<State> states, String data) {
        String[] lines = data.split("\n");
        String[] temp;

        for (int i = 1; i < lines.length; i++) {
            cleanData(lines[i]);
            temp = lines[i].split(",");

            String stateName = temp[8];
            State s = getState(stateName, states);
            if (s == null) states.add(s);

            String countyName = temp[9];
            County c = getCounty(countyName, states);
            if(c == null) states.get(s).addCounty(c);

            County county = new County(temp[9], Integer.parseInt(temp[10]));
            Election2016 values = new Election2016(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]), Double.parseDouble(temp[3]));

            states.get(s).addCounty(county);
            states.get(s).getCounty(county).setVote2016(values);
        }

    }

    private static County getCounty(String countyName, List<State> states) {
        for (State s:states) {
            for (County c : s.getCounties()) {
                if (c.getName().equals(countyName)) return c;
            }
        }
        return null;
    }

    private static State getState(String stateName, List<State> states) {
        for (State s:states) {
            if(s.getName().equals(stateName)) return s;
        }
        return null;
    }
}
