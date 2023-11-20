import zlib

def extract_data(filename, offset, output_file):
    with open(filename, 'rb') as f:
        f.seek(offset)
        data = f.read()
        try:
            decompressed_data = zlib.decompress(data)
            with open(output_file, 'wb') as out_file:
                out_file.write(decompressed_data)
        except zlib.error:
            print('No se pudo descomprimir los datos.')
            return None

# Reemplaza 'evidence.png' con el nombre de tu archivo
output_file = 'datos_extraidos.bin'
data = extract_data('evidence.png', 106, output_file)
if data is not None:
    print(f'Datos descomprimidos guardados en {output_file}')


# Reemplaza 'evidence.png' con el nombre de tu archivo
output_file = 'datos_extraidos.txt'
data = extract_data('evidence.png', 106, output_file)
if data is not None:
    print(f'Datos descomprimidos guardados en {output_file}')

