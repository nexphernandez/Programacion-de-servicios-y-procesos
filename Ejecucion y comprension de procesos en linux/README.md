

## Instrucciones de la práctica  

En esta práctica se trabajará en **dos fases complementarias**:

1. **Parte teórica:** Responde a los conceptos solicitados mediante la **búsqueda de información confiable**, citando siempre las **fuentes consultadas** al final de cada respuesta.  
2. **Parte práctica:** Ejecuta en Linux los **comandos indicados** y muestra la **salida obtenida** junto con una breve explicación de su significado.  

El objetivo es afianzar la comprensión de los **procesos en sistemas operativos**, tanto desde el punto de vista conceptual como práctico.  


## Bloque 1: Conceptos básicos (teoría)

### Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** Explica la diferencia entre hardware, sistema operativo y aplicación.  

**Respuesta:**  

- **Hardware**: parte física (CPU, memoria, disco, etc.).  
- **Sistema Operativo (SO)**: software que gestiona el hardware y ofrece servicios a las aplicaciones (ejemplo: Linux, Windows).  
- **Aplicación**: programas que usa el usuario y que se apoyan en el SO (ejemplo: navegador, editor de texto).  

---

1. Define qué es un **proceso** y en qué se diferencia de un **programa**.  

```
- Programa: es una entidad pasiva: un archivo que contiene un grupo de instrucciones para que éste se ejecute.

- Proceso: Es la ejecución de un programa y se considera una entidad activa porque realiza las acciones espeficadas en ese programa. 

La diferencia es que un programa es un código guardado en un disco duro para una fase previa del proceso. El proceso es un evento de ese programa en ejecución.

Link: https://www.profesionalreview.com/2020/06/20/cual-es-la-diferencia-entre-un-programa-y-un-proceso/
```
2. Explica qué es el **kernel** y su papel en la gestión de procesos. 

```
Es un software de nivel inferior que se encarga de gestionar los recursos del sistema y permitir la comunicación entre el hardware y el software.
En la gestión de procesos, el kernel crea, planifica y termina procesos, asigna los recursos necesarios (como memoria y tiempo de CPU) a cada uno, y asegura que no interfieran entre sí, garantizando un funcionamiento estable y seguro del sistema. 

Link: https://www.eaeprogramas.es/blog/negocio/tecnologia/que-es-el-kernel-cual-es-su-trabajo-y-como-funciona#:~:text=El%20Kernel%2C%20tambi%C3%A9n%20conocido%20como%20n%C3%BAcleo%2C%20es,aplicaciones%20y%20el%20procesamiento%20de%20datos%20f%C3%ADsicos.
```

3. ¿Qué son **PID** y **PPID**? Explica con un ejemplo.  

```text
-PID:Es el número único que se le asigna a un proceso cuando se inicia.
-PPID:Es otro Id que se le asigna al proceso para indicar cual es el proceso inicial que inició a este.

Ejemplo:
1. Inicio de sesión:
Cuando inicias sesión en un sistema operativo como Linux, se lanza un proceso de shell (por ejemplo, Bash). Este proceso obtiene su propio PID. 

2.Creación de un nuevo proceso:
Desde el shell, ejecutas un comando, como ls. Este comando también es un proceso, y se le asigna un nuevo PID. 

3. Identificación de la relación:

- PID del proceso hijo (ls): Digamos que el proceso ls tiene un PID de 1234. 
- PPID del proceso hijo (ls): El PPID del proceso ls será el PID del proceso de shell que lo inició, por ejemplo, el PID del shell podría ser 5678. Por lo tanto, el PPID de ls sería 5678. 
- PID del proceso padre (shell): El PID del proceso de shell es 5678. 

4. Uso práctico:
Si quisieras detener el proceso ls, usarías el comando kill con su PID: kill 1234. El PPID te muestra quién inició el proceso, lo que es útil para entender la estructura de los procesos en ejecución y cómo interactúan. 


Link:https://www.tumblr.com/sololinuxes/656041622503604224/que-es-ppid-y-como-identificarlo?redirect_to=%2Fsololinuxes%2F656041622503604224%2Fque-es-ppid-y-como-identificarlo&source=blog_view_login_wall
```
4. Describe qué es un **cambio de contexto** y por qué es costoso.  
```text
El cambio de contexto es el proceso de cambiar la CPU de un proceso, tarea o hilo a otro.

El cambio de contexto es costoso porque todo el proceso requiere un uso intensivo de recursos computacionales, y cuantos más cambios de contexto se produzcan, más lento se volverá el sistema. Esto se debe a que cada cambio de contexto implica guardar el estado actual de la CPU, cargar el estado del nuevo proceso o hilo y, a continuación, reanudar su ejecución. Esto requiere tiempo y consume recursos de la CPU, lo que puede ralentizar el sistema.

Link:https://www-netdata-cloud.translate.goog/blog/understanding-context-switching-and-its-impact-on-system-performance/?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=sge#:~:text=Context%20switching%20is%20the%20process,keep%20the%20system%20running%20smoothly.
```
5. Explica qué es un **PCB (Process Control Block)** y qué información almacena.  
6. Diferencia entre **proceso padre** y **proceso hijo**.  
7. Explica qué ocurre cuando un proceso queda **huérfano** en Linux.  
8. ¿Qué es un proceso **zombie**? Da un ejemplo de cómo puede ocurrir.  
9. Diferencia entre **concurrencia** y **paralelismo**.  
10. Explica qué es un **hilo (thread)** y en qué se diferencia de un proceso.  

---

## Bloque 2: Práctica con comandos en Linux

### Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el directorio actual?  

**Resolución:**  

```bash
    pwd
```

11. Usa echo $$ para mostrar el PID del proceso actual.

12. Usa echo $PPID para mostrar el PID del proceso padre.

13. Ejecuta pidof systemd y explica el resultado.

14. Abre un programa gráfico (ejemplo: gedit) y usa pidof para obtener sus PID.

15. Ejecuta ps -e y explica qué significan sus columnas.

16. Ejecuta ps -f y observa la relación entre procesos padre e hijo.

17. Usa ps -axf o pstree para mostrar el árbol de procesos y dibújalo.

18. Ejecuta top o htop y localiza el proceso con mayor uso de CPU.

19. Ejecuta sleep 100 en segundo plano y busca su PID con ps.

20. Finaliza un proceso con kill <PID> y comprueba con ps que ya no está.


### Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el árbol de procesos en Linux?

**Resolución:**  

```bash
    pstree
```

## Bloque 3: Procesos y jerarquía

21. Identifica el **PID del proceso init/systemd** y explica su función.
22. Explica qué ocurre con el **PPID** de un proceso hijo si su padre termina antes.
23. Ejecuta un programa que genere varios procesos hijos y observa sus PIDs con `ps`.
24. Haz que un proceso quede en **estado suspendido** con `Ctrl+Z` y réanúdalo con `fg`.
25. Lanza un proceso en **segundo plano** con `&` y obsérvalo con `jobs`.
26. Explica la diferencia entre los estados de un proceso: **Running, Sleeping, Zombie, Stopped**.
27. Usa `ps -eo pid,ppid,stat,cmd` para mostrar los estados de varios procesos.
28. Ejecuta `watch -n 1 ps -e` y observa cómo cambian los procesos en tiempo real.
29. Explica la diferencia entre ejecutar un proceso con `&` y con `nohup`.
30. Usa `ulimit -a` para ver los límites de recursos de procesos en tu sistema.

###  Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el árbol de procesos en Linux?

**Resolución:**  

```bash
    pstree
```