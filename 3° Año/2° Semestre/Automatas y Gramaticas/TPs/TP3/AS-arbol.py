#Analizador sintactico predictivo para operaciones aritméticas + * y ()
#Ejemplo de entrada id*id+id 
#Gramatica
#E → T E'
#E' → + T E'| ε
#T → F T'
#T' → * F T'| ε
#F → ( E ) | i
def obtener_col(simbolo_entrada): #obtiene columna tabla de analisis
	if(simbolo_entrada == 'i'):
		return 0
	else:	
		if(simbolo_entrada == '+'):
			return 1	
		else:	
			if(simbolo_entrada == '*'):
				return 2
			else:	
				if(simbolo_entrada == '('):
					return 3	
				else:	
					if(simbolo_entrada == ')'):
						return 4
					else:	
						if(simbolo_entrada == '$'):
							return 5
						else:	
							return 6

def obtener_fila(no_terminal): #obtiene fila tabla de analisis
	if(no_terminal == 'E'):
		return 0
	else:	
		if(no_terminal == 'E\''):
			return 1	
		else:	
			if(no_terminal == 'T'):
				return 2
			else:	
				if(no_terminal == 'T\''):
					return 3	
				else:	
					if(no_terminal == 'F'):
						return 4
					else:		
						return 5
															        
class Pila:
     def __init__(self):
         self.items = []

     def estaVacia(self): #verificar si la pila está vacía
         return self.items == []

     def insertar(self, item): #inserta elemento en la pila (cima)
         self.items.append(item)

     def extraer(self): #extrae elemento de la pila (cima)
         return self.items.pop()

     def inspeccionar(self): #devuelve el elemento de la cima de la pila
         return self.items[len(self.items)-1]

     def tamano(self): #devuelve el tamaño de la pila
         return len(self.items)
     
     def contenido(self): #devuelve el tamaño de la pila
         return (self.items)    


tabla=[["E->TE'","","","E->TE'","",""],["","E'->+TE'","","","E'->e","E'->e"],["T->FT'","","","T->FT'","",""],["","T'->e","T'->*FT'","","T'->e","T'->e"],["F->i","","","F->(E)","",""]]     
p=Pila()
p.insertar('$')
p.insertar('E')   
simbolo_entrada = 'i'
entrada = ['i','+','i','*','i','$']
entrada_2 = ['i','+','i','*','i','$']
salida = ''
print ('Arbol de sintáctico')
for simbolo_entrada in entrada:
	cima_pila = p.inspeccionar()
	while(cima_pila != simbolo_entrada):
		col = obtener_col(simbolo_entrada)
		fil = obtener_fila(cima_pila)
		salida = tabla[fil][col]	
		if(salida != ''):
			print(p.inspeccionar())
			p.extraer()
			posicion = salida.find('>')
			produccion = salida[posicion+1:len(salida)]
			produccion_pila = []
			for  simbolo in produccion: 
				if(simbolo != '\''):
					posicion_2 = produccion.find(simbolo)
					if(produccion[posicion_2+1:posicion_2+2] == '\''):
						produccion_pila.append(simbolo + '\'')					
					else:	 
						produccion_pila.append(simbolo)					
			for simbolo in reversed(produccion_pila):
				if(simbolo != 'e'):
					p.insertar (simbolo)
				else: 
					print (simbolo)				
		cima_pila = p.inspeccionar()
	if(simbolo_entrada == '$' and p.inspeccionar() == '$'):
		print("Arbol sintáctico construido!")
	else:	
		print(p.inspeccionar())
		p.extraer()
		entrada_2.pop(0)
	

			
	 
		
		 
		 
				 
