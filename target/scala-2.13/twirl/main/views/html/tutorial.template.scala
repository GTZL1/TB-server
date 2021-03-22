
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

object tutorial extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Hello World")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
"""),_display_(/*4.2*/defining(play.core.PlayVersion.current)/*4.41*/ { version =>_display_(Seq[Any](format.raw/*4.54*/("""

"""),format.raw/*6.1*/("""<section id="content">
  <div class="wrapper doc">
    <article>
      <h2>Implementing Hello World</h2>
      <p>This tutorial provides the instructions for using <a href="http://www.scala-sbt.org/" target="_blank"><code>sbt</code></a>
        (simple build tool) from a command window to build the application, but you can also integrate Play projects
        with your <a href="https://playframework.com/documentation/"""),_display_(/*12.69*/version),format.raw/*12.76*/("""/IDE" target="_blank">favorite IDE</a>.</p>
      <p> To see how simple it is to work with Play, let's add a customized "Hello World" greeting to this tutorial
        app. The main steps include:</p>
      <ul>
        <li><a href="#create">Create a new page</a></li>
        <li><a href="#action">Add an action method</a></li>
        <li><a href="#route">Define a route</a></li>
        <li><a href="#customize">Customize the greeting</a></li>
      </ul>

      <h3 id="create">Create the Hello World page</h3>
      <p>Follow these instructions to add a new page:</p>
      <ol>
        <li>With any text editor, create a file named <code>hello.scala.html</code> and save it in the <code>app/views</code>
          directory of this tutorial project.</li>
        <li>Add the following contents to the file:
          <pre><code class="language-html">&#64;main("Hello") """),format.raw/*28.63*/("""{"""),format.raw/*28.64*/("""
  """),format.raw/*29.3*/("""&lt;section id="content"&gt;
    &lt;div class="wrapper doc"&gt;
      &lt;article&gt;
        &lt;h1&gt;Hello World&lt;/h1&gt;
      &lt;/article&gt;  
      &lt;aside&gt;
        &#64;commonSidebar()
      &lt;/aside&gt;
    &lt;/div&gt;
  &lt;/section&gt;
"""),format.raw/*39.1*/("""}"""),format.raw/*39.2*/("""</code></pre>
          <p>The Twirl and HTML markup for your new page accomplishes the following:</p>
          <ul>
            <li>The <code>&#64;</code> sign tells the template engine to interpret what follows.
              <p>In this case, <code>&#64;main("Hello")</code> calls the main template, <code>main.scala.html</code>
                and passes it the page title of <code>Hello</code>.</p>
            </li>
            <li>The <code>content</code> section contains the <code>Hello World</code> greeting. The main template will
              insert this into the body of the page.</li>
            <li>The <code>&lt;aside&gt;</code> section adds the TOC to the right side so that you will be able to
              navigate back to this page.
          </ul>
      </ol>
      <h3 id="action">Add an action method</h3>
      <p>Next, add an action method that will render the new page. To keep things simple, you will add the new
        controller to the existing class. In a real application, you can organize controllers in multiple classes if
        you wish.</p>
      <p>Open the <code>app/controllers/HomeController.java</code> file. Below the tutorial method and above the
        closing brace, add the following method:
        <pre><code class="language-java">public Result hello() """),format.raw/*58.64*/("""{"""),format.raw/*58.65*/("""
  """),format.raw/*59.3*/("""return ok(views.html.hello.render());      
"""),format.raw/*60.1*/("""}"""),format.raw/*60.2*/("""</code></pre>
      </p>
      <p>This method has no input parameters and simply renders the new <code>hello</code> page.</p>

      <h3 id="route">Define a route</h3>

      <p>A <code>route</code> tells Play how to handle incoming requests and includes the request path, an HTTP
        method, and the controller action to invoke. When you add a route to the <code>routes</code> file, Play's
        routes compiler will automatically generate a router class that calls that action using an instance of that
        controller. For more information see <a href="https://www.playframework.com/documentation/2.8.x/ScalaRouting#HTTP-routing"
          target="blank">HTTP Routing</a>. By default, the controller instances are created using dependency
        injection. See <a href="https://www.playframework.com/documentation/latest/ScalaDependencyInjection#Dependency-Injection"
          target="blank">Dependency Injection</a> for more information.</p>
      <p>To define a route for the new page:</p>
      <ol>
        <li>Open the <code>conf/routes</code> file.</li>
        <li>Below the <code>tutorial</code> page route, add the following line:
          <p><code>GET     /hello      controllers.HomeController.hello</code></p>
        </li>
      </ol>
      <p>Test the new page:</p>
      <ol>
        <li>If you stopped the application for some reason, restart it with the <code>sbt run</code> command.</li>
        <li>Enter the URL <a href="http://localhost:9000/hello">http://localhost:9000/hello</a> to view the results
          of your work. The browser should respond with something like the following:
          <p><img src="assets/images/hello-one.png" alt="Add Request and response screen" "small-5 medium-4 large-3" /></p>
        </li>
      </ol>

      <h3 id="customize">Customize the greeting</h3>

      <p>As the final part of this tutorial, we'll modify the hello page to accept an HTTP request parameter that
        passes in a name. The steps include a deliberate mistake to demonstrate how Play provides useful feedback.</p>
      <p>To customize the Hello World greeting, follow these steps:</p>
      <ol>
        <li>In the <code>app/controllers/HomeController.java</code> file, modify the <code>hello</code> action
          method to accept a <code>String name</code> parameter. The modified action should look like the
          following:
          <pre><code class="language-java">public Result hello(String name) """),format.raw/*98.77*/("""{"""),format.raw/*98.78*/("""
  """),format.raw/*99.3*/("""return ok(views.html.hello.render());
"""),format.raw/*100.1*/("""}"""),format.raw/*100.2*/("""</code></pre>
        </li>
        <li>In the <code>conf/routes</code> file, add a <code>(name: String)</code> parameter at the end of the
          <code>/hello</code> route:
          <p><code>GET  /hello        controllers.HomeController.hello(name: String)</code></p>
        </li>
        <li>In Twirl templates, all variables and their types must be declared. From the <code>app/views/</code>
          directory, open the <code>hello.scala.html</code> file and do the following:
          <ul>
            <li>Insert a new line at the top of the file.</li>
            <li>On that line, add an <code>&#64;</code> directive that declares the name parameter and its type:
              <code>&#64;(name: String)</code>.</li>
            <li>To use the variable on the page, change the text in the <code>&lt;h2&gt;</code> heading from <code>Hello World</code>
              to <code>&lt;h2&gt;Hello &#64;name!&lt;/h2&gt;</code>.
            </li>
          </ul>
          <p>To test the cusomization:</p>
          <ol>
            <li>Open a new browser tab</li>
            <li>Enter the following URL and pass in any name as a query parameter to the hello method: <a target="play-docs"
                href="http://localhost:9000/hello?name=MyName">http://localhost:9000/hello?name=MyName</a>.
              <p>Play responds with a helpful compilation error that tells you the file and line number causing the
                problem. The message shows that the render method in the return value requires a typed parameter:
              </p>
              <p><img src="assets/images/compilation-error.png" alt="Error message" class="small-5 medium-4 large-3" /></p>
            </li>
          </ol>
        <li>
          <p>To fix the compilation error, modify the <code>hello</code> action method in <code>HomeController</code>
            so that the it includes the <code>name</code> parameter when rendering the view:</p>
          <pre><code class="language-java">public Result hello(String name) """),format.raw/*130.77*/("""{"""),format.raw/*130.78*/("""
  """),format.raw/*131.3*/("""return ok(javaguide.hello.html.helloName.render(name));
"""),format.raw/*132.1*/("""}"""),format.raw/*132.2*/("""</code></pre>
        </li>
        <li>
          <p>Save the file and refresh the browser. Play detects the change, automatically recompiles, and reloads
            the page. The page should display a customized greeting similar to the following:</p>
          <p><img src="assets/images/hello-custom.png" alt="Hello Malitha" class="small-5 medium-4 large-3" /></p>
        </li>
      </ol>

      <h3>Summary</h3>
      <p>Thanks for trying our tutorial. You learned how to use an action method, routes, Twirl template, and
        input parameter to create a customized Hello World greeting! You experienced how template compilation
        makes it easier to identify and fix problems and how auto-reloading saves time.
      </p>
      <h3>Next steps</h3>
      <p>To learn more about Play, check out these resources:</p>
      <ul>
        <li>Documentation: <a href="https://www.playframework.com/documentation/latest/JavaHome" target="_blank">Main
            concepts for Java</a></li>
        <li><a href="https://developer.lightbend.com/start/?group=play" target="_blank">Play Example Apps</a>,
          just download, unzip, and run.</li>
        <li>Podcast: <a href="https://soundcloud.com/lightbend/what-makes-play-framework-so-fast-with-will-sargent"
            target="_blank">What makes Play Framework so fast?</a></li>
      </ul>
    </article>
    <aside>
      """),_display_(/*158.8*/commonSidebar()),format.raw/*158.23*/("""
    """),format.raw/*159.5*/("""</aside>
  </div>
</section>
""")))}),format.raw/*162.2*/("""
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
                  DATE: 2021-03-02T12:26:57.235023600
                  SOURCE: C:/Users/David Dupraz/Documents/HEIG/TB/Poc/play-samples/app/views/tutorial.scala.html
                  HASH: 5e887ca4c397cad9f66f467ea83f34c4d234a590
                  MATRIX: 903->1|999->4|1026->6|1053->25|1092->27|1119->29|1166->68|1216->81|1244->83|1693->505|1721->512|2624->1387|2653->1388|2683->1391|2969->1650|2997->1651|4332->2958|4361->2959|4391->2962|4462->3006|4490->3007|6976->5465|7005->5466|7035->5469|7101->5507|7130->5508|9173->7522|9203->7523|9234->7526|9318->7582|9347->7583|10763->8972|10800->8987|10833->8992|10894->9022
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4|36->6|42->12|42->12|58->28|58->28|59->29|69->39|69->39|88->58|88->58|89->59|90->60|90->60|128->98|128->98|129->99|130->100|130->100|160->130|160->130|161->131|162->132|162->132|188->158|188->158|189->159|192->162
                  -- GENERATED --
              */
          