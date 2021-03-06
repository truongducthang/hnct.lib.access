package hnct.lib.access.example

import hnct.lib.access.api.AccessProcessorContainerConfig
import hnct.lib.access.api.AccessUnit
import hnct.lib.access.core.basic.BasicAccessProcessorConfig
import hnct.lib.config.Configuration
import hnct.lib.config.ConfigurationFormat
import hnct.lib.access.core.basic.BasicAccessProcessor
import scala.concurrent.duration._
import scala.concurrent.Await

object ConfigGenerator {
	
	def main(args : Array[String]) {
		
		val config = AccessProcessorContainerConfig("unit1", List(
				AccessUnit("unit1", classOf[BasicAccessProcessor], new BasicAccessProcessorConfig())
			))
			
		val fileName = "example/accessConfig.json"
			
		//write the above AccessProcessorFactoryConfig to file first
		//go to the written file and change the adapter class to the correct class
		//can use hnct.lib.access.core.basic.TestDataAdapter for testing
		//Configuration.write(fileName, config)
		
			
		// this part read back the configuration from the same file
		// initialize an instance of the data adapter, and use it to find a user by his username
		val config1 = Configuration.read(fileName, classOf[AccessProcessorContainerConfig], ConfigurationFormat.JSON)
		
		val adapter = config1.get.units(0).config.dataAdapterClass.newInstance()
		
		Await.result(adapter.findUserByUsername("abc"), 10 seconds) map { user =>
			println(user.username)
			println(user.password)
		}
		
		config1.get.units(0).config.hasher.toString()
	} 
	
}