import jdk.jshell.execution.Util;

import java.util.ArrayList;

/***
 * Main class for data parsers
 * @author Jason
 */
public class Main {
    public static void main(String[] args) {
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");

        ArrayList<ElectionResult> resulte = Utils.parse2016ElectionResults(data);
    }
}
