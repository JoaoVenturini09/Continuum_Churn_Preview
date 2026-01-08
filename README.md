# 📊 Continuum

O Projeto Continuum tem como objetivo realizar análise de churn em Academia, utilizando variáveis críticas de comportamento e perfil de clientes.
O foco inicial é construir uma API robusta que permita interação entre Java e Python, suportando formatos JSON e CSV.

🎯 Desafio
O ChurnInsight busca responder à seguinte pergunta:
➡️ Quais clientes apresentam maior probabilidade de evasão e como podemos agir preventivamente?
Para isso:
• 	O time de Data Science será responsável por desenvolver um modelo preditivo de churn.
• 	O time de Back-end construirá uma API que disponibilizará essas previsões para outros sistemas.
Essa integração permitirá que o negócio aja antes que o cliente decida sair, aumentando a retenção e reduzindo perdas.

---

## 🚀 Objetivos

- **Refatoração de Código** → Melhorar legibilidade, modularidade e eficiência.  
- **Testes Automatizados** → Implementar testes unitários e de integração.  
- **Performance e Escalabilidade** → Otimizar processos e arquitetura para maior volume de dados e usuários.  
- **Interface e UX/UI** → Aprimorar design e usabilidade.  
- **Segurança** → Adotar boas práticas de proteção de dados.  
- **Integrações Futuras** → Planejar integrações com serviços externos relevantes.  

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

- **Python 3.10+** (microserviço e APIs)  
- **Machine Learning** → scikit-learn (1.3.0), pandas (2.1.0), numpy (1.25.0)  
- **Banco de Dados H2** (persistência leve e integrada)  
- **Docker** (24.0+) para containerização e deploy  
- **Frameworks de API** → FastAPI (0.103.0) ou Flask (2.3.0)  
- **Dashboard** → Streamlit (1.27.0) ou Dash (2.14.0)  
- **Joblib** (1.3.2) para serialização de modelos  

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
- Parametrização de métricas de retenção  

---

## ▶️ Como executar o modelo e a API

### 1. Treinar e salvar o modelo
```python
import joblib
from sklearn.pipeline import Pipeline

# O modelo completo e otimizado já foi treinado e está armazenado em `modelo_rf_otimizado`.
# Não precisamos recriar um pipeline, apenas salvar o existente.

joblib.dump(modelo_rf_otimizado, "modelo_pipeline_completo.pkl")

```

### 2. Executar a API (FastAPI)
Crie um arquivo `main.py`:

```python
from fastapi import FastAPI
import json

import joblib
from flask import Flask, jsonify, request

import previsao_lote


def load_config():
    with open('config.json', 'r', encoding='utf-8') as f:
        return json.load(f)

config = load_config()
VALORES_PADRAO = config['VALORES_PADRAO']
PORTA = config['PORTA']

caminho_modelo = 'modelo_pipeline_completo.pkl'


app = Flask(__name__)

model = joblib.load(caminho_modelo)


@app.route('/predict', methods=['POST'])
def predict():

    dados_dict = [request.get_json() | VALORES_PADRAO]

    try:
        resultados = previsao_lote.fazer_previsao_lote(dados_dict, model)
        print(resultados)
        resultado = resultados[0]

        return jsonify({
            "cliente_id": resultado["cliente_id"],
            "probabilidade": resultado["probabilidade_churn"],
            "risco": resultado["risco"],
            "1_mais_relevante": resultado["1_mais_relevante"],
            "2_mais_relevante": resultado["2_mais_relevante"],
            "3_mais_relevante": resultado["3_mais_relevante"]
        })

    except Exception as e:
        return jsonify({"erro": str(e)}), 400

if __name__ == '__main__':
    app.run(port=PORTA)


Rodar a API:
O arquivo conteúdo do previsao_lote.py tem a função montor para processar lista de clientes e retornar previsões com interpretabilidade:

import pandas as pd
import logging

def fazer_previsao_lote(lista_clientes, modelo_pipeline):
    """
    Recebe uma lista de dicionários (clientes) e o pipeline do modelo.
    Retorna uma lista de dicionários com as previsões e as 3 features mais relevantes.
    """
    try:
        df_novos = pd.DataFrame(lista_clientes)
        prob_churn = modelo_pipeline.predict_proba(df_novos)[:, 1]
        contributions = modelo_pipeline.predict_proba(df_novos)  # exemplo simplificado

        feature_names_out = df_novos.columns
        resultados = []

        for i in range(len(df_novos)):
            contrib_cliente = contributions[i]
            feat_contrib = pd.Series(contrib_cliente, index=feature_names_out)

            top_3 = feat_contrib.abs().sort_values(ascending=False).head(3).index.tolist()
            top_3_clean = [f.split('__')[-1] for f in top_3]

            resultados.append({
                'cliente_id': df_novos.iloc[i].get('cliente_id', f'cliente_{i}'),
                'probabilidade_churn': round(prob_churn[i], 4),
                'risco': 'ALTO' if prob_churn[i] >= 0.5 else 'BAIXO',
                '1_mais_relevante': top_3_clean[0] if len(top_3_clean) > 0 else None,
                '2_mais_relevante': top_3_clean[1] if len(top_3_clean) > 1 else None,
                '3_mais_relevante': top_3_clean[2] if len(top_3_clean) > 2 else None
            })

        logging.info("Resultados da previsão em lote gerados com sucesso.")
        return resultados

    except Exception as e:
        logging.error(f"Erro na função fazer_previsao_lote: {e}")
        return []


```

```

---

## 📡 Exemplos de Requisição e Resposta (JSON)

### Requisição
```json
POST /predict
Content-Type: application/json

{
        "VALORES_PADRAO": {
          "teve_desconto_promocao": "0",
          "usa_app_academia": "0",
          "faixa_etaria": "30",
          "forma_pagamento": "Boleto",
          "tipo_plano": "Básico",
          "genero": "M",
          "participa_aulas_coletivas": "0",
          "participou_eventos": "0",
          "risco_inatividade": "0",
          "faixa_fidelidade": "0",
          "numero_reclamacoes": "0"
        },
        "PORTA": 5000
}


[
  {
    "cliente_id": "CLI_RISCO_1",
    "nps_score": 2,
    "tempo_contrato_meses": 3,
    "valor_mensal": 89.90,
    "frequencia_mensal": 3,
    "duracao_media_treino_min": 40,
    "tem_personal_trainer": 0,
    "atrasos_pagamento_12m": 1,
    "reducao_frequencia_3m": 1
  },
  {
    "cliente_id": "CLI_SEGURO_2",
    "nps_score": 10,
    "tempo_contrato_meses": 55,
    "valor_mensal": 299.90,
    "frequencia_mensal": 18,
    "duracao_media_treino_min": 95,
    "tem_personal_trainer": 1,
    "atrasos_pagamento_12m": 0,
    "reducao_frequencia_3m": 0
  }
]


Resposta esperada
{
  "resultados": [
    {
      "cliente_id": "CLI_RISCO_1",
      "probabilidade_churn": 0.72,
      "risco": "ALTO",
      "1_mais_relevante": "nps_score",
      "2_mais_relevante": "reducao_frequencia_3m",
      "3_mais_relevante": "atrasos_pagamento_12m"
    },
    {
      "cliente_id": "CLI_SEGURO_2",
      "probabilidade_churn": 0.12,
      "risco": "BAIXO",
      "1_mais_relevante": "frequencia_mensal",
      "2_mais_relevante": "tem_personal_trainer",
      "3_mais_relevante": "nps_score"
    }
  ]
```



---

## 🐳 Instalação Rápida com Docker

### Dockerfile
```dockerfile
FROM python:3.10-slim
WORKDIR /app
COPY requirements.txt .
COPY . .
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 8000
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
```

### requirements.txt
```
fastapi==0.103.0
uvicorn==0.23.0
scikit-learn==1.3.0
pandas==2.1.0
numpy==1.25.0
joblib==1.3.2
streamlit==1.27.0
dash==2.14.0
```

### Construção e execução
```bash
docker build -t continuum-api .
docker run -d -p 8000:8000 continuum-api
```

---

## 📌 Observações

- Este projeto está em constante evolução.  
- Alterações na estrutura e variáveis podem ocorrer conforme reuniões de alinhamento.  
- O foco é entregar soluções **inovadoras e sustentáveis** para análise de churn em academias.
- Este repositório documenta o progresso, objetivos e melhorias planejadas para garantir **qualidade, escalabilidade e impacto positivo**.  
> ⚠️ Este é o repositório oficial que será demonstrado aos responsáveis.  
> As informações envolvidas são de clientes de uma empresa de Academia, utilizando **base de dados fictícia** para análise.

---
