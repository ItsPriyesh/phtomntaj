name := "phtomntaj"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-core" % "2.1.0"
libraryDependencies += "com.squareup.retrofit" % "retrofit" % "1.9.0"

unmanagedJars in Compile += file("libs/sanselan-0.97-incubator.jar")