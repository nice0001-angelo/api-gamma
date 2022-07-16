/**
 * 
 */
package net.jin.common.util;

import java.io.*;
import java.text.*;
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
		
	}
	
	private static String makeuploadedFileName(String uploadPath, String path, String fileName) throws Exception {
	
		String uploadedFileName = uploadPath + path + File.separator + fileName;
		
		return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	}
	
	

	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath +  File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1) ;
		
		String datePath = monthPath + File.pathSeparator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		log.info(datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length-1]).exists()) {
			return;
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath+path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		
	}
}
