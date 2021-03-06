package hnct.lib.access.api.results

import hnct.lib.access.api.AccessRequest

class ActionResultCode {
	final case object SUCCESSFUL extends ActionResultCode 
	final case object FAILED extends ActionResultCode
	
	final case object FAILED_REQUEST_NOT_FOUND extends ActionResultCode
	
	final case object FAILED_NOT_AUTHENTICATED extends ActionResultCode

	override def toString() = {
		(getClass.getName.stripSuffix("$").split('.')).last.split('$').last
	}
}

object ActionResultCode extends ActionResultCode

trait ActionResult {
	
	/**
	 * The access request associated with the Action
	 */
	def request : Option[AccessRequest]
	
	/**
	 * The status of the action
	 */
	def status : ActionResultCode = ActionResultCode.FAILED
	
}