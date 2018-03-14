
name := "bandi-spark-work"

version := "1.0"

scalaVersion := "2.11.8"

val releasesName = "Repository Snapshots"
val releasesURL = new java.net.URL("http://http://shr-p-nexus-02.swiggyops.de:8081/nexus/repository/swiggy-commons-releases")
val releasesPattern = "[organisation]/[module]/[revision]/[artifact]-[revision].[ext]"
val releases = Resolver.url(releasesName, releasesURL)(Patterns(releasesPattern))

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

resolvers += "Local Maven Repository" at "file:///"+ Path.userHome + "/.m2/repository"

libraryDependencies  ++= Seq(
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.2.0",
  "org.elasticsearch" % "elasticsearch-spark-20_2.11" % "5.3.1" exclude("org.slf4j", "log4j-over-slf4j"),
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0",
  "org.lyranthe.prometheus" %% "client" % "0.8.4",
  "com.github.fge" % "json-schema-validator" % "2.2.6",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.15",
  "mysql" % "mysql-connector-java" % "5.1.24",
  "org.scalaz" %% "scalaz-core" % "7.2.15",
  "com.101tec" % "zkclient" % "0.8",
  "org.apache.zookeeper" % "zookeeper" % "3.4.5"
)

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "org.json4s" % "json4s-native_2.11" % "3.5.1"



ivyConfiguration <<= (ivyConfiguration, baseDirectory) map {
  case (c: InlineIvyConfiguration, b) => import c._
    new InlineIvyConfiguration(paths, resolvers, otherResolvers, moduleConfigurations,
      localOnly, lock, checksums, resolutionCacheDir.map(_ => b / "123"), log)
  case (other, _) => other // something unknown
}
cacheDirectory <<= baseDirectory / "234"

cleanKeepFiles <+= cacheDirectory / "update"


cleanKeepFiles ++= Seq("resolution-cache", "streams").map(target.value / _)


resolvers ++= Seq(
  "Local Maven Repository" at "file:///"+ "/Users/bandi.kishore/" + "/.m2/repository",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "clojars" at "https://clojars.org/repo",
  "conjars" at "http://conjars.org/repo"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}





