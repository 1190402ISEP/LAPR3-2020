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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAllPharmacys.sql
 * Ficheiro:  getAllPharmacys.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAllPharmacys.sql
 */

create or replace FUNCTION getAllPharmacys RETURN SYS_REFCURSOR
    IS
    vCursor SYS_REFCURSOR;
    exInputNull exception;

BEGIN


    OPEN vCursor FOR SELECT * FROM Pharmacy order by id asc;

    return vCursor;

EXCEPTION
    when no_data_found then
        raise_application_error(-20001, 'Null Information!');

END;