public class NeverRentException extends Exception {
    public NeverRentException() {
        super("Ta osoba nigdy nie wynajmowała pomieszczeń");
    }
}
