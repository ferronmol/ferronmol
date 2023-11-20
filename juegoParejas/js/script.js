import { inicio, parar, reinicio, cronometro } from "./cronometros.js";
let fotos = [
  "cabra.jpg",
  "cerdo.jpg",
  "conejo.png",
  "conejo2.jpg",
  "cordero.png",
  "elefante.jpg",
  "erizo.jpg",
  "gato.png",
  "jirafa.png",
  "leon.png",
  "mono.jpg",
  "oso.jpg",
  "osoblanco.png",
  "osopanda.png",
  "pato.png",
  "perro.png",
  "pollo.png",
  "pradera.jpg",
  "tigre.jpg",
  "vaca.png",
  "tucan.jpg",
  "loro.jpg",
  "cigueña.jpg",
  "foca.jpg",
  "koala.jpg",
  "cebra.jpg",
  "canguro.jpg",
  "pinguino.jpg",
  "zorro.jpg",
  "potro.jpg",
  "tortuga.jpg",
  "lobo.jpg",
];

const contenedor = document.getElementById("contenedor");
const visor_record = document.getElementById("visor_record");
let record = document.getElementById("record");
const visorcrono = document.getElementById("visorcrono");
let minutos = document.getElementById("Minutos");
let segundos = document.getElementById("Segundos");
let decimas = document.getElementById("Centesimas");
// juego
const juego = document.getElementById("juego");
//tenemos 3 columnas div class cuadrofoto con
// etiquetas img con fotos interrogaciones donde se colocaran las imagenes
const btn_jugar = document.getElementById("btn_jugar");
const btn_comprobar = document.getElementById("btn_comprobar");

/***************************************************** */
//las qeu voy creando globales
let cuadrofoto = document.querySelectorAll("cuadrofoto");
let misParejas = [];
let tiempoActual = 0;
let tiempoRecord = Infinity;
let control = null;
let Horas = document.getElementById("Horas");

/******************funciones************************** */
//funcion que me genera un array de 6 parejas de fotos aleatorias sin repetir
const parejas = () => {
  let fotosAleatorias = [];
  //de todas ls fotos saco 7 aleatorias y ls meto dos veces en el array
  for (let i = 0; i < 9; i++) {
    let fotoAleatoria = fotos[Math.floor(Math.random() * fotos.length)];
    fotosAleatorias.push(fotoAleatoria);
    fotosAleatorias.push(fotoAleatoria);
    //la borro del array fotos
    fotos.splice(fotos.indexOf(fotoAleatoria), 1);
  }
  //barajo el array de fotos aleatorias
  fotosAleatorias.sort(() => Math.random() - 0.5);

  return fotosAleatorias;
};
const tratarClick = (e) => {
  if (e.target.tagName === "IMG") {
    const esCuadroFoto =
      e.target.parentElement.classList.contains("cuadrofoto");
    const esImagenSeleccionada = e.target.classList.contains("seleccionado");
    const esDestapado = e.target.classList.contains("destapado");

    if (esCuadroFoto && !esImagenSeleccionada && !esDestapado) {
      // console.log(e.target);
      const cuadrosFoto = document.querySelectorAll(".cuadrofoto img");
      // console.log(cuadrosFoto);

      cuadrosFoto.forEach((img, i) => {
        img.setAttribute("id", "foto" + (i + 1));
      });

      const imagenClicada = e.target;
      imagenClicada.classList.add("seleccionado");
      const parejaIndex = parseInt(imagenClicada.id.slice(4)) - 1;
      // console.log(parejaIndex);
      imagenClicada.src = "imagenes/animales/" + misParejas[parejaIndex];
      comprobarMach();
    }
  }
};
//compruebo si hay 2 imagenes seleccionadas iguales
const comprobarMach = () => {
  let seleccionados = document.querySelectorAll(".seleccionado");
  if (seleccionados.length === 2) {
    //si hay 2 seleccionadas

    //si son iguales las dejo destapadas
    if (seleccionados[0].src === seleccionados[1].src) {
      seleccionados.forEach((img) => {
        img.classList.remove("seleccionado");
        img.classList.add("destapado");
        //borde verde
        img.style.border = "5px solid green";
      });
    } else {
      //si no son iguales las vuelvo a tapar
      setTimeout(() => {
        seleccionados.forEach((img) => {
          img.src = "imagenes/interrogacion.jpg";
          img.classList.remove("seleccionado");
        });
      }, 1000);
    }
  }
};


const ganador = () => {
  // console.log("comprobando ganador");
  let destapados = document.querySelectorAll(".destapado");
  if (destapados.length === misParejas.length) {
    console.log("HAS GANADO");
    //compruebo si es record
    console.log(tiempoActual);
    if (tiempoActual < tiempoRecord) {
      tiempoRecord = tiempoActual;
      record.innerHTML = tiempoRecord;
    }
    //oculto el juego
    juego.style.display = "none";
    //reinicio el cronometro
    reinicio();  
  } else {
    console.log("SIGUE JUGANDO. FALTAN: " + (misParejas.length - destapados.length) + " PAREJAS"  );
    console.log("LLEVAS " + tiempoActual + " SEGUNDOS");
  }
};
// En script.js

// Crea divs simulando los botones
const botonInicio = document.createElement("div");
botonInicio.id = "inicio";
const botonParar = document.createElement("div");
botonParar.id = "parar";
const botonContinuar = document.createElement("div");
botonContinuar.id = "continuar";
const botonReinicio = document.createElement("div");
botonReinicio.id = "reinicio";

// Agrega los divs al cuerpo del documento
document.body.appendChild(botonInicio);
document.body.appendChild(botonParar);
document.body.appendChild(botonContinuar);
document.body.appendChild(botonReinicio);

/*******************LISTENER************************* */
document.addEventListener("DOMContentLoaded", () => {
  juego.style.display = "none";
  //barajo las parejas
  misParejas = parejas();
  console.log("Parejas creadas:  " + misParejas);
});
//al hacer click en jugar se muestra el juego
btn_jugar.addEventListener("click", (tratarClick) => {
  juego.style.display = "block";
 
  //inicio el cronometro
  inicio();
});
juego.addEventListener("click", tratarClick);
btn_comprobar.addEventListener("click", () => {
  parar();
  ganador(); // Llamamos a la función ganador aquí
});

 