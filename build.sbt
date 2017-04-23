name := "stockholder_meeting"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.4"
libraryDependencies += "org.bouncycastle" % "bcprov-jdk15" % "1.44"
libraryDependencies += "org.bouncycastle" % "bcmail-jdk15" % "1.44"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "1.2.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"
