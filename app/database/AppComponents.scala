package database

import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.db.Database
import play.api.db.DBComponents
import play.api.db.HikariCPComponents
import play.api.db.evolutions.EvolutionsComponents
import play.api.routing.Router
import play.filters.HttpFiltersComponents

/*class AppComponents(cntx: Context)
  extends BuiltInComponentsFromContext(cntx)
    with DBComponents
    with EvolutionsComponents
    with HikariCPComponents
    with HttpFiltersComponents {
  // this will actually run the database migrations on startup
  applicationEvolutions

}*/
