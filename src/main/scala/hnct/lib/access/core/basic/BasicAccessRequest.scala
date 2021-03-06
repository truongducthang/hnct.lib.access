package hnct.lib.access.core.basic

import hnct.lib.access.api.AccessRequest

/**
 * A basic access request
 * This access request represent the login request or an authentication request
 * 
 * When it is a login request, the token will have to be the user password
 * 
 * If timeout is set, it define how long the login session will last. If it is set to -1
 * the timeout defined by the AccessProcessor will be used.
 * 
 * When it is used for authentication, the time out is not used.
 */
class BasicAccessRequest(
		override val username : Option[String],
		override val token : Option[String],
		val timeout : Long) extends AccessRequest {
	
	def this(un : Option[String], t : Option[String]) = this(un, t, -1)
	
}