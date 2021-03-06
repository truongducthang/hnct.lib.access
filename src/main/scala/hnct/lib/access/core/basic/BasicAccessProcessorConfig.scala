package hnct.lib.access.core.basic

import hnct.lib.access.api.AccessProcessorConfig
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo._

/**
 * @author ryan
 */
@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="_class")
class BasicAccessProcessorConfig(
		/**
		 * How long a particular login will be. This value is only effective
		 * if there is no login timeout specified by the login request
		 */
		var loginTimeout : Long,
		/**
		 * Whether or not to renew the login upon authentication success.
			* TODO: currently if this is true and renew on read is set to false
			* at the session library level, this field is not effective. To reimplement
			* to make this field work in all case
		 */
		var renewOnAuth : Boolean
	) extends AccessProcessorConfig {
	
	/**
	 * By default the login will never timeout
	 * 
	 */
	def this() = this(-1, true)
  
}