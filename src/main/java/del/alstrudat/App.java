package del.alstrudat;

import java.util.Scanner;

/**
 * Universal Number Base Converter menggunakan Stack Linked List.
 *
 * <p>INSTRUKSI UNTUK PENJAWAB:
 * - Implementasikan semua logika konversi di Program.java
 * - Kamu WAJIB mengimplementasikan Node dan Stack sendiri dari scratch
 * - Dilarang menggunakan java.util.Stack, java.util.LinkedList,
 *   java.util.ArrayDeque, atau koleksi sejenis sebagai struktur Stack
 * - Kamu boleh menggunakan Scanner dan String dari java standar
 */
public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String bilangan = scanner.next();
    int basisAsal = scanner.nextInt();
    int basisTujuan = scanner.nextInt();
    int modelStack = scanner.nextInt();

    String result = Program.convert(bilangan, basisAsal, basisTujuan, modelStack);
    System.out.println(result);

    scanner.close();
  }
}
