package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Program tsat = new Program();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String bilangan   = parts[0];
            int basisAsal     = Integer.parseInt(parts[1]);
            int basisTujuan   = Integer.parseInt(parts[2]);
            int modelStack    = Integer.parseInt(parts[3]);

            String result = tsat.convert(bilangan, basisAsal, basisTujuan, modelStack);
            System.out.println(result);
        }

        scanner.close();
    }
}