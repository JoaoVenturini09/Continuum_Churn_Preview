from flask import Flask, jsonify, request
import joblib
import pandas as pd
import json

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

    dados_dict = VALORES_PADRAO | request.get_json()

    df_input = pd.DataFrame([dados_dict])

    try:
        se_vai_cancelar = model.predict(df_input)[0]

        probabilidades = model.predict_proba(df_input)[0]
        probabilidade_churn = probabilidades[1]

        return jsonify({
            "previsao": int(se_vai_cancelar),
            "probabilidade": float(probabilidade_churn),
        })

    except Exception as e:
        return jsonify({"erro": str(e)}), 400

if __name__ == '__main__':
    app.run(port=PORTA)