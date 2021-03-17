insert into produto( nome_produto,tipo_produto, valor, marca) values
( 'Agua Mineral 1,5L', 'alimento', 2.69, 'Minalba'),
( 'Chocolate Suflair 50g','alimento', 2.79, 'Nestlé'),
( 'Macarrão instantâneo','alimento', 1.79, 'Nissin'),
( 'Amoxicilina 500mg','medicamento', 22.39, 'EMS'),
( 'Doril 4 COMPRIMIDOS','medicamento', 7.73, 'Hypera Pharma'),
( 'Dropropizina 120ml Xarope','medicamento', 12.46, 'Medley'),
( 'Sorvete Pote 1,5 Litros','alimento', 22.99, 'Kibom'),
( 'Feijão Carioca Tipo 1  Pacote 1kg', 'alimento', 8.29, 'Camil'),
( 'Dipirona Sódica Gotas 10ml','medicamento', 4.59, 'EMS'),
( '1KG de Filé de frango','alimento', 16.69, 'Sadia'),
( '1KG Picanha','alimento', 32.96, 'Swift'),
( 'Calmante Maracugina PI 20 Comprimidos','medicamento', 18.69, 'Hypera Pharma'),
( 'Café Pilão Tradicional Almofada 500g','alimento', 10.29, 'Pilão'),
( 'Leite Integral 1 Litro','alimento', 12.3, 'Piracanjuba');

insert into cartao(nome_titular,numero_cartao,tipo_cartao,bandeira,CVV,CPF,data_validade) values
( 'Antônio Mello', 5201183672884483, 'Crédito', 'Mastercard', '435', '04184838090', '03/23'),
( 'Antônio Mello', 5290633166314845, 'Débito', 'Mastercard', '345', '04184838090', '01/23'),
( 'Daniel Correia Gomes', 5404304686238467, 'Débito', 'Mastercard', '790', '91149125039', '06/27'),
( 'Susana B. Mattos', 342875070984440, 'Crédito', 'American Express', '1230', '98638422067', '09/21'),
( 'Clara Souza Correia', 4539211232463872, 'Crédito', 'Visa', '125', '76881245068', '10/22');

insert into usuario(nome_completo, CPF, data_nascimento,
CEP,ponto_referencia,complemento, foto_rg, foto_perfil, eh_consumidor, coordenadas, telefone, email, senha, RG, saldo, cartao_id_cartao) values
( 'José Antônio Sanches','04187548090','1947-04-13', '02913-000',null, 'Nº490', null, null, 1, '-23.504460475448486, -46.714190454434885', '11940987989','jose.sanches@gmail.com', 'joselito',  null, 0.0,1),
( 'Antônio Mello','04184838090','1957-04-13', '02913-000',null, 'Nº450', null, null, 1, '-23.504592088, -46.7138931076', '11940405989','Antonio.melo@gmail.com', 'anto32snci49',  null, 0.0,1),
( 'Susana B. Mattos','98638422067','1952-12-03', '02913-030',null, 'Nº370', null, null, 1,'-23.50508349488, -46.7129489558','11491939245','Mattos.susana@gmail.com', 'Wz2WhHRqGf9q',  null, 0.0,1),
( 'João Pessoa','76881237565','1975-11-11', '02915-100',null, 'Nº398',null, null, 1,'-23.4989735141, -46.7107743342','11783010395','joao@gmail.com', 'joao123', '216976573', 3.82,4),
( 'Eduardo Ribeiro Sousa','6564838090','1963-02-06', '02914-030',null, 'Nº98',null, null, 1,'-23.50327261112975, -46.70979487588505', '112543-5607','sousa.edu@gmail.com', 'anto32snci49', null, 0,1),
( 'Breno Alves Ferreira','98638467067','1972-04-12', '02675-031',null, 'Nº11',null, null, 1,'-23.50336360842573, -46.70840550918631','3589-7352','breno72@gmail.com', 'gn893h5945',  null, 0,1),
( 'Vitoria Carvalho Cardoso','7688546565','1955-10-16', '02914-110',null, 'Nº43',null, null, 1,'-23.50253457638946, -46.711126708979705','11783634395','VitoriaCarvalhoCardoso@jourrapide.com', 'microscopio543', '216976573', 3.82,4),
( 'Francisco Souza Pereira','04184838090','1955-04-13', '02913-000',null, 'Nº450',null, null, 1,'-23.504592080, -46.7138931070', '11940405989','chico', 'chico123',  null, 0.0,1),
( 'Fábio Araujo Cunha','91149125039','2001-11-11', '02913-040',null, 'Nº316', null, null, 0,'-23.5044984864, -46.7114816928','11398367463','Caraujo.fabio@gmail.com', 'gXYQdUzY8g9g', '108265912', 12.50,2),
( 'Clara Souza Correia','04184838090','1998-10-22', '02912-080',null, 'Nº575', null, null, 0,'-23.50680340451, -46.71098409937','11396721374','Clara.souza@gmail.com', '43UgVjYTpWPE', '206129452', 11.98,3),
( 'Julia Rocha Cardoso','76881245068','1995-12-26', '02914-010',null, 'Nº398',null, null, 0,'-23.50278788212, -46.7094984534','11783010395','Cardoso.Julia@gmail.com', 'tA6sJDrGNgfv', '216978373', 7.82,4),
( 'Luan Rocha Ferreira','74549125039','2000-04-24', '02915-100',null, 'Nº123',null, null, 0,'-23.50219407289896, -46.71026454302053','2996-6591','luan546@gmail.com', 'n9c37468c', '108265912', 12.50,2),
( 'Bianca Lima Silva','04186438090','1994-07-04', '02914-000',null, 'Nº94',null, null, 0,'-23.50295239430559, -46.71015073739696','11396851374','BiancaLimaSilva@teleworm.us', 'cmw8mo5tw54', '206129452', 11.98,3),
( 'Isabella Barbosa Barros','17639467593','1996-11-25', '02914-010',null, 'Nº27',null, null, 0,'-23.50278788212, -46.7094984534','11733013295','IsabellaBarbosaBarros@jourrapide.com', 'hierarquico435', '216978373', 7.82,4);



insert into post( titulo, data_hora_realizacao, descricao, taxa_entrega,limite_quantidade_item,
limite_peso_entrega, local_tarefa, esta_em_espera,solicitante_id, tempo_estimado_realizacao, entregador_id,foi_entregue,foi_aceito) values
( 'Comprar Pão', '2020-12-15T10:12:30', 'Estou indo comprar pão', 1.76, 2,3.5 ,'Mercado do zé',0,null, '00:20:30', 8,0,0),
( 'Comprar Doril', '2020-12-15T10:18:30', 'Estou indo comprar doril', 2.20, 4,2.3, 'Loja do Marcos',0,null, '00:45:30', 8,0,0),
( 'Comprar Xarope pra tosse', '2020-12-15T10:15:20', 'Estou indo comprar xarope pra tosse', 1.50, 5,4.75 ,'Drogasil',0,null, '00:30:30', 9,0,0),
( 'Comprar Leite', '2020-10-26T10:17:15', 'Estou indo comprar leite', 3.76, 2,3.5 ,'Extra',0, null,'00:20:30',10,0,0),
( 'Comprar Atroveran', '2020-10-26T10:12:40', 'Estou indo comprar atroveran', 1.95, 4,2.3, 'Drogasil',0, null, '00:45:30',11,0,0),
( 'Comprar Conhaque', '2020-10-26T10:08:00', 'Estou indo comprar conhaque', 3.50, 4,3.3, 'Extra',0, null, '00:45:30',12,0,0),
( 'Comprar Ração para gato ', '2020-10-26T10:13:30', 'Estou indo comprar ração para gato', 1.50, 1,4.75 ,'Petshop da Maria',0,null, '00:30:30',11,0,0),
( 'Comprar Desodorante', '2020-10-26T10:12:40', 'Estou indo comprar desodorante', 1.75, 4,2.3, 'Drogasil',0,null, '00:45:30',13,0,0),
( 'Comprar Chinelo', '2020-10-27T10:08:00', 'Estou indo comprar chinelo', 3.11, 4,3.3, 'Extra',0,null, '00:45:30',9,0,0);

insert into pedido(data_hora,taxa_entrega,nome_estabelecimento,produtos_ids,valor_total_compras,post_id)values
('2007-12-03T10:15:30', 3.45,'Loja da Amália', '5,2', 12.3, 1),
('2003-12-03T10:15:30', 3.45,'Bar do Zé', '1,4', 15.3, 2),
('2007-12-03T10:15:30', 1.45,'Bar do Jão', '3,2', 21.3,3);
----------------------------------------------------------------------------------------------------------------




