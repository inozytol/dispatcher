package inozytol.fileDispatcher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.IOException;

import static java.nio.file.StandardCopyOption.*;

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
	if(fileToStore==null) System.err.println("lol file to store null");
	Path target = storeFolder.resolve(fileToStore.getFileName());
	try{
	    Files.move(fileToStore, target, REPLACE_EXISTING);
	} catch (IOException e){
	    System.err.println(e);
	    target = null;
	}
	return target==null?null:target.toString();
    }

    public String [] fileList(){
	return null;
    }
    
    public static void main(String [] argv) {
	System.out.println("works");
    }
}
