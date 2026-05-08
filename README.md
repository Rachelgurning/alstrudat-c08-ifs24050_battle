# alstrudat-c02-ifs24051

## Description

Buatlah program **Universal Number Base Converter** yang mengkonversi bilangan
dari satu basis (basis asal) ke basis lain (basis tujuan) menggunakan implementasi
**Stack berbasis Linked List** yang kamu buat sendiri.

Program menerima 4 inputan dalam satu baris dipisahkan spasi:
1. `bilangan` — Bilangan dalam format string (mendukung digit `0-9` dan huruf `A-Z` untuk basis > 10)
2. `basisAsal` — Basis asal bilangan (integer, 2-36)
3. `basisTujuan` — Basis tujuan konversi (integer, 2-36)
4. `modelStack` — Model implementasi Stack yang digunakan:
   - `1` = **StackTopFirst**: top of stack ada di **node pertama** (head)
   - `2` = **StackTopLast**: top of stack ada di **node terakhir** (tail)

Hasil konversi dicetak tanpa newline di akhir. Huruf pada output menggunakan **uppercase** (A-Z).

### Ketentuan Implementasi

Kamu **WAJIB** mengimplementasikan struktur data berikut sendiri sebagai inner class di `Program.java`:
- `Node` — simpul linked list (char data + Node next)
- `StackTopFirst` — Stack linked list, top di node pertama (push sisipkan di depan, pop ambil dari depan)
- `StackTopLast` — Stack linked list, top di node terakhir (push sisipkan di belakang, pop ambil dari belakang)

**Dilarang** menggunakan `java.util.Stack`, `java.util.LinkedList`, `java.util.ArrayDeque`,
atau koleksi sejenis sebagai struktur Stack utama.

### Alur Konversi

1. Konversi bilangan dari `basisAsal` ke desimal menggunakan perkalian berurutan
2. Konversi desimal ke `basisTujuan` dengan cara sisa bagi berulang, push setiap remainder ke Stack
3. Pop semua elemen dari Stack untuk menghasilkan representasi bilangan di basis tujuan
4. Jika input bilangan adalah `"0"`, langsung kembalikan `"0"`

### Catatan Penting

- Kedua model Stack (1 dan 2) harus menghasilkan **output yang sama** karena
  keduanya adalah implementasi LIFO yang berbeda secara struktur internal namun
  identik secara fungsionalitas
- Input huruf bisa uppercase maupun lowercase (contoh: `FF` atau `ff`, `ZZ` atau `zz`)
- Basis tujuan bisa sampai 36 — gunakan huruf A-Z untuk nilai 10-35 pada output

## Source Codes

| No | File | Deskripsi |
|----|------|-----------|
| 1 | App.java | Entry point — membaca input, **jangan diubah** |
| 2 | Program.java | Implementasi Node, StackTopFirst, StackTopLast, dan method convert() |

## Test Cases

| No | Input | Output | Keterangan |
|----|-------|--------|------------|
| 1 | `26 10 2 1` | `11010` | Desimal 26 ke Biner, StackTopFirst |
| 2 | `26 10 2 2` | `11010` | Desimal 26 ke Biner, StackTopLast |
| 3 | `255 10 16 1` | `FF` | Desimal 255 ke Heksadesimal |
| 4 | `FF 16 10 2` | `255` | Heksadesimal FF ke Desimal |
| 5 | `100 10 36 1` | `2S` | Desimal 100 ke Basis 36 |
| 6 | `0 10 2 1` | `0` | Edge case: bilangan nol |
| 7 | `42 10 2 1` | `101010` | Desimal 42 ke Biner, StackTopFirst |
| 8 | `42 10 2 2` | `101010` | Desimal 42 ke Biner, StackTopLast |
| 9 | `ZZ 36 10 1` | `1295` | Basis 36 ZZ ke Desimal |
| 10 | `1295 10 36 2` | `ZZ` | Desimal 1295 ke Basis 36, StackTopLast |

## Compile

```
mvn clean package
```

## Run

```
java -cp target/app.jar del.alstrudat.App
```
