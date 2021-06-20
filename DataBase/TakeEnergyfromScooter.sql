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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/TakeEnergyfromScooter.sql
 * Ficheiro:  TakeEnergyfromScooter.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: TakeEnergyfromScooter.sql
 */

create or replace procedure TakeEnergyFromScooter(p_vehicleID Scooter.ID%TYPE, p_TotalEnergy float, p_phaID integer)
    IS
    v_CurrentEnergy Float;
    v_vehicleWH Float;
    v_discount float;
BEGIN

    select battery into v_CurrentEnergy
    from Vehicle
    where id = p_vehicleID and pharmacyID = p_phaID;

    select BATTERYCAPACITY into v_vehicleWH
    from Vehicle
    where id = p_vehicleID and pharmacyID = p_phaID;

    v_discount := (p_TotalEnergy / v_vehicleWH) * 100;

    UPDATE Vehicle SET battery= v_CurrentEnergy - v_discount WHERE id = p_vehicleID and pharmacyID = p_phaID;

END;
/

commit;