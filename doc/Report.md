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
<li> <b>RF6</b> : Come giocatore voglio che al comando <u><i>captures</i></u> l’applicazione mostri i pezzi catturati con caratteri Unicode;</li>  
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
<li> <b>RNF1</b> : il software deve essere eseguito su Linux con <i>Terminal</i> tramite container Docker;</li>   
<li> <b>RNF2</b> : il software deve essere eseguito su Mac OS con <i>Terminal</i> tramite container Docker;</li>   
<li> <b>RNF3</b> : il software deve essere eseguito su Windows con <i>Terminal del sottosistema Windows per Linux</i> e <i>Git Bash</i> (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ....) tramite container Docker;</li>    
<li> <b>RNF4</b>: il software deve mostrare la scacchiera contenente i pezzi in formato UTF-8 (Unicode Transformation Format, 8 bit);</li>  
<li> <b>RNF5</b>: il software è stato sviluppato con il linguaggio Java;</li>   
<li> <b>RNF6</b> : le build del software devono essere costruite con successo;</li>  
<li> <b>RNF7</b> : il software deve avere l'immagine Docker caricata con successo nella repository delle immagini Docker;</li>  
</ul>
</ol>

[Torna all'indice](#indice)

</li>
<li>

# <a name="sys_des"></a>System Design


#### <a name="pkg_cmpnts_diag"></a>i.Diagramma dei package

<p align="center"><img src="drawings/DiagrammaDeiPackage.png" width=100% height=100%></p>

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

Sono stati generati i test automatici tramite il testing framework open source di Java: JUnit. La copertura del codice scritto è pari all'80%.

![](/doc/drawings/coverallsScacchi.png) 

Seguono le immagini tratte dal report dei test automatici di Coveralls.

Le uniche classi escluse dai casi di test sono “InterfacciaUtente” e ”AppMain” poiché sono di tipo Boundary. Queste classi hanno l’esclusivo compito di comunicare con l’utente attraverso messaggi visualizzati a schermo oppure mediante richieste di inserimento da tastiera, dunque i test sui flussi di input e output sarebbero stati superflui, in quanto propri di Java. 

La copertura della classe Controller è parziale poiché è stata esclusa la funzione playGame(), in quanto richiama i metodi della classe InterfacciaUtente e utilizza metodi già testati separatamente.  

![](/doc/drawings/coverallsStatus.png)  


[Torna all'indice](#indice)

</li>
<li>

# <a name="user_man"></a>Manuale utente

[Torna all'indice](#indice)

</li>
<li>

# <a name="dev_proc"></a>Processo di sviluppo e organizzazione del lavoro
Lo sviluppo del software è stato eseguito da un team di sei componenti.   
Lo stile di processo utilizzato è stato quello iterativo, basato sulla suddivisione del progetto in sottoinsiemi di funzionaità dette **iterazioni**; ogni iterazione si articola, a sua volta, in analisi, progetto, codifica e sperimentazione.    
![](/res/img/report/iterazioni.png)  
Al termine di ogni iterazione viene, quindi, prodotta una build funzionante del codice che sarà poi testata ed integrata nel progetto. Inoltre, alla fine di ogni sprint, è prevista una verifica di quanto sviluppato con il product owner.    

Il tutto è stato strutturato facendo riferimento ai principi dello *sviluppo agile*.    

In particolare, il framework agile seguito per la gestione della realizzazione del software è stato **Scrum**.  
I progressi del progetto sono stati effettuati in quattro iterazioni detti **sprint**: ogni singolo sprint ha avuto una durata costante, detta *timeboxing*, di circa 2 settimane. 
Ogni sprint e' stato caratterizzato da una lista di requisiti da sviluppare e la gestione del tempo per la realizzazione delle singole funzionalità sono state stabilite dai membri del gruppo.  
Le varie funzionalità sono state trattate come user story e ognuna è stata assegnata ad uno o al più due membri del team di sviluppo. Qualsiasi membro del team ha potuto revisionare il lavoro degli altri membri proponendo delle modifiche o semplicemente approvandolo. 

Ad ogni sprint, è stata utilizzata una  **scrum board**  digitale che riassume lo stato di ogni user story dalla sua nascita *"To do"*  fino alla sua conlusione *"Done"*, attraversando tre step: *"In Progress"*, *"Review"* e *"Ready"*.  
Le user story vengono, infine, poste nello stato *"Done"* dal product owner solo dopo aver visionato il lavoro svolto dal team.  

![](/res/img/report/scrumboard.png)  

Ogni sprint è stato introdotto da uno  **sprint planning**  guidato dal product owner, il quale definiva la *product backlog* (lista delle user story da realizzare) e lo sprint goal (obiettivo dell'iterazione).

Allo sprint planning è seguito lo **sprint backlog**, in cui il gruppo ha individuato i task e la stima in termini di tempo. Ogni giorno è stato effettuato il  **Daily scrum meeting**  della durata di 10-15 minuti, in cui ogni membro del team ha esposto i propri problemi, il lavoro fatto precedentemente al meeting e il lavoro che avrebbe svolto dopo il meeting. Ogni fine iterazione è stata seguita dalla **sprint review**,  in cui sono stati presentati i risultati raggiunti durante lo sprint. Inoltre, al termine dello sprint review di ogni iterazione, è seguito lo **sprint retrospective**. Durante quest'ultimo, il team ha svolto una riunione per discutere l'andamento dello sprint appena concluso evidenziandone gli aspetti positivi, negativi e le eventuali modifiche da apportare. 

Come mezzo di comunicazione tra il product owner, i vari gruppi e gli stessi membri del gruppo, è stato utilizzato il software **Slack**.    
Per comunicare con il product owner e gli altri team è stato usato il workspace *softeng1920* suddiviso in cinque canali pubblici: `assistenza`, `reclami`, `general`, `consegne`, `cercooffrogruppo`.  

Il canale `assistenza` è stato utilizzato per presentare le difficoltà riscontrate durante lo sprint e chiedere aiuto al committente o ai membri degli altri team;  
il canale `consegne` è stato utilizzato per comunicare al committente di aver terminato il lavoro;  
il canale `cercooffrogruppo` è stato utilizzato solo nella fase precedente all'inizio del progetto con lo scopo di agevolare la formazione di team;  
il canale `general` è stato utilizzato dal product owner per comunicare informazioni utili allo svolgimento del progetto;  
il canale `reclami` è stato utilizzato dopo la fine di ogni sprint per presentare un'eventuale dubbio riguardo alla valutazione attribuita allo sprint.  
  
![](/res/img/report/slack1.png)  

Oltre ai canali pubblici del workspace, il team per comunicare ha utilizzato un canale privato `proj-catmull`.  

![](/res/img/report/slack2.png)  

In aggiunta, a causa dell'emergenza sanitaria, le riunioni tenute dai vari componenti del team sono state svolte sulla piattafoma **"Microsoft Teams"**.

**Lavoro sul codice dell' applicazione**

Il workflow utilizzato da ogni membro del team è stato il `Github Flow` in cui sono stati eseguiti i seguenti passi:

1. Subito prima di lavorare sul codice, è stato opportuno eseguire una `git pull` e lavorare sul codice più aggiornato;  

2. Per ogni nuova feature, user story o bug fix è stata creata l’issue su cui lavorare su GitHub;  

3. E' stato creato un nuovo branch sul repository locale denominato con il numero oppure con il titolo dell'issue (`issue#n` oppure `titoloissue`) attraverso il comando `git branch <nome branch>`;    

4. E' stato effettuato uno spostamento (checkout) sul nuovo branch appena creato con il comando `git checkout <nome branch>`;  

5. Con il comando `git commit -m "<descrizione>"`, sono stati effettuati commit per ogni operazione consistente svolta sul progetto. Ogni commit è caratterizzato da una breve descrizione avente lo scopo di riassumere l'operazione effettuata;  

6. Tramite il comando `git push origin <nome branch>` è stato aggiornato il branch sulla sorgente remota (origin) in GitHub;   

7. Ogni parte del codice correttamente implementata è stata testata con opportuni test di unità;  

8. Dopo l’esecuzione dei test è stato possibile lanciare gli strumenti di Quality Assurance (checkstyle e findbugs) per assicurare di aver scritto codice di qualità;  

9. A questo punto, è stato possibile aprire una pull request;    

10. Per ogni pull request è stato scritto un titolo esplicativo e, come commento, una descrizione per il revisore. Nel commento è stato incluso un riferimento all'issue nella forma `closes #n`. Infine, come reviewers sono stati scelti tutti i componenti del team tranne l'assignee;  

11. Una volta lanciata la pull request, si è attivata la costruzione automatica della build. In caso di conflitti, è stato opportuno risolverli;  

12. Dopo aver discusso gli eventuali commenti dei reviewer, sono state apportate le modifiche necessarie attraverso commit sul branch di lavoro;  

13. Ricevuta l'approvazione esplicita dei reviewers, si è potuto procedere da GitHub al merge del nuovo branch con il master branch sul repository remoto;  

14. Se la build GitHub Actions e il merge su GitHub sono entrambi andati a buon fine, il branch sul repository remoto e su quello locale sono stati eliminati mediante la sequenza di comandi: `git checkout master`, `git pull` e `git branch -d <nome branch>`;  
  
![](/res/img/report/githubflow.png)  

**Esecuzione immagine docker** 

Una volta caricata l’immagine del container, da GitHub Actions su GitHub Packages, è stata scaricata l’immagine ed eseguito il container mediante l’installazione locale di Docker.  
Per eseguire l'immagine è stato prima lanciato il comando `docker pull docker.pkg.github.com/softeng1920-inf-uniba/docker_1920/catmull:latest` e, in seguito, `docker run -it --rm docker.pkg.github.com/softeng1920-inf-uniba/docker_1920/catmull:latest`.  

[Torna all'indice](#indice)

</li>
<li>

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
-   Imparare e approfondire un nuovo linguaggio di programmazione;
-   Conoscere e applicare le dinamiche dello sviluppo agile;
-   Applicare  dei principi di programmazione ad oggetti che precedentemente erano stati acquisiti in maniera meramente teorica;
-   Implementare un'interfaccia più elaborata del gioco attraverso colori, caratteri e font speciali;
-   Imparare a lavorare in gruppo nell'ambito dello sviluppo software;
-   Generare i test automatici di quasi tutta l'applicazione, imparando ad utilizzare JUint e acquisendo una visione più completa del codice scritto;
-   Aver realizzato un gioco di strategia storico di tale portata e complessià.
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
-   Non aver potuto sviluppare e implementare la fine del gioco poiché lo sviluppo è stato arrestato alla fase di mediogioco.  
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati 
-   Lavorare in un gruppo di cui non si conosce parte dei componenti. E' risultato difficile e impegnativo combinare idee e approcci differenti ai compiti da svolgere;
-   Bilanciare il tempo da dedicare al progetto con tutti gli altri corsi del semestre da seguire, soprattutto verso la fine del progetto, a causa di un carico di lavoro non indifferente. 

[Torna all'indice](#indice)
