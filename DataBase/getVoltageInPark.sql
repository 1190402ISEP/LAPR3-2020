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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getVoltageInPark.sql
 * Ficheiro:  getVoltageInPark.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getVoltageInPark.sql
 */

create or replace function getVoltageInPark(p_pharmacyID Park.pharmacyID%type) return Park.TotalVoltage%type
    is
    v_volt Park.TotalVoltage%type;
begin

    

    select TotalVoltage into v_volt
    from Park where pharmacyID = p_pharmacyID AND parkTypeID = 1;

    return v_volt;

end;
/
commit;