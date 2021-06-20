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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/removeDrone.sql
 * Ficheiro:  removeDrone.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: removeDrone.sql
 */

create or replace procedure removeDrone(pId Drone.id%TYPE,pIdPh Pharmacy.id%type)
    IS

    vExists integer;
    vCheckDroneDeleted INTEGER;
    vCheckVehicleDeleted Integer;

BEGIN

    select count(*) into vExists from Drone where id = pId and pharmacyid=pIdPh;

    if vExists =0 then
         raise_application_error(-20010, 'Not Found on database!');

    end if;

    DELETE FROM Drone WHERE id = pId and pharmacyid=pIdPh;


    select count(*) into vCheckDroneDeleted from drone where id = pId and pharmacyid=pIdPh;

    if vCheckDroneDeleted>0 then
        raise_application_error(-20011, 'Not Removed From database!');
    end if;


    DELETE FROM VEHICLE WHERE ID= pId and PHARMACYID=pIdPh;

    select count(*) into vCheckVehicleDeleted from VEHICLE where id = pId and pharmacyid=pIdPh;

    if vCheckVehicleDeleted>0 then
        raise_application_error(-20012, 'Not Removed From database!');
    end if;
END;
/
commit;