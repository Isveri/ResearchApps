#!/bin/bash

# Liczba powtórzeń pętli
LOOP_COUNT=5
current_dir=$(pwd)

# Funkcja uruchamiająca aplikację Spring za pomocą Maven
start_spring_app() {
  local app_path=$1
  cd "$app_path" || exit

  mvn spring-boot:run & echo $! >> /tmp/maven_app_pids

  cd "$current_dir" || exit
}

# Funkcja zatrzymująca procesy Maven
stop_maven_processes() {
  if [ -f /tmp/maven_app_pids ]; then
    while read -r pid; do
      kill $pid
    done < /tmp/maven_app_pids
    rm /tmp/maven_app_pids
  fi
}

# Główna pętla
for i in $(seq 1 $LOOP_COUNT); do
  echo "Rozpoczynanie iteracji $i..."

  # Krok 1: Postawienie bazy danych za pomocą Docker Compose
  docker-compose up -d
  if [ $? -ne 0 ]; then
    echo "Nie udało się uruchomić Docker Compose. Przerywanie skryptu."
    exit 1
  fi

  sleep 3

  # Krok 2: Uruchomienie 3 aplikacji Spring za pomocą Maven
  if [ "$1" == "RestLinear" ]; then
    start_spring_app ".\microservices\linear\rest\rest-core"
    start_spring_app ".\microservices\linear\rest\rest-product-service"
    start_spring_app ".\microservices\linear\rest\rest-payment-service"
  elif [ "$1" == "GrpcLinear" ]; then
    start_spring_app ".\microservices\linear\grpc\grpc-core"
    start_spring_app ".\microservices\linear\grpc\grpc-product-service"
    start_spring_app ".\microservices\linear\grpc\grpc-payment-service"
  elif [ "$1" == "RestStar" ]; then
    start_spring_app ".\microservices\star\rest\rest-core"
    start_spring_app ".\microservices\star\rest\rest-product-service"
    start_spring_app ".\microservices\star\rest\rest-auth-service"
  elif [ "$1" == "GrpcStar" ]; then
    start_spring_app ".\microservices\star\grpc\grpc-core"
    start_spring_app ".\microservices\star\grpc\grpc-product-service"
    start_spring_app ".\microservices\star\grpc\grpc-auth-service"
  else
    echo "Bledy parametr: $1. Skrypt przerwany"
    docker-compose down
    stop_maven_processes
    exit 1
  fi

  # Krok 3: Poczekanie 20 sekund aż aplikacje się uruchomią
  sleep 16


  # Krok 4: Uruchomienie testu Gatlinga za pomocą Maven
  if [ "$2" == "LinearRestCrud" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.linear.rest.RestCRUDSimulation')
  elif [ "$2" == "LinearRestImage" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.linear.rest.RestImageSimulation')
  elif [ "$2" == "LinearGrpcCrud" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.linear.grpc.GrpcCRUDSimulation')
  elif [ "$2" == "LinearGrpcImage" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.linear.grpc.GrpcImageSimulation')
  elif [ "$2" == "StarRestCrud" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.star.rest.RestCRUDSimulation')
  elif [ "$2" == "StarRestImage" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.star.rest.RestImageSimulation')
  elif [ "$2" == "StarGrpcCrud" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.star.grpc.GrpcCRUDSimulation')
  elif [ "$2" == "StarGrpcImage" ]; then
    (cd ".\gatling" && mvn gatling:test '-DclassName=io.gatling.test.star.grpc.GrpcImageSimulation')
  else
    echo "Nie wspierany test case"
    docker-compose down
    stop_maven_processes
    exit 1;
  fi

  sleep 5

  # Krok 6: Usunięcie kontenera z bazą danych i wyłączenie aplikacji
  docker-compose down -v
  stop_maven_processes
  sleep 5

  echo "Zakończono iterację $i."
  echo "Oczekiwanie na restart"
  sleep 50
done

echo "Testy zakończone"