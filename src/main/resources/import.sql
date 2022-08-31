insert into assortimento(nome_fornitore, data, quantita) values("McDonalds", "2022-08-29 12:35", 100);
insert into assortimento(nome_fornitore, data, quantita) values("KFC", "2022-08-29 12:35", 50);
insert into assortimento(nome_fornitore, data, quantita) values("Starbucks", "2022-08-29 12:35", 30);
insert into assortimento(nome_fornitore, data, quantita) values("Subway", "2022-08-29 12:35", 20);
insert into assortimento(nome_fornitore, data, quantita) values("Old Wild West", "2022-08-29 12:35", 10);
insert into assortimento(nome_fornitore, data, quantita) values("Burger Kings", "2022-08-29 12:35", 60);

insert into acquisto(data, quantita) values("2022-08-30 12:35", 60);
insert into acquisto(data, quantita) values("2022-08-30 12:35", 40);
insert into acquisto(data, quantita) values("2022-08-30 12:35", 25);
insert into acquisto(data, quantita) values("2022-08-30 12:35", 16);
insert into acquisto(data, quantita) values("2022-08-30 12:35", 7);
insert into acquisto(data, quantita) values("2022-08-30 12:35", 15);

insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Cartolina", "Immagine quadro rinascimentale", 4.99, 1, 1);
insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Souvenir", "Ricordo del museo", 9.90, 10, 2, 2);
insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Penna", "Penna con motivo che rimanda a una delle opere esposte", 2.99, 3, 3);
insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Riproduzione", "Riproduzione in miniatura scultura medievale", 49.99, 4, 4);
insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Biglietto", "Ingresso omaggio da regalare", 6.99, 5, 5);
insert into prodotto(nome, descrizione, prezzo, acquisto_id, assortimento_id) values("Libro", "Spiegazione dettagliata delle opere del museo", 9.99, 6, 6);