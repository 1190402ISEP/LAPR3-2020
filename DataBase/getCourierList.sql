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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getCourierList.sql
 * Ficheiro:  getCourierList.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getCourierList.sql
 */

create or replace FUNCTION getCourierList(pPharmacyId Courier.scooterPharmacyID%type) RETURN SYS_REFCURSOR
    IS
    vCursor SYS_REFCURSOR;
    exInputNull exception;

BEGIN


    OPEN vCursor FOR SELECT name,email,nif,niss,state FROM Courier c where pPharmacyId =scooterPharmacyID order by c.nif asc;

    return vCursor;

EXCEPTION
    when no_data_found then
        raise_application_error(-20001, 'Null Information!');
        return null;

END;
/
commit;
