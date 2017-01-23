path=C:\Program Files\Java\jdk1.8.0_20\bin;%path%

javac -d . controller/*.java
javac -d . view/*.java
javac -d . model/*.java
javac Main.java

java Main
