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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/changeToCharging.sql
 * Ficheiro:  changeToCharging.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: changeToCharging.sql
 */

create or replace procedure changeVehicleToCharging(p_VehicleID Vehicle.id%type)
    is

begin
    update Vehicle set STATE = 4 where id = p_VehicleID;

end;
/
commit;