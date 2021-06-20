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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateStock.sql
 * Ficheiro:  updateStock.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateStock.sql
 */

create or replace PROCEDURE updateStock(p_id Product.id%TYPE, p_stock Product.stock%TYPE, p_idFarm Pharmacy.id%type)
    IS

    stockInvalido exception;
    produtoInvalido exception ;

    v_count int;
BEGIN


    if p_stock < 0 then
        raise stockInvalido;
    end if;

    select count(*) into v_count from PRODUCT where PHARMACYID = p_idFarm and id = p_id;

    if v_count = 0 then
        raise produtoInvalido;
    end if;

    update PRODUCT set stock = p_stock where PHARMACYID = p_idFarm and ID = p_id;


EXCEPTION
    when produtoInvalido then
        raise_application_error(-20004, 'Invalid product id!');
    when no_data_found then
        raise_application_error(-20003, 'Invalid Data!');
    when stockInvalido then
        raise_application_error(-20005, 'Invalid stock!');
END;
/