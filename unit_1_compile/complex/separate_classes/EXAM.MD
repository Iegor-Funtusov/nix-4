### Run compile ###
`$ javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.11.jar src/ua/com/alevel/test/Test.java src/ua/com/alevel/Main.java`
### Run program ###
`$ java -cp build/classes/:./libs/commons-lang3-3.11.jar:. ua.com.alevel.Main`