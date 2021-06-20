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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getCordsOfAllPharmacys.sql
 * Ficheiro:  getCordsOfAllPharmacys.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getCordsOfAllPharmacys.sql
 */

create or replace function getCordsOfAllPharmacys return sys_refcursor
is

    v_return sys_refcursor;
begin

    open v_return for
        select id, ADDRESS from PHARMACY;
    return v_return;

end;
/
commit;