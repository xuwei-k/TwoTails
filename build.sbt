import TwoTails._
import com.typesafe.sbt.SbtSite.SiteKeys._
import com.typesafe.sbt.SbtGhPages.GhPagesKeys._

lazy val root = (project in file(".")).settings(
  scalaVersion := ScalacVersion,
  crossVersion := CrossVersion.full,
  crossScalaVersions := Versions,
  publishArtifact := false
)
.aggregate(plugin, lib)

lazy val plugin = build("twotails", "core").settings(
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided"
  ),
  scalacOptions in Test ++= Seq(
    "-Xplugin:" + (packageBin in Compile).value,
    "-verbose",
    "-Yno-adapted-args",
    "-Ywarn-value-discard",
    "-Ywarn-dead-code",
    //"-P:twotails:memory",
    "-Xprint-types",
    "-Xlog-reflective-calls",
    "-Xprint:twotails",
    "-Ylog:twotails"//,
    //"-Ycheck:twotails"
  ),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
)
.dependsOn(lib)

lazy val lib = build("twotails-annotations", "annotations")