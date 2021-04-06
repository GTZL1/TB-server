
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object title extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html lang="en">

  <head>
    <title>"""),_display_(/*7.13*/title),format.raw/*7.18*/("""</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" media="screen" href='"""),_display_(/*9.50*/routes/*9.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*9.97*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(/*10.50*/routes/*10.56*/.Assets.versioned("stylesheets/prism.css")),format.raw/*10.98*/("""'>
    <link rel="shortcut icon" type="image/png" href='"""),_display_(/*11.55*/routes/*11.61*/.Assets.versioned("images/favicon.png")),format.raw/*11.100*/("""'>
    <script src='"""),_display_(/*12.19*/routes/*12.25*/.Assets.versioned("javascripts/hello.js")),format.raw/*12.66*/("""' type="text/javascript"></script>
    <script src='"""),_display_(/*13.19*/routes/*13.25*/.Assets.versioned("javascripts/prism.js")),format.raw/*13.66*/("""' type="text/javascript"></script>
  </head>

  <body>
    <section id="top">
      <div class="wrapper">
        <img class="resize" src="assets/images/play_icon_reverse.svg" alt="logo" />
        <h1>Highly Entertaining and Intelligent Game</h1>
      </div>
    </section>
    """),_display_(/*23.6*/content),format.raw/*23.13*/("""
  """),format.raw/*24.3*/("""</body>

</html>"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-04-06T17:00:38.291280300
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/app/views/title.scala.html
                  HASH: 985ab00750800856abc1ea4a658173cfa858f119
                  MATRIX: 912->1|1037->31|1067->35|1152->94|1177->99|1338->234|1352->240|1413->281|1493->334|1508->340|1571->382|1656->440|1671->446|1732->485|1781->507|1796->513|1858->554|1939->608|1954->614|2016->655|2333->946|2361->953|2392->957
                  LINES: 27->1|32->1|34->3|38->7|38->7|40->9|40->9|40->9|41->10|41->10|41->10|42->11|42->11|42->11|43->12|43->12|43->12|44->13|44->13|44->13|54->23|54->23|55->24
                  -- GENERATED --
              */
          