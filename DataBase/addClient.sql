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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addClient.sql
 * Ficheiro:  addClient.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addClient.sql
 */

create or replace procedure addClient(p_email Users.email%TYPE, p_NIF Users.NIF%TYPE, p_username Users.name%TYPE, p_password Users.password%TYPE, p_coordinates Address.coordinates%type, p_street Address.street%type, p_postalCode Address.postalCode%type,p_doorNumber Address.doorNumber%type)
    IS
    vAdded INTEGER;

    ex exception ;
    pragma exception_init(ex,-1);
BEGIN

    INSERT INTO USERS (EMAIL, NIF, NAME, PASSWORD)
    VALUES (p_email, p_NIF, p_username, p_password);


    INSERT INTO Address (COORDINATES, STREET, POSTALCODE, DOORNUMBER)
    VALUES (p_coordinates, p_street, p_postalCode, p_doorNumber);
    
    INSERT INTO Client (USEREMAIL, COORDINATES, CREDITS)
    VALUES (p_email, p_coordinates, 200);

    SELECT count(*)
    INTO vAdded
    FROM USERS u
    INNER JOIN CLIENT c
    ON u.email = c.useremail
    INNER JOIN ADDRESS a
    ON c.coordinates = a.coordinates
    WHERE u.email = p_email;

    if vAdded is null then
            raise_application_error(-20010, 'Not Added!');
    end if;
exception
    when ex then
        raise_application_error(-20150,'Invalid Address!');
   
END;
/
commit;

