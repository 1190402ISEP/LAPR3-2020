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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/TrulyCharge.sql
 * Ficheiro:  TrulyCharge.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: TrulyCharge.sql
 */

create or replace procedure ChargeEnergyVehicle(p_vehicleID Vehicle.id%TYPE, p_Percentual float, p_phaID integer)
    IS
    v_CurrentEnergy Float;
    v_upcount float;
BEGIN

    select battery into v_CurrentEnergy
    from Vehicle
    where id = p_vehicleID and pharmacyID = p_phaID;    

    v_upcount := v_CurrentEnergy+p_Percentual;

    UPDATE Vehicle SET battery= v_upcount WHERE id = p_vehicleID and pharmacyID = p_phaID;

END;
/

commit;