package academy.pocu.comp3500.lab4;

public enum Algorithm {
    CRC32(0, "CRC32"),
    MD2(1, "MD2"),
    MD5(2, "MD5"),
    SHA1(3, "SHA1"),
    SHA256(4, "SHA-256");

    private final int index;
    private final String algorithmString;

    Algorithm(int index, String algorithmString) {
        this.index = index;
        this.algorithmString = algorithmString;
    }

    public int getIndex() {
        return index;
    }

    public String getAlgorithmString() {
        return algorithmString;
    }
}
