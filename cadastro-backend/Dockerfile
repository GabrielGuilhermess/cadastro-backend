# Etapa de build: utiliza uma imagem Maven para compilar a aplicação
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
# Copia o pom.xml e baixa as dependências (para cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copia o restante do código e compila a aplicação
COPY src/ ./src/
RUN mvn clean package -DskipTests

# Etapa final: utiliza uma imagem leve para executar a aplicação
FROM eclipse-temurin:17-jdk-alpine
# Instala o Bash na imagem Alpine
RUN apk add --no-cache bash
WORKDIR /app
# Copia o JAR gerado na etapa de build e renomeia para app.jar
COPY --from=build /app/target/cadastro-backend-0.0.1-SNAPSHOT.jar app.jar
# Copia o script wait-for-it.sh para a imagem
COPY wait-for-it.sh /app/wait-for-it.sh
# Torna o script executável
RUN chmod +x /app/wait-for-it.sh
# Define o ENTRYPOINT para aguardar o Postgres e iniciar a aplicação
ENTRYPOINT ["/app/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "app.jar"]
