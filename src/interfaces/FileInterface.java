package interfaces;

import java.io.IOException;
import customExceptions.DuplicaterFileException;
import customExceptions.FileNotInDirectoryException;

public interface FileInterface {
	public void showFileList();
	public void removeFile() throws FileNotInDirectoryException;
	public void addFile()throws DuplicaterFileException, IOException;
}
