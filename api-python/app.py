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

    dados_dict = [request.get_json()]

    try:
        print("vnova")
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