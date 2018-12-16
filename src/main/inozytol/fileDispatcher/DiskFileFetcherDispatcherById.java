package inozytol.fileDispatcher;

import java.nio.file.Path;

public class DiskFileFetcherDispatcherById implements FileFetcherDispatcherById {

    private Path storeFolder = null;
    
    public DiskFileFetcherDispatcherById(Path storeFolder) {
	this.storeFolder = storeFolder;
    }
    
    public Path getFile(String hash) {
	// create path to file basing on source folder and hash name
	// return that path. Or maybe return input stream?
	// path should be more portable...
	return null;
    }

    /**
     * Stores file in directory given as storeFolder
     * @param fileToStore file that should be sent to store
     * @return id of stored file or null if storing failed for some reason
     */
    public String storeFile(Path fileToStore) {
	return null;
    }

    public String [] fileList(){
	return null;
    }
    
    public static void main(String [] argv) {
	System.out.println("works");
    }
}
