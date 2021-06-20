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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createPark.sql
 * Ficheiro:  createPark.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createPark.sql
 */

create or replace procedure createPark( p_voltage park.TOTALVOLTAGE%type,
                                        p_max_capacity PARK.MAXCAPACITY%type,
                                        p_phid PARK.ID%type,
                                        p_type PARK.PARKTYPEID%type)
    IS
    invalid_park exception;

    v_id_introduced PARK.ID%type;

    v_id int;
BEGIN

    select count(*) +1 into v_id from park;

    INSERT INTO PARK( ID, MAXCAPACITY, PHARMACYID, PARKTYPEID,TOTALVOLTAGE )
    VALUES ( v_id, p_max_capacity, p_phid, p_type, p_voltage);

    SELECT ID
    INTO v_id_introduced
    FROM PARK
    WHERE PARK.ID = v_id;

    if v_id_introduced IS NULL THEN
        raise invalid_park;
    end if;

    exception
    WHEN invalid_park THEN
        raise_application_error( -2020, 'Park couldnt be created.');

end;
/
commit;