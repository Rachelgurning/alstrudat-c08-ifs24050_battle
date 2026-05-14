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
            String op = parts[0];

            switch (op) {
                case "INSERT": {
                    int key = Integer.parseInt(parts[1]);
                    int priority = Integer.parseInt(parts[2]);
                    tsat.insert(key, priority);
                    break;
                }
                case "DELETE": {
                    int key = Integer.parseInt(parts[1]);
                    tsat.delete(key);
                    break;
                }
                case "QUERY_BST_RANGE": {
                    int lo = Integer.parseInt(parts[1]);
                    int hi = Integer.parseInt(parts[2]);
                    tsat.queryBSTRange(lo, hi);
                    break;
                }
                case "QUERY_AVL_HEIGHT": {
                    tsat.queryAVLHeight();
                    break;
                }
                case "QUERY_AVL_BALANCE": {
                    int key = Integer.parseInt(parts[1]);
                    tsat.queryAVLBalance(key);
                    break;
                }
                case "QUERY_HEAP_MIN": {
                    tsat.queryHeapMin();
                    break;
                }
                case "QUERY_HEAP_KMIN": {
                    int k = Integer.parseInt(parts[1]);
                    tsat.queryHeapKMin(k);
                    break;
                }
                case "IS_AVL_VALID": {
                    tsat.isAVLValid();
                    break;
                }
                case "COUNT_BST_GREATER": {
                    int x = Integer.parseInt(parts[1]);
                    tsat.countBSTGreater(x);
                    break;
                }
                default:
                    break;
            }
        }
        scanner.close();
    }
}