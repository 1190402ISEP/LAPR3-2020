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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/removeProduct.sql
 * Ficheiro:  removeProduct.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: removeProduct.sql
 */

create or replace procedure removeProduct(pId PRODUCT.ID%TYPE, p_pharmacyID pharmacy.id%type)
    IS
    vCountAux INTEGER;
    vExists integer;
    
BEGIN

    select count(*) into vExists from PRODUCT where ID = pId and p_pharmacyID = PHARMACYID;
    
    if vExists is null then
         raise_application_error(-20010, 'Not Found on database!');

    end if;
    
    DELETE FROM PRODUCT WHERE ID = pId and p_pharmacyID = PHARMACYID;

    select count(*) into vCountAux from PRODUCT where ID = pId and p_pharmacyID = PHARMACYID;
    
    if vCountAux>0 then
        raise_application_error(-20011, 'Not Removed From database!');
    end if;

END;
/
COMMIT;
