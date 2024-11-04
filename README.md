# DataTech
**Integrantes do Grupo:**

**Nome Completo:** Ana Paula Nascimento Silva- rm552513

**Nome Completo:** Calina Thalya Santana da Silva- rm552523

**Nome Completo:** Geovana Ribeiro Domingos Silva- rm99646

**Nome Completo:** Leonardo Camargo Lucena- rm552537

**Nome Completo:** Nathan Nunes Calsonari- rm552539

# Instruções de Execução
Para executar o programa, você precisa primeiramente configurar o application.propperties para acessar o seu banco de dados e a parte da mensageria. Além disso, na usuarioController, você precisa colocar o email que irá utilizar para enviar para outras pessoas.

Após isso, precisa se registrar no site para ter acesso a lista de produtos e outras coisas.

# Link do vídeo:
[https://www.youtube.com/watch?v=KWpIaUxcklE](https://www.youtube.com/watch?v=cWEegZS4ikg)

# Como executar a aplicação de java com pipelines

# 1) Conferir serviços
**É necessário ter criado no seu portal azure o grupo de recursos, um aplicativo de serviço e um banco de dados sql server**

![image](https://github.com/user-attachments/assets/29ba6b5c-06ff-410b-824c-ad701fc9ae7c)

# 2) Azure Organizations
**Agora você precisa entrar no seu portal da azure organizations para conseguir criar as pipelines**

![image](https://github.com/user-attachments/assets/86402edc-e8bf-4ee7-9f68-023094f04942)

# 3) Criando um novo projeto
**Dentro da própria página, você vai clicar em (+New Project). Em seguida, ele pedirá para você preencher os dados para a criação do projeto**

![image](https://github.com/user-attachments/assets/8fdd06ef-a038-498d-8845-0f04930067aa)

**Lembre-se de colocar como agile nas opções**

![image](https://github.com/user-attachments/assets/0f6893c5-3ad1-4ba5-82b8-70bdf53da21c)

# 4) Criando repos e importando projeto do github
**Agora, você precisa ir na lateral esquerda e selecionar repos, em seguida, clique na opção de import a repository**

![image](https://github.com/user-attachments/assets/563a9f43-1efe-4202-ad2c-d363de36ac5b)

**Agora coloque o link do repositório que está aqui em baixo e clique em import :**

**Repositório:** https://github.com/NathanNunesCalsonari/sprint4_devops

![image](https://github.com/user-attachments/assets/5c891c3a-049f-411d-b010-b53267ac86ae)

# 5) Criando uma tarefa, criando uma branch e configurando a branch
**Vamos criar um work item para a nossa branch. Nessa work item precisamos deixar ela ativa e criar uma branch a partir dela**

![image](https://github.com/user-attachments/assets/6d8021c7-ac10-4fe4-9c74-4c0dba06e6e2)

**Agora vamos ir na parte de branchs e veremos que temos a nossa branch criada**

![image](https://github.com/user-attachments/assets/57e684d3-b564-49f9-bf6b-57a9fe422f11)

**Vamos clicar nos 3 pontinhos e selecionar ela como "Set as compare branch" e depois vamos em branch policies**

![image](https://github.com/user-attachments/assets/0f85f68b-f8f6-4b81-ba06-b24bffab078a)

**Vamos na branch main e colocamos essas opções:**

![image](https://github.com/user-attachments/assets/a041a689-ecb8-4fe1-9e69-0586b2150605)

# 6) Criando nossa pipeline de build
**Agora vamos criar nossa pipeline de build. Vamos ir no editor classico dela**

**A seguir, siga as imagens onde terá os passos e as configurações corretas pro projeto**

**1)**
![image](https://github.com/user-attachments/assets/798b8986-368c-4639-a883-b4986d0ce468)

**2)**
![image](https://github.com/user-attachments/assets/6ce89f5e-6989-40c7-b562-29afb619e1e8)

**3)**
![image](https://github.com/user-attachments/assets/91abb9d3-125c-4c5e-8b13-e18251f27242)

**4)**
![image](https://github.com/user-attachments/assets/88f9981f-00fc-4e71-8804-3424ca9797df)

**5)**
![image](https://github.com/user-attachments/assets/c1b3d36d-d352-4978-b053-3f8eb7e1512c)

**6)**
![image](https://github.com/user-attachments/assets/355925b5-b36d-4c8e-a57b-64b3d84e5a46)

**7) Importante colocar o **/target/*.jar no contents**

![image](https://github.com/user-attachments/assets/de036836-0ec0-4600-a417-e4d6c951e045)

**8)**
![image](https://github.com/user-attachments/assets/146e65b1-ce8e-4253-8c8e-f9bda93a6023)

**9)**
![image](https://github.com/user-attachments/assets/f9a211ea-4b66-49ba-8578-fb548418b8f4)

**Agora de um save & queue na pipeline**
![image](https://github.com/user-attachments/assets/7b4e0235-e032-426f-8366-37159b805f0c)

**Agora nosso projeto estará fazendo o build da pipeline**
![image](https://github.com/user-attachments/assets/3b112e31-343f-4454-8a17-d9097b913d14)

# 7) Habilitando opção de continuous integration
**Após finalizar nosso build, vamos habilitar uma opção no triggers da pipeline**

![image](https://github.com/user-attachments/assets/6bea0adb-afeb-40e4-9119-6764d9440277)

**Habilitando a opção**
![image](https://github.com/user-attachments/assets/ffde8ad5-2714-4188-82ca-ffed8c1ce42b)

# 8) Criando a pipeline de release

**Em pipelines, vamos em releases e vamos selecionar a opção de Azure App Service Deployment**

![image](https://github.com/user-attachments/assets/97f2cc23-37de-42ca-b417-1706172251b0)

**Agora, os próximos passos, siga as imagens a seguir para configurar certinho a pipeline de release**

![image](https://github.com/user-attachments/assets/f44d1036-85cf-47bf-ad2a-084e46c82c8d)

![image](https://github.com/user-attachments/assets/2e142b0e-7094-42b2-a819-b92f73e98aa2)

![image](https://github.com/user-attachments/assets/726022a1-d760-4956-9cef-08fe4d5fa8d4)

![image](https://github.com/user-attachments/assets/88699ccf-b12d-48b9-90b6-af735741fa74)

**Nessa parte, você precisa colocar sua assinatura da azure e autorizar. Em seguuida, coloque o nome do seu aplicativo de serviço**

![image](https://github.com/user-attachments/assets/98f4f988-a872-4485-a9b3-2cb32243ff2c)

![image](https://github.com/user-attachments/assets/05333959-11a1-4c3e-ae75-da534a386462)

**Importante colocar como .jar**

![image](https://github.com/user-attachments/assets/4d64cec7-915f-414e-a5db-d6eed5775854)

**Após essas configurações, clique em save e depois em create release**

![image](https://github.com/user-attachments/assets/fd443af0-0c7e-438e-8e73-7a067e1b0588)

![image](https://github.com/user-attachments/assets/4192565e-7b82-41b3-89ce-12bb7d7dae77)

**Sua release começará a ser feita**

![image](https://github.com/user-attachments/assets/3d68f0a8-8293-47e0-861e-b602145260ec)
