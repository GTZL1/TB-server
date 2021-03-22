// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/conf/routes
// @DATE:Mon Mar 22 20:40:23 CET 2021


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
