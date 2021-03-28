// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/conf/routes
// @DATE:Sun Mar 28 16:41:34 CEST 2021


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
