package NewGameTracker.Model;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteToFile {

    public static int writeToFile(String fileName, GameTrackerEngine model) {
        try {
            String file = fileName + ".txt";
            File myFile = new File(file);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(myFile), StandardCharsets.UTF_8
            ));

            writer.write(generateGameScore(model));
            writer.write(lineBreak());
            writer.write(generateDiscipline(model));
            writer.write(lineBreak());
            writer.write(generatePlayerStats(model));
            writer.write(lineBreak());
            writer.write(generateGameLogSummary(model));

            writer.close();

        } catch (Exception e) {
            System.out.println("Error creating file");
            return -1;
        }

        return 1;
    }


    private static String generateGameScore(GameTrackerEngine model) {
        return "Final Score:\n" +
            "SURLFC: " + model.getUsydScore() + " | Opposition: " + model.getOpponentScore();
    }

    private static String generateDiscipline(GameTrackerEngine model) {
        return "Discipline:\n" +
                "Completed Sets: " + model.getCompletedSet() + "\n" +
                "Un-Completed Sets: " + model.getUnCompletedSet() + "\n" +
                "Penalties For:" + model.getPenaltiesFor() + "\n" +
                "Penalties Against: " + model.getPenaltiesAgainst();
    }


    private static String generatePlayerStats(GameTrackerEngine model) {
        StringBuilder toReturn = new StringBuilder("Player Specific Stats:\n");

        for (int i : model.getPlayerMap().keySet()) {
            Player p = model.getPlayerMap().get(i);
            toReturn.append("Name: ").append(p.getName()).append(" | Number: ").
                    append(p.getNumber()).
                    append(" | Tackles: ").
                    append(p.getTackles()).
                    append(" | Hit Ups: ").
                    append(p.getHitUps()).
                    append(" | Trys: ").
                    append(p.getTrys()).
                    append(" | Try Assists: ").
                    append(p.getTryAssists()).
                    append(" | Errors: ").
                    append(p.getErrors()).
                    append("\n");
        }

        return toReturn.toString();
    }

    private static String generateGameLogSummary(GameTrackerEngine model) {
        StringBuilder toReturn = new StringBuilder("Game Log:\n");
        for (String s : model.getGameLogList()) {
            toReturn.append(s).append("\n");
        }
        return toReturn.toString();
    }


    private static String lineBreak() {
        return "\n\n***********************************\n\n";
    }
}
