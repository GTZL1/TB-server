
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

object commonSidebar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*2.2*/defining(play.core.PlayVersion.current)/*2.41*/ { version =>_display_(Seq[Any](format.raw/*2.54*/("""
"""),format.raw/*3.1*/("""<h3>Table of Contents</h3>
<ul>
  <li><a href=""""),_display_(/*5.17*/routes/*5.23*/.HomeController.index),format.raw/*5.44*/("""#Introduction">Welcome</a>
  <li><a href=""""),_display_(/*6.17*/routes/*6.23*/.HomeController.explore),format.raw/*6.46*/("""">Play application overview</a>
  <li><a href=""""),_display_(/*7.17*/routes/*7.23*/.HomeController.tutorial),format.raw/*7.47*/("""">Implementing Hello World</a>
</ul>
<h3>Related Resources</h3>
<ul>
  <li><a href="https://playframework.com/documentation/"""),_display_(/*11.57*/version),format.raw/*11.64*/("""" target="_blank">Play documentation</a></li>
  <li><a href="https://discuss.lightbend.com/c/play/" target="_blank">Forum</a></li>
  <li><a href="https://gitter.im/playframework/playframework" target="_blank">Gitter Channel</a></li>
  <li><a href="https://stackoverflow.com/questions/tagged/playframework" target="_blank">Stackoverflow</a></li>
  <li><a href="https://lightbend.com/how" target="_blank">Professional support</a></li>
</ul>
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-03-02T12:26:57.128300800
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/Poc/play-samples/app/views/commonSidebar.scala.html
                  HASH: 35e014d3bb1af046d936754c1beab28a548cb015
                  MATRIX: 908->1|1004->5|1051->44|1101->57|1128->58|1202->106|1216->112|1257->133|1326->176|1340->182|1383->205|1457->253|1471->259|1515->283|1667->408|1695->415
                  LINES: 27->1|32->2|32->2|32->2|33->3|35->5|35->5|35->5|36->6|36->6|36->6|37->7|37->7|37->7|41->11|41->11
                  -- GENERATED --
              */
          