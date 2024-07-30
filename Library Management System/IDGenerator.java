public class IDGenerator {
    private static long idCounter = 0;

    public static synchronized long generateID() {
        return idCounter++;
    }
}
