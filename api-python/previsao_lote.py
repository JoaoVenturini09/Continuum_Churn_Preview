import logging
import numpy as np
import pandas as pd
from treeinterpreter import treeinterpreter as ti


def fazer_previsao_lote(lista_clientes, modelo_pipeline):
    """
    Recebe uma lista de dicionários (clientes) e o pipeline do modelo.
    Retorna uma lista de dicionários com as previsões e as 3 features mais relevantes.
    """
    try:
        logging.info(f"Iniciando previsão em lote para {len(lista_clientes)} clientes.")

        # Converte a lista de dicionários em DataFrame
        df_novos = pd.DataFrame(lista_clientes)
        logging.info("Dados de entrada convertidos para DataFrame.")

        # 1. ENGENHARIA DE FEATURES (Repetir a lógica do treino)
        # Nota: Idealmente, esta lógica deveria estar em uma função reutilizável ou no Pipeline.
        df_novos['engajamento_por_custo'] = df_novos['frequencia_mensal'] / df_novos['valor_mensal'].replace(0, np.nan)

        df_novos['risco_inatividade'] = pd.cut(df_novos['dias_desde_ultimo_acesso'],
                                        bins=[-1, 7, 15, 30, 91],
                                        labels=['Ativo (0-7d)', 'Alerta (8-15d)', 'Risco (16-30d)', 'Crítico (30d+)'],
                                        right=False)

        df_novos['faixa_etaria'] = pd.cut(df_novos['idade'],
                                   bins=[0, 15, 25, 35, 50, 120],
                                   labels=['Criança/Adolescente', 'Jovem (16-25)', 'Adulto Jovem (26-35)', 'Adulto (36-50)', 'Sênior (51+)'],
                                   right=True)

        df_novos['faixa_fidelidade'] = pd.cut(df_novos['tempo_contrato_meses'],
                                        bins=[-1, 6, 18, 36, 120],
                                        labels=['Novato (0-6m)', 'Intermediário (7-18m)', 'Fiel (19-36m)', 'Veterano (36m+)'],
                                        right=False)
        logging.info("Engenharia de features aplicada aos novos dados.")

        # 2. PREVISÃO
        # O pipeline cuida do drop de colunas extras (cliente_id, etc) se configurado,
        # mas aqui vamos garantir que passamos apenas o que o preprocessor espera.
        FEATURES_MODELO = modelo_pipeline.named_steps['preprocessor'].feature_names_in_
        X_novos = df_novos[FEATURES_MODELO]

        prob_churn = modelo_pipeline.predict_proba(X_novos)[:, 1]

        # 3. INTERPRETABILIDADE LOCAL (Tree Interpreter)
        # Transformar os dados usando o preprocessor do pipeline
        X_transformed = modelo_pipeline.named_steps['preprocessor'].transform(X_novos)
        feature_names_out = modelo_pipeline.named_steps['preprocessor'].get_feature_names_out()

        # Obter contribuições das features para a previsão
        # prediction, bias, contributions
        _, _, contributions = ti.predict(modelo_pipeline.named_steps['classifier'], X_transformed)

        # 4. FORMATAÇÃO DOS RESULTADOS
        resultados = []
        for i in range(len(df_novos)):
            # Pegar contribuições para a classe positiva (churn=1) - índice 1
            contrib_cliente = contributions[i, :, 1]
            feat_contrib = pd.Series(contrib_cliente, index=feature_names_out)

            # Identificar as 3 features com maior impacto absoluto
            top_3 = feat_contrib.abs().sort_values(ascending=False).head(3).index.tolist()
            # Limpar nomes (remover prefixos como 'num__' ou 'cat__')
            top_3_clean = [f.split('__')[-1] for f in top_3]

            resultados.append({
                'cliente_id': df_novos.iloc[i]['cliente_id'],
                'probabilidade_churn': round(prob_churn[i], 4),
                'risco': 'ALTO' if prob_churn[i] >= 0.5 else 'BAIXO',
                '1_mais_relevante': top_3_clean[0] if len(top_3_clean) > 0 else None,
                '2_mais_relevante': top_3_clean[1] if len(top_3_clean) > 1 else None,
                '3_mais_relevante': top_3_clean[2] if len(top_3_clean) > 2 else None
            })

        logging.info("Resultados da previsão com interpretabilidade local formatados.")
        return resultados
    except Exception as e:
        logging.error(f"Erro na função fazer_previsao_lote: {e}")
        return []