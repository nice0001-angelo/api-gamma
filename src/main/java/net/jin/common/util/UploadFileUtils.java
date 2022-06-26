/**
 * 
 */
package net.jin.common.util;

import java.io.*;
import java.util.*;

import org.springframework.util.*;

import lombok.extern.slf4j.*;

/**
 * @author njh
 *
 */
@Slf4j
public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath, String originName, byte[] fileData) throws Exception{
		
		UUID uid = UUID.randomUUID();
		
		String saveName = uid.toString()+"_"+originName;
		
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath+savedPath, saveName);
		
		FileCopyUtils.copy(fileData, target);
		
		String uploadedFileName = makeuploadedFileName(uploadPath, savedPath, saveName);
				
		return uploadedFileName;
		
		System.out.println();
	}
	
	private static String makeuploadedFileName(String uploadPath, String path, String fileName) throws Exception {
	
		String uploadedFileName = uploadPath + path + File.separator + fileName;
		
		System.out.println();//05031186525
		
		return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	}
	
	

}
