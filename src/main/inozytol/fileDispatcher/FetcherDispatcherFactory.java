package inozytol.fileDispatcher;

import java.nio.file.Path;

public class FetcherDispatcherFactory{

    public static FileFetcherDispatcherById getDispatcher(Path storageFolder){
	return new DiskFileFetcherDispatcherById(storageFolder); 
    }
    

}
