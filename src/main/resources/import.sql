
insert into assortimento(nome_fornitore, data) values("McDonalds", "2022-08-29 12:35");
insert into assortimento(nome_fornitore, data) values("KFC", "2022-08-29 12:35");
insert into assortimento(nome_fornitore, data) values("Starbucks", "2022-08-29 12:35");
insert into assortimento(nome_fornitore, data) values("Subway", "2022-08-29 12:35");
insert into assortimento(nome_fornitore, data) values("Old Wild West", "2022-08-29 12:35");
insert into assortimento(nome_fornitore, data) values("Burger Kings", "2022-08-29 12:35");

insert into acquisto(data) values("2022-08-30 12:35");
insert into acquisto(data) values("2022-08-30 12:35");
insert into acquisto(data) values("2022-08-30 12:35");
insert into acquisto(data) values("2022-08-30 12:35");
insert into acquisto(data) values("2022-08-30 12:35");
insert into acquisto(data) values("2022-08-30 12:35");

insert into prodotto(nome, descrizione, prezzo) values("Cartolina", "Immagine quadro rinascimentale", 4.99);
insert into prodotto(nome, descrizione, prezzo) values("Souvenir", "Ricordo del museo", 9.99);
insert into prodotto(nome, descrizione, prezzo) values("Penna", "Penna con motivo che rimanda a una delle opere esposte", 2.99);
insert into prodotto(nome, descrizione, prezzo) values("Riproduzione", "Riproduzione in miniatura scultura medievale", 49.99);
insert into prodotto(nome, descrizione, prezzo) values("Biglietto", "Ingresso omaggio da regalare", 6.99);
insert into prodotto(nome, descrizione, prezzo) values("Libro", "Spiegazione dettagliata delle opere del museo", 9.99);

insert into prodotto_acquisti(prodotti_id, acquisti_id) values(1,1);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(1,2);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(1,3);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(2,1);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(2,3);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(4,1);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(1,4);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(6,6);
insert into prodotto_acquisti(prodotti_id, acquisti_id) values(5,1);

insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(1,1);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(1,2);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(1,3);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(1,4);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(2,1);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(2,2);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(2,3);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(3,1);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(3,2);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(3,5);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(3,6);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(4,6);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(4,5);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(4,4);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(5,3);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(5,2);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(5,1);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(6,2);
insert into prodotto_assortimenti(prodotti_id, assortimenti_id) values(6,1);

insert into quantita(quantita) values(100);
insert into quantita(quantita) values(90);
insert into quantita(quantita) values(80);
insert into quantita(quantita) values(50);
insert into quantita(quantita) values(30);
insert into quantita(quantita) values(10);

insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(1,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(2,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(3,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(4,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(5,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(6,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(5,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(4,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(3,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(2,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(1,1);
insert into assortimento_quantita_ass(assortimenti_id, quantita_ass_id) values(2,1);

insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(6,6);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(5,5);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(4,4);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(3,3);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(5,2);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(4,1);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(3,2);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(2,3);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(6,4);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(2,5);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(3,6);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(4,5);
insert into acquisto_quantita_acq(acquisti_id, quantita_acq_id) values(5,4);

insert into guida(nome, cognome) values("Michelangelo", "De Nicola");
insert into guida(nome, cognome) values("Davide", "Catalano");
insert into guida(nome, cognome) values("Federica", "Cetroni");
insert into guida(nome, cognome) values("Mattia", "Verardi");

