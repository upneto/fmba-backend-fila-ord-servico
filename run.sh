IMAGE_NAME="fmba-backend/fila-ordem-servico"
CONTAINER_NAME="app_fila_ordem_servico"

## Cria Jar do projeto ##

echo "-> Cria Jar do projeto: ${CONTAINER_NAME}"
mvn clean install package

## Executa criação da imagem Docker ##

echo "-> Parando a execução atual do container: ${CONTAINER_NAME}"
docker stop ${CONTAINER_NAME}

echo "-> Removendo imagem:  ${IMAGE_NAME}"
docker image rm ${IMAGE_NAME}:latest -f

echo "-> Gerando imagem:  ${IMAGE_NAME}"
docker-compose up
