import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path archivo = Path.of("C:/tmp/niats");

        try (Stream<Path> fileList = Files.walk(archivo).skip(1).sorted(Comparator.comparing(Path::getNameCount).reversed())){
            fileList.forEach((p) -> {
                try {
                    if (Files.isRegularFile(p)){
                        Files.move(p, Path.of(archivo + "/" + p.getFileName()));
                    } else {
                        Files.delete(p);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
