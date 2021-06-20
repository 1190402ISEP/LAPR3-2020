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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getDroneByID.sql
 * Ficheiro:  getDroneByID.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getDroneByID.sql
 */

create or replace FUNCTION getScooterByID(pId SCOOTER.id%TYPE) RETURN SYS_REFCURSOR
    IS
    vScooter SYS_REFCURSOR;
    vAux     integer;
    exIdInexistente exception;
BEGIN
    SELECT count(*) into vAux from Scooter where id = pId;

    if vAux = 0 then
        raise exIdInexistente;
    end if;

    OPEN vScooter FOR
    SELECT * FROM VEHICLE v inner join Drone d on v.ID = d.ID and v.PHARMACYID = d.PHARMACYID
        WHERE v.ID = pId and d.ID=pId;

    RETURN vScooter;

EXCEPTION
    when no_data_found then
        raise_application_error(-20003, 'Null Data!');
        return null;
    when exIdInexistente then
        raise_application_error(-20004, 'ID not found!');
        return null;
END;
/