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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAllScooters.sql
 * Ficheiro:  getAllScooters.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAllScooters.sql
 */

create or replace FUNCTION getAllScooters(pIdPharmacy Vehicle.PHARMACYID%TYPE) RETURN SYS_REFCURSOR
    IS
    vCursor SYS_REFCURSOR;
    exInputNull exception;

BEGIN


    OPEN vCursor FOR
        SELECT v.ID, s.QRCODE, v.BATTERYCAPACITY, v.STATE, v.MAXPAYLOAD, v.BATTERY
        FROM VEHICLE v
                 inner join SCOOTER s on v.PHARMACYID = s.PHARMACYID and v.ID = s.ID
        where s.PHARMACYID = pIdPharmacy
        ORDER BY s.ID ASC;

    return vCursor;

EXCEPTION
    when no_data_found then
        raise_application_error(-20001, 'Null Information!');
        RETURN NULL;
END;
/
commit;