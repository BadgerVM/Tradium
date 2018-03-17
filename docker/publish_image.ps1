Write-Output "Building image of tradium"
docker build --no-cache -t dgarciaci/tradium:latest .

Write-Output "Pushing image to Dockerhub"
docker push dgarciaci/tradium:latest