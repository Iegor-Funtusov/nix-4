### prepare manifest ###
`$ echo Main-Class: ua.com.alevel.Main>MANIFEST.MF`
### prepare jar output ###
`$ mkdir -p build/jar`
### run compile ###
`$ javac -sourcepath src/ -d build/classes/ src/ua/com/alevel/Main.java`
### create jar file ###
`$ jar cfm build/jar/HelloWorld.jar MANIFEST.MF -C build/classes/ .`
### Run program ###
`$ java -jar build/jar/HelloWorld.jar`