# 📊 Continuum

Projeto **Continuum** tem como objetivo realizar **análise de churn em Academia**, utilizando variáveis críticas de comportamento e perfil de clientes.  
O foco inicial é construir uma API robusta que permita interação entre **Java** e **Python**, suportando formatos **JSON** e **CSV**, com apoio da ciência de dados.

Constante evolução, focado em entregar soluções inovadoras e sustentáveis. Este repositório documenta o progresso, objetivos e melhorias planejadas para garantir qualidade, escalabilidade e impacto positivo.

- Atentar-se ao fato de que este é o repositório oficial que será demonstrado aos responsáveis. 

- As informações envolvidas são de clientes de uma empresa de Academia.
  
- Com base de dados fictício que sera mais adequado para nossa análise de dados.

---

## 🚀 Objetivo

Durante a evolução do projeto, os seguintes pontos serão trabalhados:

• 	Refatoração de Código Melhorar a legibilidade, modularidade e eficiência da base de código.

• 	Testes Automatizados Implementar testes unitários e de integração para aumentar a confiabilidade.

• 	Performance e Escalabilidade Otimizar processos e arquitetura para suportar maior volume de dados e usuários.

• 	Interface e Experiência do Usuário (UX/UI) Aprimorar design e usabilidade para tornar o sistema mais intuitivo.

• 	Segurança Adotar boas práticas de segurança e proteção de dados.

• 	Integrações Futuras Planejar e implementar integrações com serviços externos relevantes.


---

## 🔑 Variáveis consideradas alterar depois do acordo durante as semanas Contrato de integração
- nps_score	
- tempo_contrato_meses
- tentou_cancelar_antes
- valor_mensal	
- atrasos_pagamento_12m
- duracao_media_treino_min	
- engajamento_por_custo	
- reducao_frequencia_3m	
- frequencia_mensal
- tem_personal_trainer

---

## 🏗️ Arquitetura
- **Java API** → Responsável pela exposição dos endpoints REST.
- **Python Engine** → Processamento de dados, análise estatística e machine learning.
- **Integração** → Comunicação via JSON/CSV entre os serviços.
- **Microserviço** → Modular, escalável e preparado para futuras expansões.

---

## ⚙️ Tecnologias e Ferramentas

• 	Python (microserviço e APIs)

• 	Machine Learning (bibliotecas como scikit-learn, pandas, numpy)

• 	Banco de Dados H2 (persistência leve e integrada)

• 	Docker (containerização e deploy)

• 	Frameworks de API (FastAPI ou Flask)

• 	Dashboard (Streamlit ou Dash para visualização)

## 📂 Estrutura do projeto

- pode ser alterado por qualquer membro da equipe caso tenha de desenvolver novos pontos que venha surgir em reunião.



## 📈 Entregáveis Esperados

1. 	Microserviço Python com endpoints REST.
2. 	Modelos de machine learning integrados para previsão de churn.
3. 	API com tratamento de erros e logs centralizados.
4. 	Endpoint de estatísticas de dados.
5. 	Banco H2 configurado para persistência.
6. 	Dashboard simples para visualização de riscos e métricas.
7. 	Processamento batch para análises periódicas.
8. 	Container configurado para deploy.
9. 	Parametrização de métricas de retenção.
