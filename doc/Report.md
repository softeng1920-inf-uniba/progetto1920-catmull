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

# <a name="intro"></a>Introduzione

[Torna all'indice](#indice)

# <a name="dom_mod"></a>Modello di dominio

[Torna all'indice](#indice)

# <a name="spec_req"></a>Requisiti specifici

#### <a name="func_req"></a>Requisiti funzionali


#### <a name="not_func_req"></a>Requisiti non funzionali

[Torna all'indice](#indice)
# <a name="sys_des"></a>System Design

#### <a name="arch_style"></a>Stile architetturale adottato (opzionale)
#### <a name="pkg_cmpnts_diag"></a>Diagramma dei package, diagramma dei componenti (opzionali)
#### <a name="comments1"></a>Commentare le decisioni prese (opzionale)

[Torna all'indice](#indice)

# <a name="oo_design"></a>OO Design
## <a name="class_seq_diag"></a>Diagrammi delle classi e diagrammi di sequenza
Sono state scelte cinque user story per la realizzazione dei diagrammi: due per la gestione delle mosse e tre per la gestione di alcuni comandi.
Per i diagrammi di classe sono stati riportati solo i metodi e gli attributi che partecipano attivamente alla realizzazione della user story.
Per i diagrammi di sequenza sono stati riportati i nomi delle classi poichè sono classi speciali Singleton.
### <p align=center><strong>Sprint 1:</strong></p>

- `Inizia nuova partita`: 
 E' stata scelta questa user story poiché, dopo l'inserimento del comando "Play", permette di iniziare una nuova partita e predispone l'applicazione all'acquisizione di un comando.  
![](/doc/drawings/IniziaNuovaPartita.png)  
  
![](/doc/drawings/IniziaNuovaPartita2.png)    
Il diagramma di sequenza evidenzia la differenza del comportamento del programma a seconda del momento in cui viene inserito il comando.
Nello specifico, quando si inserisce il comando all'inizio dell'applicazione, è la classe AppMain che richiama il Controller che a
sua volta inizializza le classi Scacchiera e Turno. Invece, se l'utente inserisce il comando Play durante una partita, la gestione avviene
esclusivamente nella classe Controller e, in aggiunta, è richiesta la conferma da parte dell'utente.

- `Mostrare la scacchiera`: Questa user story è considerata primaria perché consente al giocatore di conoscere lo stato attuale del gioco, attraverso l'inserimento del
comando "board".   
![](/doc/drawings/MostrareLaScacchiera.png) 
  
![](/doc/drawings/MostrareLaScacchiera2.png)   
Come si può notare dal diagramma di sequenza, se il comando è inserito prima dell'inizio di una nuova partita, la gestione e le chiamate ai metodi della classe InterfacciaUtente sono affidate alla classe AppMain, altrimenti questo avviene nella classe Controller.   

- `Mostrare le mosse giocate`   
![](/doc/drawings/MostrareLeMosseGiocate.png)   
  
![](/doc/drawings/MostrareLeMosseGiocate2.png)    
Analogamente alle user story precedenti, è stata fatta una distinzione nel diagramma di sequenza, in quanto all'inserimento del comando "moves" l'applicazione restistituisce le mosse giocate da entrambi i giocatori, quindi se viene inserito prima dell'inizio del gioco verrà restituito un messaggio di errore.   

- `Muovere un pedone`: Questa user story è stata scelta in quanto il pedone è l'unica pedina con una mossa speciale, ovvero l'en passant, ed esemplifica la gestione delle mosse e delle catture all'interno del gioco.   
![](/doc/drawings/MuoverePedone.png)   
  
![](/doc/drawings/MuoverePedone2.png)   
Al fine di non appesantire il diagramma di sequenza, sono riportate solo le chiamate e i messaggi più importanti. Inoltre, la gestione della mossa avviene nella classe Controller, il cui metodo principale è mossaScacchi, e nella classe Pedone, il cui metodo principale è isMossaValida.   

### <p align=center><strong>Sprint 2:</strong></p>
- `Muovere il re`: Il re è la pedina più importante nel gioco degli scacchi. E' fondamentale conoscere la sua implementazione perchè ad ogni mossa l'utente
non deve lasciare il proprio re sotto scacco. Per questo, con l'aggiunta di questa user story, possiamo rappresentare la gestione delle mosse in modo
globale.   
![](/doc/drawings/MuovereRe.png)   
  
Nel diagramma delle classi sono state inserite, a differenza dei precedenti, tutte le classi che rappresentano una pedina, in quanto sono necessarie per poter verificare che una mossa non implichi lo scacco del re.   
![](/doc/drawings/MuovereRe2.png) 
  
Il diagramma di sequenza è uguale al precedente con la differenza della classe Re al posto della classe Pedone.    


## <a name="design_patt"></a> Design pattern utilizzati:  
- `SINGLETON`: sono state create delle classi statiche, dette Singleton, i cui costruttori sono privati e tutti gli attributi e i metodi sono statici.
Le classi Singleton sono: Turno, Menu, InterfacciaUtente, Controller e Scacchiera.
Questa scelta è stata adottata perché è possibile giocare una partita alla volta, quindi, ad esempio, ci servirà solo una Scacchiera.

- `BRIDGE`: per l'implemenazione delle pedine è stata creata la classe astratta di tipo Bridge "Pezzo". In questo modo è stata separata
l’astrazione dall' implementazione del metodo isMossaValida, poiché ogni pedina effettua mosse differenti.
E' stata presa questa decisione poiché la classe Scacchiera è formata da Celle che possono contene al più un Pezzo. Con l'utilizzo del
design Bridge è possibile selezionare l'implementazione del metodo direttamente a run-time a seconda del tipo specifico di pedina.

- `MEDIATOR`: il Mediator è una classe che incapsula come interagiscono gli oggetti per poter gestire facilmente le iterazioni tra loro.
Nel progetto è stata creata la classe Controller che gestisce tutte le comunicazioni tra le classi. Grazie a questa scelta è stato possibile centralizzare la gestione del gioco e facilitare la programmazione.
Per esempio, la classe Scacchiera non deve comunicare direttamente con la classe Pedone durante la mossa di un pedone. 



[Torna all'indice](#indice)

# <a name="test_recap"></a>Riepilogo del test
#### <a name="coverall_stats"></a>Tabella riassuntiva di coveralls (o jacoco), con dati sul numero dei casi di test e copertura del codice

[Torna all'indice](#indice)

# <a name="user_man"></a>Manuale utente

[Torna all'indice](#indice)

# <a name="dev_proc"></a>Processo di sviluppo e organizzazione del lavoro

[Torna all'indice](#indice)

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati

[Torna all'indice](#indice)
