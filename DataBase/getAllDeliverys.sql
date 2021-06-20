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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAllDeliverys.sql
 * Ficheiro:  getAllDeliverys.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAllDeliverys.sql
 */

create or replace function getAllDeliverys return sys_refcursor

is
    v_return sys_refcursor;
begin
    open v_return for
        Select * from DELIVERY;

    return v_return;

end;
/
commit