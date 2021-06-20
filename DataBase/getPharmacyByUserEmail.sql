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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getPharmacyByUserEmail.sql
 * Ficheiro:  getPharmacyByUserEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getPharmacyByUserEmail.sql
 */

create or replace FUNCTION getPharmacyByUserEmail(p_Email Pharmacy.Owner%TYPE) RETURN SYS_REFCURSOR
    IS
    vPharmacy SYS_REFCURSOR;
    vAux     integer;
    exIdInexistente exception;
BEGIN
    SELECT count(*) into vAux from Pharmacy where owner like p_Email;

    if vAux = 0 then
        raise exIdInexistente;
    end if;

    OPEN vPharmacy FOR
    SELECT * FROM Pharmacy WHERE owner like p_Email;

    RETURN vPharmacy;
EXCEPTION
    when no_data_found then
        raise_application_error(-20003, 'Null Data!');
    when exIdInexistente then
        raise_application_error(-20004, 'Pharmacy not found!');
END;
/