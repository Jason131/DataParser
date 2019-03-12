import java.util.List;

/***
 * Main class for data parsers
 * @author Jason
 */

public class Main {
    private static List<State> states;

    public static void main(String[] args) {
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        Utils.parseElection2016(states, data);

//        data = Utils.readFileAsString("data/Education.csv");
//        Utils.parseEducation2016(data);
//
//        data = Utils.readFileAsString("data/Unemployment.csv");
//        Utils.parseEmployment2016(data);
    }

}
