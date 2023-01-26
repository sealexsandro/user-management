
## Bem-vindo a este repositório

Esse projeto é o desafio técnico Para Desenvolvedor Back-end na Attornatus.

## Descrição:
Este projeto trata-se de API simples para gerenciar Pessoas

- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar endereços da pessoa
- Poder informar qual endereço é o principal da pessoa 

## EndPoints:
Os endpoints para teste e acesso ao seus métodos HTTPs (GET, POST, PUT, PATCH) desta API são:

#### 🟡 POST - Criar um User

<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users
</code></pre> 


{<br>
	"name": "Sebastião",<br>
    "birthDate": "18/03/2000",<br>
    "addresses": [<br>
        {<br>
	        "street": "Palmares Vila Coy",<br>
	        "cep": "123456",<br>
	        "number": 789,<br>
	        "city": "Jacaré Verde",<br>
	        "state": "Nevada"<br>
        }<br>
    ]<br>
}<br>

 <br><br>
  
#### 🟡 PUT - Atualizar User
 <div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate">  http://localhost:8081/users/{userId}
</code></pre> 

{<br>
	"name": "Sebastião Silva",<br>
    "birthDate": "1/03/2020",<br>
    "addresses": [<br>
        {<br>
	        "street": "Palmares Vila Coy",<br>
	        "cep": "123456",<br>
	        "number": 789,<br>
	        "city": "Jacaré Verde",<br>
	        "state": "Nevada"<br>
        }<br>
    ]<br>
}<br>
    
   
#### 🟢  GET - Buscar um User Por ID
<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users/{userId}
</code></pre> 

  
  
#### 🟢  GET - Buscar todos os Users
<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users
</code></pre> 
  

#### 🟡 POST - Salvar Um endereço novo

<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users/address/{userId}
</code></pre> 

{<br>

	"street": "Avenida Francesa",<br>
	"cep": "123456",<br>
	"number": 789,<br>
	"city": "Patos",<br>
	"state": "Paraíba"<br>   
   
}<br>

 <br><br>
  
 #### 🟢  GET - Buscar todos os endereços de um User
<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users/address/{userId}
</code></pre> 


 #### 🟢  PATCH - Informar qual é o endereço principal do User
<div class="snippet-clipboard-content position-relative overflow-auto"> 
<pre class="notranslate"><code class="notranslate"> http://localhost:8081/users/address/{userId}/{addressId}
</code></pre> 
  
  
  
  