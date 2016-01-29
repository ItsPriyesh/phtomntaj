name := "phtomntaj"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.sksamuel.scrimage" %% "scrimage-core" % "2.1.0"
)

unmanagedJars in Compile += file("libs/android-support-v7-palette.jar")