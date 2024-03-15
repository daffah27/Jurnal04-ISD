import java.util.Collections;
import java.util.Comparator;

class Tugas implements Comparable<Tugas> {
    String mataKuliah;
    String namaTugas;
    String deadline;

    public Tugas(String mataKuliah, String namaTugas, String deadline) {
        this.mataKuliah = mataKuliah;
        this.namaTugas = namaTugas;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Mata Kuliah = " + mataKuliah + ", Tugas = " + namaTugas + ", Deadline = " + deadline;
    }

    @Override
    public int compareTo(Tugas other) {
        return this.deadline.compareTo(other.deadline);
    }
}
