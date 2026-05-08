package del.alstrudat;

/**
 * INSTRUKSI:
 * Implementasikan semua logika konversi basis bilangan di bawah ini.
 * Kamu WAJIB membuat inner class Node, StackTopFirst, dan StackTopLast sendiri.
 * Dilarang menggunakan java.util.Stack, java.util.LinkedList,
 * java.util.ArrayDeque, atau koleksi sejenis sebagai struktur Stack.
 *
 * <p>Struktur data yang wajib kamu implementasi sendiri (sebagai inner class):
 *   - Node          : simpul linked list (data bertipe char + pointer next)
 *   - StackTopFirst : Stack berbasis linked list, top ada di node PERTAMA (head)
 *                     push = sisipkan di depan, pop = ambil dari depan
 *   - StackTopLast  : Stack berbasis linked list, top ada di node TERAKHIR (tail)
 *                     push = sisipkan di belakang, pop = ambil dari belakang
 *
 * <p>Alur konversi yang harus diimplementasi di method convert():
 *   1. Konversi bilangan dari basisAsal ke desimal (gunakan long)
 *      - Digit 0-9 bernilai 0-9
 *      - Huruf A-Z (atau a-z) bernilai 10-35
 *   2. Konversi desimal ke basisTujuan menggunakan Stack sesuai model:
 *      - model 1 -> gunakan StackTopFirst
 *      - model 2 -> gunakan StackTopLast
 *      - Push sisa bagi (remainder) satu per satu ke Stack
 *      - Pop semua dari Stack untuk mendapatkan hasil akhir
 *   3. Output menggunakan huruf UPPERCASE (A-Z untuk nilai 10-35)
 *   4. Jika bilangan = "0", langsung kembalikan "0"
 *
 * <p>Format input (satu baris, spasi sebagai pemisah):
 *   bilangan basisAsal basisTujuan modelStack
 *   Contoh: "FF 16 10 2"  ->  output: "255"
 *           "26 10 2 1"   ->  output: "11010"
 */
public class Program {

  // TODO: Implementasikan inner class Node di sini
  // Contoh field yang dibutuhkan: char data, Node next

  // TODO: Implementasikan inner class StackTopFirst di sini
  // Method yang dibutuhkan: push(char), pop(), isEmpty()

  // TODO: Implementasikan inner class StackTopLast di sini
  // Method yang dibutuhkan: push(char), pop(), isEmpty()

  /**
   * Mengkonversi bilangan dari basisAsal ke basisTujuan menggunakan Stack.
   *
   * @param number   Bilangan dalam bentuk String (contoh: "FF", "26", "ZZ")
   * @param baseFrom Basis asal (2-36)
   * @param baseTo   Basis tujuan (2-36)
   * @param model    Model Stack: 1 = StackTopFirst, 2 = StackTopLast
   * @return Hasil konversi dalam bentuk String (huruf uppercase)
   */
  public static String convert(String number, int baseFrom, int baseTo, int model) {
    // TODO: Implementasikan logika konversi di sini
    return "";
  }
}
