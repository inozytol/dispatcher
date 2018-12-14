import java.nio.file.Path;

public interface FileFetcherDispatcherByHash {
    Path getFile(String hash);
    String storeFile(Path fileToStore);
}
