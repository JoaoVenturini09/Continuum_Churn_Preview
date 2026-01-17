# Organizadores do Hackathon

<div>
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
  
  <img src="https://img.shields.io/badge/Alura-13294B?style=for-the-badge">
  
  <img src="https://img.shields.io/badge/No%20Country-000000?style=for-the-badge&logoColor=white">
  
</div>
 

---

# <img src="https://github.com/user-attachments/assets/d8419ab3-9338-4d35-9913-1c29d07296f7" alt="l" width="150"/> 

O Projeto Continuum propõe uma solução completa para previsão de churn, unindo Data Science e Back-end: o time de DS treina o modelo preditivo e o time de BE expõe previsões via API para que o negócio aja antes que o cliente decida sair. Com base em hábitos de uso e histórico de pagamento, a academia pode identificar clientes com alta probabilidade de evasão e realizar ações de retenção.

Para cada faixa de probabilidade sugerimos algumas ações de retenção ao cliente. 

Exemplo: 

- Para a faixa entre 0% e 19%, sugerimos manter relacionamento padrão.
- Para a faixa entre 20% e 39%, sugerimos comunicação ativa e incentivo ao engajamento.
- Para a faixa entre 40% e 59%, sugerimos oferta de benefício leve (ex: brinde ou aula extra).
- Para a faixa entre 60% e 79%, sugerimos oferta de desconto ou upgrade de plano.
- Para a faixa entre 80% e 100%, sugerimos ação personalizada com contato direto do time de retenção.

## 📊 Detalhes sobre o funcionamento de Dados: [Modelo Continuum Preview (PDF)](https://github.com/JoaoVenturini09/Continuum_Churn_Preview/blob/docker/Modelo_Continuum_Preview.pdf)
---

🎯 Desafio

O ChurnInsight busca responder à seguinte pergunta:

➡️ Quais clientes apresentam maior probabilidade de evasão ?

Essa integração permitirá que o negócio aja antes que o cliente decida sair, aumentando a retenção e reduzindo perdas.

🔎 Visão geral da arquitetura
• 	Fluxo: Dados de clientes → API Java (DTO valida e persiste em H2) → Chamada à API Python (modelo) → Resposta com probabilidade/risco → Persistência do resultado e interpretabilidade.

• 	Integração: API Java (Spring) orquestra entrada e persistência; API Python (FastAPI) entrega previsões com interpretabilidade das features mais relevantes.

• 	Persistência: Banco H2 em modo dev para agilidade e leveza; pode evoluir para RDBMS gerenciado em produção.

• 	Formatos: Integração e interoperabilidade em JSON e CSV.

---

## 🚀 Objetivos
 
 • Este projeto tem como objetivo utilizar Inteligência Artificial (Machine Learning) para identificar quais alunos possuem maior probabilidade de cancelar sua matrícula (churn).
Com isso, a academia pode tomar ações preventivas, oferecendo suporte ou incentivos antes que o aluno decida sair.

 • A ferramenta transforma a gestão da academia de reativa (esperar o cancelamento) para proativa (agir antes do churn acontecer).

---

## 🔑 Variáveis consideradas 

- `nps_score`  
- `tempo_contrato_meses`  
- `tentou_cancelar_antes`  
- `valor_mensal`  
- `atrasos_pagamento_12m`  
- `duracao_media_treino_min`  
- `engajamento_por_custo`  
- `reducao_frequencia_3m`  
- `frequencia_mensal`  
- `tem_personal_trainer`  

---

## ⚙️ Tecnologias e Ferramentas

- **Python** → (microserviço e APIs)  
- **Machine Learning** → (scikit-learn , pandas , numpy) 
- **Banco de Dados H2** → (persistência leve e integrada)  
- **Docker** → (para containerização e deploy)  
- **Frameworks de API** → (Flask para serviços em Python)
- **Java + Spring Boot** → (backend robusto e escalável)
- **Joblib** → (serialização de modelos)  
-  **Front end** → (HTML , CSS e JavaScript)
---

## 📈 Entregáveis do Projeto:

- Microserviço Python com endpoints REST  
- Modelos de machine learning integrados para previsão de churn  
- API com tratamento de erros e logs centralizados  
- Endpoint de estatísticas de dados  
- Banco H2 configurado para persistência  
- Dashboard simples para visualização de riscos e métricas  
- Processamento batch para análises periódicas  
- Container configurado para deploy  

---

## ▶️ API Python de Previsão de Churn


## 🚀 Visão Geral

A API Python realiza a integração com API Java para previsão de churn, com o Flask que carrega um modelo de Machine Learning previamente treinado e salvo (Churn_Academia_V15.ipynb).
A API recebe dados de clientes em formato JSON e retorna:

• 	Probabilidade de churn

• 	Classificação de risco (ALTO/BAIXO)

• 	As 3 features mais relevantes para a previsão

---

## 📡 Exemplos de Requisição e Resposta (JSON)

### Requisição


```
{
    "nome": "Fulano",
    "cliente_id": "CLI_RISCO_1",
    "genero": "F",
    "idade": 22.0,
    "data_inicio_contrato": "10/01/2024",
    "tempo_contrato_meses": 3.0,
    "tipo_plano": "Básico",
    "valor_mensal": 89.90,
    "forma_pagamento": "Boleto",
    "frequencia_mensal": 3.0,
    "duracao_media_treino_min": 40.0,
    "tem_personal_trainer": 0.0,
    "participa_aulas_coletivas": 0.0,
    "usa_app_academia": 0.0,
    "atrasos_pagamento_12m": 1.0,
    "teve_desconto_promocao": 0.0,
    "nps_score": 2.0,
    "numero_reclamacoes": 1.0,
    "participou_eventos": 0.0,
    "reducao_frequencia_3m": 1.0,
    "dias_desde_ultimo_acesso": 20.0,
    "tentou_cancelar_antes": 1,
    "engajamento_por_custo" : "55"
}
```

Resposta do JSON: 

```
{
  "probabilidade_churn": 0.7768,
  "risco": "ALTO",
  "primeiro_mais_relevante": "tentou_cancelar_antes",
  "segundo_mais_relevante": "nps_score",
  "terceiro_mais_relevante": "frequencia_mensal"
}

```


---
## 🚀 Visão Geral H2

Este backend Java utiliza Spring Boot com banco H2 embutido para ambiente de desenvolvimento.

O H2 é um banco relacional leve, que roda em memória ou em arquivo, ideal para testes rápidos sem necessidade de instalação externa.

📂 Pré-requisitos: 

• 	☕ JDK: Temurin ou OpenJDK 

• 	🛠️ Maven: para build e execução

• 	🗄️ Banco: H2 embutido (não precisa instalar nada)

---
# 📊 Estatísticas de Churn

Este serviço expõe um endpoint para consultar estatísticas básicas sobre os avaliados e suas probabilidades de churn.

Big numbers: 

•  Quantidade de avaliados.

• Média de probabilidade entre todos os avaliados 

•  Probabilidade alta (Em percentual, quantos registros tiveram ALTA probabilidade de churn).

---
## 🐳 Instalação Rápida com Docker (opcional para demo)

### 📦 Executando com Docker

### Rodar com `docker compose`

Na raiz do projeto Brande Docker, execute no terminal:

```
docker compose up --build
```

---

## 🧪 Testando a API

Após subir o container, você pode testar no navegador ou via ferramentas como **VSCode REST Client**, **live server**:

```bash
http://localhost:8080/analises-churn
```

Para ter acesso pelo navegador usar este caminho para API:

```bash
http://localhost:8080/index.html
```

Para ter acesso pelo navegador usar este caminho pela OCI para API:

```bash
http://140.238.180.36:8080/index.html
```

---

## ✅ Dicas

- Certifique-se de que o **Docker Desktop** ou serviço equivalente esteja rodando.
  
- Caso queira alterar portas, ajuste o `docker-compose.yml`.
  
- Para parar os containers:
  
```bash
docker compose down
```

---

## 📌 Observações
     
> ⚠️ Este é o repositório oficial que será demonstrado aos responsáveis.

> As informações envolvidas são de clientes de uma empresa de Academia, utilizando **base de dados fictícia** para análise.

> Lead-in de dados: Os aliases no DTO (JsonAlias) estão alinhados ao pipeline do modelo, facilitando integração direta.

> H2 em dev: Ideal para demonstração e testes rápidos. Em produção, migre para banco gerenciado.

> Interpretabilidade: As três features mais relevantes por cliente ajudam ações de retenção (marketing e suporte) de forma objetiva.

> Evolução: O projeto é modular e preparado para escalar, incluindo troca de modelo, novas variáveis e integração com serviços externos.

---

# 🙌 Créditos Finais — Projeto Continuum Churn Preview

Este repositório documenta o trabalho desenvolvido pela equipe **Continuum Churn Preview**, dentro da iniciativa **ChurnInsight — Prévia de Cancelamentos de Clientes**.  

O projeto uniu esforços de **Ciência de Dados** e **Back-End** para construir uma solução integrada de previsão de churn, permitindo que empresas ajam de forma preventiva na retenção de clientes.

---

## 📌 Informações da Equipe

- **Nome da equipe na plataforma:** `H12-25-B-Equipamento 31-Ciência de Dados`  
- **Nome da equipe:** `Continuum Churn Preview`  
- **Projeto:** `ChurnInsight — Prévia de Cancelamentos de Clientes`  

---

## 👨‍💻 Liderança

- **Líder Geral / Data Science:** João Venturini  
- **Líder Back-End:** Gabryel Júlio dos Santos  

---

## 👩‍🔬 Equipe de Data Science

- João Venturini — [LinkedIn](https://www.linkedin.com/in/joaoventurini/)  
- Andreza Lucas — [LinkedIn](https://www.linkedin.com/in/andreza-lucas-da-silva-datascience/)  
- João Victor Lima Caris de Oliveira — [LinkedIn](https://www.linkedin.com/in/joãovictorcybersecurity/)  
- Pedro Afonso Pinto Moraes Santos — [LinkedIn](https://www.linkedin.com/in/pedro-afonso-pinto-moraes-santos-5330621b3/)  

---

## 🖥️ Equipe de Back-End

- Nayara Calixto — [LinkedIn](https://www.linkedin.com/in/nayara-calixto-dev/)  
- Gabryel Júlio dos Santos — [LinkedIn](https://www.linkedin.com/in/gabryel-santos)  

---

## 🎯 Reconhecimento

Este projeto é fruto de **colaboração multidisciplinar**, unindo ciência de dados e engenharia de software para entregar uma solução inovadora e sustentável.  

Agradecemos a todos os membros pela dedicação, criatividade e comprometimento em cada etapa do desenvolvimento.

---

