$var = $(get-item ${PWD}).parent.FullName
$pathProject = $var + "\tradium"
$pathJar = $pathProject + "\target"

Write-Output "Creating jar of tradium..."
#Create jar contafin
docker run -it --rm --name tradium -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn clean package -DskipTests

#Move jar to actual directory
$tradiumJar = "tradium-0.0.1-SNAPSHOT.jar"
$existeJar = Test-Path $tradiumJar
if($existeJar -eq $True){
    Remove-Item $tradiumJar
}
mv ${pathJar}/contafin-0.0.1-SNAPSHOT.jar .

Write-Output "Creating Docker image of tradium..."
#Creating image 
docker build --no-cache -t dgarciaci/tradium:latest .