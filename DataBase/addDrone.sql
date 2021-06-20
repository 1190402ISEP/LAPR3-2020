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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addDrone.sql
 * Ficheiro:  addDrone.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addDrone.sql
 */

create or replace function addDrone(pBatteryCapacity VEHICLE.batterycapacity%TYPE, pState VEHICLE.state%TYPE,
                                    pMaxPayload VEHICLE.MAXPAYLOAD%TYPE, pBattery VEHICLE.battery%type,
                                    pIdPh DRONE.pharmacyID%type) return integer
    IS
    vNewVehicle Integer;
    vNewDrone   INTEGER;
    vID         integer;
BEGIN
    select count(*) + 1 into vid from VEHICLE;
    insert into VEHICLE (id, BATTERYCAPACITY, PHARMACYID, STATE, MAXPAYLOAD, BATTERY)
    values (vid, pBatteryCapacity, pIdPh, pState, pMaxPayload, pBattery);


    SELECT COUNT(*) INTO vNewVehicle FROM VEHICLE WHERE ID = vID;

    if (vNewVehicle is null) then
        raise_application_error(-20008, 'Error Creating Vehicle!');
    end if;

    insert into DRONE (ID, PHARMACYID) values (vID, pIdPh);

    SELECT COUNT(*) INTO vNewDrone FROM Drone WHERE ID = vID;

    if vNewDrone is null then
        raise_application_error(-20008, 'Error Creating Drone!');
    end if;
    return vID;
END;
/