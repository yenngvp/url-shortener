
echo '================= Start build, deploy, and starting URLShortener application ==================='
echo "Step 1:   Stopping & Removing running containers...";
docker stop url-shortener-client;
docker rm url-shortener-client;
docker stop url-shortener-server;
docker rm url-shortener-server;
docker stop postgres;
docker rm postgres;

echo 'Step 2:   Removing images...'
docker rmi $(docker images --filter=reference=url-shortener* -q);

echo 'Step 3:   Packaging sprint-boot application...'
./mvnw clean package -DskipTests

echo 'Step 4:   Run docker compose up...'
docker compose up
echo '================= Deploy successfully! =========================='
