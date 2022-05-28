/**
 * 
 */
package net.jin.common.util;

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
	
	

}
