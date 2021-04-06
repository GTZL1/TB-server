// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/conf/routes
// @DATE:Tue Apr 06 17:16:53 CEST 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
