// This code defines an enum called "Mode" that represents two possible values: offensive and defensive.

package softwareDevelopment2Coursework;

public enum Mode {
// The first value of the Mode enum is OFFENSIVE, with a value of 3.
	OFFENSIVE(3),
// The second value of the Mode enum is DEFENSIVE, with a value of 2.
	DEFENSIVE(2);

	// This private variable will hold the integer value of each Mode.
	private int value;

	// This is the constructor for the Mode enum, which takes an integer value.
	private Mode(int value) {
		this.value = value;
	}

	// This public method returns the integer value of a Mode.
	public int getValue() {
		return value;
	}
}