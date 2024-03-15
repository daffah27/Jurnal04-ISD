import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class ToDoList {
    private static LinkedList<Tugas> listTugas = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuChoice;
        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Input Tugas");
            System.out.println("2. Delete Tugas");
            System.out.println("3. Lihat List Tugas");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    tambahTugas();
                    break;
                case 2:
                    hapusTugas();
                    break;
                case 3:
                    System.out.println("1. Print Depan");
                    System.out.println("2. Print Belakang");
                    System.out.println("3. Urutkan");
                    System.out.print("Pilihan: ");
                    switch (scanner.nextInt()) {
                        case 1:
                            cetakDariAwal();    
                            break;
                        case 2:
                            cetakDariAkhir();
                            break;
                        case 3:
                            tampilkanDataTerurut();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                    }
                    break;
                case 4:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (menuChoice != 4);
    }

    private static void tambahTugas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama Mata Kuliah: ");
        String mataKuliah = scanner.nextLine();
        System.out.print("Masukkan nama Tugas: ");
        String namaTugas = scanner.nextLine();
        System.out.print("Masukkan deadline (DD/MM/YYYY): ");
        String deadline = scanner.nextLine();

        Tugas newTugas = new Tugas(mataKuliah, namaTugas, deadline);
        listTugas.addFirst(newTugas);
        System.out.println("Tugas berhasil ditambahkan.");
    }

    private static void hapusTugas() {
        if (listTugas.isEmpty()) {
            System.out.println("List tugas kosong.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hapus tugas berdasarkan:");
        System.out.println("1. Mata Kuliah");
        System.out.println("2. Nama Tugas");
        System.out.println("3. Akhir List");
        System.out.print("Pilihan: ");

        switch (scanner.nextInt()) {
            case 1:
                hapusTugasByMataKuliah();
                break;
            case 2:
                hapusTugasByNamaTugas();
                break;
            case 3:
                hapusTugasAkhirList();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void hapusTugasByMataKuliah() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama Mata Kuliah: ");
        String mataKuliah = scanner.nextLine();

        ListIterator<Tugas> iterator = listTugas.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().mataKuliah.equals(mataKuliah)) {
                iterator.remove();
                System.out.println("Tugas berhasil dihapus.");
                return;
            }
        }
        System.out.println("Tugas dengan Mata Kuliah '" + mataKuliah + "' tidak ditemukan.");
    }

    private static void hapusTugasByNamaTugas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama Tugas: ");
        String namaTugas = scanner.nextLine();

        ListIterator<Tugas> iterator = listTugas.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().namaTugas.equals(namaTugas)) {
                iterator.remove();
                System.out.println("Tugas berhasil dihapus.");
                return;
            }
        }
        System.out.println("Tugas dengan Nama Tugas '" + namaTugas + "' tidak ditemukan.");
    }

    private static void hapusTugasAkhirList() {
        listTugas.removeLast();
        System.out.println("Tugas terakhir berhasil dihapus.");
    }

   private static void tampilkanDataTerurut() {
        if (listTugas.isEmpty()) {
            System.out.println("List tugas kosong.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String sesuai;
        System.out.println("Tampilkan data terurut berdasarkan:");
        System.out.println("1. Deadline");
        System.out.println("2. Nama Mata Kuliah");
        System.out.println("3. Nama Tugas");
        System.out.print("Pilihan: ");
        

        switch (scanner.nextInt()) {
            case 1:
                Collections.sort(listTugas); 
                sesuai = "DEADLINE";
                break;
            case 2:
                Collections.sort(listTugas, new Comparator<Tugas>() {
                    @Override
                    public int compare(Tugas t1, Tugas t2) {
                        return t1.mataKuliah.compareTo(t2.mataKuliah);
                    }
                });
                sesuai = "MATA KULIAH";
                break;
            case 3:
                Collections.sort(listTugas, new Comparator<Tugas>() {
                    @Override
                    public int compare(Tugas t1, Tugas t2) {
                        return t1.namaTugas.compareTo(t2.namaTugas);
                    }
                });
                sesuai = "NAMA TUGAS";
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        System.out.println("=== LIST TERURUT SESUAI "+ sesuai + " ===");
        for (Tugas tugas : listTugas) {
            System.out.println(tugas);
        }
    }

    private static void cetakDariAwal() {
        if (listTugas.isEmpty()) {
            System.out.println("List tugas kosong.");
            return;
        }

        System.out.println("=== LIST TUGAS ===");
        for (Tugas tugas : listTugas) {
            System.out.println(tugas);
        }
    }

    private static void cetakDariAkhir() {
        if (listTugas.isEmpty()) {
            System.out.println("List tugas kosong.");
            return;
        }

        System.out.println("=== LIST TUGAS (DARI AKHIR) ===");
        ListIterator<Tugas> iterator = listTugas.listIterator(listTugas.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
}
