package io.themepark.view

import io.themepark.domain.AttractionId
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/**
  * Attraction display
  * @param id AttractionId
  */
@JSExportTopLevel("Attraction")
class Attraction(id: AttractionId) {


  object Attraction {

    @JSExport
    def main(canvas: html.Canvas): Unit = {
      val draw: CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
      val (h, w) = (canvas.height, canvas.width)
    }
  }

}