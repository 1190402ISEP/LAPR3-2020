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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateScooter.sql
 * Ficheiro:  updateScooter.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateScooter.sql
 */

create or replace procedure updateScooter(pUniqueNumber VEHICLE.id%TYPE,
                                          pQrCode SCOOTER.QRCODE%TYPE,
                                          pBatteryCapacity VEHICLE.batterycapacity%TYPE,
                                          pState VEHICLE.state%TYPE,
                                          pMaxWeight VEHICLE.MAXPAYLOAD%TYPE,
                                          pActualBattery VEHICLE.battery%type,
                                          pIDFarmacia Pharmacy.id%type)
    IS
    vCountAux INTEGER;
BEGIN
    Select count(*) into vCountAux from VEHICLE where id= pUniqueNumber and PHARMACYID=pIDFarmacia;
    if vCountAux = 0 then
        raise_application_error(-20018, 'Not Exists database!');
    end if;
    if pQrCode is not null then
        UPDATE SCOOTER SET QRCODE=pQrCode WHERE id = pUniqueNumber and pharmacyid = pIDFarmacia;
    end if;

    if pBatteryCapacity is not null then
        UPDATE VEHICLE SET batterycapacity=pBatteryCapacity WHERE id = pUniqueNumber and pharmacyid = pIDFarmacia;
    end if;

    if pState is not null then
        UPDATE VEHICLE SET state=pState WHERE id = pUniqueNumber and pharmacyid = pIDFarmacia;
    end if;
    --
    if pMaxWeight is not null then
        UPDATE VEHICLE SET MAXPAYLOAD=pMaxWeight WHERE id = pUniqueNumber and pharmacyid = pIDFarmacia;
    end if;

    if pActualBattery is not null then
        UPDATE VEHICLE SET battery=pActualBattery WHERE id = pUniqueNumber and pharmacyid = pIDFarmacia;
    end if;

END;
/
commit;