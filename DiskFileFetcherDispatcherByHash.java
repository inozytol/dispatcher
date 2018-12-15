package inozytol.fileDispatcher;

import java.nio.file.Path;

public class DiskFileFetcherDispatcherByHash implements FileFetcherDispatcherByHash {

    private Path sourceFolder = null;
    
    public DiskFileFetcherDispatcherByHash(Path sourceFolder) {
	this.sourceFolder = sourceFolder;
    }
    
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
