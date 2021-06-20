create or replace procedure updateDrone(pUniqueNumber VEHICLE.id%TYPE,
                                      pBatteryCapacity VEHICLE.batterycapacity%TYPE,
                                      pState VEHICLE.state%TYPE,
                                      pMaxWeight VEHICLE.MAXPAYLOAD%TYPE,
                                      pActualBattery VEHICLE.battery%type,
                                      pIDFarmacia Pharmacy.id%type)
IS
BEGIN

    if pBatteryCapacity is not null then
        UPDATE VEHICLE SET batterycapacity=pBatteryCapacity WHERE id = pUniqueNumber and pharmacyid=pIDFarmacia;
    end if;

    if pState is not null then
        UPDATE VEHICLE SET state=pState WHERE id = pUniqueNumber and pharmacyid=pIDFarmacia;
    end if;
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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateDrone.sql
 * Ficheiro:  updateDrone.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateDrone.sql
 */

    --
    if pMaxWeight is not null then
        UPDATE VEHICLE SET MAXPAYLOAD=pMaxWeight WHERE id = pUniqueNumber and pharmacyid=pIDFarmacia;
    end if;

    if pActualBattery is not null then
        UPDATE VEHICLE SET battery=pActualBattery WHERE id = pUniqueNumber and pharmacyid=pIDFarmacia;
    end if;

END;
/
