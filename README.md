# Nuclear 360 — API (Entrega Cloud / DevOps · Sprint 1)

Prova de conceito de **Docker + Microsoft Azure** para a API do **Nuclear 360**, uma plataforma gamificada que aproxima a sociedade da tecnologia nuclear, mostrando suas aplicações na saúde, agricultura, indústria, pesquisa e energia.

**Parceiro:** AMAZUL (Marinha do Brasil) · **Challenge FIAP**

## Integrantes

| Nome              | RM       |
|-------------------|----------|
| Pedro Falchi      | RM566967 |
| Matheus Guimarães | RM567912 |
| Lucas Shida       | RM568194 |

Turma: 2TDSPS

## Descrição da solução

API REST em **Spring Boot (Java 21)** que gerencia as perguntas e respostas do quiz do Nuclear 360. Nesta sprint, a API é containerizada com **Docker** e executada em uma **máquina virtual no Azure**, provisionada por **Azure CLI**. O banco de dados é o **H2 embarcado**, que roda dentro da própria aplicação — por isso a solução sobe em um único container.

## Arquitetura

![Arquitetura](arquitetura_h2.png)

Fluxo: o cliente acessa a API pela internet → o **NSG** (firewall do Azure) libera a porta 8080 → a requisição chega na **VM Ubuntu**, onde o **Docker** roda o **container da API** (com o H2 embarcado). A porta 22 (SSH) fica restrita ao IP do desenvolvedor.

## Tecnologias

- Java 21 · Spring Boot · Spring Data JPA · H2
- Docker (multi-stage build, usuário não-root)
- Microsoft Azure (VM Ubuntu) · Azure CLI
- Maven

## Como executar

Pré-requisitos: conta Azure, [Azure CLI](https://learn.microsoft.com/cli/azure/install-azure-cli) e Git.

```bash
# 1. autenticar no Azure
az login

# 2. provisionar a VM e subir a API (cria tudo via Azure CLI)
bash deploy.sh
# ao final, o script imprime o IP público da VM

# 3. testar a API (status 200)
#    http://SEU-IP:8080/api/pergunta

# 4. ao terminar, apagar todos os recursos
bash cleanup.sh
```

Para testar a imagem localmente antes do Azure:

```bash
docker build -t nuclear360-api .
docker run -d -p 8080:8080 --name api nuclear360-api
# http://localhost:8080/api/pergunta
docker rm -f api
```

## Como Docker e Cloud Computing são utilizados na solução

No Nuclear 360, o **Docker** empacota a API com todas as suas dependências numa imagem, garantindo que a aplicação rode de forma idêntica no computador de cada integrante e na nuvem, o que elimina o problema do "na minha máquina funciona". O **Cloud Computing (Azure)** hospeda esse container em uma máquina virtual e torna a API acessível a partir de qualquer lugar, o que é essencial para o objetivo do projeto: alcançar escolas e eventos muito além das palestras presenciais da AMAZUL. A infraestrutura é criada por **Azure CLI**, de forma automatizada e reproduzível, e pode ser apagada com um único comando ao fim do uso. Nas próximas sprints, a mesma imagem Docker pode migrar para serviços gerenciados do Azure e escalar em momentos de pico, como um quiz ao vivo com várias turmas simultâneas.

## Vídeo demonstrativo

YouTube: https://youtube.com/shorts/YvpkIviBxd0?feature=share