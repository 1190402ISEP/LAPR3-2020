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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addBasketProduct.sql
 * Ficheiro:  addBasketProduct.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addBasketProduct.sql
 */

create or replace procedure addBasketProduct(p_email Client.useremail%type, p_barcode product.barcode%type,
                                             p_quantity basketProducts.quantity%type)
    IS

    quantidadeInvalida exception;

BEGIN

    if p_quantity < 1 then
        raise quantidadeInvalida;
    end if;

    insert into BASKETPRODUCTS values (p_email, p_barcode, p_quantity);

exception
    when quantidadeInvalida then
        raise_application_error(-2011, 'Invalid Quantity');


end;
/
commit;