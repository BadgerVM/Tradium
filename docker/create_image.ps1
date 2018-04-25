$var = $(get-item ${PWD}).parent.FullName
$pathProjectAngular = $var + "\Fase4angular"
$pathProject = $var + "\Fase4"
$pathJar = $pathProject + "\target"

#Create Angular
docker run -it --rm --name tradium-angular -v ${pathProjectAngular}:/otp/tradium -w /otp/tradium teracy/angular-cli ng build --base-href /new/

#Move angular files to Fase4
rm ${pathProject}\src\main\resources\static\new\*
cp ${pathProjectAngular}\dist\* ${pathProject}\src\main\resources\static\new

#Create jar Tradium
docker run -it --rm --name tradium -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn package -DskipTests

#Delete previous jar
rm security_ejem7-0.1.0.jar

#Move jar to actual directory
mv ${pathJar}/security_ejem7-0.1.0.jar .

#Create image
docker build -t dgarciaci/tradium3 .
