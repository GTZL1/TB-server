
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

object hello extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/main("Hello")/*1.15*/ {_display_(Seq[Any](format.raw/*1.17*/("""
  """),format.raw/*2.3*/("""<section id="content">
    <div class="wrapper doc">
      <article>
        <h1>Hello World</h1>
      </article>
      <aside>
      """),_display_(/*8.8*/commonSidebar()),format.raw/*8.23*/("""
      """),format.raw/*9.7*/("""</aside>
    </div>
  </section>
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
                  DATE: 2021-03-02T17:47:57.308624500
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/Poc/play-samples/app/views/hello.scala.html
                  HASH: 872867c8f37406af984733b92eaf68ee9f5a9436
                  MATRIX: 989->1|1010->14|1049->16|1079->20|1246->162|1281->177|1315->185
                  LINES: 32->1|32->1|32->1|33->2|39->8|39->8|40->9
                  -- GENERATED --
              */
          