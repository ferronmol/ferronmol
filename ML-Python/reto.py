from itertools import permutations
letras = [
    "D",
    "S",
    "L",
    "I",
    "H",
    "P",
    "S",
    "N",
    "I",
    "O",
    "I",
    "O",
    "C",
    "G",
    "G",
    "N",
    "L",
    "R",
    "I",
    "X",
    "P",
    "B",
    "N",
    "Q",
    "G",
    "H",
]


def componer_palabra(lista, palabra):
    # Convierte la lista a una cadena en minúsculas
    lista_cadena = "".join(
        [elemento.lower() if elemento.isalpha() else elemento for elemento in lista]
    )
    # Convierte la palabra a minúsculas
    palabra = palabra.lower()
    for letra in palabra:
        if letra in lista_cadena:
            # Si la letra está en la lista, quítala de la lista
            lista_cadena = lista_cadena.replace(letra, "", 1)
        else:
            # Si la letra no está en la lista, la palabra no se puede componer
            return False
    # Si todas las letras de la palabra están en la lista, la palabra se puede componer
    return True


palabra = "white"
resultado = componer_palabra(letras, palabra)
print("la palabra" ,palabra ,  "tiene este resultado:",resultado)
# Cargar un diccionario de palabras en español
def cargar_diccionario(filename):
    with open(filename, "r", encoding="utf-8") as file:
        return set(line.strip().lower() for line in file)

diccionario_espanol = cargar_diccionario("es_ES.dic")

# Función para encontrar palabras válidas en el diccionario
def encontrar_palabras_validas(letras, diccionario):
    palabras_validas = set()
    
    # Generar todas las permutaciones de las letras
    for r in range(1, len(letras) + 1):
        for perm in permutations(letras, r):
            palabra = "".join(perm).lower()
            if palabra in diccionario:
                palabras_validas.add(palabra)
    
    return palabras_validas

# Encontrar palabras válidas
palabras_validas = encontrar_palabras_validas(letras, diccionario_espanol)

# Imprimir las palabras válidas encontradas
print(palabras_validas)

