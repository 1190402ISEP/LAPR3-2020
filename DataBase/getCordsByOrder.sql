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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getCordsByOrder.sql
 * Ficheiro:  getCordsByOrder.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getCordsByOrder.sql
 */

create or replace function getCordinatesByOrder(p_OrderId ClientOrder.id%type) return Address.coordinates%type
    is

    v_coordinates Client.coordinates%type;
begin

    select c.coordinates into v_coordinates
    from ClientOrder co
    inner join Client c
    on co.useremail = c.Useremail
    where co.id = p_OrderId;

    return v_coordinates;

end;
/