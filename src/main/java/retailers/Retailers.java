package retailers;

public interface Retailers {

    boolean isAvailable(int size);
    void closeWebDriver();
    Double findPrice();
}
