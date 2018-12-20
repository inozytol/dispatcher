package inozytol.fileDispatcher;

import java.nio.file.Path;

public interface FileFetcherDispatcherById {
    Path getFile(String id);
    String storeFile(Path fileToStore);
    boolean removeFile(String id);
    String [] fileList();
}
