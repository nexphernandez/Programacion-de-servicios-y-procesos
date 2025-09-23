**Procesos y Servicios (modo alumno, sin root) — Trabajo en `$HOME/dam` con *user units* de systemd**

> **Importante:** Esta guía está adaptada para **usuarios sin privilegios de root**.  
> Todo se hace **en tu carpeta** `~/dam` usando **systemd --user** (un administrador por usuario), sin tocar `/etc` ni `/usr/local`.  
> Pega **salidas reales** y responde a las preguntas cortas.

---

## Preparación

Crea tu área de trabajo y variables útiles:

```bash
mkdir -p ~/dam/{bin,logs,units}
export DAM=~/dam
echo 'export DAM=~/dam' >> ~/.bashrc
```

Comprobar versión de systemd y que el *user manager* está activo:

```bash
systemctl --user --version | head -n1
systemctl --user status --no-pager | head -n5
```
**Pega salida aquí:**

```bash
 dam  ~  mkdir -p ~/dam/{bin,logs,units}
 dam  ~  export DAM=~/dam
 dam  ~  echo 'export DAM=~/dam' >> ~/.bashrc
 dam  ~  systemctl --user --version | head -n1
systemd 255 (255.4-1ubuntu8.6)
 dam  ~  
 dam  ~  systemctl --user status --no-pager | head -n5
● a108pc04
    State: running
    Units: 262 loaded (incl. loaded aliases)
     Jobs: 0 queued
   Failed: 0 units

```

**Reflexiona la salida:**

```text
systemctl: Es la herramienta principal para controlar y gestionar el sistema systemd

systemctl --user --version | head -n1 : muestra la versión del sistema systemd y las funciones compatibles que se han compilado en tu distribución Linux.

systemctl --user status --no-pager | head -n5: muestra el estado del servicio y las unidades que se ejecutan bajo el usuario actual
```

---

## Bloque 1 — Conceptos (breve + fuentes)

1) ¿Qué es **systemd** y en qué se diferencia de SysV init?  

**Respuesta:**  
```txt
systemd es un gestor del sistema y de servicios para sistemas operativos Linux. Es el sistema de inicialización por defecto para las principales distribuciones de Linux.
Por otro lado SysV es un sistema de inicialización anterior a systemd más antiguo y secuencial que utiliza scripts, mientras que Systemd es un sistema moderno y paralelo que utiliza archivos de unidad.
```

_Fuentes:_
https://documentation.suse.com/es-es/sle-micro/6.0/html/Micro-systemd-basics/index.html
https://www.maxizamorano.com/entrada/19/proceso-de-arranque-en-linux-systemd-vs-sysv-init


2) **Servicio** vs **proceso** (ejemplos).  

**Respuesta:**  

```txt
Un proceso es una instancia de un programa en ejecución con sus propios recursos y estado, mientras que un servicio es un tipo específico de proceso que opera en segundo plano, sin intervención del usuario, diseñado para proporcionar funcionalidades de forma continua a otros programas o al sistema. 
```

_Fuentes:_
https://openwebinars.net/blog/gestion-de-procesos-y-servicios-desde-shell-script-en-windows/#:~:text=Shell%20en%20Linux.-,Diferencias%20entre%20un%20proceso%20y%20un%20servicio,detener%20y%20eliminar%20un%20servicio.

3) ¿Qué son los **cgroups** y para qué sirven?  

**Respuesta:**  

```txt
Los cgroups (control groups) son una funcionalidad del kernel Linux que permite agrupar procesos y controlar de manera granular los recursos del sistema que consumen, como CPU, memoria, I/O, y otros. Permiten a los administradores de sistemas gestionar el uso de recursos por grupos de procesos de forma eficiente, garantizando una distribución justa y controlada.
```
_Fuentes:_
https://sergiobelkin.com/posts/que-son-los-cgroups-y-para-que-sirven/

4) ¿Qué es un **unit file** y tipos (`service`, `timer`, `socket`, `target`)?  

**Respuesta:**  
```txt
Las units files  son el elemento fundamental de gestión de recursos en systemd. Cualquier aspecto administrado con systemd tendrá una unit asociada. Existen varios tipos de units:

    - service: gestión de servicios
    - socket: gestión de los sockets utilizados por otras units (como servicios)
    - device: gestión de dispositivos
    - mount y automount: gestión de puntos de montaje
    - swap: gestión de espacios de intercambio
    - target: gestión de targets
    - path: utilizada para activación de units .service basada en eventos del sistema de archivos referenciado por el path
    - timer: gestión de eventos, al estilo cron o at
    - snapshot: gestión de instantáneas del sistema
    - slice: gestión de recursos asociados a procesos usando cgroup
    - scope: gestión de procesos organizados y creados por systemd
```
_Fuentes:_
https://manuais.iessanclemente.net/index.php/Gesti%C3%B3n_del_Sistema_con_Systemd#Gesti%C3%B3n_de_Unit_Files


5) ¿Qué hace `journalctl` y cómo ver logs **de usuario**?  



**Respuesta:**  
```txt
Journalctl es una eficiente herramienta para consultar y mostrar registros de eventos o ficheros log en Linux.Para ver los logs de usuarios en journalctl, utiliza el parámetro _UID junto con el ID de usuario (UID).
```

_Fuentes:_
https://www.ionos.es/digitalguide/servidores/herramientas/que-es-journalctl/#:~:text=journalctl%20permite%20gestionar%20y%20analizar,de%20los%20datos%20de%20registro.
https://www.busindre.com/logs_del_sistema_con_journalctl_systemd
---

## Bloque 2 — Práctica guiada (todo en tu `$DAM`)

> Si un comando pide permisos que no tienes, usa la **versión `--user`** o consulta el **ayuda** con `--help` para alternativas.

### 2.1 — PIDs básicos

**11.** PID de tu shell y su PPID.

```bash
echo "PID=$$  PPID=$PPID"
```
**Salida:**

```bash
PID=19759  PPID=19738
```

**Pregunta:** ¿Qué proceso es el padre (PPID) de tu shell ahora?  

**Respuesta:**
```txt
EL PPID es 19738
```
---

**12.** PID del `systemd --user` (manager de usuario) y explicación.

```bash
pidof systemd || pgrep -u "$USER" -x systemd
```

**Salida:**

```bash
pidof systemd || pgrep -u "$USER" -x systemd
3313
```
**Pregunta:** ¿Qué hace el *user manager* de systemd para tu sesión?  

**Respuesta:**

```txt
Gestiona tareas como la ejecución de servicios o temporizadores, la configuración de unidades en directorios del usuario ($HOME/) y la ejecución de aplicaciones y servicios que no requieren permisos de root
```
---

### 2.2 — Servicios **de usuario** con systemd

Vamos a crear un servicio sencillo y un timer **en tu carpeta** `~/.config/systemd/user` o en `$DAM/units` (usaremos la primera para que `systemctl --user` lo encuentre).

**13.** Prepara directorios y script de práctica.

```bash
mkdir -p ~/.config/systemd/user "$DAM"/{bin,logs}
cat << 'EOF' > "$DAM/bin/fecha_log.sh"
#!/usr/bin/env bash
mkdir -p "$HOME/dam/logs"
echo "$(date --iso-8601=seconds) :: hello from user timer" >> "$HOME/dam/logs/fecha.log"
EOF
chmod +x "$DAM/bin/fecha_log.sh"
```

**14.** Crea el servicio **de usuario** `fecha-log.service` (**Type=simple**, ejecuta tu script).

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.service
[Unit]
Description=Escribe fecha en $HOME/dam/logs/fecha.log

[Service]
Type=simple
ExecStart=%h/dam/bin/fecha_log.sh
EOF

systemctl --user daemon-reload
systemctl --user start fecha-log.service
systemctl --user status fecha-log.service --no-pager -l | sed -n '1,10p'
```
**Salida (pega un extracto):**

```text
○ fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log
     Loaded: loaded (/home/dam/.config/systemd/user/fecha-log.service; static)
     Active: inactive (dead)

sep 23 18:42:10 a108pc04 systemd[3313]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.

```
**Pregunta:** ¿Se creó/actualizó `~/dam/logs/fecha.log`? Muestra las últimas líneas:

```bash
tail -n 5 "$DAM/logs/fecha.log"
```

**Salida:**

```text
2025-09-23T18:42:10+01:00 :: hello from user timer
```

**Reflexiona la salida:**

```text
Se creo el archivo pero no se actualiza cada minuto como deberia, porque no esta habilitado
```

---

**15.** Diferencia **enable** vs **start** (modo usuario). Habilita el **timer**.

```bash
cat << 'EOF' > ~/.config/systemd/user/fecha-log.timer
[Unit]
Description=Timer (usuario): ejecuta fecha-log.service cada minuto

[Timer]
OnCalendar=*:0/1
Unit=fecha-log.service
Persistent=true

[Install]
WantedBy=timers.target
EOF

systemctl --user daemon-reload
systemctl --user enable --now fecha-log.timer
systemctl --user list-timers --all | grep fecha-log || true
```

**Salida (recorta):**

```text
Created symlink /home/dam/.config/systemd/user/timers.target.wants/fecha-log.timer → /home/dam/.config/systemd/user/fecha-log.timer.
Tue 2025-09-23 18:52:00 WEST  25s -                                       - fecha-log.timer                fecha-log.service
```
**Pregunta:** ¿Qué diferencia hay entre `enable` y `start` cuando usas `systemctl --user`?  

**Respuesta:**
```txt
La diferencia es que enable configura el servicio para que se inicie automáticamente al iniciar sesión o arrancar el sistema, mientras que start inicia el servicio inmediatamente pero solo hasta que la sesión termine o el sistema se reinicie
```
---

**16.** Logs recientes **del servicio de usuario** con `journalctl --user`.

```bash
journalctl --user -u fecha-log.service -n 10 --no-pager
```

**Salida:**

```text
sep 23 18:42:10 a108pc04 systemd[3313]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:52:22 a108pc04 systemd[3313]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.
sep 23 18:53:22 a108pc04 systemd[3313]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.

```
**Pregunta:** ¿Ves ejecuciones activadas por el timer? ¿Cuándo fue la última?  

**Respuesta:**
```txt
La ultima ejecucion fue esta:
sep 23 18:53:22 a108pc04 systemd[3313]: Started fecha-log.service - Escribe fecha en $HOME/dam/logs/fecha.log.

```
---

### 2.3 — Observación de procesos sin root

**17.** Puertos en escucha (lo que puedas ver como usuario).

```bash
lsof -i -P -n | grep LISTEN || ss -lntp
```
**Salida:**

```text
State   Recv-Q   Send-Q     Local Address:Port      Peer Address:Port  Process  
LISTEN  0        4096       127.0.0.53%lo:53             0.0.0.0:*              
LISTEN  0        32         192.168.122.1:53             0.0.0.0:*              
LISTEN  0        64               0.0.0.0:35531          0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:51699          0.0.0.0:*              
LISTEN  0        64               0.0.0.0:2049           0.0.0.0:*              
LISTEN  0        4096          127.0.0.54:53             0.0.0.0:*              
LISTEN  0        4096           127.0.0.1:631            0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:111            0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:34137          0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:8000           0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:56321          0.0.0.0:*              
LISTEN  0        4096             0.0.0.0:36987          0.0.0.0:*              
LISTEN  0        4096                [::]:44183             [::]:*              
LISTEN  0        4096                   *:9100                 *:*              
LISTEN  0        64                  [::]:41167             [::]:*              
LISTEN  0        4096                [::]:42395             [::]:*              
LISTEN  0        4096               [::1]:631               [::]:*              
LISTEN  0        64                  [::]:2049              [::]:*              
LISTEN  0        4096                [::]:36161             [::]:*              
LISTEN  0        4096                [::]:111               [::]:*              
LISTEN  0        511                    *:80                   *:*              
LISTEN  0        4096                   *:22                   *:*              
LISTEN  0        4096                [::]:8000              [::]:*              
LISTEN  0        4096                [::]:38675             [::]:*    
```
**Pregunta:** ¿Qué procesos *tuyos* están escuchando? (si no hay, explica por qué)  

**Respuesta:**
```txt
Todos los procesos que ponen listen en la salida anterior
```
---

**18.** Ejecuta un proceso bajo **cgroup de usuario** con límite de memoria.

```bash
systemd-run --user --scope -p MemoryMax=50M sleep 200
ps -eo pid,ppid,cmd,stat | grep "[s]leep 200"
```

**Salida:**

```text
Running as unit: run-r4fee977457b44213acd51a630f1f7c30.scope; invocation ID: 44c656b89f144423876fc55ea9dd7861
```
**Pregunta:** ¿Qué ventaja tiene lanzar con `systemd-run --user` respecto a ejecutarlo “a pelo”?  

**Respuesta:**
```txt
Lanzar con systemd-run --user permite ejecutar programas y servicios como si fueran servicios de usuario, otorgando ventajas como la ejecución sin permisos de root
```
---

**19.** Observa CPU en tiempo real con `top` (si tienes `htop`, también vale).

```bash
top -b -n 1 | head -n 15
```
**Salida (resumen):**

```text
top - 19:01:53 up  3:59,  1 user,  load average: 0,74, 0,54, 0,48
Tareas: 408 total,   1 ejecutar,  406 hibernar,    0 detener,    1 zombie
%Cpu(s):  0,0 us,  0,6 sy,  0,0 ni, 98,1 id,  1,3 wa,  0,0 hi,  0,0 si,  0,0 st 
MiB Mem :  31453,3 total,  24455,4 libre,   4412,4 usado,   3051,4 búf/caché    
MiB Intercambio:   2048,0 total,   2048,0 libre,      0,0 usado.  27040,9 dispon

    PID USUARIO   PR  NI    VIRT    RES    SHR S  %CPU  %MEM     HORA+ ORDEN
      1 root      20   0   23236  13912   9176 S   0,0   0,0   0:01.82 systemd
      2 root      20   0       0      0      0 S   0,0   0,0   0:00.02 kthreadd
      3 root      20   0       0      0      0 S   0,0   0,0   0:00.00 pool_wo+
      4 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
      5 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
      6 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
      7 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
     10 root       0 -20       0      0      0 I   0,0   0,0   0:00.00 kworker+
```
**Pregunta:** ¿Cuál es tu proceso con mayor `%CPU` en ese momento?  

**Respuesta:**
```txt
Todos los procesos tienen el mismo '%CPU'.
```
---

**20.** Traza syscalls de **tu propio proceso** (p. ej., el `sleep` anterior).
> Nota: Sin root, no podrás adjuntarte a procesos de otros usuarios ni a algunos del sistema.

```bash
pid=$(pgrep -u "$USER" -x sleep | head -n1)
strace -p "$pid" -e trace=nanosleep -tt -c -f 2>&1 | sed -n '1,10p'
```

**Salida (fragmento):**

```text
strace: must have PROG [ARGS] or -p PID
Try 'strace -h' for more information.
```
**Pregunta:** Explica brevemente la syscall que observaste.  

**Respuesta:**

---

### 2.4 — Estados y jerarquía (sin root)

**21.** Árbol de procesos con PIDs.

```bash
pstree -p | head -n 50
```

**Salida (recorta):**

```text
systemd(1)-+-ModemManager(1853)-+-{ModemManager}(1863)
           |                    |-{ModemManager}(1865)
           |                    `-{ModemManager}(1867)
           |-NetworkManager(1823)-+-{NetworkManager}(1858)
           |                      |-{NetworkManager}(1859)
           |                      `-{NetworkManager}(1860)
           |-accounts-daemon(1157)-+-{accounts-daemon}(1200)
           |                       |-{accounts-daemon}(1201)
           |                       `-{accounts-daemon}(1821)
           |-agetty(2227)
           |-apache2(2281)-+-apache2(2294)
           |               |-apache2(2295)
           |               |-apache2(2296)
           |               |-apache2(2297)
           |               `-apache2(2298)
           |-at-spi2-registr(3664)-+-{at-spi2-registr}(3673)
           |                       |-{at-spi2-registr}(3674)
           |                       `-{at-spi2-registr}(3675)
           |-avahi-daemon(1159)---avahi-daemon(1253)
           |-blkmapd(1077)
           |-colord(2018)-+-{colord}(2025)
           |              |-{colord}(2026)
           |              `-{colord}(2028)
           |-containerd(1995)-+-{containerd}(2020)
           |                  |-{containerd}(2021)
           |                  |-{containerd}(2022)
           |                  |-{containerd}(2023)
           |                  |-{containerd}(2024)
           |                  |-{containerd}(2037)
           |                  |-{containerd}(2038)
           |                  |-{containerd}(2052)
           |                  |-{containerd}(2053)
           |                  |-{containerd}(2054)
           |                  |-{containerd}(2059)
           |                  |-{containerd}(2060)
           |                  |-{containerd}(2061)
           |                  `-{containerd}(2516)
           |-containerd-shim(3134)-+-apache2(3157)-+-apache2(3271)
           |                       |               |-apache2(3272)
           |                       |               |-apache2(3273)
           |                       |               |-apache2(3274)
           |                       |               `-apache2(3275)
           |                       |-{containerd-shim}(3135)
           |                       |-{containerd-shim}(3136)
           |                       |-{containerd-shim}(3137)
           |                       |-{containerd-shim}(3138)
           |                       |-{containerd-shim}(3139)
           |                       |-{containerd-shim}(3140)
           |                       |-{containerd-shim}(3141)
           |                       |-{containerd-shim}(3142)

```
**Pregunta:** ¿Bajo qué proceso aparece tu `systemd --user`?  

**Respuesta:**

---

**22.** Estados y relación PID/PPID.

```bash
ps -eo pid,ppid,stat,cmd | head -n 20
```
**Salida:**

```text
    PID    PPID STAT CMD
      1       0 Ss   /sbin/init splash
      2       0 S    [kthreadd]
      3       2 S    [pool_workqueue_release]
      4       2 I<   [kworker/R-rcu_g]
      5       2 I<   [kworker/R-rcu_p]
      6       2 I<   [kworker/R-slub_]
      7       2 I<   [kworker/R-netns]
     10       2 I<   [kworker/0:0H-events_highpri]
     12       2 I<   [kworker/R-mm_pe]
     13       2 I    [rcu_tasks_kthread]
     14       2 I    [rcu_tasks_rude_kthread]
     15       2 I    [rcu_tasks_trace_kthread]
     16       2 S    [ksoftirqd/0]
     17       2 I    [rcu_preempt]
     18       2 S    [migration/0]
     19       2 S    [idle_inject/0]
     20       2 S    [cpuhp/0]
     21       2 S    [cpuhp/1]
     22       2 S    [idle_inject/1]

```
**Pregunta:** Explica 3 flags de `STAT` que veas (ej.: `R`, `S`, `T`, `Z`, `+`).  

**Respuesta:**
```txt
    - R: Running -> es un proceso que esta en ejecucion
    - S: Sleeping -> Es un proceso dormido, está esperando a un evento para activarse
    - Z: Zombie -> Es un proceso que ha terminado pero su proceso padre no ha recolectado la informacion sobre su finalizacion.
```
---

**23.** Suspende y reanuda **uno de tus procesos** (no crítico).

```bash
# Crea un proceso propio en segundo plano
sleep 120 &
pid=$!
# Suspende
kill -STOP "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
# Reanuda
kill -CONT "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
```
**Pega los dos estados (antes/después):**

```text
sleep 120 &
pid=$!
# Suspende
kill -STOP "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
# Reanuda
kill -CONT "$pid"
# Estado
ps -o pid,stat,cmd -p "$pid"
[1] 72246

[1]+  Detenido                sleep 120
    PID STAT CMD
  72246 T    bash
    PID STAT CMD
  72246 S    sleep 120

```
**Pregunta:** ¿Qué flag indicó la suspensión?  

**Respuesta:**
```txt
Sleeping
```
---

**24. (Opcional)** Genera un **zombie** controlado (no requiere root).

```bash
cat << 'EOF' > "$DAM/bin/zombie.c"
#include <stdlib.h>
#include <unistd.h>
int main() {
  if (fork() == 0) { exit(0); } // hijo termina
  sleep(60); // padre no hace wait(), hijo queda zombie hasta que padre termine
  return 0;
}
EOF
gcc "$DAM/bin/zombie.c" -o "$DAM/bin/zombie" && "$DAM/bin/zombie" &
ps -el | grep ' Z '
```
**Salida (recorta):**

```text
[1]+  Hecho                   sleep 120
[1] 74798
0 Z  1001   11630   11618  0  80   0 -     0 -      ?        00:00:00 sd_espeak-ng-mb
```
**Pregunta:** ¿Por qué el estado `Z` y qué lo limpia finalmente?  

**Respuesta:**

---

### 2.5 — Limpieza (solo tu usuario)

Detén y deshabilita tu **timer/servicio de usuario** y borra artefactos si quieres.

```bash
systemctl --user disable --now fecha-log.timer
systemctl --user stop fecha-log.service
rm -f ~/.config/systemd/user/fecha-log.{service,timer}
systemctl --user daemon-reload
rm -rf "$DAM/bin" "$DAM/logs" "$DAM/units"
```

---

## ¿Qué estás prácticando?
- [ ] Pegaste **salidas reales**.  
- [ ] Explicaste **qué significan**.  
- [ ] Usaste **systemd --user** y **journalctl --user**.  
- [ ] Probaste `systemd-run --user` con límites de memoria.  
- [ ] Practicaste señales (`STOP`/`CONT`), `pstree`, `ps` y `strace` **sobre tus procesos**.
