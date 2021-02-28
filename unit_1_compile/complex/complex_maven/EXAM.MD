### generate a maven prject ###
`$ mvn archetype:generate -DgroupId=ua.com.alevel -DartifactId=app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`
### create jar file ###
`$ mvn clean install`
### Run program ###
`$ java -jar target/app-1.0-SNAPSHOT.jar `