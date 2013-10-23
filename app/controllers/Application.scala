package controllers

import java.io.File
import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
   val rootFolderPath = System.getProperty("user.dir")
   val reportsFolder = new File(rootFolderPath + "/public/reports") 
   val files = getRecursiveListOfFiles(reportsFolder)
   val reportNames = scala.collection.mutable.ArrayBuffer[String]()
    for (f <- files){
       reportNames += f.getName()
    }
    Ok(views.html.index(reportNames))
  }

  def getRecursiveListOfFiles(dir:File): Array[File] = {
    val these = dir.listFiles
    these ++ these.filter(_.isDirectory).flatMap(getRecursiveListOfFiles) 
  }
  
}
