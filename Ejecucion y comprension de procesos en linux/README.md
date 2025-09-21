

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
```txt
El PCB (Process Control Block) es una estructura de datos utilizada por el sistema operativo para almacenar toda la información necesaria sobre un proceso en ejecución. El PCB contiene:

- PID: El identificador único del proceso.

- Estado del proceso: Indica si el proceso está en ejecución, bloqueado, esperando, etc.

-Contador de programa: Guarda la dirección de la siguiente instrucción que debe ejecutarse.

- Registros de CPU: Almacena los registros del procesador cuando el proceso es interrumpido (por ejemplo, valores de los registros de la CPU).

- Información de memoria: Detalles sobre el espacio de direcciones del proceso (páginas de memoria, segmentación, etc.).

- Información de archivos: Listado de archivos abiertos por el proceso.

El PCB es esencial para que el sistema operativo pueda realizar cambios de contexto entre procesos de manera efectiva.

Link:https://www.tutorialspoint.com/operating_system/os_process_control_block.htm
```
6. Diferencia entre **proceso padre** y **proceso hijo**.  
```txt
- Proceso padre: Es el proceso que crea a otro proceso. Un proceso puede crear varios procesos hijos mediante una llamada al sistema como fork() en Linux.

- Proceso hijo: Es el proceso que resulta de la creación de un proceso padre. Los procesos hijos son copias del proceso padre (aunque pueden diferir en algunas propiedades debido a las llamadas como exec() o clone()).

Link: https://man7.org/linux/man-pages/man2/fork.2.html
```
7. Explica qué ocurre cuando un proceso queda **huérfano** en Linux.  
```txt
Un proceso se considera huérfano cuando su proceso padre termina antes que él. En Linux, cuando un proceso huérfano ocurre, el proceso init (PID 1) lo adopta automáticamente para evitar que quede sin un proceso responsable de su control.

Link: https://linux.die.net/man/2/fork
```
8. ¿Qué es un proceso **zombie**? Da un ejemplo de cómo puede ocurrir.
```txt
Un proceso zombie es un proceso que ha terminado su ejecución, pero todavía no ha sido eliminado completamente del sistema porque su proceso padre no ha llamado a wait() para recoger el código de salida. El proceso sigue ocupando una entrada en la tabla de procesos aunque ya no esté en ejecución.

Link: https://es.wikipedia.org/wiki/Proceso_zombie

```  
9. Diferencia entre **concurrencia** y **paralelismo**. 
```txt
- Concurrencia: Se refiere a la capacidad de un sistema para manejar múltiples tareas aparentemente al mismo tiempo. En concurrencia, no necesariamente se ejecutan en paralelo, sino que el sistema alterna entre tareas. Se puede hacer en un solo núcleo (CPU) utilizando multitarea.
- Paralelismo: Implica la ejecución real simultánea de múltiples tareas. En un sistema con múltiples núcleos de CPU, diferentes hilos o procesos pueden ejecutarse al mismo tiempo en diferentes núcleos.

Link: https://www.geeksforgeeks.org/operating-systems/difference-between-concurrency-and-parallelism/

``` 
10. Explica qué es un **hilo (thread)** y en qué se diferencia de un proceso.  
```txt
- Hilo (Thread): Es la unidad más pequeña de ejecución dentro de un proceso. Un proceso puede tener uno o más hilos que comparten el mismo espacio de memoria y recursos del proceso padre. Los hilos dentro de un proceso se ejecutan en paralelo o de forma concurrente, y todos comparten el mismo espacio de direcciones.

- Proceso: Es un programa en ejecución que tiene su propio espacio de memoria y recursos asignados. Cada proceso se ejecuta de forma independiente de otros.

Diferencias clave:

1. Memoria: Los hilos comparten el espacio de memoria del proceso, mientras que los procesos tienen su propio espacio de memoria.

2. Creación: Los hilos se crean dentro de un proceso, mientras que los procesos son independientes entre sí.

3. Recursos: Los procesos son más pesados en cuanto a recursos, ya que cada uno necesita su propio espacio de memoria y recursos del sistema, mientras que los hilos comparten la mayor parte de estos recursos.
```

Link: https://www.geeksforgeeks.org/operating-systems/difference-between-process-and-thread/

---

## Bloque 2: Práctica con comandos en Linux

### Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el directorio actual?  

**Resolución:**  

```bash
    pwd
```

11. Usa echo $$ para mostrar el PID del proceso actual.

```bash
    $ echo $$
    1234
```

12. Usa echo $PPID para mostrar el PID del proceso padre.

```bash
    $ echo $PPID
    5678
```

13. Ejecuta pidof systemd y explica el resultado.

```bash
    $ pidof systemd
    1
```

14. Abre un programa gráfico (ejemplo: gedit) y usa pidof para obtener sus PID.

```bash
    $ gedit &
    [1] 2345

    $ pidof gedit
    2345
```

15. Ejecuta ps -e y explica qué significan sus columnas.

```bash
    $ ps -e
    PID TTY          TIME CMD
    1234 tty1     00:00:00 bash
    2345 tty1     00:00:00 gedit
    5678 tty1     00:00:00 systemd
    6789 tty1     00:00:00 ps
```

16. Ejecuta ps -f y observa la relación entre procesos padre e hijo.

```bash
    $ ps -f
    UID        PID  PPID  C STIME TTY          TIME CMD
    user      1234  5678  0 09:00 tty1     00:00:00 bash
    user      2345  1234  0 09:05 tty1     00:00:00 gedit
    root      5678     1  0 08:30 tty1     00:00:02 systemd
    user      6789  1234  0 09:10 tty1     00:00:00 ps
```

17. Usa ps -axf o pstree para mostrar el árbol de procesos y dibújalo.

```bash
    $ ps -axf
    PID TTY      STAT   TIME COMMAND
    1234 tty1     Ss     0:00 bash
    2345 tty1     S      0:00  \_ gedit
    5678 tty1     S      0:00 systemd
    6789 tty1     R+     0:00  \_ ps
```

18. Ejecuta top o htop y localiza el proceso con mayor uso de CPU.

```bash
    $ top
    top - 09:15:35 up 1 day,  3:42,  2 users,  load average: 0.14, 0.22, 0.30
    Tasks: 160 total,   1 running, 159 sleeping,   0 stopped,   0 zombie
    %Cpu(s):  5.4 us,  2.3 sy,  0.0 ni, 91.6 id,  0.2 wa,  0.0 hi,  0.4 si,  0.0 st
    MiB Mem :  8002.5 total,  3627.1 free,  1821.3 used,  2554.1 buff/cache
    MiB Swap:  2048.0 total,  2048.0 free,     0.0 used.  5517.1 avail Mem 

    PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND
    2345 user      20   0  124824  1024  1024 S   3.4  0.0   0:00.10 gedit
    1234 user      20   0  107632  5124  4320 S   1.0  0.1   0:02.10 bash
    5678 root      20   0  302384  8104  6000 S   0.5  0.1   0:02.00 systemd
```

19. Ejecuta sleep 100 en segundo plano y busca su PID con ps.

```bash
    $ sleep 100 &
    [1] 6123

    $ ps -e | grep sleep
    6123 ?        00:00:00 sleep
```

20. Finaliza un proceso con kill <PID> y comprueba con ps que ya no está.

```bash
    $ kill 6789
    $ ps

    PID TTY      STAT   TIME COMMAND
    1234 tty1     Ss     0:00 bash
    2345 tty1     S      0:00 gedit
    5678 tty1     S      0:00 systemd
```

### Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el árbol de procesos en Linux?

**Resolución:**  

```bash
    pstree
```

## Bloque 3: Procesos y jerarquía

21. Identifica el **PID del proceso init/systemd** y explica su función.

```bash
    $ pidof systemd
    1
```

22. Explica qué ocurre con el **PPID** de un proceso hijo si su padre termina antes.

```txt
Si el proceso padre termina antes que el proceso hijo, el proceso hijo se convierte en un proceso huérfano y es adoptado por el proceso init (o systemd). Su PPID cambiará al PID de init (PID 1).
```

23. Ejecuta un programa que genere varios procesos hijos y observa sus PIDs con `ps`.

```bash
    $ firefox &
    [1] 6900

    $ ps -f --ppid 6900
    UID   PID  PPID  C STIME TTY      TIME CMD
    user 6900     1  5 10:17 ?        00:00:03 firefox
    user 6912  6900  2 10:17 ?        00:00:01 Web Content
    user 6915  6900  1 10:17 ?        00:00:00 WebExtensions
```

24. Haz que un proceso quede en **estado suspendido** con `Ctrl+Z` y réanúdalo con `fg`.

```bash
    $ discord
    ^Z
    [1]+  Stopped                 discord

    $ fg 1
    discord
```

25. Lanza un proceso en **segundo plano** con `&` y obsérvalo con `jobs`.

```bash
    $ spotify &
    [2] 7010

    $ jobs
    [1]+  Running                 discord
    [2]-  Running                 spotify &
```

26. Explica la diferencia entre los estados de un proceso: **Running, Sleeping, Zombie, Stopped**.

```txt
- Running: El proceso está en ejecución y usando CPU.

- Sleeping: El proceso está esperando por algún evento o recurso (como la entrada del usuario).

- Zombie: El proceso ha terminado, pero su entrada aún no ha sido eliminada por el proceso padre.

- Stopped: El proceso está suspendido (por ejemplo, con Ctrl+Z).
```

27. Usa `ps -eo pid,ppid,stat,cmd` para mostrar los estados de varios procesos.

```bash
    $ ps -eo pid,ppid,stat,cmd
    PID  PPID STAT CMD
    1234  5678 Ss   bash
    2345  1234 S    gedit
    5678     1 S    systemd
    6789  1234 Z    sleep 100
```

28. Ejecuta `watch -n 1 ps -e` y observa cómo cambian los procesos en tiempo real.

```bash
    $ watch -n 1 ps -e
    Every 1.0s: ps -e

    PID TTY          TIME CMD
        1 ?        00:00:03 systemd
    5234 ?        00:00:06 spotify
    5301 ?        00:00:03 discord
    5478 ?        00:00:09 firefox
    7025 pts/0    00:00:00 ps
    7030 pts/0    00:00:00 sleep
```

29. Explica la diferencia entre ejecutar un proceso con `&` y con `nohup`.

```txt
- &: Ejecuta un proceso en segundo plano, pero si la sesión de terminal termina, el proceso también se detendrá.

- nohup: Ejecuta un proceso en segundo plano y lo desacopla de la terminal, lo que permite que siga ejecutándose incluso si la sesión de terminal se cierra.
```
30. Usa `ulimit -a` para ver los límites de recursos de procesos en tu sistema.

```bash
    $ ulimit -a
    core file size          (blocks, -c) 0
    data seg size           (kbytes, -d) 131072
    scheduling priority     (-e) 0
    file size               (blocks, -f) unlimited
    pending signals         (-i) 31767
    max locked memory       (kbytes, -l) 64
    max memory size         (kbytes, -m) unlimited
    open files              (-n) 1024
    pipe size               (512 bytes, -p) 8
    POSIX message queues    (bytes, -q) 819200
    real-time priority      (-r) 0
    stack size              (kbytes, -s) 8192
    cpu time                (seconds, -t) unlimited
    max user processes      (-u) 31767
    virtual memory          (kbytes, -v) unlimited
    file locks             (-x) unlimited
```

###  Ejercicio 0 (ejemplo resuelto)  

**Pregunta:** ¿Qué comando muestra el árbol de procesos en Linux?

**Resolución:**  

```bash
    pstree
```