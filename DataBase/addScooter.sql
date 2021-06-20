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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addScooter.sql
 * Ficheiro:  addScooter.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addScooter.sql
 */

create or replace function addScooter(pQrCode SCOOTER.QRCODE%TYPE, pBatteryCapacity VEHICLE.batterycapacity%TYPE,
                                      pState VEHICLE.state%TYPE, pMaxPayload VEHICLE.MAXPAYLOAD%TYPE,
                                      pBattery VEHICLE.battery%type,
                                      pIdPh Scooter.pharmacyID%type) return integer
    IS
    vNewVehicle Integer;
    vNewScooter INTEGER;
    vID         integer;
BEGIN

    select count(*) +1 into vid from VEHICLE;

    insert into VEHICLE (id,BATTERYCAPACITY, PHARMACYID, STATE, MAXPAYLOAD, BATTERY)
    values (vid,pBatteryCapacity, pIdPh, pState, pMaxPayload, pBattery);

    SELECT COUNT(*) INTO vNewVehicle FROM VEHICLE WHERE ID = vID;

    if (vNewVehicle is null) then
        raise_application_error(-20008, 'Error Creating Vehicle!');
    end if;

    insert into SCOOTER (ID, PHARMACYID, QRCODE) values (vID, pIdPh, pQrCode);

    SELECT COUNT(*) INTO vNewScooter FROM SCOOTER WHERE ID = vID;

    if vNewScooter is null then
        raise_application_error(-20008, 'Error Creating Scooter!');
    end if;
    return vID;
END;
/
commit;