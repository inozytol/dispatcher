package inozytol.fileDispatcher;

import java.nio.file.Path;

public interface FileFetcherDispatcherById {
    Path getFile(String id);
    Path getFile(String id, Path targetPath);
    String storeFile(Path fileToStore);
    boolean removeFile(String id);
    String [] fileList();
}
