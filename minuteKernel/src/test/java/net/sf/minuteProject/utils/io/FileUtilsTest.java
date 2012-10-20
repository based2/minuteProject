package net.sf.minuteProject.utils.io;

import junit.framework.TestCase;

public class FileUtilsTest extends TestCase{

	public void testStripFileName() {
		
		String test2 = "C:\\DEV\\JAVA\\mysourceforge\\MP\\minuteKernel\\bin\\generator-config-sample-views-WEB.xml";
		
		String test2stripped = FileUtils.stripFileName(test2);
		System.out.println("test2stripped ="+test2stripped);
		
		String filename = "../../test/test/myjava.java";
		String stripped = FileUtils.stripFileName(filename);
		assertTrue(stripped, stripped.equals("../../test/test"));
		
		String filenameOnly = FileUtils.stripRelativePath(filename);
		assertTrue(filenameOnly, filenameOnly.equals("myjava.java"));
//		
//		String test2 = "C:\\DEV\\JAVA\\mysourceforge\\MP\\minuteKernel\\bin\\generator-config-sample-views-WEB.xml";
//		
//		String test2stripped = FileUtils.stripFileName(test2);
//		System.out.println("test2stripped ="+test2stripped);
	}
	
	public void testGetFileFullPathFromFileInClassPath() {
		String root = FileUtils.getRoot();
		String filePathInClassPath="generator-config-petstore.xml";
		
		String result = FileUtils.getFileFullPathFromFileInClassPath(filePathInClassPath);
		System.out.println(result);
		System.out.println(root+"\\"+filePathInClassPath);
		assertNotNull(result);
		assertTrue(result.equals(root+"\\"+filePathInClassPath));
		
		// root
		filePathInClassPath=".";
		result = FileUtils.getFileFullPathFromFileInClassPath(filePathInClassPath);
		System.out.println(result);
		assertNotNull(result);
		assertFalse(result.equals(filePathInClassPath));
		
		// itself
		filePathInClassPath="!net/sf/minuteProject/utils/io/FileUtilsTest.java";
		result = FileUtils.getFileFullPathFromFileInClassPath(filePathInClassPath);
		System.out.println(result);
		assertNotNull(result);
//		assertFalse(result, result.equals(filePathInClassPath));		
		
		
	}
}
