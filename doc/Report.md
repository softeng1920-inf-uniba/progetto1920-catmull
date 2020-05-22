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
#### <a name="class_seq_diag"></a>Diagrammi delle classi e diagrammi di sequenza
##### User story "<titolo>"

#### <a name="design_patt"></a>Menzionare l'eventuale applicazione di design pattern
#### <a name="comments2"></a>Commentare le decisioni prese

[Torna all'indice](#indice)

# <a name="test_recap"></a>Riepilogo del test
#### <a name="coverall_stats"></a>Tabella riassuntiva di coveralls (o jacoco), con dati sul numero dei casi di test e copertura del codice

[Torna all'indice](#indice)

# <a name="user_man"></a>Manuale utente

[Torna all'indice](#indice)

# <a name="dev_proc"></a>Processo di sviluppo e organizzazione del lavoro
Lo sviluppo del software è stato eseguito da un team di sei componenti.   
Lo stile di processo utilizzato è stato quello iterativo, basato sulla suddivisione del progetto in sottoinsiemi di funzionaità dette **iterazioni**; ogni iterazione si articola a sua volta in analisi, progetto, codifica e sperimentazione.  
![iterazioni](./res/img/report/iterazioni.png)
Al termine di ogni iterazione viene, quindi, prodotta una build funzionante del codice che sarà poi testata ed integrata nel progetto. Inoltre, al termine di ogni sprint è prevista una verifica di quanto sviluppato con i futuri utenti ed i responsabili del progetto.  
Il tutto è stato strutturato facendo riferimento ai principi dello *sviluppo agile*, in particolare il framework agile seguito per la gestione della realizzazione del software è stato **Scrum**.
![manifesto](./res/img/report/manifesto.png)

I progressi del progetto sono stati effettuati in quattro iterazioni detti **sprint**: un singolo sprint ha avuto una durata costante, detta *timeboxing*, di circa 2 settimane. 
Ogni sprint era caratterizzato da una lista di requisiti da sviluppare e le stime di tempo per la realizzazione delle singole funzionalità sono state stabilite dai membri del gruppo.  
Ogni funzionalità è stata trattata come una user story ed assegnata ad uno o al più due membri del team di sviluppo. Qualsiasi membro del team ha potuto revisionare il lavoro degli altri membri del team proponendo delle modifiche o semplicemente approvandolo. 

Ad ogni sprint è stata utilizzata una  scrum board  digitale che riassume lo stato di ogni user story dalla sua nascita *"To do"*, attraversando vari step come *"In Progress"*, *"Review"* e *"Ready"*, fino alla sua conlusione *"Done"* in cui viene posta dal product owner solo dopo aver visionato il lavoro svolto dal team.
![scrumboard](./res/img/report/scrumboard.jpg)
Ogni sprint è stato intodotto da uno  sprint planning  guidato dal product owner, il quale definiva la *product backlog* (lista delle user story da realizzare) e lo sprint goal (obiettivo dell'iterazione).

Il team successivamente ha creato lo  sprint backlog  in cui si è dato un design preliminare e individuato i task e la loro stima in ore. Ogni giorno è stato effettuato il  Daily scrum meeting  della durata di 10-15 minuti in cui ogni membro del team ha esposto i suoi problemi, il lavoro fatto precedentemente al meeting e il lavoro che avrebbe svolto dopo il meeting. Ad ogni fine iterazione è seguita la **sprint review**  in cuoi sono stati presentati i risultati raggiunti durante lo sprint. Inoltre, al termine dello sprint review di ogni iterazione, è seguito lo **sprint retrospective** durante cui il team ha svolto una riunione per discutere l'andamento dello sprint appena concluso, evidenziandone gli aspetti positivi, negativi e le eventuali modifiche da apportate al lavoro svolto nello sprint precedente. 

Come mezzo di comunicazione tra il product owner,i vari gruppi e gli stessi membri del gruppo è stato utilizzato Slack, una startup fondata nel 2013 da Stewart Butterfield. 
Per comunicare con il product owner e gli altri team è stato usato il workspace *softeng1920* suddiviso in cinque canali pubblici: assistenza, reclami, general, consegne, cercooffrogruppo.
Il canale assistenza è utilizzato per presentare le difficoltà riscontrate durante lo sprint e chiedere aiuto al committente o ai membri degli altri team; il canale consegna viene utilizzato per comunicare al committente di aver terminato il lavoro entro i tempi stabiliti; il canale cercooffrogruppo è stato utilizzato solo nella fase precedente all'inizio del progetto con lo scopo di agevolare la formazione di team; il canale general viene utilizzato dal product owner per comunicare informazioni utili allo svolgimento del progetto a tutti i team; il canale reclami è utilizzato dopo la fine di ogni sprint per presentare un'eventuale lamentela riguardo agli errori attribuiti dal product owner al team.
![slack_pubbico](./res/img/report/slack1.png)

Oltre ai canali pubblici del workspace, il team per comunicare ha utilizzato un canale privato *"proj-catmull* (come mostrato nello screen allegato).
![slack_privato](./res/img/report/slack2.png)


**Lavoro sul codice dell' applicazione**

Il workflow utilizzato da ogni membro del team è stato il *Github Flow* in cui sono stati eseguiti i seguenti passi:

1. Subito prima di lavorare sul codice, è stato opportuno eseguire una git pull e lavorare sul codice più aggiornato.

2. Per ogni nuova feature user story o bug fix è stato creato o scelta l’issue su cui lavorare su GitHub e segnarsi come assigned.

3. E' stato creato un nuovo branch sul repository locale con il numero dell'issue o il titolo come nome del branch (issue#n oppure titoloissue) attraverso il comando  git branch <nome branch> .

4. Spostarsi sul nuovo branch appena creato con il comando  git checkout <nome branch> .

5. Lavorare al codice dell’applicazione. Effettuate piccole commit autoconsistenti di volta in volta, con uno scopo ben preciso ed una descrizione dettagliata. Evitando di fare un’unica grande commit alla fine del lavoro, a meno che la feature o il bug fix non sia stato davvero di poco conto.

6. Aggiornato con regolarità il branch sul server origin in GitHub con il comando  git push origin <nome branch> .

7. Ogni parte del codice correttamente implementata è stata testata con opportuni test di unità.

8. Dopo l’esecuzione dei test è stato possibile lanciare gli strumenti di Quality Assurance (checkstyle e findbugs) per assicurare di aver scritto codice di qualità.

9. A questo punto, dunque, si è potuto procedere all'apertura di una pull request, andando su GitHub e posizionandosi sul branch su cui si stava lavorando.

10. Scritto un titolo conciso ed esplicativo per la pull request e una descrizione significativa per il revisore come commento, incluso un riferimento all'issue nella forma closes #n. Scelto almeno un reviewer tra i componenti del team.

11. Una volta lanciata la pull request, si è attivata la costruzione automatica della build e si è atteso qualche minuto. In caso di conflitti, è stao opportuno risolverli.

12. Discussione di eventuali commenti dei reviewer e apportate le modifiche se necessarie come commit sul branch di lavoro.

13. Ricevuta l'approvazione esplicita di almeno un componente del team, si è potuto procedere da GitHub al merge del nuovo branch con il master branch sul repository remoto.

14. Se la build GitHub Actions e il merge su GitHub sono entrambi andati a buon fine, per completare il lavoro, è stato cancellato il branch sul repository remoto e sul repository locale con la sequenza di comandi:  git checkout master, git pull e git branch -d <nome branch> .

Quanto appena detto, può essere riassunto con la seguente immagine: 
![githubflow](./res/img/report/githubflow.png)

[Torna all'indice](#indice)

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati

[Torna all'indice](#indice)
