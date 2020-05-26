# Report
![](/res/img/report/logo.png)  
  
<a name="indice"></a>**Indice**
1. [Introduzione](#intro)
1. [Modello di dominio](#dom_mod)
1. [Requisiti specifici](#spec_req)
	- [Requisiti funzionali](#func_req)
	- [Requisiti non funzionali](#not_func_req)
1. [System Design](#sys_des)
	- [Stile architetturale adottato (opzionale)](#arch_style)
	- [Diagramma dei package, diagramma dei componenti (opzionali)](#pkg_cmpnts_diag)
	- [Commentare le decisioni prese (opzionale)](#comments1)
1. [OO Design](#oo_design)
	- [Diagrammi delle classi e diagrammi di sequenza *(per le user story considerate più importanti)*](#class_seq_diag)
	- [Menzionare l'eventuale applicazione di design pattern *(opzionale)*](#design_patt)
	- [Commentare le decisioni prese *(opzionale)*](#comments2)
1. [Riepilogo del test](#test_recap)
	- [Riportare la tabella riassuntiva di coveralls (o jacoco), con dati sul numero dei casi di test e copertura del codice](#coverall_stats)
1. [Manuale utente](#user_man)
1. [Processo di sviluppo e organizzazione del lavoro](#dev_proc)
1. [Analisi retrospettiva](#retro_analysis)
	* [Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti](#happy)
	* [Cosa vi ha fatto sentire insoddisfatti e vi ha deluso](#sad)
	* [Cosa vi ha fatto «impazzire» e vi ha reso disperati](#crazy)

# <a name="intro"></a> Introduzione

[Torna all'indice](#indice)

# <a name="dom_mod"></a>Modello di dominio

[Torna all'indice](#indice)

# <a name="spec_req"></a>Requisiti specifici

#### <a name="func_req"></a>Requisiti funzionali


#### <a name="not_func_req"></a>Requisiti non funzionali

[Torna all'indice](#indice)
# <a name="sys_des"></a>System Design


#### <a name="pkg_cmpnts_diag"></a>i.Diagramma dei package

<p align="center"><img src="drawings/DiagrammaDeiPackage.png" width=100% height=100%></p>

[Torna all'indice](#indice)

# <a name="oo_design"></a>OO Design
#### <a name="class_seq_diag"></a>Diagrammi delle classi e diagrammi di sequenza
##### User story "<titolo>"

#### <a name="design_patt"></a>Menzionare l'eventuale applicazione di design pattern
#### <a name="comments2"></a>Commentare le decisioni prese

[Torna all'indice](#indice)

# <a name="test_recap"></a>Riepilogo del test
#### <a name="coverall_stats"></a>Tabella riassuntiva di coveralls (o jacoco), con dati sul numero dei casi di test e copertura del codice

Sono stati generati i test automatici tramite il testing framework open source di Java: JUnit. La copertura del codice scritto è pari all' 80%.

![](/doc/drawings/coverallsScacchi.png) 

Seguono le immagini tratte dal report dei test automatici di Coveralls.

Le uniche classi escluse dai casi di test sono “InterfacciaUtente” e ”AppMain” poiché sono di tipo Boundary. Queste classi hanno l’esclusivo compito di comunicare con l’utente attraverso messaggi visualizzati a schermo oppure mediante richieste di inserimento da tastiera, dunque i test sui flussi di input e output sarebbero stati superflui, in quanto propri di Java. 

La copertura della classe Controller è parziale poiché è stata esclusa la funzione playGame(), in quanto richiama i metodi della classe InterfacciaUtente e utilizza metodi già testati separatamente.  

![](/doc/drawings/coverallsStatus.png)  


[Torna all'indice](#indice)

# <a name="user_man"></a>Manuale utente

[Torna all'indice](#indice)

# <a name="dev_proc"></a>Processo di sviluppo e organizzazione del lavoro

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati

[Torna all'indice](#indice)
