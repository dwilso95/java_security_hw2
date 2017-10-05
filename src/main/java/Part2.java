/**
 * Helper class for executing private/protected methods and accessing 
 * fields using reflection in Part 2 of Homework Assignment 2
 */
public class Part2 {

    private int internalValue;
    private int protectValue;

    /**
     * Constructor to set internalValue
     */
    public Part2(final int internalValue) {
        this.internalValue = internalValue;
    }

    /**
     * Construcotr for setting both internalValue and protectedValue
     */
    public Part2(final int internalValue, final int protectedValue) {
        this.internalValue = internalValue;
        this.protectValue = protectedValue;
    }

    public int addTestCase1(final Part2 part2) {
        return this.internalValue += part2.getInternalValue();
    }

    public void callPrivateMethod(final Part2 part2) {
        part2.privateMethod();
    }

    protected int getInternalValue() {
        return internalValue;
    }

    protected int getProtectedValue() {
        return protectValue;
    }

    private void privateMethod() {
        System.out.println("\tPrivate method of Part2 with internal value " + this.getInternalValue() + " was called.");
    }

}
