INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(1, '2022-10-05', 'Entrega de Equipamento: BRJ93000RM', 'PROJECT', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(2, '2022-12-05', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(3, '2022-10-10', 'Entrega de Equipamento: BRJ93000RM', 'PROJECT', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(4, '2022-10-07', 'Entrega de Equipamento: BRJ93000RM', 'PROJECT', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(5, '2022-10-15', 'Entrega de Equipamento: BRJ93000RM', 'PROJECT', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(6, '2023-10-30', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(7, '2022-10-20', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(8, '2022-10-10', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(9, '2021-10-02', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');
INSERT INTO tb_changes(id, date_change, description_change, type_entity, type_change, author) VALUES(10, '2022-10-01', 'Entrega de Equipamento: BRJ93000RM', 'COMPUTER', 'ENTRY', 'Washington Antunes');

INSERT INTO tb_companys (id, name) VALUES (1, 'Distribuidora SA');

INSERT INTO tb_invoices (id, number_invoice, date_entry, value_invoice, id_company) VALUES (1, '522455', '2022-10-10', 7000, 1);
INSERT INTO tb_invoices (id, number_invoice, date_entry, value_invoice, id_company) VALUES (2, '352455', '2021-07-10', 15000, 1);

INSERT INTO tb_computers (id, brand, date_entry, location, model, type_computer, value_entry, id_invoice, serial_number, patrimony_number, status_computer) VALUES (1, 'HP', '2021-12-22 11:00:54', 'TI', 'HP 240 G7 Notebook PC', 'NOTEBOOK', 2500, 1, 'BRJ132NBSY', '102544',  'STAND_BY');
INSERT INTO tb_computers (id, brand, date_entry, location, model, type_computer, value_entry, id_invoice, serial_number, patrimony_number, status_computer) VALUES (2, 'HP', '2021-10-22 10:00:54', 'TI', 'HP 240 G7 Notebook PC', 'DESKTOP', 1500, 1, 'BRJ352BRTT', '102546',  'DISCARDED');
INSERT INTO tb_computers (id, brand, date_entry, location, model, type_computer, value_entry, id_invoice, serial_number, patrimony_number, status_computer) VALUES (3, 'HP', '2021-05-22 11:00:54', 'TI', 'HP 240 G6 Notebook PC', 'DESKTOP', 1500, 1, 'BRJ442BRTT', '102346',  'DISABLED');
INSERT INTO tb_computers (id, brand, date_entry, location, model, type_computer, value_entry, id_invoice, serial_number, patrimony_number, status_computer) VALUES (4, 'HP', '2021-05-22 11:00:54', 'TI', 'HP 240 G6 Notebook PC', 'DESKTOP', 1500, 1, 'BRJ662BRTT', '102320',  'IN_USE');