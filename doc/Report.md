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





Il **gioco degli scacchi** è un gioco di strategia in cui due giocatori fronteggiano
i rispettivi eserciti, composti da pedine aventi specifiche diverse,
su un apposito campo di battaglia meglio conosciuto come scacchiera.
La locuzione deriva dal persiano Shah che significa Re, e, in seguito a diversi
adattamenti, si è diffuso nel Sud Europa con il vocabolo di origine catalana escac.
Successivamente al suo avvento il gioco si è proliferato sempre di più impegnando ogni
ambito e ogni ceto possibile, dai ceti bassi fino alla nobiltà, dalla letteratura fino
al cinema. Banalmente si trattava di un forma del gioco ancora primitiva e priva di tutte
le regole di cui oggi siamo a conoscenza. Infatti, fu a partire dal 1400 che furono
introdotte nuove regole, vedi l'arrocco, e furono attribuite nuove qualità a ciascun
pezzo. Una di queste novità riguarda la possibilità da parte del pedone di compiere un
movimento tale da farlo spostare di due case se e solo se questo debba effettuare il
suo primo movimento di tutta la partita. 250 anni dopo fu pubblicato un libro dallo
scrittore Francois-Andrè-Philidor dal nome "L'analyze des Echecs" che affronta per la
prima volta le strategie di gioco e fu davvero una svolta per quel periodo in quanto
l'opera risultò col tempo essere rimarchevole per lo sviluppo dello studio degli scacchi. Di qui a breve vennero inventate
 le prime forme di notazione, tra queste, la notazione descrittiva poi sostituita da quella che utilizziamo ancora oggi conosciuta come notazione algebrica, introdotta da Philipp Stamma nel 1737.




<p align="center"><img src="/res/img/report/DescriptiveNotation.png" alt="Notazione Descrittiva">  
<img src="/res/img/report/AlgNot.gif" alt="Notazione algebrica">
</p>




Intorno alla metà del 1800 la matematica e il gioco degli scacchi si fondono dando spazio a teorie e teoremi che spiegano e condizionano i movimenti dei pezzi sulla scacchiera. Noto è, dunque, che si possano effettuare studi approfonditi sulla scacchiera facendo ricorso alla teoria dei grafi così come alla geometria, aritmetica e analisi combinatoria. Diversi anni dopo, intorno al 1950, spuntarono i primi programmi aventi la capacità di giocare a scacchi. Inizialmente non erano in grado di affrontare ad armi pari un uomo. Tuttavia,1 in seguito alla nascita del microprocessore, i suddetti sfiorarono il livello dei campioni fino ad un evento storico del 1997 in cui l'allora campione Garry Kasparov venne battuto dal supercomputer dell'IBM chiamato Deep Blue.                                          
Per quanto riguarda la scacchiera, trattasi di una griglia quadrata costituita da 64 celle o case di cui 32 bianche e 32 nere.
L'obiettivo del gioco è quello di muovere le pedine in modo da condurre il re avversario nella condizione di non poter più effettuare alcuna mossa valida,ovvero lo **Scaccomatto**. Non è consentito lasciare il proprio Re sotto attacco, esporre il proprio Re all’attacco e nemmeno ‘catturare’ il Re avversario.
L’avversario il cui Re sia stato posto in scaccomatto ha perso la partita.
Se la posizione è tale che nessuno dei due giocatori possa in alcun modo dare
scaccomatto al Re avversario, la partita è patta.
Preliminarmente gli avversari hanno rispettivamente 16 pezzi di colore bianco
e 16 pezzi di colore nero.

I pezzi sono i seguenti:
<ul>
    <li>otto pedoni bianchi e otto pedoni neri;</li>
    <li>due Cavalli bianchi e due Cavalli neri;</li>
    <li>due Alfieri bianchi e due Alfieri neri;</li>
    <li>due Torri bianche e due Torri nere;</li>
    <li>una Donna bianca e una Donna nera;</li>
    <li>un Re bianco e un Re nero.</li>
</ul>

<p align="center">
<img src="/res/img/report/Chessboard.png">
<p align="center">Scacchiera con pezzi allo stato iniziale
</p>

<h4><i>Movimento dei pezzi</i></h4>

La prima mossa è sempre dello schieramento bianco. Non è consentito muovere un pezzo verso una casa occupata da un pezzo del medesimo
colore.
Se un pezzo viene mosso su una casa occupata da un pezzo avversario, quest’ultimo
viene catturato e rimosso dalla scacchiera come parte della stessa mossa.
Si dice che un pezzo attacca un pezzo avversario se il pezzo può effettuare una
cattura su quella casa. Si considera che un pezzo attacchi una casa anche qualora
quel pezzo sia impossibilitato a muoversi verso quella casa perché così facendo
lascerebbe o porrebbe sotto attacco il Re del proprio colore.


<b>PEDONE</b>

il pedone si può muovere sulla colonna avanzando,e solo avanzando,di una sola casa.
Nel caso in cui questo si trovi alla sua prima mossa, si può muovere in alternativa
di due case lungo la stessa colonna, a condizione,evidentemente,che dette case non siano
occupate. Il pedone si può muovere su una casa posta diagonalmente di fronte ad esso su
una colonna adiacente, esclusivamente nel caso in cui tale casa sia occupata da un
pezzo avversario e quindi trattasi di cattura.
Nel caso in cui il pedone sia stato mosso fino alla casa situata nella traversa più
lontana si verifica una "promozione", ovvero il giocatore può scegliere di trasformare
il pedone in qualunque altro pezzo della schacchiera (non per forza presente su essa)
e fargli assumere tutte le qualità relative con effetto immediato.


<p align="center"><img src="/res/img/report/pawn_mov.png">
<p align="center">(a)Potenziali movimenti pedone</p>


<b>ALFIERE</b>

l'alfiere si può muovere lungo una casa posta a qualunque distanza da esso in diagonale.
Cattura in diagonale nello stesso modo in cui si muove senza possedere la facoltà di saltare pezzi.
Ogni giocatore, all'inizio della partita, possiede un alfiere sul bianco e uno sul nero. Importante è sottolineare come questo pezzo possa muoversi esclusivamente in case del suo stesso colore di partenza. Ad esempio, l'alfiere che ha cominciato la partita su una casella bianca potrà posizionarsi, con le future mosse, solo in un'altra casella del medesimo colore.


<p align="center"><img src="/res/img/report/bishop_mov.png">
<p align="center"><font face="Comic Sans MS" size="2">(b)Potenziali movimenti alfiere</p>

<b>TORRE</b>

la torre è considerato il pezzo più importante della scacchiera dopo la donna in quanto può muoversi in qualunque casa purchè si trovi
sulla sua colonna o sulla sua traversa con una gittata illimitata. In caso di cattura il pezzo si fermerà nella stessa casa del pezzo catturato. Questo pezzo, così come quello della donna, viene chiamato <i>'pezzo pesante'</i>.


<p align="center"><img src="/res/img/report/tower_mov.png">
<p align="center">(c)Potenziali movimenti torre</p>

<b>CAVALLO</b>

il cavallo può effettuare il movimento cosiddetto "a L", ovvero può raggiungere la
casa ad esso più vicina purchè non si trovi sulla stessa colonna,traversa o diagonale.  Essendo questo un movimento unico e non somma di due movimenti, il cavallo ha la possiblità di saltare altri pezzi e quindi di passare oltre uno 'sbarramento'. Questa peculiarità fa del cavallo il pezzo più imprevedibile della scacchiera in contrapposizione col fatto che rimane uno dei cosiddetti <i>'pezzi leggeri'</i> (insieme all'alfiere) poichè si può muovere solo di tre case per volta.


<p align="center"><img src="/res/img/report/horse_mov.png">
<p align="center">(d)Potenziali movimenti cavallo</p>

<b>DONNA</b>

la Donna si può muovere su una qualunque casa lungo la colonna, la traversa o la
diagonale sulle quali si trova. Basti pensare che da una posizione centrale questo pezzo è in grado di raggiungere ben 27 case differenti e ciò rende bene l'idea di come questo sia sicuramente il pezzo più forte. Di qui l'attribuzione del nominativo di <i>'pezzo pesante'.</i>


<p align="center"><img src="/res/img/report/queen_mov.png">
<p align="center">(e)Potenziali movimenti donna</p>

<b>RE</b>

il Re si può muovere di una posizione nelle case ad esso adiacenti e catturare così come si muove, fermandosi quindi nella cella del pezzo avversario catturato.
 Il Re non può spostarsi in caselle che sono sotto minaccia da parte di un qualunque pezzo avversario.

<p align="center"><img src="/res/img/report/king_mov.png">
<p align="center">(f)Potenziali movimenti Re</p>


<b>MOVIMENTI SPECIALI</b>


Una mossa straordinaria è quella dell' "Arrocco" che riguarda precisamente Re e Torre
dello stesso colore: dalla sua casa di partenza, il Re viene trasferito di 2 case o 3 case,a seconda del tipo di arrocco, verso la
Torre che si trova ancora nella sua casa d’origine; quindi la Torre stessa viene trasferita
sulla casa che il Re ha appena attraversato. Questa mossa si può eseguire solo se
il Re e la torre non si siano mai mossi e se la casa su cui si trova il Re, quella che
andrà ad occupare e quelle che attraverserà non siano esposte a cattura.
Come accennato sono due i tipi di arrocco possibili:
<ol>
  <font color="red">
  <li><b>Arrocco lungo</b>: il re viene spostato di due case verso la torre mentre quest'ultima di tre case verso il Re;</li>
  <font color="red">
  <li><b>Arrocco corto</b>: il re viene spostato di due case verso la torre e analogamente succede alla torre nei confronti del re;</li>
</ol>  


<p align="center"><img src="/res/img/report/Chess_Castling_Mov.gif">
<p align="center">(g)Arrocco</p>



Un'altra mossa fuori dagli schemi riguarda questa volta il pedone.
Un pedone che occupi la casa nella stessa traversa e sulla colonna adiacente di un pedone
avversario il quale sia appena stato avanzato di due case dalla sua casa d’origine, può
catturare il pedone avversario come se quest’ultimo fosse stato avanzato di una sola casa.
Questa cattura è legale solo nella mossa immediatamente successiva al suddetto
avanzamento ed è detta cattura ‘en passant ’ (‘al varco’).


<p align="center"><img src="/res/img/report/En_passant.png" width="400" height="350">
<p align="center">(h)En Passant</p>

<h4><i>Notazione algebrica delle mosse</i></h4>


Per tenere memoria delle mosse che si effettuano viene adottata una notazione
particolare in grado di descriverle in maniera riassuntiva.
Per quanto riguarda il movimento bisogna scrivere la lettera iniziale del pezzo
che si desidera muovere in maiuscolo insieme alla cella di destinazione (es. torre
in a4 si indicherà con Ta4). Questo non si verifica invece per il pedone per cui
bisogna indicare solo la cella di arrivo. Se si vuole effettuare una cattura
bisogna, inoltre, aggiungere una "x" (es. cavallo cattura in e5 sarà Cxe5), mentre
per il pedone si deve anche specificare la colonna di partenza (es. pedone in a4 cattura
in b5 si indicherà con axb5 oppure axb5 e.p. in caso di en passant).
Se due pezzi identici possono spostarsi nello stesso quadrato, la lettera del
pezzo è seguita da:
<ul>
  <li>la colonna di output, se le colonne differiscono;</li>
  <li>la riga di uscita, se le colonne sono uguali ma le righe differiscono;</li>
</ul>  

Ad esempio, se due cavalli in d2 e f2 possono raggiungere e4, la mossa è indicata con
Cde4 o Cfd4 come appropriato. Se due cavalli in d2 e d6 possono raggiungere e4, la
mossa sarebbe indicata C2d4 o C6d4 " come appropriato.
Con la notazione 0-0 si indica l'arrocco corto mentre con 0-0-0 si indica l'arrocco lungo.
L'elenco di tutte le mosse viene indicato come una lista numerata di due mosse per
punto ciascuna delle quali indica rispettivamente una mossa del bianco e una del nero.
Esempio:
1.Ca5 Ta4
2.Df6 c3
3.Axg6 Th3
e così via.



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

[Torna all'indice](#indice)

# <a name="retro_analysis"></a>Analisi retrospettiva
#### <a name="happy"></a>Cosa vi ha fatto sentire soddisfatti e vi ha reso contenti
#### <a name="sad"></a>Cosa vi ha fatto sentire insoddisfatti e vi ha deluso
#### <a name="crazy"></a>Cosa vi ha fatto «impazzire» e vi ha reso disperati

[Torna all'indice](#indice)
