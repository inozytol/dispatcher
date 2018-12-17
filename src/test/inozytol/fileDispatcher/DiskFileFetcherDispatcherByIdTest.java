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
	assertTrue(idList.size()==1);
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
	DiskFileFetcherDispatcherById dffdbi = new DiskFileFetcherDispatcherById(Paths.get("./target/storage"));
	
	String id = dffdbi.storeFile(Paths.get("./target/testFiles/foo2"));
	System.out.println(id);
	assertTrue(id.equals("./target/storage/foo2"));
	assertTrue(Files.exists(Paths.get(id)));
    }
    

}

