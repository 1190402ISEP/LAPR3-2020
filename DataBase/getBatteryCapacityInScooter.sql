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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getBatteryCapacityInScooter.sql
 * Ficheiro:  getBatteryCapacityInScooter.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getBatteryCapacityInScooter.sql
 */

create or replace function getBatteryCapacityInScooter(p_scooterID Vehicle.id%type, p_pharmacyID Vehicle.pharmacyID%type) return Vehicle.batteryCapacity%type
    is
    v_batteryCap Vehicle.batteryCapacity%type;
begin

    select batteryCapacity into v_batteryCap
    from Vehicle where id = p_scooterID AND pharmacyID = p_pharmacyID;

    return v_batteryCap;

end;
/
commit;