package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Program program = new Program();
        boolean firstOutput = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length < 4) continue;

            String bilangan  = parts[0];
            int basisAsal    = Integer.parseInt(parts[1]);
            int basisTujuan  = Integer.parseInt(parts[2]);
            int modelStack   = Integer.parseInt(parts[3]);

            String result = program.convert(bilangan, basisAsal, basisTujuan, modelStack);

            if (firstOutput) {
                System.out.print(result);
                firstOutput = false;
            } else {
                System.out.print("\n" + result);
            }
        }
        scanner.close();
    }
}