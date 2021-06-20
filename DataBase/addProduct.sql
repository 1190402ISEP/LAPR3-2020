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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addProduct.sql
 * Ficheiro:  addProduct.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addProduct.sql
 */

create or replace procedure addProduct(p_name product.name%type, p_stock product.stock%type, p_price product.price%type,
                                       p_weight product.weight%type, p_iva product.iva%type, p_barCode product.barcode%type, p_pharmacyID pharmacy.id%type)
    is
    invalidProduct exception ;

    v_id    int;
    v_count int;


begin

    if p_stock < 0 or p_weight < 0 or p_iva < 0 or p_price < 0 then
        raise invalidProduct;

    end if;

    select count(*) into v_count from PRODUCT;

    if v_count = 0 then
        v_id := 1;
    else
        select max(id) + 1 into v_id from product;
    end if;


    insert into product (id, name, STOCK, PRICE, WEIGHT, IVA, BARCODE,PHARMACYID)
    values (v_id, p_name, p_stock, p_price, p_weight, p_iva, p_barCode,p_pharmacyID);

exception
    when invalidProduct then
        raise_application_error(-2050, 'Invalid Product!');

end;
/
commit;