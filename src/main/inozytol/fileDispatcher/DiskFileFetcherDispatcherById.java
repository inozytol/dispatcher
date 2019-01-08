package inozytol.fileDispatcher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.IOException;

import static java.nio.file.StandardCopyOption.*;

public class DiskFileFetcherDispatcherById implements FileFetcherDispatcherById {

    private Path storeFolder = null;
    private Path tempFolder = null;
    
    public DiskFileFetcherDispatcherById(Path storeFolder) {
	this.storeFolder = storeFolder;
	this.tempFolder = Paths.get(".");
    }

    /**
     * Should create a temporary file basing on given id and return path to it
     * Return null if something wrong happens
     * @param id of a file to copy to temporary file
     * @return path to temporary file with contents assigned to that id, null if unsuccessful
     */
    public Path getFile(String id) {
	//maybe move to guyava maybe?
	Path ret = null;
	// create temporary file being a copy of file from storage with given id
	try {
	    ret = Files.createTempFile(tempFolder,"","");

	    Files.copy(storeFolder.resolve(id), ret);
	} catch (IOException e){
	    //this should be logged (TODO:LOGGING)
	    System.err.println(e);
	}
	
	return ret;
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
	    Files.copy(fileToStore, target, REPLACE_EXISTING);
	} catch (IOException e){
	    System.err.println(e);
	    target = null;
	}
	return target==null?null:target.getFileName().toString();
    }

    /**
     * Returns a list of files in its storage directory
     * @return list of files in storage directory (list of id)
     */
    public String [] fileList(){
	
	// TODO maybe someday add walking subdirectories
	return storeFolder.toFile().list();
    }

    /**
     * Removes file with given id from store
     * Should log if exception happens
     * @param id of file to remove
     * @return true if removal was successful, false otherwise
     */
    public boolean removeFile(String id){
	Path fileToRemove = storeFolder.resolve(id);
	boolean ret = false;
	try {
	    ret = Files.deleteIfExists(fileToRemove);
	} catch (IOException e) {
	    // TODO:LOG
	    System.err.println("Trying to remove file from file store exception " + e);
	}
	return ret;
    }
    
    public static void main(String [] argv) {
	System.out.println("works");
    }
}
