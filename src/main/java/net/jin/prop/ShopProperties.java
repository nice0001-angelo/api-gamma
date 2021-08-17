/**
 * 
 */
package net.jin.prop;

import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;

import lombok.*;

/**
 * @author njh
 *
 */
@Setter
@Getter
@Component
@ConfigurationProperties("net.jin")
public class ShopProperties {
	
	//업로드된 파일 저장 위치 값
	private String uploadPath;
	
	private String secretKey;

}
