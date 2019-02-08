import inozytol.fileDispatcher.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class DiskFileFetcherDispatcherByIdTest {

    // Test that object can be created
    @Test
    public void constructorTest(){
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./storage"));
	assertTrue(dffdbi!=null);
    }

    // Test that can return list of file id's in source directory
    @Test
    public void idListTest(){
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./target/storage"));
	String [] idArray = dffdbi.fileList();
	List<String> idList = Arrays.asList(idArray);
	assertTrue(idList.contains("foo"));
	assertTrue(idList.contains("foo2"));
	assertTrue(idList.size()==2);
	assertTrue(!idList.contains("non existing file"));
    }

    // Test that can get file 
    @Test
    public void getFileTest(){
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./target/storage"));
	Path filePath = dffdbi.getFile("foo");
	assertTrue(Files.exists(filePath));
    }
    
    // Test that class can store file
    @Test
    public void storeFileTest(){
	String storeFolder = "./target/storage";
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get(storeFolder));
	
	String id = dffdbi.storeFile(Paths.get("./target/testFiles/foo2"));
	System.out.println(id);
	assertTrue(id.equals("foo2"));
	assertTrue(Files.exists(Paths.get("./target/testFiles/foo2")));
	assertTrue(Files.exists(Paths.get(storeFolder, id)));
    }

    // Test that you can remove file
    @Test
    public void removeFileExistsTest() {
	String storagePathString = "./target/storage";
	String fileToRemovenNameString = "fileToRemove";
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get(storagePathString));
	Path fileToRemove = Paths.get(storagePathString).resolve(fileToRemovenNameString);
	try {
	    //creating file to be removed
	    Files.createFile(fileToRemove);
	    assertTrue(Files.exists(fileToRemove));
	    dffdbi.removeFile(fileToRemovenNameString);
	} catch (IOException e) {
	    System.err.println("Well, this embarrassing, remove file" +
			       "if exists test thrown an error " + e);
	}
	assertTrue(!Files.exists(fileToRemove));

    }


    // Test that you can't remove file if it doesn't exist
    @Test
    public void removeFileDoesntExistTest() {
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./target/storage"));
	assertTrue(!dffdbi.removeFile("this File does not exist"));

    }

    // Test that you can retrieve file into given temp file
    @Test
    public void retrieveFileIntoTemp() {
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./target/storage"));

	Path tempFile = null;
	try {
	    tempFile = Files.createTempFile("","");
	} catch (IOException e) {
	    System.err.println("error creating temp file " + e);
	    System.exit(0);
	}
	
	Path filePath = dffdbi.getFile("foo", tempFile);
	assertTrue(Files.exists(filePath));
	assertTrue(binaryFilesAreEqual(Paths.get("./target/storage/foo"), tempFile));

	try {
	    Files.delete(tempFile);
	} catch (IOException e) {
	    System.err.println("error deleting temp file " + e);
	}
    }

    
    /** 
     * returns true if files are the same
     * returns false if any of the files does not exist
     */
    boolean binaryFilesAreEqual(Path file1, Path file2) {
        if(!Files.exists(file1) || !Files.exists(file2)) return false;

	boolean ret = false;
	int temp1;
	int temp2;
        
        try (InputStream bis1 = new BufferedInputStream(new FileInputStream(file1.toFile())); InputStream bis2 = new BufferedInputStream(new FileInputStream(file2.toFile()))) {
	    ret = true;
            do {
		temp1 = bis1.read();
		temp2 = bis2.read();
	    } while (temp1 == temp2 && temp1 != -1 && temp2 != -1);
	    if (temp1 != temp2) ret = false;
        } catch (IOException e) {
            System.out.println("Exception has occured while comparing files");
	    System.out.println(e+"");
        }
	return ret;
    }

}

