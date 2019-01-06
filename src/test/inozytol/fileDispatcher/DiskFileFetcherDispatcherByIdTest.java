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
    

}

