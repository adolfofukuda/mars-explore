# probes-api

Teste proposto para processo de admissão na elo7. 

##Ferramentas utilizadas:

1. [Oracle JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
2. [Apache Maven](https://maven.apache.org/)
3. [Docker Desktop](https://www.docker.com/products/docker-desktop)
4. [Project Lombok](https://projectlombok.org/) 
5. [Redis](https://redis.io/)
6. [Postman](https://www.getpostman.com/)

### Instalação e uso

##### 1. Gerando o artefato (na raiz do projeto)
- `mvn clean install`

##### 2. Executar o docker(na raiz do projeto):
- `docker-compose up`

##### Atualizar o container do docker(na raiz do projeto):
- `docker-compose down`
- `docker-compose up`

##### Na raiz do projeto ha um arquivo(`mars-explorer.postman_collection.json`) a ser importado no postman para vc efetuar os testes que quiser na aplicação


### Considerações

##### Optei por me inspirar nos seguintes conceitos para esse projeto:
- `SOLID` para design do software. Acrônimo importante para o entendimento de muitos patterns. Na implementação optei por dar enfase em SRP, ISP, DIP e OCP. Liskov menos um pouco.
- `DDD` como separar os concerns e termos um design com real reuso e visao clara de responsabilidades e recursos realmente fundamentais
- `TDD` para testes e auxilio no design das classes
- `RESTful` disponibilizar os serviços em uma API simples somente com HTTP, seguindo as ultimas tendencias
- `Containeirização com Docker` seguindo a tendência de conteiners para separar as aplicações com suas dependências e assim facilitar o deploy e escalabilidade


##### Flawless mas como possivel melhora rapida
- Um projeto java grande para uma api simples. Procurei no entanto criar um bom design e de certa forma, mostrar minhas preocupacoes em cumrprir as regras, fazer um bom design e termos possibilidade de add novos recursos
- Ausência de logs - Ainda que util e possivel uso de ferramentas como elastic search e kibana para busca e avaliacao de erros, optei por nao colocar ainda mais complexixade no teste
- Ausência de swagger para documentação - Poderia usar o swagger-fox que le o codigo e gera a documentação on line. Mas sem as devidas notações, a documentação seria menos legivel. Optei entao por fechar os usos da API com um projeto pequeno em postman.




