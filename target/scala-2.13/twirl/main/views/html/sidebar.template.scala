
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

object sidebar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""
"""),_display_(/*2.2*/defining(play.core.PlayVersion.current)/*2.41*/ { version =>_display_(Seq[Any](format.raw/*2.54*/("""
"""),format.raw/*3.1*/("""<h3>Site Contents</h3>
<ul>
  <li><a href=""""),_display_(/*5.17*/routes/*5.23*/.HomeController.index),format.raw/*5.44*/("""">Home</a>
  <li><a href=""""),_display_(/*6.17*/routes/*6.23*/.HomeController.rules),format.raw/*6.44*/("""">Rules</a>
  <li><a href=""""),_display_(/*7.17*/routes/*7.23*/.HomeController.newAccount),format.raw/*7.49*/("""">Create your account</a>
</ul>
""")))}),format.raw/*9.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-04-06T17:16:53.475636700
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/app/views/sidebar.scala.html
                  HASH: 3b74ff2b81c8533b1257ab4aeef073aa3dd79ed4
                  MATRIX: 902->1|998->3|1026->6|1073->45|1123->58|1151->60|1223->106|1237->112|1278->133|1332->161|1346->167|1387->188|1442->217|1456->223|1502->249|1566->284
                  LINES: 27->1|32->1|33->2|33->2|33->2|34->3|36->5|36->5|36->5|37->6|37->6|37->6|38->7|38->7|38->7|40->9
                  -- GENERATED --
              */
          