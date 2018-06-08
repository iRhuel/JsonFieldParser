import java.io.File;

public class Main {

    public static void main(String[] args) {
        JsonParser parser = new JsonParser(new File("C:\\Users\\Phil\\Desktop\\BTTesting"));
        parser.print();
    }
}
