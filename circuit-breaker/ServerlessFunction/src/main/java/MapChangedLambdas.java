import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MapChangedLambdas {
    public static void main(String[] args) throws Exception {
        File file = new File(System.getenv("GITHUB_WORKSPACE") + "/changed_files.txt");
        Scanner scanner = new Scanner(file);
        Set<String> affectedLambdas = new HashSet<>();

        System.out.println("Changed files:");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim(); // Trim leading and trailing whitespace
            System.out.println("  " + line);
            if (line.startsWith("ServerlessFunction/src/main/java/serverless/") && line.endsWith(".java")) {
                String lambdaName = line.substring(line.lastIndexOf('/') + 1, line.indexOf(".java"));
                affectedLambdas.add(lambdaName);
            }
        }

        scanner.close();
        System.out.println(String.join(",", affectedLambdas)); // Print only the affected Lambdas
    }
}
