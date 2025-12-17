# Continuum_Churn_Preview
Projeto para prever e tentar reverter o churn dos clientes

- Atentar-se ao fato de que este é o repositório oficial que será demonstrado aos responsáveis. 

- As informações envolvidas são de clientes de uma empresa de Academia.
  
- Com base de dados fictício que sera mais adequado para nossa análise de dados.

# 📊 Continuum

Projeto **Continuum** tem como objetivo realizar **análise de churn em Academia**, utilizando variáveis críticas de comportamento e perfil de clientes.  
O foco inicial é construir uma API robusta que permita interação entre **Java** e **Python**, suportando formatos **JSON** e **CSV**, com apoio da ciência de dados.

---

## 🚀 Objetivo
- Detectar padrões de **churn (cancelamento de clientes)**.
- Integrar análises estatísticas e modelos de machine learning em um **microserviço Python**.
- Fornecer endpoints para consumo em aplicações externas.

---

## 🔑 Variáveis consideradas alterar depois do acordo durante as semanas Contrato de integração
- `account_Charges_Monthly` → Valor mensal da conta.
- `internet_InternetService_Fiber optic` → Tipo de serviço de internet.
- `account_PaperlessBilling` → Faturamento sem papel.
- `account_PaymentMethod_Electronic check` → Método de pagamento.
- `internet_StreamingMovies_1` → Uso de streaming de filmes.

Essas variáveis são pontos de análise para prever a probabilidade de churn.

---

## 🏗️ Arquitetura
- **Java API** → Responsável pela exposição dos endpoints REST.
- **Python Engine** → Processamento de dados, análise estatística e machine learning.
- **Integração** → Comunicação via JSON/CSV entre os serviços.
- **Microserviço** → Modular, escalável e preparado para futuras expansões.

---

## ⚙️ Tecnologias
- **Java (Spring Boot)** → Criação da API.
- **Python (Pandas, Scikit-learn)** → Análise de dados e modelos de churn.

---

## 📂 Estrutura do projeto

- pode ser alterado por qualquer membro da equipe caso tenha de desenvolver novos pontos que venha surgir em reunião.
