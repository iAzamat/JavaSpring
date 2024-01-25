## Установка Maven
1. Зайдите на официальный сайт Maven в раздел загрузка и скачайте последнюю стабильную версию.
2. Распакуйте архив в инсталляционную директорию. Например, в "C:\Program Files\maven\" в Windows или /opt/maven в Linux.
3. Установите переменную окружения M2_HOME: в "C:\Program Files\maven\". 
4. Установите переменную окружения PATH: "%M2_HOME%\bin".
5. Проверьте корректность установки, набрав в командной строке:
6. mvn -version​

mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4

mvn validate - валидация проекта mvn package - сборка проекта

java -cp ./target/gp_app-1.0.0.jar com.gb.app/App