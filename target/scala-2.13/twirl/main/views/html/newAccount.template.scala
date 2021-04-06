
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

object newAccount extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/title("Sign in")/*1.18*/ {_display_(Seq[Any](format.raw/*1.20*/("""
  """),format.raw/*2.3*/("""<section id="content">
    <div class="wrapper doc">
      <article>
        <h1>Create your account</h1>
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
                  DATE: 2021-04-06T17:16:53.362413700
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/TB-server/app/views/newAccount.scala.html
                  HASH: a0b58c94415374903bf9afa510325746ef10b4cb
                  MATRIX: 994->1|1018->17|1057->19|1087->23|1262->173|1291->182|1325->190
                  LINES: 32->1|32->1|32->1|33->2|39->8|39->8|40->9
                  -- GENERATED --
              */
          