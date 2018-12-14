import java.nio.file.Path;

public class DiskFileFetcherDispatcherByHash implements FileFetcherDispatcherByHash {
    public Path getFile(String hash) {
	return null;
    }
    
    public String storeFile(Path fileToStore) {
	return null;
    }
    
    public static void main(String [] argv) {
	System.out.println("works");
    }
}
