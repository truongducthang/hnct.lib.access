package hnct.lib.access.playframework

import hnct.lib.access.api.{AccessRequest, User}
import hnct.lib.access.api.results.{ActionResult, LoginResult, LogoutResult}
import hnct.lib.access.core.basic.BasicAccessRequest
import play.api.mvc.Request
import play.api.mvc.WrappedRequest

/**
 * This class wrapped around an original HTTP request, and the corresponding access request
 * built from the original HTTP request. By default, user can use PlayRequestBuilder to build
 * the access request. Since the PlayRequestBuilder is also an ActionTransformer, it will also
 * transform the original request to WrappedAccessRequest.
 * 
 * This class extends Request[A] so that user can treat this as a normal request if wanted, and
 * it'll be more convenient to write code that doesn't relate to access checking
 */
case class PlayHTTPRequest[A](originalRequest : Request[A], accessRequest : Option[AccessRequest])
  extends WrappedRequest[A](originalRequest) {
  
  def this(originalRequest : Request[A]) = this(originalRequest, None)
  
  def this(originalRequest : Request[A], accessRequest : AccessRequest) = this(originalRequest, Some(accessRequest))

  // store the result if any for login and authentication
  var loginResult : Option[LoginResult] = None
  var authResult : Option[ActionResult] = None
  var logoutResult : Option[LogoutResult] = None

}