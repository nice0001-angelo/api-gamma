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
	
	private String secretKey;

}
