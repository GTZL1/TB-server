
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

object rules extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/title("HEIG game")/*1.20*/ {_display_(Seq[Any](format.raw/*1.22*/("""
  """),format.raw/*2.3*/("""<section id="content">
    <div class="wrapper doc">
      <article>
        <h1>Play the HEIG game</h1>
      </article>
      <aside>
      """),_display_(/*8.8*/sidebar()),format.raw/*8.17*/("""
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
                  DATE: 2021-04-06T17:16:53.425853300
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/app/views/rules.scala.html
                  HASH: d8d5c84ef05415832ffd3c1d44299396946085c3
                  MATRIX: 989->1|1015->19|1054->21|1084->25|1258->174|1287->183|1321->191
                  LINES: 32->1|32->1|32->1|33->2|39->8|39->8|40->9
                  -- GENERATED --
              */
          