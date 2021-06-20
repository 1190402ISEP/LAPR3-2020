/*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getPesosPorEncomenda.sql
 * Ficheiro:  getPesosPorEncomenda.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getPesosPorEncomenda.sql
 */

create or replace procedure getPesosPorEncomenda(p_orderID int, p_cord out Mappoint.coordinates%type, p_peso out float)
is

begin

    select c.COORDINATES into p_cord from CLIENTORDER co
    inner join CLIENT c on co.USEREMAIL = c.USEREMAIL
    where co.ID = p_orderID;

    select o.TOTALWEIGHT into p_peso from ORDERS o where ID = p_orderID;


end;
/
commit;