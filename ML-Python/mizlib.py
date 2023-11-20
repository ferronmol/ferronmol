import zlib
def extract_zlib_data(filename, offset, output_file):
    with open(filename, 'rb') as f:
        f.seek(offset)
        data = f.read()
        with open(output_file, 'wb') as out_file:
            out_file.write(data)

# Reemplaza 'evidence.png' con el nombre de tu archivo
output_file = 'datos_zlib.bin'
extract_zlib_data('evidence.png', 106, output_file)
print(f'Datos zlib guardados en {output_file}')
