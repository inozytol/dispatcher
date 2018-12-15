package inozytol.fileDispatcher;

import java.nio.file.Path;

public class DispatcherFactory{

    public static FileFetcherDispatcherByHash getDispatcher(Path storageFolder){
	return new DiskFileFetcherDispatcherByHash(storageFolder); 
    }
    

}
