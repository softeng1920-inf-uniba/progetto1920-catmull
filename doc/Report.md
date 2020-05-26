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

<ol>
<li>

# <a name="intro"></a>Introduzione

[Torna all'indice](#indice)

</li>
<li>

# <a name="dom_mod"></a>Modello di dominio

[Torna all'indice](#indice)

</li>
<li>

# <a name="spec_req"></a>Requisiti specifici
I requisiti specifici rappresentano le caratteristiche del software. Difatti, la formulazione di essi è utile ai progettisti per soddisfare le richieste del committente e dell'utente.  
I requisiti si suddividiono in funzionali e non funzionali: i requisiti funzionali descrivono i servizi, o funzioni, offerti dal sistema, mentre i requisiti non funzionali definiscono i vincoli sui servizi offerti dal sistema, e sullo stesso processo di sviluppo. 
<ol>
<li>

#### <a name="func_req"></a>Requisiti funzionali
<ul>
<li> <b>RF1</b> : Come giocatore voglio che eseguendo il comando <u><i>help</i></u> il risultato sia la lista dei comandi disponibili </li>      
<li> <b>RF2</b> : Come giocatore voglio che al comando <u><i>play</i></u> l'applicazione si predisponga a ricevere la prima mossa di gioco e sia in grado di ricevere altri comandi;</li>    
<li> <b>RF3</b> : Come giocatore voglio che al comando <u><i>quit</i></u> l'applicazione si chiuda e compaia il prompt del sistema operativo;</li>   
<li> <b>RF4</b> : Come giocatore voglio che al comando <u><i>board</i></u> l’applicazione mostri la scacchiera;</li>   
<li> <b>RF5</b> : Come giocatore voglio che al comando <u><i>moves</i></u> l'applicazione mostri la storia delle mosse giocate in notazione algebrica;</li>   
<li> <b>RF6</b> : Come giocatore voglio che al comando <u><i>captures</i></u> l’applicazione mostri i pezzi catturati;</li>  
<li> <b>RF7</b> : Come giocatore voglio muovere un <u><i>Pedone</i></u> in modo tale da effettuare un'avanzata, catturare pezzi e catturare pezzi con en-passant;</li>    
<li> <b>RF8</b> : Come giocatore voglio muovere un <u><i>Cavallo</i></u> in modo tale da effettuare uno spostamento o una cattura;</li>    
<li> <b>RF9</b> : Come giocatore voglio muovere la <u><i>Donna</i></u> in modo tale da effettuare uno spostamento o una cattura;</li>    
<li> <b>RF10</b> : Come giocatore voglio muovere un <u><i>Alfiere</i></u> in modo tale da effettuare uno spostamento o una cattura;</li>   
<li> <b>RF11</b> : Come giocatore voglio muovere il <u><i>Re</i></u> in modo tale da effettuare uno spostamento o una cattura;</li>    
<li> <b>RF12</b> : Come giocatore voglio muovere una <u><i>Torre</i></u> in modo tale da effettuare uno spostamento o una cattura;</li>   
<li> <b>RF13</b> : Come giocatore voglio effettuare l'<u><i>arrocco corto</i></u>, rispettando le regole degli scacchi;</li>  
<li> <b>RF14</b> : Come giocatore voglio effettuare l'<u><i>arrocco lungo</i></u>, rispettando le regole degli scacchi.</li>    

</ul>
</li>

<li>

#### <a name="not_func_req"></a>Requisiti non funzionali
<ul>
<li> <b>RNF1</b> : il software deve essere eseguito su Linux con <i>Terminal</i>;</li>   
<li> <b>RNF2</b> : il software deve essere eseguito su Mac OS con <i>Terminal</i>;</li>   
<li> <b>RNF3</b> : il software deve essere eseguito su Windows con <i>Terminal del sottosistema Windows per Linux</i>, <i>Windows Terminal(preview)</i> e <i>Git Bash</i> (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ....);</li>    
<li> <b>RNF4</b>: il software deve mostrare la scacchiera contenente i pezzi in formato UTF-8 (Unicode Transformation Format, 8 bit);</li>  
<li> <b>RNF5</b>: il software è stato sviluppato con il linguaggio Java;</li>   

</ul>
</ol>

[Torna all'indice](#indice)

</li>
<li>

# <a name="sys_des"></a>System Design

#### <a name="arch_style"></a>Stile architetturale adottato (opzionale)
#### <a name="pkg_cmpnts_diag"></a>Diagramma dei package, diagramma dei componenti (opzionali)
#### <a name="comments1"></a>Commentare le decisioni prese (opzionale)

[Torna all'indice](#indice)

</li>
<li>

# <a name="oo_design"></a>OO Design
#### <a name="class_seq_diag"></a>Diagrammi delle classi e diagrammi di sequenza
##### User story "<titolo>"

#### <a name="design_patt"></a>Menzionare l'eventuale applicazione di design pattern
#### <a name="comments2"></a>Commentare le decisioni prese

[Torna all'indice](#indice)

</li>
<li>

# <a name="test_recap"></a>Riepilogo del test
#### <a name="coverall_stats"></a>Tabella riassuntiva di coveralls (o jacoco), con dati sul numero dei casi di test e copertura del codice

[Torna all'indice](#indice)

</li>
<li>

# <a name="user_man"></a>Manuale utente

[Torna all'indice](#indice)

</li>
<li>

# <a name="dev_proc"></a>Processo di sviluppo e organizzazione del lavoro

[Torna all'indice](#indice)

</li>
<li>

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati

[Torna all'indice](#indice)
