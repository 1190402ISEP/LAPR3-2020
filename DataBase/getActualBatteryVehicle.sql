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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getActualBatteryVehicle.sql
 * Ficheiro:  getActualBatteryVehicle.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getActualBatteryVehicle.sql
 */

create or replace function getActualBatteryVehicle(p_scooterID int, p_pharmacyID int) return float
    is

    v_return float;
begin

    select (BATTERY / 100) * BATTERYCAPACITY
    into v_return
    from VEHICLE v
    where v.ID = p_scooterID
      and v.PHARMACYID = p_pharmacyID;

    return v_return;

EXCEPTION

    when no_data_found then
        return null;
end;
/

commit;


declare
    v_result float;

begin
    v_result := getActualBatteryVehicle(1,1);
    DBMS_OUTPUT.PUT_LINE(v_result);
end;
