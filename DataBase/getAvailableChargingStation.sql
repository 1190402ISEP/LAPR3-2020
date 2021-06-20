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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAvailableChargingStation.sql
 * Ficheiro:  getAvailableChargingStation.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAvailableChargingStation.sql
 */

create or replace function getAvailableChargingStation(p_phaID integer, p_tipoParque int) return integer
    is
    v_return integer;
begin

    select cs.id
    into v_return
    from CHARGINGSTATION cs
             inner join PARKPLACE pp on cs.ID = pp.CHARGINGSTATIONID
             inner join PARK p on p.id = pp.PARKID
    where p.PHARMACYID = p_phaID
      and p.PARKTYPEID = p_tipoParque
      and pp.ISAVAILABLE = 1
    fetch first row only;

    return v_return;
exception
    when no_data_found then
        return null;

end;
/

commit;