![app](https://user-images.githubusercontent.com/59378841/150705920-2a6fc1a8-a62a-4197-a55c-faacb677f82d.gif)

## 💻 Projeto
Aplicativo para reserva de ingressos de cinema. Veja os filmes que estão em cartaz e também os que vem por aí. No aplicativo é possível visualizar os filmes, salas, horários e assentos disponíveis. Os dados são exibidos no app de forma dinâmica através de uma API.

## ✨ Tecnologias

-   [ ] Android
-   [ ] Kotlin
-   [ ] Android Studio
-   [ ] Consumo de API REST
-   [ ] Uso de recycler view e nested recycler view
-   [ ] Uso de bibliotecas como Retrofit e Glide
-   [ ] Uso de fontes personalizadas

## :hammer_and_wrench: Back-end
Para o backend do projeto usei a ferramenta json-server. Com essa ferramenta pude simular o consumo de uma API REST de forma simples. Você pode acessar a documentação dessa biblioteca no link:

https://github.com/typicode/json-server
## Executando o projeto
Para executar o projeto é necessário instalar a ferramenta json-server, para isso execute o comando:
```
npm install -g json-server
```
Após isso, é preciso iniciar o servidor para ouvir as requisições:
```
json-server --host 192.168.1.16 database.json
```
O endereço IP no comando deve ser substituído pelo IP do computador local, e também deve ser alterado na variável **BASE_URL** no arquivo *MyRetrofit.kt* no  caminho: *app/src/main/java/com/lucascarlos/cineticket/api/*.

O arquivo **database.json** está disponível no caminho: *app/src/*
