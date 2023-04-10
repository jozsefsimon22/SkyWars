package softwareDevelopment2Coursework;

public enum Mode {
    OFFENSIVE(3),
    DEFENSIVE(2);

    private int value;

    private Mode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

