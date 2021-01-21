package kr.or.ddit.util;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.respository.UserDao;
import kr.or.ddit.user.respository.UserDaoI;

public class FileUtilTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtilTest.class);

	
	@Test
	public void getFileNameTest() {
		
		/***Given***/
		String contentDisposition = "form-data; name=\"file\"; filename=\"brown.png\"";

		/***When***/
		String filename = FileUtil.getFileName(contentDisposition);

		/***Then***/
		assertEquals("brown.png", filename);
		
	}
	
	@Test
	public void getFileExtensionTest() {
		
		/***Given***/
		String filename = "brown.png";
		
		/***When***/
		String extension = FileUtil.getFileExtension(filename);
		logger.debug("getFileExtensionTest : "+extension);

		/***Then***/
		assertEquals("png", extension);
	}
	
	
	
	@Test
	public void test() {
		System.out.println(UUID.randomUUID().toString());
	}
	
}
