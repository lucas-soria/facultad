from flask import Flask, render_template
app = Flask(__name__)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/informacionc/')
def informacionc():
    return  render_template('informacionc.html')

@app.route('/salud/')
def salud():
    return  render_template('salud.html')

@app.route('/manualp/')
def manualp():
    return  render_template('manualp.html')


@app.route('/psico/')
def psico():
    return  render_template('psico.html')


@app.route('/contacto/')
def contacto():
    return  render_template('contacto.html')



@app.route('/listasuper/')
def listasuper():
    return  render_template('listasuper.html')



@app.route('/entrenamiento/')
def entrenamiento():
    return  render_template('entrenamiento.html')



if __name__ == "__main__":
    app.run(debug=True)