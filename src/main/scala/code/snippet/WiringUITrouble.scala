package code
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import net.liftweb.http.js.JsCmds
import net.liftweb.http.{WiringUI, S, SHtml}

class WiringUITrouble {
  def render(xhtml: NodeSeq): NodeSeq = {
    val theCell = ValueCell(5L)
    def ajaxElement = SHtml.ajaxText("test box", x => {JsCmds.Alert("post-back worked!")})
    val wiringElementA = WiringUI(NodeSeq.Empty, theCell)((number: Long) => (ns: NodeSeq) => {ajaxElement})

    val ajaxElementB = ajaxElement; //Initalize outside of scope
    val wiringElementB = WiringUI(NodeSeq.Empty, theCell)((number: Long) => (ns: NodeSeq) => {ajaxElementB})

    <div>
      <div>Change the text in the boxes below and then click outside of them (to change focus).</div>
      <div>Box a and b both make ajax call-backs to the server, however only box b executes the handler.</div>
      <div>We can also see that only box B has a registered function in the S.functionMap.</div>
      <div>a: {wiringElementA}</div>
      <div>b: {wiringElementB}</div>
      <div>registered functions: {S.functionMap.map(_._1)}</div>
    </div>
  }
}
