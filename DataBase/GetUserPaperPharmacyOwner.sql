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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/GetUserPaperPharmacyOwner.sql
 * Ficheiro:  GetUserPaperPharmacyOwner.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: GetUserPaperPharmacyOwner.sql
 */

create or replace FUNCTION funcGetUserPaperPharmacyOwner(p_email Client.Useremail%type, p_pwd Users.password%type)
    RETURN SYS_REFCURSOR IS 
    
    c_cliente SYS_REFCURSOR;
    
BEGIN

    OPEN c_cliente FOR
    SELECT count(*)
    FROM Users u
    INNER JOIN PharmacyOwner c
    ON u.email = c.Useremail
    WHERE c.Useremail = p_email AND u.password = p_pwd;

    RETURN c_cliente;

    EXCEPTION WHEN NO_DATA_FOUND THEN
        RETURN null;
END;
/