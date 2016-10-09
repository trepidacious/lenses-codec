name := "lenses-codec root project"

version in ThisBuild := "0.1-SNAPSHOT"

organization in ThisBuild := "org.rebeam"

scalaVersion in ThisBuild := "2.11.8"

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

//SLF4J simple logger, y u log to System.err by default, even for info?
javaOptions in ThisBuild := Seq("-Dorg.slf4j.simpleLogger.logFile=System.out")

lazy val monocleVersion = "1.2.2"     // or "1.3.0-SNAPSHOT"

val circeVersion = "0.5.1"

lazy val root = project.in(file(".")).
  aggregate(`lenses-codecJS`, `lenses-codecJVM`).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val `lenses-codec` = crossProject.in(file(".")).
  settings(
    name := "lenses-codec",
    version := "0.1-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.github.julien-truffaut"  %%%  "monocle-core"    % monocleVersion,
      "com.github.julien-truffaut"  %%%  "monocle-generic" % monocleVersion,
      "com.github.julien-truffaut"  %%%  "monocle-macro"   % monocleVersion,
      "com.github.julien-truffaut"  %%%  "monocle-state"   % monocleVersion,
      "com.github.julien-truffaut"  %%%  "monocle-refined" % monocleVersion,
      "com.github.julien-truffaut"  %%%  "monocle-law"     % monocleVersion % "test",
      "io.circe"                    %%%  "circe-core"      % circeVersion,
      "io.circe"                    %%%  "circe-generic"   % circeVersion,
      "io.circe"                    %%%  "circe-parser"    % circeVersion
    ),
    //For @Lenses
    addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

  ).jvmSettings(
    // Add JVM-specific settings here
    libraryDependencies ++= Seq()

  ).jsSettings(
    // Add JS-specific settings here
    libraryDependencies ++= Seq()
)

lazy val `lenses-codecJVM` = `lenses-codec`.jvm
lazy val `lenses-codecJS` = `lenses-codec`.js
