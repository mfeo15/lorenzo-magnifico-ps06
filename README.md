# Lorenzo il Magnifico

<img src="http://oi65.tinypic.com/6zshm1.jpg" width="400" alt="Lorenzo Box Icon" align="right" />

Gioco de Lorenzo il Magnifico sviluppato in Java, con un approccio client-server

## Funzionalità

Nel progetto sono state sviluppate molte funzioni delle seguenti categorie:
1) Regole Semplificate
2) Regole Complete
3) Socket
4) CLI
5) GUI
6) Funzionalità Avanzate

Le funzionalità sviluppate in dettaglio sono:

- Possibilità di avvio tramite terminale o file batch o script
- Avvio separato Client / Server
- Stanza di attesa per la ricerca di una partita

- Caricamento dei settaggi della stanza da file XML
- Log-in per utenti che hanno già giocato
- Visualizzazione delle statistiche personali a seguti del log-in
- Timer per l'auto avvio di una partita per meno di 4 giocatori
- Timer per l'auto avvio di una partita dopo aver raggiunto i 4 giocatori
- Possibilità di far partire una partita in qualsiasi momento

- Gestione dei leader (Gioca, Attiva, Scarta) secondo le regole, con caricamento casuale da file XML 
- Gestione delle scomuniche con caricamento casuale da file XML
- Caricamento dei settaggi della Board di gioco da file XML
- Timer per la gestione dei turni di gioco, gestione di giocatore AFK
- Possibilità di passare un turno con un azione nulla
- Visione della plancia personale di un qualsiasi giocatore
- Gestione della board funzionante (Adattamento al numero di giocatori, Piazzamento Familiari, Pulizia ecc..)

- Gestione della connessione Client - Server
- Possibilità di mantenere connessioni multiple (più partite contemporanteamente)
- Permanenza dei dati su file XML

- Model e server adattati per permettere ad un quinto giocatore di giocare la partita


## Avvio
Tramite repository di GitHub è possibile scaricare l'ultima versione che è stata esportata come jar eseguibile e caricata nella categoria Release. Tale file jar va eseguito tramite il seguente comando da terminale.

```shell
> java -jar Lorenzo.jar
```

All'avvio sarà possibile scegliere se eseguire un Server oppure un Client, nel secondo casò bisognerà quindi scrivere l'IP del server che è stato avviato in precedenza, in seguto verrà richiesto se avviare il gioco con interfaccia GUI o con interfaccia CLI.

## Testing

...

## Documentazione Java

La documentazione completa in JavaDoc delle classi è disponibile [qui](https://mfeo15.github.io/lorenzo-magnifico-ps06/)

## Sviluppato con

* [Draw.io](http://www.draw.io/) - UML Diagram
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/) - IDE
* [Swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html) - Framework

## Contributi

* **Francesco Marconi** 
* **Giovanni Quattrocchi** 

Un ringraziamento speciale ad entrambi per l'aiuto alla realizzazione di questo progetto


## Autori

* **Luca Favano** 
* **Matteo Yann Feo** 
* **Aaron Karama** 

*Hanno collaborato per lo sviluppo del progetto* 

## Licenza

Progetto svolto in collaborazione col Politecnico di Milano e Cranio Games che ci ha concesso la licenza di utilizzo del materiale grafico.
