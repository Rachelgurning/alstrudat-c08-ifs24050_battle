package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Program program = new Program();
        StringBuilder output = new StringBuilder();
        boolean first = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String bilangan = parts[0];
            int basisAsal = Integer.parseInt(parts[1]);
            int basisTujuan = Integer.parseInt(parts[2]);
            int modelStack = Integer.parseInt(parts[3]);

            String result = program.convert(bilangan, basisAsal, basisTujuan, modelStack);
            if (!first) output.append("\n");
            output.append(result);
            first = false;
        }

        System.out.print(output.toString());
        scanner.close();
    }
}