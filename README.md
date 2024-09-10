# Projeto-api

Exercício 1:
Print da Requisição no Insomnia
![image](https://github.com/user-attachments/assets/36589198-1df6-4c22-add5-ea1302fbb2bc)

![image](https://github.com/user-attachments/assets/5a78e393-1fc4-4760-a2fb-8400edf4dfe8)

Inicialmente, estamos trabalhando em um projeto Aluno Online, com acessos a cadastro e informações básicas.

Com o modelo estruturado, seguimos passos para um degrau maior do nosso código. Nessa primeira etapa, criamos os Model, Service, Repository e Controller, não deixando de lado a funcionalidade do Banco de Dados, com o Postgressql.

Com o desenvolvimento gradativo, usamos anotações para fins de trazer praticidade ao código. 

Model: criamos características que o aluno vai seguir como modelo, como anotações, do tipo @ID e outras para contribuir em um auto incremento quando um novo aluno for criado.

Repository: É nessa interface que promoveremos os acessos direto ao banco de dados, que pelo programa Insomnia virmos interações diretas com a nossa API e, com teste de sucesso, os resultados foram excelentes. Nessa interface (AlunoRepository) ela herda o modelo criado pelo Model.

Service: Através dela vamos servir o sistema, onde as validações e regras se apresentaram. Assim nosso AlunoService, criou o criarAluno, assim já permitindo a injeção de dependentes e a interação entre o nosso Repository, direto com o banco.

Controller: Nessa etapa iniciamos o end-points, criamos o @RequestMapping, fazendo a rota direta com acesso ao front. Com a anotação @Autowired fizemos uma injeção do AlunoService com o objetivo de trazer o criarAluno onde podemos verificar o status por @ResponseStatus, entre este e o parâmetro no corpo(body) que vem do acesso, por sua vez em formato JSON o @RequestBody.  

Essa primeira fase, demos acesso à nossa API, com teste, novamente ditos como resultados de sucesso.
