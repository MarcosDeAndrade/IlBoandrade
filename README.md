# IlBoandrade (JAVA)

---

<p align="center"><b>IlBoandrade - Restaurant service simulator - Producer/Consumer & Writter/Reader</b><br>Software desarrollado en lenguaje JAVA, con el IDE NetBeans.</p>

---

## ***Introducción***

Simulador de servicio de un restaurante, cuenta con la utilización de hilos, semaforos (exclusión mutua), y soluciones conocidas a los problemas productor/consumidor y escritor/lector. Los parámetros iniciales de la simulación son introducidos por medio de un archivo JSON

## ***Instrucciones***

1. Descarga el repositorio, en el encontaras el archivo ***IlBoandrade***.
2. Ejecuta tu IDE deseado, se sugiere [NetBeans](https://netbeans.org/downloads/7.0/?pagelang=us).
3. Abre el archivo ***IlBoandrade*** anteriormente mencionado con el IDE.
4. Para poder compilar, es necesario tener la extención ***json-simple-1.1.1.jar***, si no dispone de ella puede encontrarla en la carpeta ***others***.
5. Compila y corre el programa.
6. Sigue las instrucciones en el menu de ***Instrucciones*** para poder proceder a la corrcta utilización.

  >Los parámetros iniciales de la simulación vienen establecidos en el archivo JSON, si desea modificarlos debe hacerlo antes de iniciar la    simulación.

## ***Especificaciones***

Un importante chef lo ha contratado para realizar una simulación del servicio de un restaurante. Para ello, se pide que realice en Java una representación computacional de dicha situación. Dada las características de la misma, es necesario utilizar hilos, semáforos y las soluciones conocidas a los problemas productos/consumidor y lector/escritor.

Durante el servicio se requieren dos tipos de empleados:

1. **Cocineros:** Encargados de producir los distintos platillos necesarios para complacer a los comensales. Es necesario mencionar que tienen a su disposición una serie de mesones de capacidades finitas, en los cuales colocan los platos una vez que están listos. Antes de producir un platillo, un cocinero comprueba si existe espacio en el mesón para colocarlo. Existen tres grupos de cocineros:

    * **Cocineros de Entradas:** A un cocinero le toma 0,25 horas producir 1 entrada. El mesón del cual disponen, cuenta con una        capacidad inicial de 20 puestos. Por normativa del Chef, puede haber un máximo de 3 cocineros de entradas en la cocina, con una         cantidad inicial de uno.
  
    * **Cocineros de Platos Fuertes:** A un cocinero le toma 0,33 horas producir 1 plato fuerte. El mesón del cual disponen, cuenta con una capacidad inicial  de 30 puestos. Por normativa de la Chef, puede haber un máximo de 4 cocineros de platos fuertes en la             cocina,     con una cantidad inicial de 2.
    
    * **Cocineros de Postres:** A un cocinero le toma 0,30 horas producir 1 postre. El mesón del cual disponen, cuenta con una capacidad inicial de 10 puestos. Por normativa del Chef, puede haber un máximo de 2 cocineros de postres en la cocina, con una cantidad inicial de 0.
    
2. **Mesoneros:** Estos empleados tienen la responsabilidad de armar un orden, para que luego sea enviado a una mesa. Para ello toman 3 entradas, 2 plato fuerte y 1 postre. Una vez obtenidos todos los platos, a un mesonero le toma 0,15 horas realizar su trabajo. Al terminar, el mesonero tiene la responsabilidad de aumentar el contador de órdenes atendidas. Puede haber un máximo de 6 mesoneros en el restaurante.

Además, el restaurante cuenta también con:

3. **Jefe de Mesoneros:** La única tarea del jefe de mesoneros es registrar el paso de las horas. El jefe posee un contador inicializado en el número de horas que faltan para cerrar el restaurante. Cada hora, el cronometrador disminuye su contador en una unidad, lo que le toma 0,05 horas. Si hay alguien leyendo el contador cuando el jefe de mesoneros va a modificarlo, él debe esperar a que el lector termine. Cuando el contador vaya a pasar el valor 0, el jefe lo reinicializa. Hay solo 1 jefe de mesoneros en el restaurante y solo 1 contador. El tiempo que el jefe no esta leyendo el contador, estará descansando.

4. **Gerente:** Cada hora se dirige al contador, para verificar cuántas horas faltan para cerrar el restaurante. Si el jefe de mesoneros está modificando el contador en ese momento, el gerente espera a que él termine antes de leer. Si el contador es distinto a 0, el gerente va a descansar en su oficina, por un período aleatorio entre 0,45 y 2 horas. Si el contador es igual a 0, el gerente manda a pagar todas las órdenes, reinicializando el contador, lo que le toma 0,1 horas de trabajo.

Su programa deberá hacer uso de una interfaz gráfica que permita observar y controlar el sistema. Se debe poder conocer en cualquier momento:

* La cantidad de cocineros de cada tipo.
* La cantidad de entradas, platos fuertes y postres que hay disponibles en los mesones.
* La cantidad de mesoneros.
* La cantidad de órdenes atendidas.
* Las horas que hacen falta para terminar.
* Qué está haciendo el jefe de mesoneros.
* Qué está haciendo el gerente.
* Cualquier otro dato que considere relevante.

La simulación debe permitir, en tiempo de ejecución:

* Contratar o despedir un cocinero de cualquiera de los tres tipos.
* Contratar o despedir un mesonero.

Además, a través de un archivo (texto, csv o json), se le debe poder indicar alprograma los siguientes parámetros:

* Tiempo, en segundos, que dura una hora en el programa.
* La capacidad máxima para cada tipo de mesón.
* La cantidad inicial de cocineros de cada tipo.
* La cantidad máxima de cocineros de cada tipo.
* La cantidad inicial de mesoneros.
* La cantidad máxima de mesoneros.

## ***Views***

### *Menu Principal*
![Main Menu](https://i.ibb.co/BzfYxVc/Imagen1.png)
  
### *Instrucciones*
![Intructions](https://i.ibb.co/ckbH2Cx/Imagen2.png)
  
### *Principal*
![Main](https://i.ibb.co/rm8PYtf/Imagen4.png)
  
### *Creditos*
![Credits](https://i.ibb.co/jb0gswD/Imagen3.png)

## ***Creditos***

Proyecto realizado por
* [Marcos De Andrade](https://github.com/MarcosDeAndrade)
* [Samuel Boada](https://github.com/systems-multimedia).

## ***Licencia***

[MIT](https://github.com/MarcosDeAndrade/IlBoandrade/blob/master/LICENSE)

&nbsp;

<p align="center">✌️</p>
<p align="center">
<sub><sup>A project by <a href="https://github.com/MarcosDeAndrade">Marcos De Andrade</a> & <a href="https://github.com/systems-multimedia">Samuel Boada</a><br>Copyright (c) 2019</sup></sub></p>
